package pl.pjatk.jaz.httpclient;

public record UserDto(NameDto name, String email, String nat) {
    public String fullName() {
        return name.first() + " " + name.last();
    }
}
