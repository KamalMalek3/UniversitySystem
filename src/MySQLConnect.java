import java.sql.*;

public class MySQLConnect {
	String url, user, pass;
	public Connection conn = null;

	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);

		} catch (Exception e) {
		}
	}

	public ResultSet SelectQuery(String query) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (Exception e) {
			return null;
		}
	}

	public ResultSet SelectPs(PreparedStatement ps) {
		try {

			ResultSet rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			return null;
		}

	}

	public int InsertUpdateDelete(String query) {
		try {

			// Create statement
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate(query);
			return result;
		} catch (Exception e) {
			return 0;
		}

	}

}
