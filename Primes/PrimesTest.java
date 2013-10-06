import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PrimesTest
{
    @Test
    public void testIsPrime() throws Exception {
        assertEquals(true, Primes.isPrime(2));
        assertEquals(true, Primes.isPrime(3));
        assertEquals(true, Primes.isPrime(5));
        assertEquals(true, Primes.isPrime(7));

        assertEquals(false, Primes.isPrime(1));
        assertEquals(false, Primes.isPrime(4));
        assertEquals(false, Primes.isPrime(6));
        assertEquals(false, Primes.isPrime(8));
        assertEquals(false, Primes.isPrime(9));
        assertEquals(false, Primes.isPrime(10));
    }

    @Test
    public void testPrimes() throws Exception {
        assertEquals(Arrays.asList(2, 3, 5, 7),
                Primes.primes(10));
    }
}
