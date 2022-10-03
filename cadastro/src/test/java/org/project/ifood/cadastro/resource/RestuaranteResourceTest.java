package org.project.ifood.cadastro.resource;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.project.ifood.cadastro.CadastroTestLifecycleManager;

import static io.restassured.RestAssured.when;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(CadastroTestLifecycleManager.class)
public class RestuaranteResourceTest {

    @Test
    @DataSet("restaurante-cenario-1.yaml")
    public void testBuscandoRestaurantes(){
        String result = when().get("/restaurantes")
                .then()
                .statusCode(200)
                .extract().asString();

        Approvals.verify(result);
    }
}
