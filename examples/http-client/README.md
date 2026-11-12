# DTO, record i klient HTTP

Projekt do wykładu 07. Pobiera użytkowników z publicznego Random User API i pokazuje:

- rekordy jako niemutowalne nośniki danych;
- DTO dopasowane do kontraktu JSON za pomocą Jacksona;
- typowaną konfigurację `@ConfigurationProperties` nadpisywaną zmiennymi środowiskowymi;
- bezpieczne składanie URI przez `UriComponentsBuilder`;
- synchroniczne wywołanie przez `RestClient` i test bez połączenia z internetem.

Przykład korzysta z rekomendowanego synchronicznego klienta `RestClient`, fluent API oraz klienta HTTP dostarczanego przez JDK 21.

## Uruchomienie

```text
mvn test
mvn spring-boot:run
```

Domyślne zapytanie pobiera dwie osoby. Konfigurację można zmienić bez rekompilacji:

```text
RANDOMUSER_RESULTS=5 mvn spring-boot:run
```

W PowerShell:

```powershell
$env:RANDOMUSER_RESULTS = 5
mvn spring-boot:run
```
