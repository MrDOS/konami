import java.util.HashMap;

public class Combinatorics
{
    public long RepYesOrderNo(long N, long R) {
        return factorial(N + R - 1) / (factorial(R) * (factorial(N - 1)));
    }

    public long RepNoOrderNo(long N, long R) {
        return factorial(N) / (factorial(R) * (factorial(N - R)));
    }

    public long RepNoOrderYes(long N, long R) {
        return factorial(N) / (factorial(N - R));
    }

    public long RepYesOrderYes(long N, long R) {
        return (long) Math.pow(N, R);
    }

    private static HashMap<Long, Long> factorials = new HashMap<Long, Long>();
    public static long factorial(long x) {
        if (x < 0) return 0;
        if (x < factorials.size()) return factorials.get(x);

        long xf;
        if (x == 0) xf = 1;
        else xf = x * factorial(x - 1);

        factorials.put(x, xf);
        return xf;
    }
}