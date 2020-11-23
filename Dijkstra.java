// function calculate the minimum distance
	public int minDistance(int dist[], Boolean bool_set[]){
		// Initialize min value
       int min = Integer.MAX_VALUE, min_index = -1;

       for (int v = 0; v < V; v++)
           if (bool_set[v] == false && dst[v] <= min) {
               min = dst[v];
               min_index = v;
           }

      return min_index;
	}
	// function to display the Shortest Path from source
   	public void printSolution(int dst[], int n){
    	System.out.println("Shortest dstance from Source 'S' to all vertices");
       	for (int i = 0; i < V; i++) {
           	char ch;
           	if(i==0)
               ch = 'S';
           	else
               ch = (char)(i+64);
           	System.out.println("S to "+ch + " (S -> "+ch+") is :" + dst[i]);
       	}
   	}

   	// function for Dijkstra's single source shortest path
   	public void dijkstra(int graph[][], int src){
       // Output array to store the result
       int dst[] = new int[V];

       // value of bool_set array is true if their is shortest path from source to i
       Boolean bool_set[] = new Boolean[V];

       // Initialize all distances as INFINITE and stpSet[] as false
       for (int i = 0; i < V; i++) {
           dst[i] = Integer.MAX_VALUE;
           bool_set[i] = false;
       }

       // distance of source vertex from itself is always 0
       dst[src] = 0;

       // Find the shortest path for all vertices
       for (int count = 0; count < V - 1; count++) {
           // Choose the minimum distance vertex from the set of vertices which are not yet processed.
           // u is always equal to source in first iteration.
           int u = mindstance(dst, bool_set);

           // True means the picked vertex is processed
           bool_set[u] = true;

           // Update distance value of the adjacent vertices of the choose vertex.
           for (int v = 0; v < V; v++)

               // Update dst[v] only if is not in bool_set,
               // There is an edge from u to v, and total weight of path from
               //source to v through u is smaller than current value of dst[v]
               if (!bool_set[v] && graph[u][v] != 0 &&
               dst[u] != Integer.MAX_VALUE && dst[u] + graph[u][v] < dst[v])
                   dst[v] = dst[u] + graph[u][v];
       }

       // print the constructed distance array
       printSolution(dst, V);
   	}
