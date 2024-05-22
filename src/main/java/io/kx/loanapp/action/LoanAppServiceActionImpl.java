package io.kx.loanapp.action;

import com.google.protobuf.Any;
import com.google.protobuf.Empty;
import io.kx.loanapp.api.LoanAppApi;
import io.kx.loanapp.domain.LoanAppDomain;
import io.kx.loanapp.domain.LoanAppEntity;
import kalix.javasdk.action.ActionCreationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletionStage;

// This class was initially generated based on the .proto definition by Kalix tooling.
// This is the implementation for the Action Service described in your io/kx/loanapp/action/loan_app_action.proto file.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class LoanAppServiceActionImpl extends AbstractLoanAppServiceAction {

  private static Logger logger = LoggerFactory.getLogger(LoanAppServiceActionImpl.class);

  public LoanAppServiceActionImpl(ActionCreationContext creationContext) {}

  @Override
  public Effect<Empty> submitLoanApplication(LoanAppApi.SubmitCommand submitCommand) {

    CompletionStage timerRegistration = timers().startSingleTimer(
            submitCommand.getLoanAppId(),
            Duration.ofSeconds(20),
            components().loanAppServiceActionImpl().expireLoanApplication(
                    LoanAppApi.DeclineCommand.newBuilder()
                            .setLoanAppId(submitCommand.getLoanAppId())
                            .setReason("Declined after not approved within 20 secs")
                            .build()
            )
    );

    return effects().asyncReply(timerRegistration.thenCompose(done -> components().loanAppEntity().submit(submitCommand).execute())
            .thenApply(any -> Empty.getDefaultInstance())
    );

  }


  @Override
  public Effect<Empty> expireLoanApplication(LoanAppApi.DeclineCommand declineCommand) {
    logger.info("#####################");
    logger.info("#####################");
    logger.info("#####################");
    logger.info("Expiring loan app id: " + declineCommand.getLoanAppId());
    logger.info("#####################");
    logger.info("#####################");
    logger.info("#####################");
    return effects().forward(components().loanAppEntity().decline(declineCommand));
  }

  @Override
  public Effect<Empty> subscribeApprovedEvent(LoanAppDomain.Approved approved) {

    CompletionStage cancelTimer = timers().cancel(approved.getLoanAppId());
    logger.info("--------------------");
    logger.info("--------------------");
    logger.info("Cancelling decline timer for loan app id: " + approved.getLoanAppId());
    logger.info("--------------------");
    logger.info("--------------------");

    return effects().asyncReply(cancelTimer.thenApply(r -> Empty.getDefaultInstance()));
  }

  @Override
  public Effect<Empty> ignoreOtherEvents(Any any) {
    return effects().ignore();
  }


}
