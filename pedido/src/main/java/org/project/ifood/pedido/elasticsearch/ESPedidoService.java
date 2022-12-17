package org.project.ifood.pedido.elasticsearch;

import io.vertx.core.json.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.project.ifood.pedido.dto.pedido.PedidoRealizadoDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class ESPedidoService {


    @Inject
    RestClient restClient;

    private ResponseListener getResponseListener() {
        return new ResponseListener() {
            @Override
            public void onSuccess(Response response) {
                System.out.println("Sucesso");
                System.out.println(response.getEntity());
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("error");
                System.out.println(e.getMessage());
            }
        };
    }

    public void index(PedidoRealizadoDTO realizadoDTO) {
        String uui = UUID.randomUUID().toString();
        String body = JsonObject.mapFrom(realizadoDTO).toString();
//        HttpEntity reqBody =  new NStringEntity(body, ContentType.APPLICATION_JSON);
        Request request = new Request("PUT", "pedidos/_doc/" + uui);
//        request.setEntity(reqBody);
        request.setJsonEntity(body);
        this.restClient.performRequestAsync(request, getResponseListener());

    }
}
