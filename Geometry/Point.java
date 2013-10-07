import java.util.Objects;

public class Point
{
	public final double x;
	public final double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		double hash = 7;
		hash = 71 * hash + this.x;
		hash = 71 * hash + this.y;
		return (int) hash;
	}

	@Override
	public boolean equals(Object p) {
		return (p instanceof Point
			&& this.x == ((Point) p).x
			&& this.y == ((Point) p).y);
	}

	public double distance(Point p) {
		return Math.sqrt((p.x - this.x) * (p.x - this.x)
			+ (p.y - this.y) * (p.y - this.y));
	}
}