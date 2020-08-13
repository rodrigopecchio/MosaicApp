import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateAccount {
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	
	public CreateAccount(String username, String password, String firstname, String lastname) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public int createUserAccount() {
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
			if(rs.next())
				result = -1; //Username exists
			else {
				PreparedStatement pst = conn.prepareStatement("INSERT INTO user(username, password, firstname, lastname) VALUES (?, ?, ?, ?)");
				pst.setString(1, username);
				pst.setString(2, password);
				pst.setString(3, firstname);
				pst.setString(4, lastname);
				pst.executeUpdate();
				PreparedStatement psta = conn.prepareStatement("SELECT userID from user WHERE username = ?");
				psta.setString(1, username);
				rs = ps.executeQuery();
				rs.next();
				result = rs.getInt("userID");
			}
		} catch(SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			sqle.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		CreateAccount newUser = new CreateAccount("testuseragain", "testpass", "admin", "admin");
		System.out.println(newUser.createUserAccount());
	}
}
