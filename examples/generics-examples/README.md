# Generics examples — Java 21

Projekt towarzyszący wykładowi o typach generycznych i wildcardach.

## Wymagania

- JDK 21
- Maven 3.9+

## Komendy

```shell
mvn compile
java -cp target/classes pl.pjatk.jaz.generics.App
```

Live coding:

```shell
java -cp target/classes pl.pjatk.jaz.generics.livecoding.CargoTransfer
```

Plik `CargoTransfer.java` jest punktem startowym ćwiczenia. Pełna wersja znajduje się w
`CargoTransferSolution.java`.

## Pakiety

- `motivation` — wersja bez generyków;
- `genericclass` — klasa `Box<T>`;
- `genericmethod` — generyczna metoda `first`;
- `bounds` — ograniczenie `<T extends Number>`;
- `wildcards` — `? extends`, `? super` oraz PECS;
- `livecoding` — ćwiczenie i rozwiązanie.
