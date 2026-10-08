package pl.pjatk.jaz.lambdas.functional;

@FunctionalInterface
public interface Operation {
    int apply(int left, int right);
}
