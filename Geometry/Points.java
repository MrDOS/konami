import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Points
{
    public enum Side {
        LEFT, ON, RIGHT
    }
    /**
     * Determine whether a point is left of, right of, or on a directional line.
     */
    public static Side side(Line2D line, Point2D point)
    {
        double side = (line.getP2().getX() - line.getP1().getX()) * (point.getY() - line.getP1().getY())
                    - (line.getP2().getY() - line.getP1().getY()) * (point.getX() - line.getP1().getX());
        return (side < 0) ? Side.RIGHT : (side > 0) ? Side.LEFT : Side.ON;
    }
}