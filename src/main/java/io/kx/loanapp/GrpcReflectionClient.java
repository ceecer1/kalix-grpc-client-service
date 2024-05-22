package io.kx.loanapp;

import io.grpc.reflection.v1alpha.ServerReflectionRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.reflection.v1alpha.ServerReflectionGrpc;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class GrpcReflectionClient {

    private final static Logger logger = LoggerFactory.getLogger(GrpcReflectionClient.class);

    public static void init() throws InterruptedException {
        logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        ManagedChannel channel = ManagedChannelBuilder.forTarget("loanapplication:80") //use 0.0.0.0:9000 for local service
                .usePlaintext().defaultLoadBalancingPolicy("round_robin").build();
        ServerReflectionGrpc.ServerReflectionStub reflectionStub = ServerReflectionGrpc.newStub(channel);
        ReflectionResponseStreamObserver reflectionResponseStreamObserver = new ReflectionResponseStreamObserver("loanapplication", channel);
        StreamObserver<io.grpc.reflection.v1alpha.ServerReflectionRequest> requestStreamObserver
                = reflectionStub.serverReflectionInfo(reflectionResponseStreamObserver);
        ServerReflectionRequest getFileContainingSymbolRequest = ServerReflectionRequest.newBuilder()
                .setFileContainingSymbol("io.kx.loanapp.api.LoanAppService").build();
        requestStreamObserver.onNext(getFileContainingSymbolRequest);
        channel.awaitTermination(10, TimeUnit.SECONDS);
    }
}
