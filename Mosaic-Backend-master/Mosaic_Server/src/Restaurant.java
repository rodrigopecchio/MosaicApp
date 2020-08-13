import java.util.ArrayList;

public class Restaurant {
	private String name; //Name of the restaurant
	private Coordinates coordinates; //Coordinates of the restaurant
	private ArrayList<Categories> categories;
	private String phone;
	private Location location;
	private String image_url;
	private double latitude;
	private double longitude;
	private int numRatings;
	private double rating;
	
	public Restaurant(String name, Coordinates coordinates, String phone, Location location) {
		this.name = name;
		this.coordinates = coordinates;
		this.phone = phone;
		this.location = location;
	}
	
	public Restaurant(String name, Location location, String phone, String image_url, double latitude, double longitude, double rating, int numRatings) {
		this.name = name;
		this.phone = phone;
		this.location = location;
		this.image_url = image_url;
		this.latitude = latitude;
		this.longitude = longitude;
		this.numRatings = numRatings;
		this.rating = rating;
	}
	
	public String getPhone() {
		return phone;
	}

	public Location getLocation() {
		return location;
	}
	
	public ArrayList<Categories> getCategories(){
		return this.categories;
	}
	
	public String getImageURL() {
		return this.image_url;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}

	/**
	 * Gets the name of the restaurant
	 * @return Name of the restaurant
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the coordinates of the restaurant
	 * @return Coordinates of the restaurant
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	public double getRating() {
		return rating;
	}
	
	public int getNumRatings() {
		return numRatings;
	}
}
