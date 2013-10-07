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
		return Integer.reverseBytes(this.x) ^ this.y;
	}

	@Override
	public boolean equals(Object p) {
		return (p instanceof Point
			&& this.x == p.x
			&& this.y == p.y);
	}
}