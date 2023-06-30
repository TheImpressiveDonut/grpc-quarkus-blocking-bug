package com.example.security.grpc;

import com.example.security.quarkus.DummyAuthenticationRequest;
import com.google.common.base.Strings;
import io.grpc.Metadata;
import io.quarkus.grpc.auth.GrpcSecurityMechanism;
import io.quarkus.security.identity.request.AuthenticationRequest;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DummyGrpcSecurityMechanism implements GrpcSecurityMechanism {

    @Override
    public boolean handles(Metadata metadata) {
        String header = metadata.get(DummyConstants.AUTHORIZATION_METADATA_KEY);
        return !Strings.isNullOrEmpty(header);
    }

    @Override
    public AuthenticationRequest createAuthenticationRequest(Metadata metadata) {
        String header = metadata.get(DummyConstants.AUTHORIZATION_METADATA_KEY);
        return new DummyAuthenticationRequest(header);
    }
}
