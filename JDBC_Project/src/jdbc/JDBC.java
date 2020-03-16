package jdbc;

import java.sql.*;
import java.util.Scanner;

public class JDBC {
	
	static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";  
	static final String DB_URL = "jdbc:derby://localhost:1527/JDBC Project";


	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		   try{
		      //STEP 2: Register JDBC driver
			   Class.forName(JDBC_DRIVER);

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");

	           conn = DriverManager.getConnection(DB_URL);

		      //STEP 4: Execute a query
		      String stmt = "SELECT GroupName, HeadWriter, YearFormed, Subject FROM WritingGroups WHERE GroupName = ?";
		      pstmt= conn.prepareStatement(stmt);
		      
	             Scanner scan =new Scanner(System.in);


	             System.out.println("Enter writing group you'd like to pull data of: ");
	             String groupName=scan.nextLine();
	             
	             pstmt.setString(1, groupName);
	             
	             ResultSet rs = pstmt.executeQuery();
		      
		      //STEP 5: Extract data from result set
	             
	             while(rs.next() ){
	                 System.out.println(rs.getString("GroupName") + " " +rs.getString("HeadWriter") + " " +
	                         rs.getDate("YearFormed") + " " +rs.getString("Subject"));
	             }

		      //STEP 6: Clean-up environment
		      rs.close();
		      //stmt.close();
		      conn.close();
		      scan.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
	}
		

}

		

