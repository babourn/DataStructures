package ds.airports.bab2177;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is them main class for Programming project 3 it contains a  front end system for the project.
 * @author Brian
 *
 */

public class mainf2012 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub


			try {
				Scanner in;
				String input;
				String command = null;
				int s;
				Processor p = null;
				FileReader g = null;
				if(args.length == 0){
					System.out.println("Welcome to the main class of Programming 3 for Data Structures");
					System.out.println("Brought to you by Brian Bourn- bab2177@columbia.edu");
					System.out.println("the menu choices are:");
					System.out.println("a: Load File\nb: Search for Cities by State\nc: Search for City by Name");
					System.out.println("d: Set Current City\ne: Show Current City\nf: Get Closest Cities by GPS");
					System.out.println("g: Get Closest Cities by Cost\nh: get Shortest path\ni: Quit the program");
					input = null;
					in = new Scanner(System.in);
				}else{
					System.out.println("Welcome to the main class of Programming 3 for Data Structures");
					System.out.println("Brought to you by Brian Bourn- bab2177@columbia.edu");
					File z = new File(args[0]);
					input = null;
					in = new Scanner(z);

				}
				while(!(input == "i")){
					if(in.hasNext()){
						command = in.next();
					}else{
						input = "i";
						command = "i";
					}
					if(command.equals("a")){
						
						System.out.println("What's the file you would like to load?");
						input = in.next();
						if(g == null){
							//Load File into the system
							g = new FileReader(input);
							g.loadCities();
							g.edgeAdder();
							MyGraphMap m = new MyGraphMap(g.cities, g.allEdges);
							p = new Processor(m);
						}else{
							System.out.println("Do you want to load a new City list or add to the current one? New/Add");
							command = in.next();
							if(command.equals("Add")){
								FileReader f = new FileReader(input);
								f.loadCities();
								g.cities.addAll(f.cities);
								g.edgeAdder();
								MyGraphMap m = new MyGraphMap(g.cities, g.allEdges);
								p = new Processor(m);
							}else{
								g = new FileReader(input);
								g.loadCities();
								g.edgeAdder();
								MyGraphMap m = new MyGraphMap(g.cities, g.allEdges);
								p = new Processor(m);
							}
						}
					}else if(command.equals("b")){
						// Get Cities from a state

						if(p == null){
							System.out.println("No Graph");
						}else{
							System.out.println("What state would you like to search for?");
							input = in.next();
							p.getCitiesFromState(input);
						}
					}else if(command.equals("c")){
						

						
						//Get City by name
						if(p == null){
							System.out.println("No Graph");
						}else{
							System.out.println("Which city would you like to search for?");
							input = in.next();
							p.getCity(input);
						}
					}else if(command.equals("d")){

						
						if(p == null){
							System.out.println("No Graph");
						}else{
							System.out.println("What's the Id number of the city you would like to use as the current city?");
							s = in.nextInt();
							
							//Set Current City
							p.setCurrentCity(s);
						}
					}else if(command.equals("e")){
						

						
						if(p == null){
							System.out.println("No Graph");
						}else if(p.current == null){
							System.out.println("No Current City");
						}else{
							
							//Show Current City
							p.showCurrentCity();
						}
					}else if(command.equals("f")){
						if(p == null){
							System.out.println("No Graph");
						}else{	
							System.out.println("How many cities would you like to print?");
							s = in.nextInt();
							//Find Closest Cities by Geographic Distance
							p.closestByGPS(s);
						}
					}else if(command.equals("g")){
						if(p == null){
							System.out.println("No Graph");
						}else{
							System.out.println("How many cities would you like to print?");
							s = in.nextInt();
							//Find Closest Cites by Edge
							p.closestByEdge(s);
						}
					}else if(command.equals("h")){
						if(p == null){
							System.out.println("No Graph");
						}else{
							
							System.out.println("What's the Id number of the city you want the shortest path to?");
							s = in.nextInt();
							//Find The shortest Path Between Cities
							p.shortestPath(s);
						}
					}else{
						input = "i";
					}
				}
				System.out.println("GoodBye");
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Thats not a real File. GoodBye");
			}
		}

}
