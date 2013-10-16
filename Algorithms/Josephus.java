public class Josephus
{
    /**
     * Stems from "game" where n people stand in a circle.  Every turn,
     * the k-th person is executed.  The last person is the "winner".
     * Given the parameters, we wish to find where to stand to win.
     * @param n number of people
     * @param k count
     * @return the winning spot
     */
    public static int run(int n, int k) {
        int r = 0;
        for (int i = 2; i <= n; i++)
            r = (r + k) % i;
        return r + 1;
    }
}
