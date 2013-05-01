package ds.airports.bab2177;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * 
 * This class will read the input file and make the initial cities.
 * @author Brian Bourn
 * 
 * 
 */
public class FileReader {

	public File input;
	public Scanner in;
	public ArrayList<City> cities = new ArrayList<City>();
	public ArrayList<Edge> allEdges = new ArrayList<Edge>();
	public City current;
	public String name;
	public String state;
	int total;
	
	public FileReader(String s) throws FileNotFoundException{
		input = new File(s);
		getAmount();
	}
	/**
	 * This will tell us how many cities there will be;
	 * @throws FileNotFoundException
	 */
	public void getAmount() throws FileNotFoundException{
		in = new Scanner(input);
	    total = Integer.parseInt(in.nextLine());
	}
	
	
	/**
	 * This method will read an input file and produce a list of cities and edges.
	 * it will fill each city class with its name, state, latitude, longitude, outgoingEdges,
	 * ID# and incomingEdges.
	 * @return cities this will be the main list of cities
	 */
	public void loadCities() {

		for(int i =  0; i < total; i ++){
			//first get name and state
			String info = in.nextLine();
			int seperator = info.indexOf(",");
			if(seperator == -1){
				name = info;
				state = info;
			}
			else{
				name = info.substring(0,seperator);
				state = info.substring(seperator + 2);
			}

			
			
			//then get latitude and longitude
			double lon = Double.parseDouble(in.nextLine());
			double lat = Double.parseDouble(in.nextLine());
			
			//then make the basic city and add it to the array
			current = new City(name, state, lat, lon, i+1);
			cities.add(current);

			
			
		}
		

		
	}
	
	public void edgeAdder(){
		allEdges.clear();
		for(City c: cities){
			c.edges.clear();
			c.incomingEdges.clear();
		}
		// this will randomly add edges
		for(City c: cities){
			c.edges = addEdges(c);
			for(Edge d: c.edges){
				City e = d.toCity;
				double cost = d.cost;
				Edge newEdge = new Edge( d.fromCity, cost, c);
				e.incomingEdges.add(newEdge);
			}
		}
	}
	
	
	/**
	 * This will take a city and generate an arrayList of outgoing edges from that city
	 * @param c the city that the edge is coming from
	 * @return an arrayList of edges
	 */
	public ArrayList<Edge> addEdges(City c){
		ArrayList<Edge> edges = new ArrayList<Edge>();
		int e = 2+(int)(Math.random()*(6 +1));
		for(int i = 0; i < e; i++){
			Edge currentEdge = addEdge(c);
			
			//ensure that the edge cannot be back to itself
			while(currentEdge.toCity.equals(c)){
				currentEdge = addEdge(c);
			}
			
			//ensure that there are not multiple edges to the same city
			while(edges.contains(currentEdge)){
				currentEdge = addEdge(c);
			}

			edges.add(currentEdge);
			allEdges.add(currentEdge);
		}
		
		return edges;
		
	}
	
	/**
	 * This will generate and actual edge including the city that it's to and it's cost
	 * @return the edge that was just generated
	 */
	public Edge addEdge(City c){
		int idnum = (int) (total*Math.random());
		current = cities.get(idnum);
		double cost = 100 +(Math.random()*(1900 +1));
		Edge currentEdge = new Edge(c, cost, current);
		return currentEdge;
	}
	
}
