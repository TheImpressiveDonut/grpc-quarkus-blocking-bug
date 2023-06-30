package com.example.security.quarkus;

import javax.security.auth.Subject;
import java.io.Serializable;
import java.security.Principal;

public class DummyPrincipal implements Principal {

    private final String token;

    public DummyPrincipal(String token) {
        this.token = token;
    }

    @Override
    public String getName() {
        return "Dummy";
    }
}
