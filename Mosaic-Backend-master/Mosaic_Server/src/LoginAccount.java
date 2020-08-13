import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAccount {
	private String username;
	private String password;
	
	public LoginAccount(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public int loginUser() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			String s = "jdbc:mysql://168.235.102.179:3306/mosaic?";
			conn = DriverManager.getConnection(s, "mosaic", "DR5@FL9#4ndL!");
			ps = conn.prepareStatement("SELECT * from user WHERE username = ?");
			ps.setString(1, this.username);
			rs = ps.executeQuery();
			if(!rs.next())
				result = -1; //User doesn't exist;
			else {
				String password = rs.getString("password");
				if(this.password.equals(password))
					result = rs.getInt("userID");
				else
					result = -2; //Wrong password;
			}
		} catch(SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			sqle.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		LoginAccount test = new LoginAccount("testuser", "testpass");
		System.out.println(test.loginUser());
	}
}
