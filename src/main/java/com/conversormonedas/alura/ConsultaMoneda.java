package com.conversormonedas.alura;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {
    public Moneda buscarMoneda(String monedaBase) {
        // PEGÁ TU API KEY ACÁ
        String apiKey = "cec0405fe477ee3bb3298b30";
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + monedaBase);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moneda.class);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo realizar la conversión.");
        }
    }
}