import java.util.LinkedList;
import java.util.List;

class Sieve {

    private final int maxPrime;

    Sieve(final int maxPrime) {
        this.maxPrime = maxPrime;
    }

    List<Integer> getPrimes() {
        final List<Integer> primes = new LinkedList<Integer>();
        for (int i = 2; i <= maxPrime; i++) {
            boolean isPrime = true;
            for (final Integer prime : primes) {
                if (i % prime == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(i);
            }
        }
        return primes;
    }
}
