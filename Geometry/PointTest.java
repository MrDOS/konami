import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PointTest
{
    @Test
    public void testHashCode() throws Exception {
    	assertEquals(true, new Point(0, 1).hashCode() == new Point(0, 1).hashCode());
    	assertEquals(false, new Point(1, 0).hashCode() == new Point(1, 0).hashCode());
    }

    @Test
    public void testEquals() throws Exception {
    	assertEquals(true, new Point(0, 1).equals(new Point(0, 1)));
    	assertEquals(false, new Point(0, 1).equals(new Point(1, 0)));
    }
}