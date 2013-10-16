import java.awt.geom.Point2D;

public class Polygons
{
    /**
     * Determine the area of a polygon.
     */
    public static double area(Point2D[] points) {
        double area = 0.0;
        for (int i = 0; i < points.length - 1; i++) {
            int j = (i + 1) % points.length;
            area += points[i].getX() * points[j].getY();
            area -= points[i].getY() * points[j].getX();
        }
        return Math.abs(area / 2.0);
    }
}