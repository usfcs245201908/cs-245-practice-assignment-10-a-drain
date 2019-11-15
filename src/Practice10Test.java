import java.util.List;
import java.util.Iterator;


public class Practice10Test {


	public Graph getGraph(int size) {
		return new GraphImplementation(size);
	}


	public boolean createGraphTest() {
		// Just create a graph and expect it not to blow up...
		try {
			Graph g = getGraph(3);
		} catch (Exception e) {
			return false;
		}
		return true;
	}


	public boolean negativeGraphTest() {
		// Just create a graph and get invalid edges...
		try {
			Graph g = getGraph(5);
			g.addEdge(4, 5);
		} catch (Exception e) {
			return true;
		}
		return false;
	}


	public boolean addEdgeTest() {
		try {
			Graph g = getGraph(3);

			g.addEdge(0, 1);
			g.addEdge(0, 2);

			// Check the neighbours...
			Iterator<Integer> neighbours = g.neighbors(0).iterator();
			boolean contains1 = false;
			boolean contains2 = false;
			int items = 0;
			while (neighbours.hasNext()) {
				int n = neighbours.next();
				++items;
				if (n == 1) contains1 = true;
				if (n == 2) contains2 = true;
			}

			return contains1 && contains2 && items == 2;
		} catch (Exception e) {
			return false;
		}
	}


	public boolean topologicalSortTest() {
		try {
			Graph g = getGraph(3);
			int [] expected = {0, 2, 1};

			g.addEdge(0, 1);
			g.addEdge(0, 2);
			g.addEdge(2, 1);
			List<Integer> schedule = g.topologicalSort();
			if (schedule.size() != 3)
				return false;
			for (int i = 0; i < expected.length; i++)
				if (schedule.get(i) != expected[i])
					return false;
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public void runTest () {
		int grade = 0;

		if (createGraphTest()) {
			grade += 10;
			System.out.println("[+10%] Passed create graph test");
		} else {
			System.out.println("[    ] Failed create graph test");
		}

		if (negativeGraphTest()) {
			grade += 10;
			System.out.println("[+10%] Passed negative graph test");
		} else {
			System.out.println("[    ] Failed negative graph test");
		}

		if (addEdgeTest()) {
			grade += 30;
			System.out.println("[+30%] Passed adding edge / check neighbours test");
		} else {
			System.out.println("[    ] Failed adding edge / check neighbours test");
		}

		// Topological sort will be checked by hand, because ...
		if (topologicalSortTest()) {
			grade += 50;
			System.out.println("[+50%] Passed topological sort test");
		} else {
			System.out.println("[    ] Failed topological sort test");
		}

		System.out.println("Starting point for this assignment: " + grade + "%");
	}

	public static void main(String[] args) {
		Practice10Test test = new Practice10Test();
		test.runTest();
	}

}
