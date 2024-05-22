package io.kx.loanapp.domain;

import com.google.protobuf.Empty;
import io.kx.loanapp.api.LoanAppApi;
import kalix.javasdk.eventsourcedentity.EventSourcedEntity;
import kalix.javasdk.eventsourcedentity.EventSourcedEntityContext;
import kalix.javasdk.testkit.EventSourcedResult;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class LoanAppEntityTest {

  @Test
  public void submitLoanAppTest() {
    LoanAppEntityTestKit service = LoanAppEntityTestKit.of(LoanAppEntity::new);
    String loanAppId = UUID.randomUUID().toString();
    LoanAppApi.SubmitCommand submitCommand = LoanAppApi.SubmitCommand.newBuilder()
            .setLoanAppId(loanAppId)
            .setClientId("Peter-client")
            .setClientMonthlyIncomeCents(10000000)
            .setLoanAmountCents(200000)
            .setLoanDurationMonths(20)
            .build();

    EventSourcedResult<Empty> result = service.submit(submitCommand);
    assertTrue(result.didEmitEvents());
    LoanAppApi.GetCommand getCommand = LoanAppApi.GetCommand.newBuilder()
            .setLoanAppId(loanAppId).build();
    EventSourcedResult<LoanAppApi.LoanAppState> getResult = service.get(getCommand);
    assertEquals(LoanAppApi.LoanAppStatus.STATUS_IN_REVIEW, getResult.getReply().getStatus());
  }


  @Test
  @Disabled("to be implemented")
  public void getTest() {
    LoanAppEntityTestKit service = LoanAppEntityTestKit.of(LoanAppEntity::new);
    // GetCommand command = GetCommand.newBuilder()...build();
    // EventSourcedResult<LoanAppState> result = service.get(command);
  }


  @Test
  public void approveTest() {
    LoanAppEntityTestKit service = LoanAppEntityTestKit.of(LoanAppEntity::new);
    String loanAppId = UUID.randomUUID().toString();
    LoanAppApi.SubmitCommand submitCommand = LoanAppApi.SubmitCommand.newBuilder()
            .setLoanAppId(loanAppId)
            .setClientId("Peter-client")
            .setClientMonthlyIncomeCents(10000000)
            .setLoanAmountCents(200000)
            .setLoanDurationMonths(20)
            .build();

    EventSourcedResult<Empty> result = service.submit(submitCommand);
    assertTrue(result.didEmitEvents());

    LoanAppApi.ApproveCommand approveCommand = LoanAppApi.ApproveCommand.newBuilder()
            .setLoanAppId(loanAppId).build();

    EventSourcedResult<Empty> approveResult = service.approve(approveCommand);
    assertTrue(approveResult.didEmitEvents());

    LoanAppApi.GetCommand getCommand = LoanAppApi.GetCommand.newBuilder()
            .setLoanAppId(loanAppId).build();
    EventSourcedResult<LoanAppApi.LoanAppState> getResult = service.get(getCommand);
    assertEquals(LoanAppApi.LoanAppStatus.STATUS_APPROVED, getResult.getReply().getStatus());
  }


  @Test
  @Disabled("to be implemented")
  public void declineTest() {
    LoanAppEntityTestKit service = LoanAppEntityTestKit.of(LoanAppEntity::new);
    // DeclineCommand command = DeclineCommand.newBuilder()...build();
    // EventSourcedResult<Empty> result = service.decline(command);
  }

}
