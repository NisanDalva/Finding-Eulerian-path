
public class test {

	public static void main(String[] args) {
		/**
		 *     1
		 *     /\
		 *    /  \
		 *   /    \
		 * 3 ------- 2 
		 *   |\   /|
		 *   | \ / |
		 *   |  \  |
		 *   | / \ |
		 *   |/   \|
		 * 4 ------- 5
		 */
		 
		
		BasicGraph graph = new BasicGraph(6);
		
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 3);
		graph.addEdge(2, 4);
		graph.addEdge(2, 5);
		graph.addEdge(3, 4);
		graph.addEdge(3, 5);
		graph.addEdge(4, 5);
		
		graph.printEulerTour();
	}

}
