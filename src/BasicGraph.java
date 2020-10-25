import java.util.ArrayList;

public class BasicGraph {
	private int vertices; // num of vertices
    private ArrayList<Integer>[] adj; // adjacency list
  
    @SuppressWarnings("unchecked")
	BasicGraph(int numOfVertices) {
        this.vertices = numOfVertices;
        adj = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++)
            adj[i] = new ArrayList<>();
    }
    
    
    /**
     * add edge from u to v
     * @param u
     * @param v
     */
    public void addEdge(Integer u, Integer v) {
        adj[u].add(v);
        adj[v].add(u);
    }
    
    /**
     * remove edge u-v
     * @param u
     * @param v
     */
    public void removeEdge(Integer u, Integer v) {
        adj[u].remove(v);
        adj[v].remove(u);
    }
    

    public void printEulerTour() {
    	//find a vertex with odd degree
    	Integer u = 0; 
    	for (int i = 0; i < vertices; i++) {
    		if (adj[i].size() % 2 == 1) {
    			u = i;
    			break;
    		}
    	}
    	
    	//print tour starting from odd degree vertex
    	printEulerUtil(u);
    	System.out.println();
    }

    /**
     * Print Euler tour starting from vertex u
     * @param u
     */
    private void printEulerUtil(Integer u) {
    	for (int i = 0; i < adj[u].size(); i++) {
    		Integer v = adj[u].get(i);

    		//check if edge u-v is valid
    		if (isValidNextEdge(u, v)) {
    			System.out.println(u + "-->" + v); 

    			//no need this edge
    			removeEdge(u, v);  
    			printEulerUtil(v); 
    		}
    	}
    }

    /**
     * check if edge u-v can be considered as next edge in Euler tour 
     * @param u
     * @param v
     * @return
     */
    private boolean isValidNextEdge(Integer u, Integer v) {
    	/*
    	 * the edge u-v is valid in one of the following two cases: 
    	 * 1. if v is the only adjacent vertex of u or size of adjacent vertex list is 1
    	 */
    	if (adj[u].size() == 1)
    		return true;
    	/*
    	 * 2. if there are multiple adjacents, then u-v is not a bridge.
    	 * Do following steps to check if u-v is a bridge:
    	 * a. count of vertices reachable from u 
    	 */
    	boolean[] isVisited = new boolean[this.vertices];
    	int count1 = dfsCount(u, isVisited);

    	/*
    	 * b. Remove edge (u, v), and after removing the edge, count vertices reachable from u.
    	 */
    	removeEdge(u, v);
    	isVisited = new boolean[this.vertices];
    	int count2 = dfsCount(u, isVisited);

    	/*
    	 * c. Add the edge back to the graph.
    	 */
    	addEdge(u, v);
    	return (count1 > count2) ? false : true;
    } 

    /**
     * DFS based function to count reachable vertices from v 
     * @param v
     * @param isVisited
     * @return
     */
    private int dfsCount(Integer v, boolean[] isVisited) {
    	//mark the current node as visited 
    	isVisited[v] = true;
    	int count = 1;
    	//recur for all vertices adjacent to this vertex
    	for (int adj : adj[v]) {
    		if (!isVisited[adj])
    			count = count + dfsCount(adj, isVisited);
    	}
    	return count;
    }
}
