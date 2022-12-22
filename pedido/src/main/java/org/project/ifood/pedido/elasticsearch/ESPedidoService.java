package org.project.ifood.pedido.elasticsearch;

import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.jboss.logging.Logger;
import org.project.ifood.pedido.dto.pedido.PedidoRealizadoDTO;
import org.project.ifood.pedido.utils.ParserJson;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class ESPedidoService {

    private static final Logger LOG = Logger.getLogger(ESPedidoService.class);

    @Inject
    RestClient restClient;

    public void index(PedidoRealizadoDTO realizadoDTO) {
        String uui = UUID.randomUUID().toString();
        Request request = new Request("PUT", "pedidos/_doc/" + uui);
        request.setJsonEntity(ParserJson.toJSON(realizadoDTO));
        this.restClient.performRequestAsync(request, getResponseListener());

    }

    private ResponseListener getResponseListener() {
        return new ResponseListener() {
            @Override
            public void onSuccess(Response response) {
                LOG.info("elastic: " + response.getEntity());
            }

            @Override
            public void onFailure(Exception e) {
                LOG.error(e.getMessage(), e.getCause());
            }
        };
    }

}
