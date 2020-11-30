//Rose Tabares - CS245 - Assignment02 - Best Road Trip Route
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;

public class leastDistanceRoute{
  
	public static ArrayList<Integer> distance = new ArrayList();
	//read files
	public static LinkedList<String> route(String start, String end, ArrayList<String> attractions) throws IOException{
		//make object of CalcRoute class
		CalcRoute calcRoute = new CalcRoute();

		String csvSplit = ",";
		String line = "";
		//use bufferedreader to read the roads file:
        BufferedReader roadsFile = new BufferedReader(new FileReader("roads.csv"));
        //while loop line until null (reach end of file):
        while ((line = roadsFile.readLine()) != null)
        {
            String[] road = line.split(",");//spilt the file at the commas
            calcRoute.addEdge(road[0],road[1],Integer.parseInt(road[2]));//add the edges
            distance.add(Integer.parseInt(road[2]));
        }
        //close the bufferreader
        roadsFile.close();

        //make new bufferreader with the attractions file:
        BufferedReader attractionsFile = new BufferedReader(new FileReader("attractions.csv"));
        while ((line = attractionsFile.readLine()) != null)//while the line is not null
        {
            String[] attraction = line.split(",");//split at the comma
            calcRoute.addPlace(attraction[1],attraction[0]);//add places as verticies
        }
        //close the bufferreader
        attractionsFile.close();


        // for(String place: attractions)//loop through the file
        //     calcRoute.placeRoute(place);//add selected cities between the 2 main cities
        for(int i=0; i<attractions.size(); i++){
        	calcRoute.placeRoute(attractions.get(i));
        }

        LinkedList<String> route = calcRoute.findRoute(start,end);//find the route

        return route;//return the route
	}


	// public List<String> route(String starting_city, String ending_city, List<String> attractions){
 // 		//Creates empty list of locations of size of List<String> attractions + 2 (for starting and ending city) called List<String> locationsList
	// 	int numLocations = attractions.getSize() + 2;
	// 	List<String> locationsList = new List(numLocations);
	// 	//Add starting_city in position 0 and ending_city in position -1 of locationsList attractionsLocations( attractions, locationsList )
	// 	locationsList.add(0, starting_city);
	// 	locationsList.add(numLocations-1, ending_city);
	// 	return locationsList;
	// 	//add locations of attractions to locationList with function attractionslocations()
	// 	// locationsList = attractionsLocations(attractions, locationsList);
	// 	//Use Dijkstra’s algorithm to calculate shortest distances and add them to List<String> route = Dijkstra( locationsList )
	// 	// List<String> minRoute = new List(numLocations);
	// 	// minRoute = Dijkstra(locationsList);

	// 	//return String[] route representing the route the user should take, ex
	// 		//Grand Canyon AZ, Bemidji MN, New York NY, etc
 //  		// return minRoute;
 //  		// return locationsList;
	// }

	// public List<String> attractionsLocations( List<String> attractions, List<String> locationsList ){
 //  		String csvSplit = ",";
	// 	String line = "";
	// 	BufferedReader attractionsFile = null;

 //  		try{
	// 		attractionsFile = new BufferedReader(new FileReader("attractions.csv"));
	// 		while ((line = attractionsFile.readLine()) != null){
	// 			String[] attraction = line.split(csvSplit);
	// 			//attraction[0] = Event or place of interest, attraction[1] = location
	// 			//Loop through String[] attractions and finds the location of that attraction by reading attractions.csv and add them to locationsList
	// 			for (int i=0; i<attractions.getSize(); i++){
	// 				if(attractions.get(i).equals(attraction[0])){
	// 					locationsList.add((i+1), attraction[1]);}
	// 			}
	// 		}
	// 	} catch(FileNotFoundException e){
	// 		System.out.println("File not found");
	// 	} catch(IOException e){
	// 		System.out.println("IOException");
	// 	} finally {
	// 		if (attractionsFile != null){
	// 			try{
	// 				attractionsFile.close();
	// 			} catch(IOException e){
	// 				System.out.println("IOException");
	// 			}
	// 		}
	// 	}
	// 	return locationsList;
	// }

	// public int calcDistance(String location1, String location2){
	//   	int distanceMiles = 0;
	//   	//use roads.csv to calculate distances between locations
	// 	String csvSplit = ",";
	// 	String line = "";
	// 	BufferedReader roadsFile = null;

