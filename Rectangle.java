import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle class that represents a rectangle Implements functionality for
 * performing operations between rectangles i.e: intersection, containment,
 * adjacency
 * 
 * NOTE: Rectangles are created with the assumption that the grid origin (0,0)
 * is equal to the top left of the grid assuming x and y = infinity: (0,0) is
 * the top left of the grid (x,0) is the top right of the grid (0,y) is the
 * bottom left of the grid (x,y) is the bottom right of the grid
 * 
 * @author Michael Zemsky
 */
public final class Rectangle {

	private final int width, height, x, y;
	private final Point topLeft, topRight, bottomLeft, bottomRight;
	private final Edge topEdge, bottomEdge, leftEdge, rightEdge;

	/**
	 * EXAMPLE: Rectangle(0,0,5,5) creates a 5 by 5 rectangle with the top left
	 * corner (0,0) and bottom right corner of (5,5)
	 * 
	 * @param x
	 *            - x coordinate of the top left corner of the rectangle
	 * @param y
	 *            - y coordinate of the top left corner of the rectangle
	 * @param width
	 *            - width of rectangle
	 * @param height
	 *            - height of rectangle
	 */
	public Rectangle(int x, int y, int width, int height) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;

		// the variables below don't necessarily have to be class level
		// variables,
		// however since they are used often it's easier & less messy this way.
		this.topLeft = new Point(getX(), getY());
		this.topRight = new Point(getX() + getWidth(), getY());
		this.bottomLeft = new Point(getX(), getY() + getHeight());
		this.bottomRight = new Point(getX() + getWidth(), getY() + getHeight());

		this.topEdge = new Edge(topLeft, topRight);
		this.bottomEdge = new Edge(bottomLeft, bottomRight);
		this.leftEdge = new Edge(topLeft, bottomLeft);
		this.rightEdge = new Edge(topRight, bottomRight);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * top edge: (x,y) -> (x+width,y)
	 * 
	 * @return Edge object representing top edge of the rectangle
	 */
	public Edge getTopEdge() {
		return topEdge;
	}

	/**
	 * bottom edge: (x,y+height) -> (x+width,y+height)
	 * 
	 * @return Edge object representing bottom edge of this rectangle
	 */
	public Edge getBottomEdge() {
		return bottomEdge;
	}

	/**
	 * left edge: (x,y) -> (x,y+height)
	 * 
	 * @return Edge object representing left edge of this rectangle
	 */
	public Edge getLeftEdge() {
		return leftEdge;
	}

	/**
	 * right edge: (x+width,y) -> (x+width,y+height)
	 * 
	 * @return Edge object representing right edge of this rectangle
	 */
	public Edge getRightEdge() {
		return rightEdge;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("[x=").append(x).append(",y=").append(y)
				.append(",width=").append(width).append(",height=")
				.append(height).append("]");
		return result.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		}
		Rectangle other = (Rectangle) o;

