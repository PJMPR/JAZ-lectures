package pl.pjatk.jaz.lambdas;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class App {
    public static void main(String[] args) {
        List<String> names = List.of("Ada", "Barbara", "Jan", "Krzysztof");
        Predicate<String> longName = name -> name.length() >= 5;
        Function<String, String> label = String::toUpperCase;
        names.stream().filter(longName).map(label).forEach(System.out::println);
    }
}
