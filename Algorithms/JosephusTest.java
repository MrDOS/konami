import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class JosephusTest
{
    @Test
    public void runTest() throws Exception {
        assertEquals(31, Josephus.run(41, 3));
        assertEquals(36, Josephus.run(50, 10));
        assertEquals(65, Josephus.run(95, 36));
        assertEquals(1, Josephus.run(2, 2));
    }
}
