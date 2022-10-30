package org.project.ifood.marketplace.testContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;

public class MarketplaceTestLifecyManager implements QuarkusTestResourceLifecycleManager {

    public static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:12.2")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @Override
    public Map<String, String> start() {
        POSTGRES.start();
        Map<String, String> properties = new HashMap<>();
        properties.put("quarkus.datasource.reactive.url", POSTGRES.getJdbcUrl().replace("jdbc:", ""));
        properties.put("quarkus.datasource.username", POSTGRES.getUsername());
        properties.put("quarkus.datasource.password", POSTGRES.getPassword());
        return properties;
    }

    @Override
    public void stop() {
        if (POSTGRES.isRunning()) POSTGRES.stop();
    }
}