		return (width == other.getWidth() && height == other.getHeight()
				&& x == other.getX() && y == other.getY());
	}

	/**
	 * Checks if rectangle other is contained within this rectangle
	 * 
	 * @param other
	 *            - the other rectangle
	 * @return returns true if this rectangle fully contains the other rectangle
	 */
	public boolean contains(Rectangle other) {
		int x1 = x;
		int y1 = y;
		int x2 = x + width;
		int y2 = y + height;

		int x3 = other.getX();
		int y3 = other.getY();
		int x4 = x3 + other.getWidth();
		int y4 = y3 + other.getHeight();
		// checks if other rect is within this rect's bounds
		if ((x1 < x3 && y1 < y3) && (x2 > x4 && y2 > y4)) {
			return true;
		}
		return false;
	}

	/**
	 * checks if this rectangle and another rectangle are adjacent
	 * 
	 * @param other
	 *            - the rectangle to check adjacency with
	 * @return returns true if this rectangle is adjacent to the other
	 */
	public boolean isAdjacent(Rectangle other) {
		// if the other rect is contained within this rect then it can't be
		// truly adjacent
		if (this.contains(other)) {
			return false;
		}
		// if the other rect is exactly the same as this one, that isn't
		// adjacency...
		if (this.equals(other)) {
			return false;
		}
		boolean isAdj = false;
		/*
		 * always get the opposite edge from the other rect, since the other
		 * rect's left edge cannot be adj with this rect's left edge without one
		 * containing the other same applies for right & right, top & top,
		 * etc...
		 */
		boolean topEdgeAdj = this.topEdge.isEdgeOnEdge(other.getBottomEdge());
		boolean bottomEdgeAdj = this.bottomEdge
				.isEdgeOnEdge(other.getTopEdge());
		boolean leftEdgeAdj = this.leftEdge.isEdgeOnEdge(other.getRightEdge());
		boolean rightEdgeAdj = this.rightEdge.isEdgeOnEdge(other.getLeftEdge());

		if (topEdgeAdj) {
			isAdj = true;
		}

		// given two rects, only one side can be adjacent,
		// if multiple adjacency then one rect is within another, which doesnt
		// count as adjacency
		if (bottomEdgeAdj && isAdj) {
			return false;
		} else if (bottomEdgeAdj && !isAdj) {
			isAdj = true;
		}

		if (leftEdgeAdj && isAdj) {
			return false;
		} else if (leftEdgeAdj && !isAdj) {
			isAdj = true;
		}

		if (rightEdgeAdj && isAdj) {
			return false;
		} else if (rightEdgeAdj && !isAdj) {
			isAdj = true;
		}

		return isAdj;
	}

	/**
	 * Helper method for rectangle intersection
	 * 
	 * @return array of Points consisting the 4 corners of this rectangle
	 */
	public Point[] getCorners() {
		Point[] pArray = { topLeft, topRight, bottomLeft, bottomRight };
		return pArray;
	}

	/**
	 * Another helper method for intersection Checks if the given point is on
	 * any edge of this rectangle
	 * 
	 * @param p
	 *            - point to check
	 * @return returns true if the point is on any edge of this rectangle
	 */
	public boolean isPointOnRect(Point p) {
		if (topEdge.isPointOnEdge(p) || bottomEdge.isPointOnEdge(p)
				|| leftEdge.isPointOnEdge(p) || rightEdge.isPointOnEdge(p)) {
			return true;
		}
		return false;
	}

	/**
	 * checks if this rectangle and another intersect, and if they do finds &
	 * returns the points of which the two rectangles intersect
	 * 
	 * @param other
	 *            - the rectangle to check intersection with
	 * @return returns a list containing the points of intersection of this
	 *         rectangle and the other if there is no intersection, this method
	 *         returns an empty list
	 */
	public List<Point> intersection(Rectangle other) {
		List<Point> pointsOfIntersection = new ArrayList<>();
		// Check if the rectangle is adjacent, because if it is doesn't count as
		// intersecting
		if (this.isAdjacent(other)) {
			return pointsOfIntersection;
		}

		int x1 = this.getX();
		int y1 = this.getY();
		int x2 = x1 + this.getWidth();
		int y2 = y1 + this.getHeight();

		int x3 = other.getX();
		int y3 = other.getY();
		int x4 = x3 + other.getWidth();
		int y4 = y3 + other.getHeight();

		/*
		 * If two rectangles properly intersect (are not just adjacent), then
		 * that intersection creates a rectangle of its own So below we compute
		 * the bounds for rectangle that is a result of the intersection
		 */
		int x5 = Math.max(x1, x3);
		int y5 = Math.max(y1, y3);
		int x6 = Math.min(x2, x4);
		int y6 = Math.min(y2, y4);
		int iWidth = x6 - x5;
		int iHeight = y6 - y5;

		// true when there is no intersection between this and other
		if (x5 > x6 || y5 > y6) {
			return pointsOfIntersection;
		}

		// rectangle that is created as result of intersection
		Rectangle r3 = new Rectangle(x5, y5, iWidth, iHeight);

		// all possible points of intersection = corners of rectangle that
		// resulted from intersection of this rect & other
		Point[] r3Points = r3.getCorners();

		// ensures that we only return points that intersect on both rectangles
		for (Point p : r3Points) {
			if (this.isPointOnRect(p) && other.isPointOnRect(p)) {
				pointsOfIntersection.add(p);
			}
		}
		return pointsOfIntersection;
	}
}
