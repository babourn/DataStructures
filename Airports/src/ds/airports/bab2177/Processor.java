package ds.airports.bab2177;


import java.util.ArrayList;
import java.util.Collections;

/**
 * This class will do the bulk of the work for this assignment. it will contain
 * all of the main methods for finding the needed information these will be
 * b. getCitiesFromState(String s)
 * c. getCity(String s)
 * d. setCurrentCity(int id)
 * e. showCurrentCity()
 * f. closestByGPS(int s)
 * g. closestByEdge(int s)
 * h. shortestPath(int id)
 * 
 * @author Brian Bourn
 * 
 */
public class Processor {

	public ArrayList<City> cities;
	public ArrayList<City> citiesBackup;
	public City current;
	public static final double POSITIVE_INFINITY = 1d/0d;
	public MyGraphMap graph;
	
	public Processor(MyGraphMap m){
		graph = m;
		this.cities = graph.vertexes;
		this.citiesBackup = cities;
		
		
	}
	
	/**
	 * This method will simply print all the cities from an array list
	 * and the node information
	 */
	public void printAll(){
		for(City c: cities){
			System.out.println("\n\nCity:");
			System.out.println(c.name + ", " + c.state);
			System.out.println(c.lat);
			System.out.println(c.lon);
			System.out.println(c.id);
			System.out.println("Edges: ");
			for(Edge e: c.edges){
				System.out.println(e.toCity.name + ", " + e.toCity.state);
				System.out.println(e.cost + "\n");
			}
			System.out.println(c.incomingEdges.size() + " Incoming Edges: ");
			for(Edge e: c.incomingEdges){
				System.out.println(e.toCity.name + ", " + e.toCity.state);
				System.out.println(e.cost + "\n");
			}
		}
	}
	
	
	/**
	 * this method will search and array list of cities for a city with a name that matches
	 * the provided String s
	 * @param s
	 */
	public void getCity(String s){
		boolean found = false;
		for(City d: cities){
			if(d.name.equals(s)){
				System.out.println("City:");
				System.out.println(d.name + ", " + d.state);
				System.out.println("ID: " + d.id);
				System.out.println("Latitude: " + d.lat);
				System.out.println("Longitude: " + d.lon);
				found = true;
			}
		}
		if(!found){
			System.out.println("No City found named: " + s);
		}
		
	}
	/**
	 * this method will take a String s and check an array list of cities to find a cities
	 * that are in state s name and then it will print out relevant data for those cities
	 * @param s
	 */
	public void getCitiesFromState(String s){
		String name = s;
		for(City c: cities){
			
			if(c.state.equals(name)){
				System.out.println("\nCity:");
				System.out.println(c.name + ", " + c.state);
				System.out.println("ID: " + c.id);
				System.out.println(c.edges.size()+ " Outgoing Edges: ");
				System.out.println(c.incomingEdges.size() + " Incoming Edges: ");
			}
		}
	}
	
	/**
	 * This method will set the Current city 
	 * @param id
	 */
	public void setCurrentCity(int id){
		for(City d: graph.vertexes){
			if(d.id == id){
				current = d;
			}
		}
	}
	
	/**
	 * this method will display all the information of the current city
	 * including:
	 * Name, State
	 * Id
	 * Latitude
	 * Longitude
	 * # of outgoing edges
	 * # of incoming edges
	 */
	public void showCurrentCity(){
		System.out.println("City:");
		System.out.println(current.name + ", " + current.state);
		System.out.println("ID: " + current.id);
		System.out.println("Latitude: " + current.lat);
		System.out.println("Longitude: " + current.lon);
		System.out.println(current.edges.size()+ " Outgoing Edges: ");
		System.out.println(current.incomingEdges.size() + " Incoming Edges: ");
	}
	
	/**
	 * This method will find the s closest cities by GPS, meaning the geographically
	 * closest cities. it will not depend on edges/cost
	 * 
	 * if the current city is not set it will select one at random
	 * 
	 * @param s the amount of cities  to return
	 */
	public void closestByGPS(int s){
		if(current == null){
			int index = (int)(Math.random()*graph.vertexes.size());
			current = graph.vertexes.get(index);
			showCurrentCity();
		}
		for(City c: graph.vertexes){
			LatLongConverter l = new LatLongConverter(current.lat, current.lon, c.lat, c.lon);
			double miles = l.getMiles();
			c.dist = miles;
		}
		cities = graph.vertexes;
		
		Collections.sort(cities, new MyCityComparable());
		for(int i = 1; i<=s ; i++){
			City c = cities.get(i);
			System.out.println(c.name + ", " + c.state + "  -  " + c.dist + " miles");
		}
		graph.vertexes = citiesBackup;
	}
	
	
	
	/**
	 * This algorithm will find the closest cities based on edge costs	 
	 * 
	 * if the current city is not set it will select one at random
	 * 
	 * @param s
	 */
	public void closestByEdge(int s){
		if(current == null){
			int index = (int)(Math.random()*graph.vertexes.size());
			current = graph.vertexes.get(index);
			showCurrentCity();
		}
		graph.dijkstra(current);
		cities = graph.vertexes;
		Collections.sort(cities, new EdgeCostComparable());
		for(int i = 1; i<s; i++){
			City c = cities.get(i);
			System.out.println(c.name + ", " + c.state + "  -  " + "$" + c.edgeCost);
		}
	}
	
	/**
	 * This method will implement Dijkstra's algorithm to find the shortest path between two cities
	 * based on the edge cost
	 * 
	 * if the current city is not set it will select one at random
	 * 
	 * @param s is the id number of the city we are headed to
	 */
	public void shortestPath(int s){
		if(current == null){
			int index = (int)(Math.random()*graph.vertexes.size());
			current = graph.vertexes.get(index);
			showCurrentCity();
		}
		cities = graph.vertexes;
		City c = null;
		for(City d: graph.vertexes){
			if(d.id == s){
				c = d;
			}
		}
		if(c == null){
			System.out.println("Destination city not found");
		}else if(c.equals(current)){
			System.out.println("That City is also your start point...");
			System.out.println("Total Cost: $" + "$0.0");
		}
		else{
			City q = c;
			ArrayList<City> path = new ArrayList<City>();
			path.add(c);
			graph.dijkstra(current);
			if(c.edgeCost != POSITIVE_INFINITY){
				System.out.println("Total Cost: $" + c.edgeCost);
				System.out.println(c.name);
				while(!(current == c.path)){
					path.add(c.path);
					c = c.path;
				}
				Collections.reverse(path);
				System.out.println("Total Stops (Excluding Start): " + path.size());
				for(City d: path){
					System.out.println(d.path.name + ", " + d.path.state + "  -  " + "$" + d.path.costFP);
				}
				System.out.println(q.name + ", " + q.state + "  -  " + "$" + q.costFP);
			}else{
				System.out.println("No Route Via Directed Edges");
			}
		}
	}
	
	
}
