# Spring Data JPA

Przykład do wykładu `lectures/spring-data-jpa`. Wymaga Java 21, Mavena i działającego Docker Desktop.

## PostgreSQL w Windows Terminal (PowerShell)

```powershell
docker run --name jaz-postgres `
  -e POSTGRES_DB=jazdb `
  -e POSTGRES_USER=jaz `
  -e POSTGRES_PASSWORD=jaz `
  -p 5432:5432 `
  -d postgres:17
```

Uruchom aplikację:

```powershell
mvn spring-boot:run
```

Przydatne polecenia:

```powershell
docker ps
docker logs jaz-postgres
docker exec -it jaz-postgres psql -U jaz -d jazdb
docker stop jaz-postgres
docker start jaz-postgres
docker rm -f jaz-postgres
```

Hasło `jaz` służy wyłącznie do lokalnej demonstracji.

## Automatyczne dane demonstracyjne

Przy pierwszym uruchomieniu aplikacja wypełnia pustą bazę większym zbiorem danych:

- 30 autorów;
- 8 wydawców;
- 15 kategorii;
- 500 książek;
- 500 rekordów szczegółów książek;
- około 1000 powiązań w tabeli `book_categories`.

Generator jest idempotentny: jeżeli tabela `books` zawiera rekordy, kolejne uruchomienie
nie dodaje drugiego zestawu danych. Aby wygenerować zestaw ponownie, usuń dane lub cały
kontener PostgreSQL. Liczby można zmienić przez stałe na początku klasy
`DemoDataInitializer`.

## Zakres przykładów

Model pokazuje cztery rodzaje relacji:

- `Author` → `Book`: dwukierunkowe `@OneToMany` / `@ManyToOne`;
- `Publisher` → `Book`: kolejne `@OneToMany` / `@ManyToOne`, z przykładem kaskady;
- `Book` ↔ `BookDetails`: `@OneToOne`, `cascade = ALL` i `orphanRemoval`;
- `Book` ↔ `Category`: `@ManyToMany` z jawną tabelą łączącą `book_categories`.

Repozytoria zawierają przykłady:

- wyszukiwania, `exists` i `count` wynikających z nazw metod;
- przechodzenia po zagnieżdżonych właściwościach, np. `AuthorName` i `CategoriesName`;
- `Top`, sortowania, zakresu `Between` oraz `Pageable`;
- named query, `@EntityGraph`, JPQL w `@Query` i zapytania native SQL;
- aktualizacji zbiorczej przez `@Modifying`.
