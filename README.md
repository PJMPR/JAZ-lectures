# JAZ Lectures

Portal interaktywnych wykładów dla przedmiotu Java Zaawansowana.

Aktualnie dostępny wykład: „Generyki i wildcardy”. Jego strona znajduje się w
`lectures/generics/index.html`.

## Uruchomienie strony

Strona nie wymaga procesu budowania. Otwórz główny `index.html` albo uruchom dowolny lokalny serwer HTTP,
np. z poziomu IDE. Nawigacja działa klawiszami strzałek, `Home`, `End`; klawisz `F` przełącza pełny ekran.

## Publikacja na GitHub Pages

Po wysłaniu plików do repozytorium wybierz w ustawieniach GitHub:

1. **Settings → Pages**,
2. **Deploy from a branch**,
3. gałąź `main` i katalog `/ (root)`.

Pliki używają ścieżek względnych, więc strona działa również w repozytorium projektowym
pod adresem `https://<użytkownik>.github.io/<repozytorium>/`.

## Przykłady Java

Projekt Maven znajduje się w `examples/generics-examples`. Wymaga Java 21.
