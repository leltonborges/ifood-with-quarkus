package org.project.ifood.marketplace.config.providers;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.flywaydb.core.Flyway;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class FlywayConfig {

    @ConfigProperty(name = "quarkus.flyway.migrate-at-start")
    boolean isMigrate;
    @ConfigProperty(name = "quarkus.flyway.clean-at-start")
    boolean isClean;
    @ConfigProperty(name = "quarkus.datasource.reactive.url")
    String datasourceUrl;
    @ConfigProperty(name = "quarkus.datasource.username")
    String datasourceUsername;
    @ConfigProperty(name = "quarkus.datasource.password")
    String datasourcePassword;

    @ConfigProperty(name = "quarkus.flyway.default-schema")
    String defaultSchema;

    @ConfigProperty(name = "quarkus.flyway.create-schemas")
    boolean isCreateSchema;

    public void runFlywayMigration(@Observes StartupEvent event) {
        Flyway flyway = Flyway.configure()
                              .dataSource("jdbc:" + datasourceUrl, datasourceUsername, datasourcePassword)
                              .cleanDisabled(!isClean)
                              .defaultSchema(defaultSchema)
                              .createSchemas(isCreateSchema)
                              .load();

        if (isClean) flyway.clean();
        if (isMigrate) flyway.migrate();
    }
}
