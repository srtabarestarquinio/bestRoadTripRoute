//Rose Tabares - CS245 - Assignment02 - Best Road Trip Route
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;

public class leastDistanceRoute{
  
	public static ArrayList<Integer> distance = new ArrayList();
	//create object from DijkstraAlgorithm class
    DijkstraAlgorithm dijAlg = new DijkstraAlgorithm();
	
    //function to find the shortest route from the given information:
    public LinkedList<String> findRoute(String starting_city, String ending_city){

        int start = dijAlg.getIndex(starting_city);//set the start to the index of the starting city
        int end = dijAlg.getIndex(ending_city);//set the end to the index of the ending city

        LinkedList<Integer> attraction = dijAlg.getAttraction();
        boolean places = !attraction.isEmpty();//if there are no attraction in the list
        if(!places)//if no attractions
            attraction.add(start);//add the start

        //create object from calcDistances class
        calcDistances calcDist = new calcDistances();

        LinkedList<LinkedList<Integer>> lp = calcDist.var(attraction);//call var on attractions
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
            dijAlg.Dijkstra(attraction.get(i),dist,prev);//call Dijkstra on a dist and prev
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
                LinkedList<Integer> path = calcDist.shortestPath(p1,p2,prev1.get(p1i));
            }

            end2 = var.get(var.size()-1);//get the latest path
            curr_dist += dist1.get(startNum).get(end2);//update the distance

            LinkedList<Integer> bestpath = calcDist.shortestPath(end,end2,prev1.get(endNum));//intialize the best path
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
            LinkedList<Integer> routefinal = calcDist.shortestPath(p1,p2,prev1.get(p1i));//set path final to path of p1,p1,prev1.get(p1i)
            //loop through the final path
            for(int j = 0; j < routefinal.size()-1; j++){
                String comp = dijAlg.vertexGet(routefinal.get(j));
                finalRoute.addLast(comp);//add the information to the path final
            }
        }
        String comp = dijAlg.vertexGet(end);
        finalRoute.addLast(comp);//add the ending city

        return finalRoute;
    }  

	public LinkedList<String> route(String start, String end, ArrayList<String> attractions) throws IOException{
		
		String csvSplit = ",";
		String line = "";
		//use bufferedreader to read the roads file:
        BufferedReader roadsFile = new BufferedReader(new FileReader("roads.csv"));
        //while loop line until null (reach end of file):
        while ((line = roadsFile.readLine()) != null){
            String[] road = line.split(csvSplit);//spilt the file at the commas
            
            dijAlg.addEdge(road[0],road[1],Integer.parseInt(road[2]));//add the edges
            distance.add(Integer.parseInt(road[2]));
        }
        //close the bufferreader
        roadsFile.close();

        //make new bufferreader with the attractions file:
        BufferedReader attractionsFile = new BufferedReader(new FileReader("attractions.csv"));
        while ((line = attractionsFile.readLine()) != null){
            String[] attraction = line.split(",");//split at the comma
            dijAlg.addPlace(attraction[1],attraction[0]);//add places as verticies
        }
        //close the bufferreader
        attractionsFile.close();

		for(int i=0; i<attractions.size(); i++){
        	dijAlg.placeRoute(attractions.get(i));
        }
        //call findRoute function from CalcRoute class
        LinkedList<String> route = findRoute(start,end);//find the route

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
	        //use get function for distance then accumulate to final distance calculation:
	        finalDist += distance.get(i);
	    }
	    System.out.println("\nBest Route to travel Least Distance: \n" + route);
	    System.out.println("\nVisiting the following attractions:");
	    for(int j=0; j<attractions.size(); j++){//loop to print attractions
	        System.out.println(attractions.get(j));
	    }
	    System.out.println("\nTotal Distance to Travel: " + finalDist + " miles");  
	}
}
