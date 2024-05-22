package io.kx.loanapp.action;

import com.google.protobuf.Empty;
import io.kx.loanapp.GrpcReflectionClient;
import kalix.javasdk.action.ActionCreationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This class was initially generated based on the .proto definition by Kalix tooling.
// This is the implementation for the Action Service described in your io/kx/loanapp/action/startup_action.proto file.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class StartUpAction extends AbstractStartUpAction {

  private static Logger logger = LoggerFactory.getLogger(StartUpAction.class);

  public StartUpAction(ActionCreationContext creationContext) {}

  @Override
  public Effect<Empty> init(Empty empty) {
    try {
      logger.info("####################");
      logger.info("####################");
      GrpcReflectionClient.init();
      logger.info("####################");
      logger.info("####################");
    } catch (Exception ex) {
      logger.info("--------------------", ex.getMessage());
    }
    return effects().reply(Empty.getDefaultInstance());
  }
}
