package pl.pjatk.jaz.generics.livecoding;

import java.util.ArrayList;
import java.util.List;

/**
 * Punkt startowy live codingu. Zadanie: uogólnij metodę transfer przy użyciu PECS.
 * Gotowe rozwiązanie znajduje się w CargoTransferSolution.
 */
public class CargoTransfer {
    public static void transfer(List<Integer> source, List<Number> destination) {
        // TODO 1: dodaj parametr typu <T>
        // TODO 2: źródło powinno mieć typ List<? extends T>
        // TODO 3: cel powinien mieć typ List<? super T>
        for (Integer item : source) {
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
