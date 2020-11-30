//Rose Tabares - CS245 - Assignment02 - Best Road Trip Route - calcDistances.java
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;

public class calcDistances{
	//function that returns a list of all the variations:
    public static LinkedList<LinkedList<Integer>> var(LinkedList<Integer> l){
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

    //function to get the shortest path:
    public LinkedList<Integer> shortestPath(int start, int end, ArrayList<Integer> previous){
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
	







}
