import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PointTest
{
    @Test
    public void testHashCode() throws Exception {
    	assertEquals(true, new Point(0, 1).hashCode() == new Point(0, 1).hashCode());
    	assertEquals(false, new Point(0, 1).hashCode() == new Point(1, 0).hashCode());
    }

    @Test
    public void testEquals() throws Exception {
    	assertEquals(true, new Point(0, 1).equals(new Point(0, 1)));
    	assertEquals(false, new Point(0, 1).equals(new Point(1, 0)));
    }

    @Test
    public void testDistance() throws Exception {
    	assertEquals(0, new Point(0, 0).distance(new Point(0, 0)), 0.00001);
    	assertEquals(1, new Point(0, 0).distance(new Point(1, 0)), 0.00001);
    	assertEquals(Math.sqrt(2), new Point(0, 0).distance(new Point(1, 1)), 0.00001);
    	assertEquals(1, new Point(0, 0).distance(new Point(0, 1)), 0.00001);
    }
}