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
    public static Side side(Line2D.Double line, Point2D.Double point) {
        double side = (line.x2 - line.x1) * (point.y - line.y1)
                    - (line.y2 - line.y1) * (point.x - line.x1);
        return (side < 0) ? Side.RIGHT : (side > 0) ? Side.LEFT : Side.ON;
    }
}