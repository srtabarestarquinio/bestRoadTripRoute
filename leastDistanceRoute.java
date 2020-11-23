//Rose Tabares - CS245 - Assignment02 - Best Road Trip Route
import java.util.*;

public class leastDistanceRoute{
	//constructor:
	public leastDistanceRoute(){

	}
	private Lists<leastDistanceRoute> list;
	public leastDistanceRoute(Lists<leastDistanceRoute> newList){
		list = newList;
	}
  
	public List<String> route(String starting_city, String ending_city, List<String> attractions){
 	//Creates empty list of locations of size of List<String> attractions + 2 (for starting and ending city) called List<String> locationsList
	

	//Add starting_city in position 0 and ending_city in position -1 of locationsList attractionsLocations( attractions, locationsList )
	


	//Use Dijkstra’s algorithm to calculate shortest distances and add them to List<String> route = Dijkstra( locationsList )
	


	//return List<String> route representing the route the user should take, ex
		//Grand Canyon AZ, Bemidji MN, New York NY, etc
  
  }

  public void attractionsLocations( List<String> attractions, List<String> locationsList ){
  	//Loops through List<String> attractions and finds the location of that attraction by reading attractions.csv and add them to locationsList
  	for (int i=0; i<attractions.getSize(); i++){

  	}
  }

  public float calcDistance(String location1, String location2){
  	//use roads.csv to calculate distances between locations
	

	// Loop over roads.csv until find row that contains both locations and save the 3rd column to get the miles in between them
	

	// return number of miles as distance

  }

  public List<String> Dijkstra( List<String> locationsList ){
  	//Vertex = locationsList
  	
  	//Known = start with all as false
 	
 	//Path = start with all as -1, then leave starting_city or locationsList[0] as -1 path because it will be starting point and all others will go from there, and set this with
	
	//Known=true and Cost=0
	
	//Cost = start with all cost=-1, then use calcDistance function to get costs: float cost = calcDistance(location1, location2)
	
	//Implement Dijkstra’s Algorithm until all Known are true and go backwards with Path and Vertex to create route and set to List<String> routeDijkstra
	



	//return routeDijkstra
  }

  public static void main(String[] args){
 	//Take in starting, ending city and attraction
  	//Assume the user will supply a starting city (San Francisco, for example) and an ending city (eg. Miami). In addition, the user will supply zero, one or more events or places of interest by name
  	leastDistanceRoute minRoute = new leastDistanceRoute();
  	Scanner input=new Scanner(System.in);

  	System.out.println("Welcome to Road Trip Planning, your best place to find the best route that will minimize the amount of miles you will need to travel to get to all of your places of interest!");
  	System.out.println("Please enter the following information: ");
	System.out.println("Starting city:");
  	String starting_city = input.nextLine();
  	System.out.println("Ending city:");
  	String ending_city = input.nextLine();
  	
  	Lists<leastDistanceRoute> temp = new ArrayList<String>();

	//Store attractions.csv and roads.csv in file variables


	//Create List<String> route and store return value from route(starting_city, ending_city, attraction) function


	//Print List<String> route
  
  }

}
