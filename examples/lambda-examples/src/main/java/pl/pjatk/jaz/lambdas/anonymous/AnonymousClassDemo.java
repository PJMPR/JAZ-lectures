package pl.pjatk.jaz.lambdas.anonymous;

import java.util.Comparator;

public class AnonymousClassDemo {
    public static void main(String[] args) {
        Comparator<String> byLength = new Comparator<>() {
            @Override
            public int compare(String first, String second) {
                return Integer.compare(first.length(), second.length());
            }
        };
        System.out.println(byLength.compare("Java", "lambda"));
    }
}
