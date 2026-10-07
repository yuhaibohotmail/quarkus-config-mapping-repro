package org.acme.lib;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReproService {

    private final ReproConfig config;

    ReproService(ReproConfig config) {
        this.config = config;
    }

    public String name() {
        return config.name();
    }
}
