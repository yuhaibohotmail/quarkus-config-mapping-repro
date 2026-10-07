package org.acme.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.inject.Inject;

import org.acme.lib.ReproConfig;
import org.acme.lib.ReproService;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ReproTest {

    @Inject
    ReproConfig config;

    @Inject
    ReproService service;

    @Test
    void mappingFromDependencyIsRegistered() {
        assertEquals("x", config.name());
        assertEquals("x", service.name());
    }
}
