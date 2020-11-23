//Rose Tabares - CS245 - Assignment02 - Best Road Trip Route - calcDistances.java
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;

public class calcDistances{

	public static final String oglocation1 = "San Francisco CA";
	public static final String oglocation2 = "Grand Canyon AZ";

	public static int distanceMiles = 0;

	public int calcDistance(String location1, String location2){
	  	// int distanceMiles = 0;
	  	//use roads.csv to calculate distances between locations
		String csvSplit = ",";
		String line = "";
		BufferedReader roadsFile = null;
		List<String> locationsfrom1 = new List();
		boolean flag = false;


  		try{
			roadsFile = new BufferedReader(new FileReader("roads.csv"));
			while ((line = roadsFile.readLine()) != null){
				String[] road = line.split(csvSplit);
				//road[0] = locations1, road[1] = locations2, road[2] = distance in miles
				// Loop over roads.csv until find row that contains both locations and save the 3rd column to get the miles in between them
				if((location1.equals(road[0]) && location2.equals(road[1])) || (location2.equals(road[0]) && location1.equals(road[1]))){
					distanceMiles += Integer.valueOf(road[2]);
					return distanceMiles;
				}
				else if(location1.equals(road[0])){
					distanceMiles+= Integer.valueOf(road[2]) + calcDistance(road[1], oglocation2);
				}
				// else if(location1.equals(road[1])){
				// 	distanceMiles+= Integer.valueOf(road[2]) + calcDistance(road[0], oglocation2);

				// }
				// else if(location1.equals(road[0]) || location1.equals(road[1])){
					
				// 	if(location1.equals(road[0])){
				// 		locationsfrom1.add(road[1]);
				// 		distanceMiles+= Integer.valueOf(road[2]) + calcDistance(road[1], oglocation2);
				// 		// i = calcDistance(road[1], oglocation2);
				// 	}
				// 	else{
				// 		locationsfrom1.add(road[0]);
				// 		// distanceMiles+= Integer.valueOf(road[2]) + calcDistance(road[0], oglocation2);

				// 		// calcDistance(road[1], road[0]);
				// 		// while(flag==false){
							
				// 		// }
				// 	}	
				// }
				else{
					distanceMiles += 0;
				}


			}
		} catch(FileNotFoundException e){
			System.out.println("File not found");
		} catch(IOException e){
			System.out.println("IOException");
		} finally {
			if (roadsFile != null){
				try{
					roadsFile.close();
				} catch(IOException e){
					System.out.println("IOException");
				}
			}
		}
		// for(int i=0; i<locationsfrom1.getSize(); i++){
		// 	System.out.println(locationsfrom1.get(i));
		// }
		// return number of miles as distance
		return distanceMiles;
		// return distanceMiles;
	}

	public static void main(String[] args){
 
       calcDistances dist = new calcDistances();
       String a = "San Francisco CA";
       String b = "Grand Canyon AZ";
       int dist_a_b = dist.calcDistance(a, b);
       System.out.println(dist_a_b);
   	}



}
