package io.kx.loanapp;

import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import io.grpc.reflection.v1alpha.ServerReflectionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionResponseStreamObserver implements StreamObserver<ServerReflectionResponse> {

    private final Logger logger = LoggerFactory.getLogger(ReflectionResponseStreamObserver.class);
    private final String identifier;
    private final ManagedChannel channel;

    public ReflectionResponseStreamObserver(String identifier, ManagedChannel channel) {
        this.identifier = identifier;
        this.channel = channel;
    }

    @Override
    public void onNext(ServerReflectionResponse response) {
        // Handle the received ServerReflectionResponse
        logger.info("####################################");
        logger.info("####################################");
        logger.info("Received response for " + identifier + ": " + response);
        logger.info("####################################");
        logger.info("####################################");
    }

    @Override
    public void onError(Throwable throwable) {
        // Handle error
        throwable.printStackTrace();
    }

    @Override
    public void onCompleted() {
        // Handle completion
        logger.info("####################################");
        logger.info("####################################");
        logger.info("Server reflection request completed for " + identifier);
        logger.info("####################################");
        logger.info("####################################");
        // Clean up resources if necessary
        channel.shutdown();
    }
}

