package pl.pjatk.jaz.generics;

import pl.pjatk.jaz.generics.bounds.Numbers;
import pl.pjatk.jaz.generics.genericclass.Box;
import pl.pjatk.jaz.generics.genericmethod.ArrayUtils;
import pl.pjatk.jaz.generics.wildcards.ListOperations;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Box<String> message = new Box<>("System online");
        System.out.println(message.get());

        String first = ArrayUtils.first(new String[]{"alpha", "beta"});
        System.out.println("First: " + first);

        System.out.println("As double: " + Numbers.asDouble(42));

        List<Integer> source = List.of(10, 20, 30);
        List<Number> destination = new ArrayList<>();
        ListOperations.copy(source, destination);
        System.out.println("Transferred: " + destination);
    }
}
