package org.project.ifood.cadastro.resource;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.project.ifood.cadastro.CadastroTestLifecycleManager;
import org.project.ifood.cadastro.dto.restaurante.AtualizarRestaurante;
import org.project.ifood.cadastro.model.Restaurante;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(CadastroTestLifecycleManager.class)
public class RestuaranteResourceTest {

    @Test
    @DataSet("restaurante-cenario-1.yaml")
    @TestSecurity(authorizationEnabled = false)
    public void testBuscandoRestaurantes() {
        String result = given().when().get("/restaurantes")
                .then()
                .statusCode(200)
                .extract().asString();

        Approvals.verify(result);
    }

    @Test
    @DataSet("restaurante-cenario-1.yaml")
    @TestSecurity(user = "testUser", roles = {"proprietario"})
    public void testAlterarRestaurante() {
        AtualizarRestaurante dto = new AtualizarRestaurante();
        dto.nomeFantasia = "novoNome";
        dto.proprietario = "novoProprietario";
        Long parametroValor = 123L;
        RestAssured.given()
                .contentType(ContentType.JSON)
                .with().pathParam("id", parametroValor)
                .body(dto)
                .when()
                .put("/restaurantes/{id}")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        Restaurante entidade = Restaurante.findById(parametroValor);
        assertEquals(dto.nomeFantasia, entidade.nome);
        assertEquals(dto.proprietario, entidade.proprietario);
    }

    private RequestSpecification given() {
        return RestAssured.given().contentType(ContentType.JSON);
    }
}
