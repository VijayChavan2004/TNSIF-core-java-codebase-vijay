package jdbcoperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ResultSet {

	public static void main(String[] args) {
		try {
			//step 1: Load or register the driver
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver is load successfully");
			
			//step 2: create connection 
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dypatil", "root", "root");
			System.out.println("Connection created successfully");
			
			//step 3: create a statement 
			Statement stmt=con.createStatement();
			
			System.out.println("Statement created succesfully");
			
			
			java.sql.ResultSet rs=stmt.executeQuery("select * from student");
			System.out.println("stdid\t name \t subject \t duration");
			//step 4: write a query
			
			//String q="insert into student values(103, 'Aditya','Java',89.97)";
			
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+
					"\t"+rs.getString(3)+"\t"+rs.getDouble(4));
		}
		
		}
		catch(Exception e)
		{
			System.err.println(e);
		}

	}

}
