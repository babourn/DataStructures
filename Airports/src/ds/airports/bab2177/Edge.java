package ds.airports.bab2177;

/**
 * This is an Edge Object
 * @author Brian Bourn
 *
 */
public class Edge {

	public City fromCity;
	public City toCity;
	public double cost;
	
	
	/**
	 * @param toCity the city that the edge is going to
	 * @param cost the cost/weight of that edge
	 */
	public Edge(City fromCity, double cost, City toCity){
		this.fromCity = fromCity;
		this.toCity = toCity;
		//I rounded the cost to two decimal places so that the weights can be dollars
		this.cost = (double) Math.round(cost*100)/100;
	}
}
