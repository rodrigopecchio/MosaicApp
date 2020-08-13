import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;

public class Yelp {
	private double latitude; //Latitude of the headquarter
	private double longitude; //Longitude of the headquarter
	
	//ErO7Lu-vGSlPkBW8rNfnO3sjMUILz34NTMIA0bf-QVvLm-d7w16ORx9_hYR4kUKk8RE5I86eo9ohAwQIeiu3l40lOft5iw1VotxwioQIkbykCFRcuaozxrgjWpgoX3Yx
	//wlMU-liGZx9jrIky175ASa_x1VvbFTVpstmuHea4khMGSGPRVg8b7mU8Bbkyr4JnSqu8IBzqfR-1_qFed5Q5LmwAJSTol14_nHvg7XePMKMKGZ49hxTuv9ddTTQmX3Yx
	public Yelp() {
		this.latitude = 34.052235;
		this.longitude = -118.243683;
	}
	
	/**
	 * Gets restaurant information from Yelp API
	 * @param name - Name of the restaurant
	 * @return Restaurant object of the restaurant
	 */
	public Restaurant getInfo(String name) {
		try {
			name = name.replace(" ", "+"); //Replaces the spaces in the name to fit URL format
			URL url = new URL("https://api.yelp.com/v3/businesses/search?latitude=" + latitude + "&longitude=" + longitude + "&term=" + name); //URL Link to find restaurant
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //URL connection
			connection.setRequestMethod("GET");
			//API authorization
			connection.setRequestProperty("authorization", "Bearer 2fqA1ur37NsRFERXB-LcDsyKsP9mDbUpHxpz-8RTEgBUOYAZoH04CVIwEuRRndxzDu5g6PgzxHQdxPozL6zMLDtHHXxIPjxtYLuuz-yvdPZgLuKZD-X3TIrnIZEwX3Yx");
			if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) { //If we correctly searched
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream())); //Get the JSON data
				StringBuffer data = new StringBuffer(); //Store all of the JSON data
				String line = br.readLine(); //Store each line of the data received from API
				
				//Until we reached the end of the API data, keep building it into our string
				while (line != null)
				{
					data.append(line); //Add it into our StringBuilder
					line = br.readLine(); //Read next line
				}
				br.close(); //Close BufferedReader
				
				Gson gson = new Gson(); //Alows us to put our JSON data into an object
				RestaurantList businesses = gson.fromJson(data.toString(), RestaurantList.class); //Convert JSON to GSON
				
				return businesses.getBusinesses().get(0); //Get the first restaurant returned.
			}
		} catch (MalformedURLException mue) { //If there is a bad URL
			System.out.println("Cannot search due to bad URL.");
		} catch (IOException ioe) { //If an IOException occurred
			System.out.println("IOException " + ioe.getMessage());
		}
		System.out.println("Yelp request failed."); //If we didn't correctly search
		
		return null;
	}
}
