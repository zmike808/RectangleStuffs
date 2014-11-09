/**
 * Edge class that represents a line between two points However in this
 * implementation it is only used to represent an edge of a rectangle
 * 
 * @author Michael Zemsky
 */
public final class Edge {
	private final Point start;
	private final Point end;

	/**
	 * @param start
	 *            - starting point of edge
	 * @param end
	 *            - ending point of edge
	 */
	public Edge(Point start, Point end) {
		this.start = start;
		this.end = end;
	}

	public Point getStartPoint() {
		return start;
	}

	public Point getEndPoint() {
		return end;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("[").append(start.toString()).append("-->")
				.append(end.toString()).append("]");
		return result.toString();
	}

	/**
	 * checks if point p lies on this edge
	 * 
	 * @param p
	 *            - the point to check
	 * @return returns true if point p lies on this edge; otherwise returns
	 *         false
	 */
	public boolean isPointOnEdge(Point p) {
		if (p.isBetweenPoints(start, end)) {
			return true;
		}
		return false;
	}

	/**
	 * checks if this edge partially or fully contains edge e
	 * 
	 * @param e
	 *            - the edge to check against this edge
	 * @return true if Edge e is a subedge of or equal to this Edge; otherwise
	 *         returns false
	 */
	public boolean isEdgeOnEdge(Edge e) {
		if (isPointOnEdge(e.getStartPoint()) && isPointOnEdge(e.getEndPoint())) {
			return true;
		}
		return false;
	}
}