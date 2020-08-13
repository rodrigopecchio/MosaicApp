
public class Coordinates {
	private double latitude;
	private double longitude;
	
	public Coordinates(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * Gets latitude.
	 * @return Latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * Gets longitude.
	 * @return Longitude
	 */
	public double getLongitude() {
		return longitude;
	}
}
