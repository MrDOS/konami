import org.junit.Test;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class PointsTest
{
    @Test
    public void testSide() throws Exception {
        /* left of line */
        assertEquals(Points.Side.LEFT, Points.side(new Line2D.Double(0, 0, 2, 0), new Point2D.Double(1, 1)));
        assertEquals(Points.Side.LEFT, Points.side(new Line2D.Double(0, 0, 0, 2), new Point2D.Double(-1, 1)));
        /* right of line */
        assertEquals(Points.Side.RIGHT, Points.side(new Line2D.Double(0, 0, 2, 0), new Point2D.Double(1, -1)));
        assertEquals(Points.Side.RIGHT, Points.side(new Line2D.Double(0, 0, 0, 2), new Point2D.Double(1, 1)));
        /* on line */
        assertEquals(Points.Side.ON, Points.side(new Line2D.Double(0, 0, 2, 0), new Point2D.Double(1, 0)));
        assertEquals(Points.Side.ON, Points.side(new Line2D.Double(0, 0, 0, 2), new Point2D.Double(0, 1)));
    }
}