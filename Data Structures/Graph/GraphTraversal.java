import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 
 * @author Sagar Rathod.
 * 
 * GraphTraversal using Adjacency List as graph representation.
 * 
 */
public class GraphTraversal {
	
	/**
	 *  [1] ++++++++++++ [2]
	 *   +				  +   +
	 *   +				  +      +
	 *   +				  +      [3]
	 * 	 +				  +		 +
	 *   +			  	  +   +
	 *	[4]				  [5]
	 */
	
	//DFS(1) == 1 -> 2-> 3-> 5-> 4
	//DFS(3) == 3 -> 2-> 1-> 5-> 4
	
	
	//BFS(1) == 1 -> 2-> 4-> 3-> 5
	
	static int N = 5;
	//Adjancy List
	// Skip Node 0, start from Node 1
	 static	int adjacencyList[][] = {  {},
							 {2,4},
							 {1,3,5},
							 {2, 5},
							 {1},
							 {2, 3}
			              };
			
		static boolean visited[] = new boolean[N + 1];
	
	public static void main(String[] args) {
	
			//dfs(1);
			//dfs(3);
			bfs(1);
	}
	
	/**
	 * Depth First Search
	 * DFS begins at a starting node, and proceeds to all other nodes that are
	 * reachable from the starting node using the edges of the graph.
	 * Time Complexity O(n + m) where n is no. of nodes and m is no. of Edges
	 * @param s
	 */
	static void dfs(int s) {
		if(visited[s])
			return;
		visited[s] = true;
		System.out.print(s + " ");
		for(int i: adjacencyList[s]) {
			dfs(i);
		}
	}
	
	/**
	 * Breadth First Search
	 * BFS visits the nodes in increasing order of their distance from the starting
	 * node. Thus we can calculate the distance from starting node to all other
	 * nodes using BFS.
	 * Time Complexity O(n + m) where n is no. of nodes and m is no. of Edges
	 * @param x
	 */
	static void bfs(int x) {
		
		visited[x] = true;
		System.out.print("\nNode\tDistance");
		System.out.print("\n"+x + "\t" + 0);
		int distance[] = new int[visited.length];
		
		Queue<Integer> queue = new ArrayBlockingQueue<Integer>(visited.length);
		queue.add(x);
		
		while(!queue.isEmpty()) {
			int s = queue.poll();
			
			for(int u: adjacencyList[s]) {
			
				if(visited[u])
					continue;
				
				visited[u] = true;
				distance[u] = distance[s] + 1;
				System.out.print("\n" + u +"\t" + distance[u]);
				queue.add(u);				
			}
		}
		
	}

}
