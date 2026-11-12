package pl.pjatk.jaz.httpclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class RandomUserClient {
    private final RestClient http;
    private final RandomUserProperties properties;

    public RandomUserClient(RestClient http, RandomUserProperties properties) {
        this.http = http;
        this.properties = properties;
    }

    public RandomUserResponse fetchUsers() {
        URI uri = UriComponentsBuilder.fromUri(properties.baseUrl())
                .pathSegment("api", "1.4")
                .queryParam("results", properties.results())
                .queryParam("nat", properties.nationality())
                .queryParam("seed", properties.seed())
                .build()
                .encode()
                .toUri();

        return http.get()
                .uri(uri)
                .retrieve()
                .body(RandomUserResponse.class);
    }
}
