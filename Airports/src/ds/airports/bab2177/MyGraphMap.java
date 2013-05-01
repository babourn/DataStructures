package ds.airports.bab2177;

import java.util.ArrayList;

/**
 * This Class is the main Graph class it houses two arrayLists one of the cities/vertexes
 * and one of the Edges this class also implements dijkstra's
 * @author Brian Bourn
 *
 */
public class MyGraphMap {

	public ArrayList<City> vertexes = new ArrayList<City>();
	public ArrayList<Edge> edges = new ArrayList<Edge>();
	public static final double POSITIVE_INFINITY = 1d/0d;
	public boolean d = false; //This boolean tells us if dijkstra's has been run yet, we wouldn't wanna do it twice
	
	public MyGraphMap(ArrayList<City> vertexes, ArrayList<Edge> edges){
		this.vertexes = vertexes;
		this.edges = edges;
	}
	
	/**
	 * This method will implement dijkstra's algorithm
	 * @param current is the starting city
	 */
	public void dijkstra(City current){
		if(!d){
			for(City c: vertexes){
				c.edgeCost = POSITIVE_INFINITY;
			}
			current.edgeCost = 0;
			current.costFP = 0;
			City c = current;
	
			
			//set up the boolean for if there are still cites with unknown edge cost.
			boolean unknown = true;
			while(unknown){
				for(City d: vertexes){
					if(!d.known){
						if(c == null){
							c = d;
						}
						if(d.edgeCost < c.edgeCost){
							c = d;
						}
					}
				}
				
				c.known = true;
				for(Edge e: c.edges){
					City d = e.toCity;
					if(!d.known){
						double cost = e.cost;
						if(c.edgeCost + cost < d.edgeCost){
							//update d's edge cost and path
							d.edgeCost = (double) Math.round((c.edgeCost + cost)*100)/100;
							d.path = c;
							d.costFP = cost;
	
						}
					}
				}
				unknown = false;
				for(City d: vertexes){
					if(!d.known){
						unknown = true;
					}
				}
				c = null;
			}
			
			
			d = true;
		}
	}
	
}
