import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;

public class Points
{
    public enum Side {
        LEFT, ON, RIGHT
    }
    /**
     * Determine whether a point is left of, right of, or on a directional line.
     */
    public static Side side(Line2D.Double line, Point2D.Double point) {
        double side = (line.x2 - line.x1) * (point.y - line.y1)
                    - (line.y2 - line.y1) * (point.x - line.x1);
        return (side < 0) ? Side.RIGHT : (side > 0) ? Side.LEFT : Side.ON;
    }

    /**
     * Discover the convex hull of a collection of points. Uses QuickHull.
     * @param points the points
     * @return the points that form the convex hull
     */
    public static List<Point2D.Double> convexHull(List<Point2D.Double> points) {
        Point2D.Double left = new Point2D.Double(Double.MAX_VALUE, 0);
        Point2D.Double right = new Point2D.Double(Double.MIN_VALUE, 0);
        for (Point2D.Double point : points) {
            if (point.x < left.x)
                left = point;
            if (point.x > right.x)
                right = point;
        }

        ArrayList<Point2D.Double> hull = new ArrayList<Point2D.Double>();
        /* In order to retain compatibility with Polygons.area(), we build the
         * hull counter-clockwise. */
        hull.add(right);
        hull.add(left);
        int hullSize = 2;

        int startingIndex = 0;
        boolean pastFirst = false;
        Line2D.Double segment;
        Point2D.Double point;
        do {
            segment = new Line2D.Double(hull.get(startingIndex),
                hull.get((startingIndex + 1) % hullSize));
            point = furthestPoint(segment, points, Side.RIGHT);
            if (point != null) {
                hull.add(startingIndex + 1, point);
                hullSize++;
            }
            else {
                startingIndex = (startingIndex + 1) % hull.size();
                pastFirst = true;
            }
        } while (startingIndex != 0 || !pastFirst);

        return hull;
    }

    /**
     * Determine the furthest point in a group from a line.
     */
    public static Point2D.Double furthestPoint(Line2D.Double line,
                                               List<Point2D.Double> points,
                                               Side side) {
        Point2D.Double furthest = null;
        double distance = Double.MIN_VALUE;
        for (Point2D.Double point : points)
            if (side(line, point) == side && line.ptLineDist(point) > distance)
                furthest = point;
        return furthest;
    }
}