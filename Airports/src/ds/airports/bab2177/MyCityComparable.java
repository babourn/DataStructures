package ds.airports.bab2177;

import java.util.Comparator;

/**
 * this Comparator will compare the actual distances between cities.
 * @author Brian Bourn
 *
 */

public class MyCityComparable implements Comparator<City>{
	
	public int compare(City c, City d) {
		return(c.dist<d.dist ? -1 : (c.dist==d.dist ? 0: 1));

	}

	
}