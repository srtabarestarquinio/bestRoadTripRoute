//Rose Tabares - CS245 - Assignment02 - Best Road Trip Route - CalcRoute.java class
import java.util.*;

public class CalcRoute
{
    private ArrayList<Vertex> vertex;//ArrayList of vertices
    private ArrayList<ArrayList<Edge>> edge;//ArrayList of edges
    private LinkedList<Integer> attraction;//list of vertices to visit on the way

    //constructor for CalcRoute class:
    public CalcRoute(){
        vertex = new ArrayList<Vertex>();//create arraylist for vertex
        edge = new ArrayList<ArrayList<Edge>>();//create arraylist for edge
        attraction = new LinkedList<Integer>();//create arraylist for attraction
    }

    //helper classes to create graph:
    private class Edge{
        public int v1,v2; // index of the vertices
        int distance; // distance in miles
    }
    private class Vertex{
        public String city;//string to store the city name
        public ArrayList<String> attractions; // list of attractions to visit
        //constructor:
        public Vertex(String name){
            city = name;
            //make a new arrayList with the attractions:
            attractions = new ArrayList<String>();
        }
    }

    //function to find the index in the vertex list:
    public int getIndex(String name){
        for(int v = 0; v < vertex.size(); v++){
            if(vertex.get(v).city.equalsIgnoreCase(name)){
                return v;
            }
        }
        //if cannot find index in vertex list, then user spelled place wrong or does not exist in file, therefore return -1:
        return -1;
    }
 
    //function to test if a vertex with the name exists:
    public boolean checkVertex(String name){
        return (getIndex(name) >= 0);//the name of the vertex
    }

    //function to add edge
    public void addEdge(String vName1, String vName2, int dist){
        //if vertex1 is not edge:
        if(!checkVertex(vName1)){
            Vertex v1 = new Vertex(vName1);//add vertex
            vertex.add(v1);//add vertex
            edge.add(new ArrayList<Edge>());//add an ArrayList of edges
        }
        int v1 = getIndex(vName1);
        ArrayList<Edge> edges1 = edge.get(v1);

        //if vertex2 is not edge:
        if(!checkVertex(vName2)){
            Vertex v2 = new Vertex(vName2);//add vertex
            vertex.add(v2);
            edge.add(new ArrayList<Edge>());//add the edge to an ArrayList
        }
        int v2 = getIndex(vName2);
        ArrayList<Edge> edges2 = edge.get(v2);


        Edge e1 = new Edge();//create an edge
        e1.v1 = v1;//set the first vertex equal to vertex 1
        e1.v2 = v2;//set second vertex to vertex 2
        e1.distance = dist;//set the distance to the value of dist

        Edge e2 = new Edge();//create second edge
        e2.v1 = v2;//set the first vertex equal to vertex 2
        e2.v2 = v1;//set the first vertex equal to vertex 1
        e2.distance = dist;//set the distance to the value of dist

        edges1.add(e1);//add the new edge to the list of edges
        edges2.add(e2);//add the new edge to the list of edges
    }

    //function to add the list of attractions of the city
    public boolean addPlace(String city, String place){
        //if it isn't a vertex:
        if(!checkVertex(city)){
            return false;
        }

        int vIndex = getIndex(city);//set the vertex to the index of the city
        vertex.get(vIndex).attractions.add(place);//get the information and add it to the attractions list

        return true;
    }

    //function to add the name and index of the city with the attractions to add them to the list:
    public boolean placeRoute(String place){
        for(int i = 0; i < vertex.size(); i++){
            Vertex v = vertex.get(i);//get the information
            //if the attraction is in that place
            if(v.attractions.contains(place)){
                attraction.add(i);//add it to the attractions list
                return true;
            }
        }
        return false;
    }

