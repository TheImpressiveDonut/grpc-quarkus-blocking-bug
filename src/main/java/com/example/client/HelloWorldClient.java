package com.example.client;

import com.example.security.grpc.DummyCredentials;
import helloworld.Helloworld;
import helloworld.MutinyGreeterGrpc;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.logging.Logger;

@Path("/")
public class HelloWorldClient {

    private final Logger logger = Logger.getLogger(HelloWorldClient.class.getName());

    @GrpcClient("greeter")
    MutinyGreeterGrpc.MutinyGreeterStub stub;

    @GET
    @Path("/helloB")
    public Uni<String> sayHelloBlocking() {
        return stub
                .withCallCredentials(new DummyCredentials("test-token"))
                .sayHelloBlocking(Helloworld.HelloRequest.newBuilder().setName("testB").build())
                .map(Helloworld.HelloReply::getMessage);
    }

    @GET
    @Path("/helloBWithR")
    public Uni<String> sayHelloBlockingWithRolesAllowed() {
        return stub
                .withCallCredentials(new DummyCredentials("test-token"))
                .sayHelloBlockingWithRolesAllowed(Helloworld.HelloRequest.newBuilder().setName("testBWithR").build())
                .map(Helloworld.HelloReply::getMessage);
    }

    @GET
    @Path("/helloNonB")
    public Uni<String> sayHelloNonBlocking() {
        return stub
                .withCallCredentials(new DummyCredentials("test-token"))
                .sayHelloNonBlocking(Helloworld.HelloRequest.newBuilder().setName("testNonB").build())
                .map(Helloworld.HelloReply::getMessage);
    }

}
