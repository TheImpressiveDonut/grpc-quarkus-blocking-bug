package com.example.security.grpc;

import io.grpc.Metadata;

public class DummyConstants {

    public static final Metadata.Key<String> AUTHORIZATION_METADATA_KEY = Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);
}
