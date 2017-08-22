import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SumOfMultiples {

    private final int number;
    private final int[] set;

    SumOfMultiples(int number, int[] set) {
        this.number = number;
        this.set = set;
    }

    private static void multiples(final Set<Integer> multiples, final int number, int multiple) {
        int value = multiple;
        while (number > value) {
            multiples.add(value);
            value += multiple;
        }
    }

    int getSum() {
        final Set<Integer> multiples = new HashSet<>();
        for (final int multiple : set) {
            multiples(multiples, number, multiple);
        }
        int sum = 0;
        for (final int value : multiples) {
            sum += value;
        }
        return sum;
    }

}
