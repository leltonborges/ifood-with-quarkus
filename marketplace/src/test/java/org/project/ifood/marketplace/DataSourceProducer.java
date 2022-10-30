package org.project.ifood.marketplace;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.project.ifood.marketplace.testContainer.MarketplaceTestLifecyManager;

import javax.enterprise.inject.Produces;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceProducer {

    @ConfigProperty(name = "quarkus.datasource.reactive.url")
    String url;

    @ConfigProperty(name = "quarkus.datasource.username")
    String userName;

    @ConfigProperty(name = "quarkus.datasource.password")
    String password;

    @Produces
    public Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:"+url, userName, password);
    }

    @Produces
    public MarketplaceTestLifecyManager lifecyManager() throws SQLException {
        return new MarketplaceTestLifecyManager();
    }

}
