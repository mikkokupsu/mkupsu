import java.util.ArrayList;
import java.util.List;

public class PrimeCalculator {
    private static final int FIRST_PRIME = 2;

    public int nth(final int steps) {
        if (steps < 1) {
               throw new IllegalArgumentException("Undefined prime");
        }
        final List<Integer> primes = new ArrayList<>(steps);
        primes.add(FIRST_PRIME);
        int currentValue = FIRST_PRIME;
        while (primes.size() < steps) {
            currentValue++;
            boolean isPrime = true;
            for (final int prime : primes) {
                /* Optimization since all dividers are in interval [2, N/2] */
                if (currentValue / prime < 2) {
                    break;
                } else if (currentValue % prime == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(currentValue);
            }
        }
        return primes.get(steps - 1);
    }
}
