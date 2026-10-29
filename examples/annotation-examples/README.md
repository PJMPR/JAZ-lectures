# Przykłady do wykładu „Adnotacje w Javie”

Projekt zawiera dwa przykłady:

1. Pakiet `scheduling` pokazuje prostą adnotację `@Scheduled` umieszczaną na metodach.
2. Pakiet `objectbuilder` pokazuje tworzenie obiektu na podstawie adnotacji i refleksji.

## Struktura pakietów

```text
pl.pjatk.jaz.annotations
├── scheduling
│   ├── annotation    definicja @Scheduled
│   ├── model         klasa zawierająca oznaczone metody
│   └── runtime       kod odczytujący adnotację przez refleksję
└── objectbuilder
    ├── annotation    definicje adnotacji buildera
    ├── core          ObjectBuilder i jego wyjątek
    ├── model         przykładowa budowana klasa
    └── demo          uruchamialny przykład
```

## ObjectBuilder

Przykład obejmuje adnotacje umieszczane na różnych elementach programu:

- `@Buildable` na klasie,
- `@BuildConstructor` na konstruktorze,
- `@ConfigValue` na parametrach konstruktora,
- `@ConfigValue` na polach,
- `@PostBuild` na metodzie uruchamianej po zbudowaniu obiektu.

`ObjectBuilder` wybiera konstruktor, przygotowuje jego argumenty, tworzy instancję,
uzupełnia pola i wywołuje metodę kończącą inicjalizację. Kod zawiera komentarze
wyjaśniające definicje adnotacji i używane operacje refleksji.

## Uruchomienie

W IDE uruchom jedną z metod `main`:

- `pl.pjatk.jaz.annotations.scheduling.runtime.AnnotationRunner`,
- `pl.pjatk.jaz.annotations.objectbuilder.demo.ObjectBuilderDemo`.

Możesz też najpierw wykonać `mvn compile`.
