package pl.pjatk.jaz.httpclient;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.net.URI;

@Validated
@ConfigurationProperties("randomuser")
public record RandomUserProperties(
        URI baseUrl,
        @Min(1) @Max(100) int results,
        @NotBlank String nationality,
        @NotBlank String seed) {
}
