import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class PolygonsTest
{
    @Test
    public void testAreaRectangle() throws Exception {
        assertEquals(1, Polygons.area(new Point2D[] {
            new Point2D.Double(0, 0),
            new Point2D.Double(1, 0),
            new Point2D.Double(1, 1),
            new Point2D.Double(0, 1)
        }), 0);

        assertEquals(4, Polygons.area(new Point2D[] {
            new Point2D.Double(0, 0),
            new Point2D.Double(2, 0),
            new Point2D.Double(2, 2),
            new Point2D.Double(0, 2)
        }), 0);
    }
}