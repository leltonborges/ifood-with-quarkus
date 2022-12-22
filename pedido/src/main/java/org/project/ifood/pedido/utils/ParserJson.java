package org.project.ifood.pedido.utils;

import io.vertx.core.json.JsonObject;

public final class ParserJson {
    private ParserJson() {}

    public static <T> String toJSON(T obj) {
        return JsonObject.mapFrom(obj).toString();
    }
}
