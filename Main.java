import java.util.List;

/**
 * Main class - just runs some simple unit tests & examples to make sure
 * everything works
 * 
 * @author Michael Zemsky
 */
public class Main {

	public static void main(String[] args) {
		// 5x5 rect: (0,0) -> (5,5)
		Rectangle a = new Rectangle(0, 0, 5, 5);
		// 5x5 rect: (2,2) -> (7,7)
		Rectangle b = new Rectangle(2, 2, 5, 5);
		// a and b should have 2 pts of intersection, should not contain
		// eachother and should not be adjacent
		runTests(a, b, 2, false, false, false);

		a = new Rectangle(0, 0, 10, 10);
		b = new Rectangle(3, 3, 3, 3);
		// should have 0 pts of intersection & a should contain b
		runTests(a, b, 0, true, false, false);

		a = new Rectangle(0, 0, 5, 5);
		b = new Rectangle(0, 5, 5, 1);
		// a and b should be adj on the bottom edge of a and top edge of b
		runTests(a, b, 0, false, false, true);
		// b and a should be adj, since a and b are adj
		runTests(b, a, 0, false, false, true);
	}

	/**
	 * Runs all three simple unit tests & compares the actual results to the
	 * expected results Although native java asserts only work if you enable
	 * them by adding -ea to JVM arguments
	 * 
	 * @param r1
	 *            - instance of Rectangle
	 * @param r2
	 *            - another instance of Rectangle
	 * @param shouldIntersect
	 *            - number of points that result from the intersection of r1 and
	 *            r2
	 * @param shouldContain
	 *            - whether or not r1 should contain r2
	 * @param shouldContainReverse
	 *            - whether or not r2 should contain r1
	 * @param shouldBeAdj
	 *            - whether or not r1 and r2 should be adjacent
	 */
	public static void runTests(Rectangle r1, Rectangle r2,
			int shouldIntersect, boolean shouldContain,
			boolean shouldContainReverse, boolean shouldBeAdj) {

		List<Point> intersect = testIntersection(r1, r2);
		assert intersect.size() == shouldIntersect;

		boolean isContained = testContainment(r1, r2);
		assert isContained == shouldContain;

		isContained = testContainment(r2, r1);
		assert isContained == shouldContainReverse;

		boolean isAdj = testAdjacency(r1, r2);
		assert isAdj == shouldBeAdj;
	}

	/**
	 * super simple unit test for rectangle intersection
	 */
	public static List<Point> testIntersection(Rectangle r1, Rectangle r2) {
		System.out.println("Testing intersection on Rects:");
		System.out.println("Rect1: " + r1.toString());
		System.out.println("Rect2: " + r2.toString());

		List<Point> intersect = r1.intersection(r2);
		if (intersect.isEmpty()) {
			System.out.println("NO INTERSECTION FOUND!");
		} else {
			System.out.println("INTERSECTION FOUND AT POINTS:");
		}
		for (Point p : intersect) {
			System.out.println(p.toString());
		}
		System.out.println();
		return intersect;
	}

	/**
	 * super simple unit test for rectangle containment
	 */
	public static boolean testContainment(Rectangle r1, Rectangle r2) {
		System.out.println("Testing containment on Rects:");
		System.out.println("Rect1: " + r1.toString());
		System.out.println("Rect2: " + r2.toString());

		boolean isContained = r1.contains(r2);

		if (isContained) {
			System.out.println("Rect2 is inside of Rect1!");
		} else {
			System.out.println("Rect2 is not inside of Rect1!");
		}
		System.out.println();
		return isContained;
	}

	/**
	 * super simple unit test for rectangle adjacency
	 */
	public static boolean testAdjacency(Rectangle r1, Rectangle r2) {
		System.out.println("Testing adjacency on Rects:");
		System.out.println("Rect1: " + r1.toString());
		System.out.println("Rect2: " + r2.toString());

		boolean isAdj = r1.isAdjacent(r2);

		if (isAdj) {
			System.out.println("Rect2 is adjacent to Rect1!");
		} else {
			System.out.println("Rect2 is not adj to Rect1!");
		}
		System.out.println();
		return isAdj;
	}

}
