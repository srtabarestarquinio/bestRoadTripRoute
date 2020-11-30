//Rose Tabares - CS245 - Assignment02 - Best Road Trip Route - Dijkstra.java class

import java.util.*;

public class DijkstraAlgorithm{
  //Dijkstra algorithm to find least cost route (minimun distance route):
  private ArrayList<Vertex> vertex;//ArrayList of vertices
  private ArrayList<ArrayList<Edge>> edge;//ArrayList of edges

  //constructor for DijkstraAlgorithm class:
  public DijkstraAlgorithm(){
      vertex = new ArrayList<Vertex>();//create arraylist for vertex
      edge = new ArrayList<ArrayList<Edge>>();//create arraylist for edge
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
  public ArrayList<Vertex> getVertex(){
    return vertex;
  }
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


  
}
