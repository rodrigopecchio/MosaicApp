import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DBUtil {

	private Connection conn;
	
	public DBUtil(String ip, int port, String username, String password, String db) {
		// connection details
		try {
			String s = "jdbc:mysql://" + ip + ":" + port + "/" + db;
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(s, username, password);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
		
	}
	
	public void getAllBusinesses(PrintWriter pw) throws SQLException {
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM business");
		ResultSet rs = ps.executeQuery();
		
		// new restaurant list object will be filled in with results
		ArrayList<Restaurant> allBusinesses = new ArrayList<Restaurant>();
		
		while(rs.next()) {
			// for location object
			String city;
			String state;
			String address1;
			String zip_code;
			Restaurant restaurant;
			// for restaurant object that will go in restaurant list
			String name;
			Location location;
			String phone;
			String image;
			double latitude;
			double longitude;
			int numRatings;
			double rating;
			
			city = rs.getString("businessCity");
			state = rs.getString("businessState");
			address1 = rs.getString("businessAddress");
			zip_code = rs.getString("businessZipCode");
			
			location = new Location(city, state, address1, zip_code);
			
			name = rs.getString("businessName");
			phone = rs.getString("businessPhone");
			image = rs.getString("businessImage");
			latitude = rs.getDouble("businessLatitude");
			longitude = rs.getDouble("businessLongitude");
			numRatings = rs.getInt("businessNumRatings");
			rating = rs.getDouble("businessRating");
			restaurant = new Restaurant(name, location, phone, image, latitude, longitude, rating, numRatings);
			
			allBusinesses.add(restaurant);
		}
		
		FileWriter fw = null; //Creates a FileWriter object
		try {
			fw = new FileWriter("results.json");
			//Write our data into JSON format and print neatly
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(allBusinesses, fw); //Write to our file
			gson.toJson(allBusinesses, pw);
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe.getMessage());
		} finally {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void returnBusinessesInJSON(String businessName, PrintWriter pw) throws SQLException {
		
		System.out.println("returnBusinessesInJSON");
		ResultSet rs = null;
		
		// gets first letter of businessname
		String firstLetter = businessName.charAt(0) + "";
		// make sure the restaurant searched is the first one in the result query
		//String mySQLCommand = "SELECT * FROM business WHERE businessName = " + businessName + ";";
		// get 7 other results that are similar names, practical when business doesn't exist will still return these 7
		String mySQLCommand = "SELECT * FROM business WHERE businessName LIKE \"%" + businessName + "%\" LIMIT 13;";
		// prepares statement
		PreparedStatement ps = this.conn.prepareStatement(mySQLCommand);
		// executes query stores in result set
		rs = ps.executeQuery();
		
		// new restaurant list object will be filled in with results
		ArrayList<Restaurant> searchResult = new ArrayList<Restaurant>();
		
		int count = 0;
		while(rs.next()) {
			// for location object
			String city;
			String state;
			String address1;
			String zip_code;
			Restaurant restaurant;
			// for restaurant object that will go in restaurant list
			String name;
			Location location;
			String phone;
			String image;
			double latitude;
			double longitude;
			double rating;
			int numRatings;
			
			city = rs.getString("businessCity");
			state = rs.getString("businessState");
			address1 = rs.getString("businessAddress");
			zip_code = rs.getString("businessZipCode");
			
			location = new Location(city, state, address1, zip_code);
			
			name = rs.getString("businessName");
			phone = rs.getString("businessPhone");
			image = rs.getString("businessImage");
			latitude = rs.getDouble("businessLatitude");
			longitude = rs.getDouble("businessLongitude");
			numRatings = rs.getInt("businessNumRatings");
			rating = rs.getDouble("businessRating");
			restaurant = new Restaurant(name, location, phone, image, latitude, longitude, rating, numRatings);
			
			searchResult.add(restaurant);
			count++;
		}
		if (count == 0) {
			getAllBusinesses(pw);
			return;
		}

		FileWriter fw = null; //Creates a FileWriter object
		try {
			fw = new FileWriter("results.json");
			//Write our data into JSON format and print neatly
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(searchResult, fw); //Write to our file
			gson.toJson(searchResult,pw);
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe.getMessage());
		} finally {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
public void getBusinessesFromCategory(String catID, PrintWriter pw) throws SQLException {
		
		ResultSet rs = null;
		String mySQLCommand = "SELECT * FROM business WHERE categoryID=" + catID + " LIMIT 13;";
		// prepares statement
		PreparedStatement ps = this.conn.prepareStatement(mySQLCommand);
		// executes query stores in result set
		rs = ps.executeQuery();
		
		// new restaurant list object will be filled in with results
		ArrayList<Restaurant> searchResult = new ArrayList<Restaurant>();
		
		while(rs.next()) {
			// for location object
			String city;
			String state;
			String address1;
			String zip_code;
			Restaurant restaurant;
			// for restaurant object that will go in restaurant list
			String name;
			Location location;
			String phone;
			String image;
			double latitude;
			double longitude;
			double rating;
			int numRatings;
			
			city = rs.getString("businessCity");
			state = rs.getString("businessState");
			address1 = rs.getString("businessAddress");
			zip_code = rs.getString("businessZipCode");
			
			location = new Location(city, state, address1, zip_code);
			
			name = rs.getString("businessName");
			phone = rs.getString("businessPhone");
			image = rs.getString("businessImage");
			latitude = rs.getDouble("businessLatitude");
			longitude = rs.getDouble("businessLongitude");
			
			numRatings = rs.getInt("businessNumRatings");
			rating = rs.getDouble("businessRating");
			restaurant = new Restaurant(name, location, phone, image, latitude, longitude, rating, numRatings);
			
			searchResult.add(restaurant);
		}
		

		FileWriter fw = null; //Creates a FileWriter object
		try {
			fw = new FileWriter("results.json");
			//Write our data into JSON format and print neatly
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(searchResult, fw); //Write to our file
			gson.toJson(searchResult,pw);
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe.getMessage());
		} finally {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public double getDistance(double lat1, double long1, double lat2, double long2) {
		double longDiff = long2 - long1;
		double distance = 3963.0 * Math.acos((Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(longDiff)));
		return distance;	
	}
	
	public static void getFavorites(String username) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String s = "jdbc:mysql://168.235.102.179:3306/mosaic?";
			conn = DriverManager.getConnection(s, "mosaic", "DR5@FL9#4ndL!");
			ps = conn.prepareStatement("SELECT * FROM business b, user u, favorite f WHERE u.username = ? AND u.userID = f.userID AND b.businessID = f.businessID;");
			ps.setString(1, username);
			rs = ps.executeQuery();
			ArrayList<Restaurant> allBusinesses = new ArrayList<Restaurant>();

			while(rs.next()) {
				// for location object
				String city;
				String state;
				String address1;
				String zip_code;
				Restaurant restaurant;
				// for restaurant object that will go in restaurant list
				String name;
				Location location;
				String phone;
				String image;
				double latitude;
				double longitude;
				double rating;
				int numRatings;
				
				city = rs.getString("businessCity");
				state = rs.getString("businessState");
				address1 = rs.getString("businessAddress");
				zip_code = rs.getString("businessZipCode");
				
				location = new Location(city, state, address1, zip_code);
				
				name = rs.getString("businessName");
				phone = rs.getString("businessPhone");
				image = rs.getString("businessImage");
				latitude = rs.getDouble("businessLatitude");
				longitude = rs.getDouble("businessLongitude");
				
				numRatings = rs.getInt("businessNumRatings");
				rating = rs.getDouble("businessRating");
				restaurant = new Restaurant(name, location, phone, image, latitude, longitude, rating, numRatings);
				
				allBusinesses.add(restaurant);
			}
			
			FileWriter fw = null; //Creates a FileWriter object
			try {
				fw = new FileWriter("results.json");
				//Write our data into JSON format and print neatly
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				gson.toJson(allBusinesses, fw); //Write to our file
			} catch (IOException ioe) {
				System.out.println("IOException: " + ioe.getMessage());
			} finally {
				if(fw != null)
					try {
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
		} catch(SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
		}
	}
	
	public boolean loginUser(String username, String password, PrintWriter pw) {
		ResultSet rs = null;
		String mySQLCommand = "SELECT * FROM user WHERE username='" + username + "';";
		// prepares statement
		try {
			PreparedStatement ps = this.conn.prepareStatement(mySQLCommand);
		
			// executes query stores in result set
			rs = ps.executeQuery();
			
			// new restaurant list object will be filled in with results
			
			while(rs.next()) {
				if (rs.getString("username").equals(username) && rs.getString("password").equals(password)) {
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					String s = gson.toJson(true);
					pw.println(s);
					return true;
				}
			}
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String s = gson.toJson(false);
			pw.println(s);			
			return false;
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String s = gson.toJson(false);
		pw.println(s);		
		return false;
	}
	
	public synchronized boolean registerUser(String username, String password, PrintWriter pw) {
		ResultSet rs = null;
		String mySQLCommand = "SELECT * FROM user WHERE username='" + username + "';";
		// prepares statement
		try {
			PreparedStatement ps = this.conn.prepareStatement(mySQLCommand);
		
			// executes query stores in result set
			rs = ps.executeQuery();
			
			// new restaurant list object will be filled in with results
			
			while(rs.next()) {
				if (rs.getString("username").equals(username) && rs.getString("password").equals(password)) {
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					String s = gson.toJson(false);
					pw.println(s);
					return false;
				}
			}
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String s = gson.toJson(true);
			pw.println(s);
			return false;
		}
		try {
			String insertCommand = "INSERT INTO user (username, password) VALUES ('" + username + "','" + password + "');";;
			PreparedStatement ps = this.conn.prepareStatement(insertCommand);
			ps.execute();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String s = gson.toJson(true);
			pw.println(s);
			return true;
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String s = gson.toJson(false);
			pw.println(s);
			return false;
		}
	}
	
	public synchronized void addRating(double rating, String bizName, PrintWriter pw) {
		String mySQLCommand = "SELECT * FROM business WHERE businessName='" + bizName + "';";
		try {
			PreparedStatement ps = this.conn.prepareStatement(mySQLCommand);
			ResultSet rs = ps.executeQuery();
			rs.next();
			double currentRating = rs.getDouble("businessRating");
			int currentNumRatings = rs.getInt("businessNumRatings");
			currentRating *= (double) currentNumRatings;
			currentRating += (double) rating;
			currentNumRatings++;
			currentRating = (double) currentRating / currentNumRatings;
			String insertCommand = "UPDATE business SET businessRating=" + currentRating + ", businessNumRatings=" + currentNumRatings + " WHERE businessName='" + bizName + "';";
			PreparedStatement insertPS = this.conn.prepareStatement(insertCommand);
			insertPS.execute();
			pw.println("true");
			System.out.println("Added rating of " + rating + " to " + bizName);
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
			pw.println("false");
		}
	}
	
	public void getRating(String bizName, PrintWriter pw) {
		String mySQLCommand = "SELECT * FROM business WHERE businessName='" + bizName + "';";
		try {
			PreparedStatement ps = this.conn.prepareStatement(mySQLCommand);
			ResultSet rs = ps.executeQuery();
			rs.next();
			//System.out.println(rs.getDouble("businessRating"));
			pw.println(rs.getDouble("businessRating"));
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
			pw.println("-1");
		}
	}

	public Connection getConn() {
		return conn;
	}
	
}
