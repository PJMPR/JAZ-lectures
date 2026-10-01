# JAZ Lectures

Portal interaktywnych wykładów dla przedmiotu Java Zaawansowana.

Aktualnie dostępny wykład: „Generyki i wildcardy”. Jego strona znajduje się w
`lectures/generics/index.html`.

## Uruchomienie strony

Strona nie wymaga procesu budowania. Otwórz główny `index.html` albo uruchom dowolny lokalny serwer HTTP,
np. z poziomu IDE. Nawigacja działa klawiszami strzałek, `Home`, `End`; klawisz `F` przełącza pełny ekran.

## Publikacja na GitHub Pages

Workflow `.github/workflows/deploy-pages.yml` publikuje stronę automatycznie po każdym wysłaniu
zmian na gałąź `main`. Można go również uruchomić ręcznie z zakładki **Actions**.

Przy pierwszym wdrożeniu wybierz w repozytorium **Settings → Pages → Source → GitHub Actions**.
Po zakończeniu akcji portal będzie dostępny pod adresem:

`https://pjmpr.github.io/JAZ-lectures/`

Pliki używają ścieżek względnych, więc strona działa również w repozytorium projektowym
pod adresem `https://<użytkownik>.github.io/<repozytorium>/`.

## Przykłady Java

Projekt Maven znajduje się w `examples/generics-examples`. Wymaga Java 21.
