import java.sql.*;

public class MySQLConnect {
	String url,user, pass;
	public Connection conn= null;
	public void connect ()
	{
		try {
			// register and Load JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Make connection to the DBMS
			 
		conn = DriverManager.getConnection(url, user, pass);
		System.out.println("Connection established!");
			
		 } 
		 catch (Exception e) {
			System.out.println("exception " + e);
		 }
	}
		
	public ResultSet SelectQuery(String query) {
		try {
			
			// Create statement
			Statement stmt = conn.createStatement();

			// Print the users table
			ResultSet rs = stmt.executeQuery(query);
			return rs;
					 } 
		 catch (Exception e) {
			System.out.println("exception " + e);
			return null;
		 }
	}

	public ResultSet SelectPs(PreparedStatement ps) {
			try {
				
				ResultSet rs = ps.executeQuery();
				return rs;
						 } 
			 catch (Exception e) {
				System.out.println("exception " + e);
				return null;
			 }

	}

	public int  InsertUpdateDelete(String query)
	{
		try {
			
			// Create statement
			Statement stmt = conn.createStatement();
			int result=stmt.executeUpdate(query);
			return result;
			} 
		 catch (Exception e) {
			System.out.println("exception " + e);
			return 0;
		 }
		
	}

}