    //Dijkstra algorithm to find least cost route (minimun distance route):
    public void Dijkstra(int start, ArrayList<Integer> distance, ArrayList<Integer> previous){
        ArrayList<Integer> list = new ArrayList<Integer>();//intiate an ArrayList to store the information

        int ai,bi;//intialize variables
        int alternate,minDistance,curDistance;//intialize variables
        int a,b;//intialize variables
        for(b = 0; b < vertex.size(); b++){
            distance.add(-1);//add -1 to the distance arraylist
            previous.add(-1);//aff -1 to the previous arraylist
            list.add(b);//add b to the arraylist
        }
        distance.set(start,0);//set the distance to the value of start and 0

        while(!list.isEmpty()){
            ai = minDistance = -1;//set ai to the min distance -1
            for(bi = 0; bi < list.size(); bi++){
                b = list.get(bi);//get bi from the list and set it to b
                curDistance = distance.get(b);//set curdistance to the distance that you get from b
                //if the current disnace is <0 and ai < 0 or the current< min distance:
                if(curDistance >= 0 && (ai < 0 || curDistance < minDistance)){
                    ai = bi;//set ai to equal bi
                    minDistance = curDistance;//and min distance euqal to current distance
                }
            }

            a = list.get(ai);//set a to list.get ai
            list.remove(ai);//remove ai fromt he list

            //loop through the size of edge.get(a):
            for(bi = 0; bi < edge.get(a).size(); bi++){
                b = edge.get(a).get(bi).v2;//set b to edge.get(a)

                if(list.contains(b)){
                    alternate = distance.get(a) + edge.get(a).get(bi).distance;//set alternate to distance at a + the distance at bi
                    //if the distance at b is <0 or the alternate is < the distance of b:
                    if(distance.get(b) < 0 || alternate < distance.get(b)){
                        distance.set(b,alternate);//set the distance to b and alternate
                        previous.set(b,a);//set previous to a and b
                    }
                }
            }
        }
    }

    //function to get the shortest path:
    private LinkedList<Integer> shortestPath(int start, int end, ArrayList<Integer> previous){
        LinkedList<Integer> p = new LinkedList<Integer>();//linkedlist to store the path

        int a = end;//a to the end
        //if a equals the start or previous.get(a) = 0:
        if(a == start || previous.get(a) >= 0){
            while(a >= 0){
                p.addFirst(a);//add a to the path
                a = previous.get(a);//set a to previous.get(a)
            }
        }
        return p;
    }

    //function that returns a list of all the variations:
    private static LinkedList<LinkedList<Integer>> var(LinkedList<Integer> l){
        LinkedList<LinkedList<Integer>> list = new LinkedList<LinkedList<Integer>>();//make a linked list

        if(l.size() == 1){
            // duplicate the array am to not affect it further
            LinkedList<Integer> l1 = new LinkedList<Integer>();//duplicate the array
            l1.addLast(l.get(0));//add the value at 0
            list.addLast(l1);//add l1 to the list
        }
        else{
            for(int i = 0; i < l.size(); i++){
                LinkedList<Integer> l1 = new LinkedList<Integer>();//intiate l1

                //add all values to l1:
                for(int j = 0; j < l.size(); j++){
                    if(j != i){
                        l1.add(l.get(j));//add the information from l.get(j) to l1
                    }
                }
                LinkedList<LinkedList<Integer>> list1 = var(l1);//recursivly call the function
                //add the elements to the end of each variation:
                for(int j = 0; j < list1.size(); j++){
                    LinkedList<Integer> array = list1.get(j);//intialize the array
                    array.add(0,l.get(i));//add the information from l.get(i) to the 0 position
                    list.add(array);//add the array to the list
                }
            }
        }
        return list;
    }

