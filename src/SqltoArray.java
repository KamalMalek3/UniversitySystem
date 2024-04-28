import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqltoArray {
	

	  public String[] fill(String query) {
	        try {
	        	MySQLConnect cc = new MySQLConnect();
	    		cc.url = "jdbc:mysql://localhost:3306/oop2_project";
	    		cc.user = "root";
	    		cc.pass = "0000";
	    		cc.connect();

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
	            return null;
	        }
	    }
}
