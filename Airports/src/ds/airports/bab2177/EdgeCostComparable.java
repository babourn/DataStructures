package ds.airports.bab2177;

import java.util.Comparator;


/**
 * This Comparator will compare two Cities based off the total cost to get there from the current city
 * @author Brian Bourn
 *
 */
public class EdgeCostComparable implements Comparator<City> {
	
	public int compare(City c, City d) {
		return(c.edgeCost<d.edgeCost ? -1 : (c.edgeCost==d.edgeCost ? 0: 1));

	}

}
