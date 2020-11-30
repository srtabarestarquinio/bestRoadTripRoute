//Rose Tabares - CS245 - Assignment02 - Best Road Trip Route
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;

public class leastDistanceRoute{
  
	public static ArrayList<Integer> distance = new ArrayList();

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
            String[] road = line.split(csvSplit);//spilt the file at the commas
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

		for(int i=0; i<attractions.size(); i++){
        	calcRoute.placeRoute(attractions.get(i));
        }
        //call findRoute function from CalcRoute class
        LinkedList<String> route = calcRoute.findRoute(start,end);//find the route

        return route;
	}

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
		//add user input attractions to attractions list:
	  	for (int i=0; i<numAttractions; i++){
	  		System.out.println("Attraction "+(i+1)+" :");
	  		String temp = input.nextLine();
	  		attractions.add(temp);
	  	}
	  	//find the route calling the route function:
	    LinkedList<String> route = minRoute.route(starting_city,ending_city,attractions);
	    //initialize final distance and then from for loop add all distances:
	    int finalDist = 0;
	    //for loop through the route:
	    for(int i=0; i<route.size(); i++){
	        //use get function for distance:
	        int d= distance.get(i);
	        //accumulate to final distance calculation:
	        finalDist += d;
	    }
	    System.out.println("\nBest Route to travel Least Distance: \n" + route);
	    System.out.println("\nVisiting the following attractions:");
	    for(int j=0; j<attractions.size(); j++){//loop to print attractions
	        System.out.println(attractions.get(j));
	    }
	    System.out.println("\nTotal Distance to Travel: " + finalDist + " miles");  
	}
}
