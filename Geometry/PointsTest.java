import org.junit.Test;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    @Test
    public void testConvexHull() throws Exception {
        List<Point2D.Double> diamond = Arrays.asList(
            new Point2D.Double(1, 0),
            new Point2D.Double(0, 1),
            new Point2D.Double(-1, 0),
            new Point2D.Double(0, -1)
        );

        List<Point2D.Double> square = Arrays.asList(
            new Point2D.Double(1, 1),
            new Point2D.Double(-1, 1),
            new Point2D.Double(-1, -1),
            new Point2D.Double(1, -1)
        );

        List<Point2D.Double> noisyDiamond = new ArrayList<Point2D.Double>(diamond);
        noisyDiamond.add(new Point2D.Double(0, 0));

        List<Point2D.Double> noisySquare = new ArrayList<Point2D.Double>(square);
        Random random = new Random();
        for (int i = 0; i < 100; i++)
            noisySquare.add(new Point2D.Double(random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1));

        assertEquals(diamond, Points.convexHull(diamond));
        assertEquals(square, Points.convexHull(square));
        assertEquals(diamond, Points.convexHull(noisyDiamond));
    }
}