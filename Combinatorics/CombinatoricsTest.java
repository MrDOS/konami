import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CombinatoricsTest
{
    @Test
    public void testFactorial() throws Exception {
        assertEquals(1, Combinatorics.factorial(0));
        assertEquals(1, Combinatorics.factorial(1));
        assertEquals(2, Combinatorics.factorial(2));
        assertEquals(6, Combinatorics.factorial(3));
        assertEquals(24, Combinatorics.factorial(4));
        assertEquals(120, Combinatorics.factorial(5));
        assertEquals(720, Combinatorics.factorial(6));
        assertEquals(5040, Combinatorics.factorial(7));
        assertEquals(40320, Combinatorics.factorial(8));
        assertEquals(362880, Combinatorics.factorial(9));
        assertEquals(3628800, Combinatorics.factorial(10));
        assertEquals(479001600, Combinatorics.factorial(12));
    }

    @Test
    public void testLargeFactorial() throws Exception {
        assertEquals(2432902008176640000L, Combinatorics.factorial(20));
    }
}