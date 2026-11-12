package pl.pjatk.jaz.httpclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HttpClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(HttpClientApplication.class, args);
    }

    @Bean
    CommandLineRunner showUsers(RandomUserClient client) {
        return args -> client.fetchUsers().results().forEach(user ->
                System.out.printf("%s <%s>%n", user.fullName(), user.email()));
    }
}
