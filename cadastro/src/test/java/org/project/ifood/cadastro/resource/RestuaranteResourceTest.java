package org.project.ifood.cadastro.resource;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.approvaltests.Approvals;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.project.ifood.cadastro.CadastroTestLifecycleManager;
import org.project.ifood.cadastro.model.Restaurante;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
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

    @Test
    @DataSet("restaurante-cenario-1.yaml")
    public void testAlterarRestaurante(){
        Restaurante dto = new Restaurante();
        dto.nome = "novoNome";
        Long parametroValor = 123L;
        given()
                .with().pathParam("id", parametroValor)
                .body(dto)
                .when()
                .put("/restaurantes/{id}")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode())
                .extract().asString();
        Restaurante entidade = Restaurante.findById(parametroValor);
        Assert.assertEquals(dto.nome, entidade.nome);
    }

    private RequestSpecification given(){
        return RestAssured.given().contentType(ContentType.JSON);
    }
}
