import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
	
	public static DBUtil dbUtil;
	
	public Main() {
		dbUtil = new DBUtil("168.235.102.179",3306,"mosaic","DR5@FL9#4ndL!","mosaic");
	}

	public DBUtil getDBUtil() {
		return dbUtil;
	}
	
	public static void main(String[] args) {

	}
	
	//RUN SERVER
	
	
	
}
