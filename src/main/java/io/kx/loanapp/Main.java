package io.kx.loanapp;

import io.kx.loanapp.action.LoanAppServiceActionImpl;
import io.kx.loanapp.action.StartUpAction;
import io.kx.loanapp.domain.LoanAppEntity;
import io.kx.loanapp.view.LoanAppByStatusView;
import kalix.javasdk.Kalix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public final class Main {

  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  public static Kalix createKalix() {
    // The KalixFactory automatically registers any generated Actions, Views or Entities,
    // and is kept up-to-date with any changes in your protobuf definitions.
    // If you prefer, you may remove this and manually register these components in a
    // `new Kalix()` instance.
    return KalixFactory.withComponents(
      LoanAppEntity::new,
      LoanAppByStatusView::new,
      LoanAppServiceActionImpl::new,
      StartUpAction::new);
  }

  public static void main(String[] args) throws Exception {
    LOG.info("starting the Kalix service");
//    for (int i = 0; i < 20; i++) {
//      Thread.sleep(1000 * 7);
//      GrpcReflectionClient.init();
//    }
    createKalix().start();
  }
}
