import java.util.ArrayList;

public class Primes
{
    /**
     * Determine whether or not a number is prime.
     */
    public static boolean isPrime(long x) {
        if (x == 1)
            return false;
        if (x == 2 || x == 3)
            return true;

        for (long i = 2; i < x / 2 + 1; i++)
            if (x % i == 0)
                return false;
        return true;
    }

    /**
     * Prime finding via Sieve of Eratosthenes.
     */
    public static ArrayList<Integer> primes(int n) {
        /* We could estimate the number of primes less than n by
		 * pi(x) ~= x/(log x - 1) but over 10,000 iterations, it's faster to
		 * let the array expand by itself than to calculate the estimate. */
        ArrayList<Integer> primes = new ArrayList<Integer>();
        /* The method works by iterating over all integers [2, n] and marking
         * all multiples as composite (not prime). By the definition of prime
         * numbers, all numbers not marked as composite are prime. */
        boolean composite[] = new boolean[n + 1];
        int markLimit = (int) Math.sqrt(n);

        for (int m = 2; m <= n; m++) {
            /* If we don't already know that the number isn't prime... */
            if (!composite[m]) {
                /* ...mark it off as being prime. */
                primes.add(m);
                /* If the number being evaluated is below a reasonable limit,
                 * mark all multiples as prime. */
                if (m <= markLimit)
                    for (int k = m * m; k <= n; k += m)
                        composite[k] = true;
            }
        }

        return primes;
    }
}