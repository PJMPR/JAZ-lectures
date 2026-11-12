package pl.pjatk.jaz.httpclient;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PageInfoDto(
        String seed,
        @JsonProperty("results") int resultCount,
        int page,
        String version) {
}
