# Spring Intro

Mały projekt demonstracyjny do wykładu „Wprowadzenie do Spring”. Pokazuje ten sam przypadek w trzech krokach:

1. ręczne tworzenie grafu obiektów;
2. konfigurację kontenera przez `@Bean`;
3. wykrywanie komponentów przez `@ComponentScan`.

Projekt celowo używa Spring Framework bez Spring Boot. Dzięki temu na wykładzie widać, za co odpowiada kontener IoC, zanim kolejny wykład doda auto-konfigurację Boota.

## Uruchomienie

```text
mvn test
mvn exec:java
```

W `App.java` można przełączać konfigurację pomiędzy `ExplicitConfiguration` i `ScanConfiguration`.
