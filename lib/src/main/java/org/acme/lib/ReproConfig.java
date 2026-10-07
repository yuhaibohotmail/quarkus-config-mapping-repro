package org.acme.lib;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "repro")
public interface ReproConfig {
    String name();
}
