import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlInteract {
	private MySQLConnect cc = new MySQLConnect();
	private Popup popup = new Popup();

	public SqlInteract() {
		cc.url = "jdbc:mysql://localhost:3306/oop2_project";
		cc.user = "root";
		cc.pass = "0000";
		cc.connect();
	}

	public String[] fill(String query) {
		try {
			// Create statement
			Statement stmt = cc.conn.createStatement();

			// Execute the query
			ResultSet rs = stmt.executeQuery(query);

			// Get the number of columns
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();

			// List to store the results
			List<String> resultList = new ArrayList<>();

			// Iterate through the result set
			while (rs.next()) {
				// Iterate through columns and add each column value to the list
				for (int i = 1; i <= numColumns; i++) {
					resultList.add(rs.getString(i));
				}
			}

			// Convert list to array
			String[] resultArray = new String[resultList.size()];
			resultArray = resultList.toArray(resultArray);

			// Close the result set and statement
			rs.close();
			stmt.close();

			return resultArray;
		} catch (Exception e) {
			System.out.println("exception " + e);
			popup.showError("Error while retrieving data from database");
			return null;
		}
	}

	public int perform(String query){
		int result=0;
		System.out.println(query);
		try {
			result = cc.InsertUpdateDelete(query);
		} catch (Exception e1) {
			popup.showError("Error while editing database");
			return 0;
		}
		return result;
	}
}