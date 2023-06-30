package com.example.security.quarkus;

import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.IdentityProvider;
import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DummyIdentityProvider implements IdentityProvider<DummyAuthenticationRequest> {

    @Override
    public Class<DummyAuthenticationRequest> getRequestType() {
        return DummyAuthenticationRequest.class;
    }

    @Override
    public Uni<SecurityIdentity> authenticate(DummyAuthenticationRequest request, AuthenticationRequestContext context) {
        return Uni.createFrom().item(createSecurityIdentity(request));
    }

    public SecurityIdentity createSecurityIdentity(DummyAuthenticationRequest request) {
        QuarkusSecurityIdentity.Builder builder = new QuarkusSecurityIdentity.Builder()
                .setPrincipal(new DummyPrincipal(request.getUsername()))
                .addCredential(request.getPassword());
        builder.addRole("Dummy");
        return builder.build();
    }
}
