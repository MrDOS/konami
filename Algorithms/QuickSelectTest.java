import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class QuickSelectTest
{
    @Test
    public void quickSelectTest() throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        int max = 20;
        
        for (int i = 0; i < max; i++)
            list.add(i);
        for (int i = 0; i < max; i++) {
            Integer k = QuickSelect.quickSelect(list, 0, max - 1, i + 1);
            assertNotNull(k);
            assertEquals(i, (int)k);
        }

        Integer[] test = {24, 29, 10, 3, 8, 48, 31, 30, 38, 27};
        list = Arrays.asList(test);
        max = list.size() - 1;

        assertNull(QuickSelect.quickSelect(list, 0, max, 0));
        assertEquals(3, (int)QuickSelect.quickSelect(list, 0, max, 1));
        assertEquals(48, (int)QuickSelect.quickSelect(list, 0, max, 10));
        assertEquals(10, (int)QuickSelect.quickSelect(list, 0, max, 3));
    }
}
