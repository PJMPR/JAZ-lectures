package pl.pjatk.jaz.httpclient;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class RandomUserClientTest {
    @Test
    void mapsJsonResponseToRecords() {
        RestClient.Builder builder = RestClient.builder();
        MockRestServiceServer server = MockRestServiceServer.bindTo(builder).build();
        RandomUserProperties properties = new RandomUserProperties(
                URI.create("https://randomuser.me"), 1, "pl", "lecture seed");
        RandomUserClient client = new RandomUserClient(builder.build(), properties);

        server.expect(requestTo("https://randomuser.me/api/1.4?results=1&nat=pl&seed=lecture%20seed"))
                .andRespond(withSuccess("""
                        {"results":[{"name":{"title":"Mr","first":"Jan","last":"Kowalski"},
                        "email":"jan@example.com","nat":"PL"}],
                        "info":{"seed":"lecture seed","results":1,"page":1,"version":"1.4"}}
                        """, MediaType.APPLICATION_JSON));

        RandomUserResponse response = client.fetchUsers();

        assertThat(response.results()).hasSize(1);
        assertThat(response.results().getFirst().fullName()).isEqualTo("Jan Kowalski");
        assertThat(response.info().resultCount()).isEqualTo(1);
        server.verify();
    }
}
