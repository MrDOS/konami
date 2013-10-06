public class Integers
{
    /**
     * Calculate the number of digits in a positive integer.
     */
    public static int digits(long x) {
        int digits = 0;
        do {
            digits++;
        } while ((x /= 10) > 0);
        return digits;
    }

    /**
     * Reverse the digits of a positive integer.
     */
    public static long reverse(long x) {
        long reverse = 0;
        do {
            reverse = reverse * 10 + x % 10;
        } while ((x /= 10) > 0);
        return reverse;
    }

    /**
     * Return the digit at the zero-based nth position in a positive integer.
     */
    public static long digitAt(long x, int n) {
        return (long) (x / Math.pow(10, Integers.digits(x) - n - 1)) % 10;
    }

    /**
     * Modular exponentiation ((base^exp) % mod).
     */
    public static int modPow(int base, int exp, int mod) {
        int result = 1;
        for (; exp > 0; exp--)
            result = (result * base) % mod;
        return result;
    }

    /**
     * Determine whether or not a given integer is a power of two. In Java, this
     * is faster than ((n > 0) && ((n - 1) & n == 0)); see
     * http://forums.thedailywtf.com/forums/p/26172/292984.aspx#292984
     * and
     * http://forums.thedailywtf.com/forums/p/26172/293136.aspx#293136
     */
    public static boolean isPowerOfTwo(int x) {
        return x > 0 && Integer.bitCount(x) == 1;
    }

    /**
     * Determine the greatest common divisor of two integers.
     */
    public static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}