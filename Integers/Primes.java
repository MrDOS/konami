import java.util.ArrayList;
import java.util.Random;

public class Primes
{
    /**
     * Determine whether or not a number is prime. Uses Miller-Rabin.
     */
    public static boolean isPrime(int n) {
        /* Use some reasonably high accuracy requirement. */
        return Primes.isPrime(n, 100);
    }

    /**
     * Determine whether or not a number is prime. Uses Miller-Rabin.
     */
    public static boolean isPrime(int n, int k) {
        if (n == 0 || n == 1) return false;
        if (n == 2 || n == 3) return true;
        if ((n & 1) == 0) return false;

        int d = (n - 1) >> 1;
        int s = 1;
        while ((s & 1) == 0) {
            d >>= 1;
            s++;
        }

        Random r = new Random();
        for (int i = 0; i < k; i++) {
            int a = r.nextInt(n - 3) + 2;
            int x = Integers.modPow(a, d, n);

            if (x == 1 || x == n - 1) continue;

            for (int j = 0; j < s - 1; j++) {
                x = (x * x) % n;
                if (x == 1) return false;
                if (x == n - 1) break;
            }

            if (x == n - 1) continue;
            else return false;
        }

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