//Rose Tabares - CS245 - Assignment02 - Best Road Trip Route - Dijkstra.java class

import java.util.*;

public class Dijkstra{
  
  public static final int V = 8; //Total number of vertices is 8
  
  // function calculate the minimum distance
	public int minDistance(int dist[], Boolean bool_set[]){
		// Initialize min value
       int min = Integer.MAX_VALUE, min_index = -1;

       for (int v = 0; v < V; v++)
           if (bool_set[v] == false && dist[v] <= min) {
               min = dist[v];
               min_index = v;
           }

      return min_index;
	}
	// function to display the Shortest Path from source
  public void printSolution(int dist[], int n){
    	System.out.println("Shortest distance from Source 'S' to all vertices");
       	for (int i = 0; i < V; i++) {
           	char ch;
           	if(i==0)
               ch = 'S';
           	else
               ch = (char)(i+64);
           	System.out.println("S to "+ch + " (S -> "+ch+") is :" + dist[i]);
       	}
  }

   	// function for Dijkstra's single source shortest path
  public void dijkstra(int graph[][], int src){
       // Output array to store the result
       int dist[] = new int[V];

       // value of bool_set array is true if their is shortest path from source to i
       Boolean bool_set[] = new Boolean[V];

       // Initialize all distances as INFINITE and stpSet[] as false
       for (int i = 0; i < V; i++) {
           dist[i] = Integer.MAX_VALUE;
           bool_set[i] = false;
       }

       // distance of source vertex from itself is always 0
       dist[src] = 0;

       // Find the shortest path for all vertices
       for (int count = 0; count < V - 1; count++) {
           // Choose the minimum distance vertex from the set of vertices which are not yet processed.
           // u is always equal to source in first iteration.
           int u = minDistance(dist, bool_set);

           // True means the picked vertex is processed
           bool_set[u] = true;

           // Update distance value of the adjacent vertices of the choose vertex.
           for (int v = 0; v < V; v++)

               // Update dist[v] only if is not in bool_set,
               // There is an edge from u to v, and total weight of path from
               //source to v through u is smaller than current value of dist[v]
               if (!bool_set[v] && graph[u][v] != 0 &&
               dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                   dist[v] = dist[u] + graph[u][v];
        }

       // print the constructed distance array
       printSolution(dist, V);
  }
  // Main method
  public static void main(String[] args){
    // Matrix representation of the given graph
    int graph[][] = new int[][] { { 0, 10, 7, 4, 0, 0, 0, 0 },
                                      { 0, 0, 0, 0, 8, 0, 0, 0 },
                                      { 0, 2, 0, 2, 8, 0, 0, 0 },
                                      { 0, 0, 0, 0, 0, 8, 20, 0 },
                                      { 0, 0, 0, 0, 0, 0, 0, 12 },
                                      { 0, 0, 12, 0, 0, 0, 0, 7 },
                                      { 0, 0, 0, 0, 0, 3, 0, 0 },
                                      { 0, 0, 0, 0, 0, 0, 4, 0 } };
       Dijkstra t = new Dijkstra();
       t.dijkstra(graph, 0);
   }
}
