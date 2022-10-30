package org.project.ifood.marketplace.resources;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.project.ifood.marketplace.testContainer.MarketplaceTestLifecyManager;

import static io.restassured.RestAssured.given;

@DBRider
@QuarkusTest
@QuarkusTestResource(MarketplaceTestLifecyManager.class)
class PratoResourceTest {

    @Test
    @DataSet("prato-cenario-1.yaml")
    @DBUnit(schema = "public", caseSensitiveTableNames = true, cacheConnection = false)
    public void testAllPratos() {
        String result = given().when()
                               .get("/pratos")
                               .then()
                               .statusCode(200)
                               .extract().asString();
        Approvals.verify(result);
//                .body(is("Hello from RESTEasy Reactive"));
    }

}