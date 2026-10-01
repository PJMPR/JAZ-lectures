package pl.pjatk.jaz.generics.livecoding;

import java.util.ArrayList;
import java.util.List;

public class CargoTransferSolution {
    public static <T> void transfer(List<? extends T> source, List<? super T> destination) {
        for (T item : source) {
            destination.add(item);
        }
    }

    public static void main(String[] args) {
        List<Integer> cargo = List.of(100, 200, 300);
        List<Number> hold = new ArrayList<>();

        transfer(cargo, hold);
        System.out.println("Cargo in hold: " + hold);
    }
}