    //function to find the shortest route from the given information:
    public LinkedList<String> findRoute(String starting_city, String ending_city){
        int start = getIndex(starting_city);//set the start to the index of the starting city
        int end = getIndex(ending_city);//set the end to the index of the ending city

        boolean places = !attraction.isEmpty();//if there are no attraction in the list
        if(!places)//if no attractions
            attraction.add(start);//add the start
        LinkedList<LinkedList<Integer>> lp = var(attraction);//call var on attractions
        int nump = attraction.size();//set the number of places to attractions size

        if(places){
            attraction.add(start);//add the start to the attractions list
            attraction.add(end);//add the end to the attractions list
        }

        int startNum = nump;//set start num to nump
        int endNum = nump + 1;//set end num to nump +1
        if(!places){
            startNum = 0;//set start num to 0
            endNum = 1;//set end num to 1
        }


        ArrayList<ArrayList<Integer>> dist1 = new ArrayList<ArrayList<Integer>>();//intialze arraylist
        ArrayList<ArrayList<Integer>> prev1 = new ArrayList<ArrayList<Integer>>();//intialze arraylist
        //loop through the attractions
        for(int i = 0; i < attraction.size(); i++){
            ArrayList<Integer> dist = new ArrayList<Integer>();//intialize arraylist
            ArrayList<Integer> prev = new ArrayList<Integer>();//intialize arraylist
            Dijkstra(attraction.get(i),dist,prev);//call Dijkstra on a dist and prev
            dist1.add(dist);//add dist to dist1
            prev1.add(prev);//add prev to prev1
        }
        int dist = -1;//set dist to -1
        int min = -1;//set min to -1
        // loop through lp:
        for(int i = 0; i < lp.size(); i++){
            LinkedList<Integer> var = lp.get(i);//linked list to store the variations

            int curr_dist = 0;//set current distance to 0

            int end2 = var.get(0);//set end2 to the variation.get(0) the first path
            curr_dist += dist1.get(startNum).get(end2);//set curr_dist to dist1.get the startNum and the end2

            //loop through the variations (the next paths):
            for(int j = 0; j < var.size() - 1; j++){
                int p1 = var.get(j);//set p1 to var.get(i)
                int p2 = var.get(j+1);//set p2 to the var +1
                int p1i = attraction.indexOf(p1);//set p1 index to the index of p1
                int p2i = attraction.indexOf(p2);//set p2 index to the index of p2

                curr_dist += dist1.get(p1i).get(p2);//set current distance to current distance + the distance of p1i.get(p2)
                LinkedList<Integer> path = shortestPath(p1,p2,prev1.get(p1i));
            }

            end2 = var.get(var.size()-1);//get the latest path
            curr_dist += dist1.get(startNum).get(end2);//update the distance

            LinkedList<Integer> bestpath = shortestPath(end,end2,prev1.get(endNum));//intialize the best path
            Collections.reverse(bestpath);//reverse it to be in the right order
            //if n is 0 or distance is > current distance:
            if(i == 0 || dist > curr_dist){
                dist = curr_dist;//set distance to current
                min = i;//set min to end
            }
        }

        LinkedList<String> finalRoute = new LinkedList<String>();//intialize final path
        LinkedList<Integer> varMin = lp.get(min);//intialize the min variation
        varMin.addFirst(start);//add the start
        varMin.addLast(end);//add the end

        //loop through varMin:
        for(int i = 0; i < varMin.size()-1; i++){
            int p1 = varMin.get(i);//set p1 to varMin at i
            int p2 = varMin.get(i+1);//set p2 to varMin at i +1
            int p1i = attraction.indexOf(p1);//set p1i to the index of p1
            int p2i = attraction.indexOf(p2);//set p2i to the index of p2
            LinkedList<Integer> routefinal = shortestPath(p1,p2,prev1.get(p1i));//set path final to path of p1,p1,prev1.get(p1i)
            //loop through the final path
            for(int j = 0; j < routefinal.size()-1; j++)
                finalRoute.addLast(vertex.get(routefinal.get(j)).city);//add the information to the path final
        }
        finalRoute.addLast(vertex.get(end).city);//add the ending city

        return finalRoute;
    }  
}