 //  		try{
	// 		roadsFile = new BufferedReader(new FileReader("roadss.csv"));
	// 		while ((line = roadsFile.readLine()) != null){
	// 			String[] road = line.split(csvSplit);
	// 			//road[0] = locations1, road[1] = locations2, road[2] = distance in miles
	// 			// Loop over roads.csv until find row that contains both locations and save the 3rd column to get the miles in between them
	// 			if((location1.equals(road[0]) && location2.equals(road[1])) || (location2.equals(road[0]) && location1.equals(road[1]))){
	// 				distanceMiles = Integer.valueOf(road[2]);
	// 			}
	// 		}
	// 	} catch(FileNotFoundException e){
	// 		System.out.println("File not found");
	// 	} catch(IOException e){
	// 		System.out.println("IOException");
	// 	} finally {
	// 		if (roadsFile != null){
	// 			try{
	// 				roadsFile.close();
	// 			} catch(IOException e){
	// 				System.out.println("IOException");
	// 			}
	// 		}
	// 	}
	// 	// return number of miles as distance
	// 	return distanceMiles;
	// }
	// public List<String> Dijkstra(List<String> locationsList){
	// 	List<String>  routeDijkstra = new List(locationsList.getSize());
	  	
	//   	Graph g = new Graph();
	//   	g.addVertex();
	//   	g.addVertex();

	//   	//Vertex = locationsList
	//   	v = least_cost_unknown_vertex();
	//   	known(v) = true;
	//   	foreach n : adjacent(v);
	//   	if(cost(n)>cost(v) + edge_weight(v, n)):
	//   		update_distance(n, v);
	//   		update_path(n, v);
	  	
	//   	//Known = start with all as false
	 	
	//  	//Path = start with all as -1, then leave starting_city or locationsList[0] as -1 path because it will be starting point and all others will go from there, and set this with
		
	// 	//Known=true and Cost=0
		
	// 	//Cost = start with all cost=-1, then use calcDistance function to get costs: float cost = calcDistance(location1, location2)
	// 	int cost = calcDistance(locationsList.get(0), locationsList.get(1));
	// 	//Implement Dijkstra’s Algorithm until all Known are true and go backwards with Path and Vertex to create route and set to List<String> routeDijkstra
		
	// 	int graph[][] = new int[][]{{}}
	// 	ShortestPath t = new ShortestPath();
	// 	t.dijkstra(graph, 0);


	// 	return routeDijkstra;
	// }

  public static void main(String[] args) throws IOException{
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

  	ArrayList<String> attractions = new ArrayList<String>();//intialize the attractions list
    
    System.out.println("Number of attractions: ");
	int numAttractions = Integer.valueOf(input.nextLine());
  	// Lists<leastDistanceRoute> temp = new ArrayList<>();
  	// ArrayList<String> attractions = new ArrayList<String>();

  	for (int i=0; i<numAttractions; i++){
  		System.out.println("Attraction "+(i+1)+" :");
  		String temp = input.nextLine();
  		attractions.add(temp);
  	}

    // while(true)//collect user input while true
    // {
    //     System.out.println("\nPlease enter an attraction name (then enter done to finish): ");//enter the attractions until done
    //     String attraction = input.nextLine();
    //     if(!attraction.equalsIgnoreCase("done"))//if user input is not done
    //         attractions.add(attraction);//add the attraction to the list
    //     else//if equals done
    //         break;//break
    // }

    LinkedList<String> route = route(starting_city,ending_city,attractions);//find the route
    int finalDist = 0;//intialize final distance
    for(int i=0; i<route.size(); i++){//loop through the route
        int d= distance.get(i);//get the distance
        finalDist += d;//add to the final distance
    }
    System.out.println("Final Route: " + route);//print the route
    System.out.println("Final Distance: " + finalDist);//print the final distance
    System.out.println("You visited these attractions:");//print the attractions visited
    for(int j=0; j<attractions.size(); j++){//loop to print attractions
        System.out.println(attractions.get(j));
    }




	// System.out.println("Number of attractions: ");
	// int numAttractions = Integer.valueOf(input.nextLine());
 //  	// Lists<leastDistanceRoute> temp = new ArrayList<>();
 //  	// ArrayList<String> attractions = new ArrayList<String>();
 //  	List<String> attractions = new List();

 //  	for (int i=0; i<numAttractions; i++){
 //  		System.out.println("Attraction "+(i+1)+" :");
 //  		String temp = input.nextLine();
 //  		attractions.add(temp);
 //  	}

 //  	// for (int i=0; i<numAttractions; i++){
 //  	// 	System.out.println(attractions.get(i));
 //  	// }
 //  	//Create String bestRoute and store return value from route(starting_city, ending_city, attraction) function
 //  	List <String> bestRoute = new List();
 //  	bestRoute = minRoute.route(starting_city, ending_city, attractions);
	// //Print String bestRoute
 //  	for (int i = 0; i<bestRoute.getSize(); i++){
	// 		System.out.println(bestRoute.get(i));
	// }
  }

}
