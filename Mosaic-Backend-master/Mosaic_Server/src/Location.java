
public class Location {
	private String city;
	private String state;
	private String address1;
	private String zip_code;
	
	public Location(String city, String state, String address1, String zip_code) {
		this.city = city;
		this.state = state;
		this.address1 = address1;
		this.zip_code = zip_code;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getAddress1() {
		return address1;
	}

	public String getZip_code() {
		return zip_code;
	}
	
	
}
