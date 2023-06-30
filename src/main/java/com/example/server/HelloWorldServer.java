package com.example.server;

import helloworld.Greeter;
import helloworld.Helloworld;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import io.vertx.core.Context;
import jakarta.annotation.security.RolesAllowed;

import java.util.logging.Logger;

@GrpcService
public class HelloWorldServer implements Greeter {

    private final Logger logger = Logger.getLogger(HelloWorldServer.class.getName());

    @Override
    @Blocking
    public Uni<Helloworld.HelloReply> sayHelloBlocking(Helloworld.HelloRequest request) {
        logger.info("Blocking call without roles allowed, is on eventloop: " + Context.isOnEventLoopThread());
        return Uni.createFrom().item(
                () -> Helloworld.HelloReply.newBuilder().setMessage("Hello " + request.getName()).build());
    }

    @Override
    @Blocking
    @RolesAllowed("Dummy")
    public Uni<Helloworld.HelloReply> sayHelloBlockingWithRolesAllowed(Helloworld.HelloRequest request) {
        logger.info("Blocking call with roles allowed, is on eventloop: " + Context.isOnEventLoopThread());
        return Uni.createFrom().item(
                () -> Helloworld.HelloReply.newBuilder().setMessage("Hello " + request.getName()).build());
    }

    @Override
    @RolesAllowed("Dummy")
    public Uni<Helloworld.HelloReply> sayHelloNonBlocking(Helloworld.HelloRequest request) {
        logger.info("Non blocking call  with roles allowed, is on eventloop: " + Context.isOnEventLoopThread());
        return Uni.createFrom().item(
                () -> Helloworld.HelloReply.newBuilder().setMessage("Hello " + request.getName()).build());
    }
}
