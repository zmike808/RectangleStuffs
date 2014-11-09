/**
 * Point class that is used to represent a Point, however in this implementation
 * it is only used to represent a corner of a rectangle
 * 
 * @author Michael Zemsky
 */
public final class Point {
	private final int x;
	private final int y;

	/**
	 * @param x
	 *            - x coordinate for this point
	 * @param y
	 *            - y coordinate for this point
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("(").append(this.getX()).append(",").append(this.getY())
				.append(")");
		return result.toString();
	}

	/**
	 * checks if this point is between point a & point b
	 * 
	 * @param Point
	 *            a - start point
	 * @param Point
	 *            b - end point
	 * @return returns true if this point is between point a and point b.
	 */
	public boolean isBetweenPoints(Point a, Point b) {
		int x1 = Math.min(a.getX(), b.getX());
		int y1 = Math.min(a.getY(), b.getY());
		int x2 = Math.max(a.getX(), b.getX());
		int y2 = Math.max(a.getY(), b.getY());

		int cx = this.getX();
		int cy = this.getY();
		if (cx >= x1 && cx <= x2 && cy >= y1 && cy <= y2) {
			return true;
		}
		return false;
	}
}