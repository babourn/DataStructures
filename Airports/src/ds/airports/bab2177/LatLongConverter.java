package ds.airports.bab2177;

/**
 * this code will take a two sets of latitude and longitude and convert them into a distance
 * @author Brian Bourn
 * 
 */

public class LatLongConverter {

	public double lat1;
	public double lat2;
	public double lon1;
	public double lon2;
	
	public LatLongConverter(double lat1, double lon1, double lat2, double lon2){
		this.lat1 = lat1;
		this.lat2 = lat2;
		this.lon1 = lon1;
		this.lon2 = lon2;

	}
	
	public Double getMiles(){
		double miles = Math.sqrt(Math.pow(69.1*(lat2-lat1),2) + Math.pow(53.0*(lon2-lon1),2));
		miles = (double) Math.round(miles*1000)/1000; // rounded 
		return miles;
	}
	
}
