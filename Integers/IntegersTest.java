import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntegersTest
{
    @Test
    public void testDigits() throws Exception {
        assertEquals(1, Integers.digits(0));
        assertEquals(1, Integers.digits(1));
        assertEquals(3, Integers.digits(123));
        assertEquals(6, Integers.digits(302010));
    }

    @Test
    public void testReverse() throws Exception {
        assertEquals(0, Integers.reverse(0));
        assertEquals(1, Integers.reverse(1));
        assertEquals(321, Integers.reverse(123));
        assertEquals(10203, Integers.reverse(302010));
    }

    @Test
    public void testDigitAt() throws Exception {
        assertEquals(1, Integers.digitAt(123, 0));
        assertEquals(2, Integers.digitAt(123, 1));
        assertEquals(3, Integers.digitAt(123, 2));
    }

    @Test
    public void testModPow() throws Exception {
        assertEquals(445, Integers.modPow(4, 13, 497));
    }

    @Test
    public void testIsPowerOfTwo() throws Exception {
        assertEquals(false, Integers.isPowerOfTwo(0));
        assertEquals(true, Integers.isPowerOfTwo(2));
        assertEquals(true, Integers.isPowerOfTwo(16));
        assertEquals(false, Integers.isPowerOfTwo(123));
    }

    @Test
    public void testGcd() throws Exception {
        assertEquals(0, Integers.gcd(0, 0));
        assertEquals(1, Integers.gcd(1, 1));

        assertEquals(1, Integers.gcd(3, 4));
        assertEquals(1, Integers.gcd(4, 3));

        assertEquals(1, Integers.gcd(1, 2));
        assertEquals(1, Integers.gcd(2, 1));

        assertEquals(4, Integers.gcd(16, 20));
        assertEquals(4, Integers.gcd(20, 16));
    }
}
