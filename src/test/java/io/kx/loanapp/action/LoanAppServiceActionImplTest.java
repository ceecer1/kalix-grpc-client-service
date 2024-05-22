package io.kx.loanapp.action;

import akka.stream.javadsl.Source;
import com.google.protobuf.Empty;
import io.kx.loanapp.action.LoanAppServiceActionImpl;
import io.kx.loanapp.action.LoanAppServiceActionImplTestKit;
import io.kx.loanapp.api.LoanAppApi;
import kalix.javasdk.testkit.ActionResult;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class LoanAppServiceActionImplTest {

  @Test
  @Disabled("to be implemented")
  public void exampleTest() {
    LoanAppServiceActionImplTestKit service = LoanAppServiceActionImplTestKit.of(LoanAppServiceActionImpl::new);
    // // use the testkit to execute a command
    // SomeCommand command = SomeCommand.newBuilder()...build();
    // ActionResult<SomeResponse> result = service.someOperation(command);
    // // verify the reply
    // SomeReply reply = result.getReply();
    // assertEquals(expectedReply, reply);
  }

  @Test
  @Disabled("to be implemented")
  public void submitLoanApplicationTest() {
    LoanAppServiceActionImplTestKit testKit = LoanAppServiceActionImplTestKit.of(LoanAppServiceActionImpl::new);
    // ActionResult<Empty> result = testKit.submitLoanApplication(LoanAppApi.SubmitCommand.newBuilder()...build());
  }

  @Test
  @Disabled("to be implemented")
  public void approveLoanApplicationTest() {
    LoanAppServiceActionImplTestKit testKit = LoanAppServiceActionImplTestKit.of(LoanAppServiceActionImpl::new);
    // ActionResult<Empty> result = testKit.approveLoanApplication(LoanAppApi.ApproveCommand.newBuilder()...build());
  }

  @Test
  @Disabled("to be implemented")
  public void expireLoanApplicationTest() {
    LoanAppServiceActionImplTestKit testKit = LoanAppServiceActionImplTestKit.of(LoanAppServiceActionImpl::new);
    // ActionResult<Empty> result = testKit.expireLoanApplication(LoanAppApi.DeclineCommand.newBuilder()...build());
  }

}
