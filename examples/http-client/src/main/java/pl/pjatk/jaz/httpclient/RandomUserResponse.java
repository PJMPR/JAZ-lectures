package pl.pjatk.jaz.httpclient;

import java.util.List;

public record RandomUserResponse(List<UserDto> results, PageInfoDto info) {
    public RandomUserResponse {
        results = List.copyOf(results);
    }
}
