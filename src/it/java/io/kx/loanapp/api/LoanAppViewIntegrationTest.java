package io.kx.loanapp.api;

import com.google.protobuf.Empty;
import com.google.protobuf.util.Timestamps;
import io.kx.loanapp.Main;
import io.kx.loanapp.domain.LoanAppDomain;
import io.kx.loanapp.view.LoanAppByStatus;
import io.kx.loanapp.view.LoanAppByStatusView;
import io.kx.loanapp.view.LoanAppViewByStatusModel;
import kalix.javasdk.testkit.EventingTestKit;
import kalix.javasdk.testkit.junit.jupiter.KalixTestKitExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import static kalix.javasdk.testkit.KalixTestKit.Settings.DEFAULT;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

public class LoanAppViewIntegrationTest {

    @RegisterExtension
    public static final KalixTestKitExtension testKit =
            new KalixTestKitExtension(
                    Main.createKalix(),
                    DEFAULT.withEventSourcedEntityIncomingMessages("loanapp"));


    private final LoanAppByStatus viewClient;

    public LoanAppViewIntegrationTest() {
        viewClient = testKit.getGrpcClient(LoanAppByStatus.class);
    }

    @Test
    public void shouldFindCustomersByCity() {
        EventingTestKit.IncomingMessages loanAppEvents = testKit.getEventSourcedEntityIncomingMessages("loanapp");

        LoanAppDomain.Submitted submittedEvent = LoanAppDomain.Submitted.newBuilder()
                .setLoanAppId("loanAppId")
                .setClientId("clientId")
                .setClientMonthlyIncomeCents(10000)
                .setLoanAmountCents(200000)
                .setLoanDurationMonths(45)
                .setEventTimestamp(Timestamps.fromMillis(System.currentTimeMillis()))
                .build();

        loanAppEvents.publish(submittedEvent, "1");

        await()
                .ignoreExceptions()
                .atMost(5, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    LoanAppViewByStatusModel.LoanAppViewResponse state = viewClient.getLoanAppByStatus(LoanAppViewByStatusModel.GetLoanAppStatusRequest.newBuilder().setStatusId(1).build()).toCompletableFuture()
                            .get(3, SECONDS);
                    assertEquals(1, state.getResultsCount());
                });
    }
}
