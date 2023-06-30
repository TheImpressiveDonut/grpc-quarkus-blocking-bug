package com.example.security.quarkus;

import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;

public class DummyAuthenticationRequest extends UsernamePasswordAuthenticationRequest {

    public DummyAuthenticationRequest(String username, PasswordCredential password) {
        super(username, password);
    }

    public DummyAuthenticationRequest(String token) {
        super(token, new PasswordCredential("empty".toCharArray()));
    }
}
