package ds.airports.bab2177;

import java.util.ArrayList;



/**
 * This will be the main object class for city.
 * It will contain name, state, latitude, longitude, id#, distance to current city, total
 * cost from current city, cost from previous edge in the quickest path, the city that preceeds it
 * on the quickest path, a boolean that tells us whether or not its been processed by dijkstra's and
 * two lists of edges one for outgoing edges and one for incoming edges
 * @author Brian Bourn
 * 
 */
public class City {

	public String name;
	public String state;
	public double lat;
	public double lon;
	public int id;
	public double dist; // actual Geographic distance from start Node
	public double edgeCost; // total cost to get to this node from start node
	public double costFP; // cost from previous node as found by dijkstra's
	public City path;
	public boolean known;
	public ArrayList<Edge> edges = new ArrayList<Edge>();
	public ArrayList<Edge> incomingEdges = new ArrayList<Edge>();


	
	
	
	/**
	 * 
	 * @param name of the city
	 * @param state or country of the city
	 * @param lat Latitude of the city
	 * @param lon Longitude of the city
	 * @param id ID# of the city;
	 */
	public City(String name, String state, double lat, double lon, int id){
		this.name = name;
		this.state = state;
		this.lat = lat;
		this.lon = lon;
		this.id = id;
		known = false;
	}
	
	
}

