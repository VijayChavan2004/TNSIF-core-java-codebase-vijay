package com.tnsif.daysixteen.jdbcoperationsdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCPreparedstmt {
	//private static final String URL="jdbc:mysql://localhost:3306/PP";
    //private static final String USERNAME="root";
	//private static final String password="";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver load Successfully");
		
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/dypatil","root","root");
		System.out.println("Connection created successfully....!");
		
				
	Statement stmt=conn.createStatement();
		//String query = "create table pramod(stdid int,stdname varchar(20),subject varchar(20), marks int)";

		//boolean flag = stmt.execute(query);
		 
		 //A  PreparedStatement is a object to insert a record into a database table 
		//"(?,?,?,?)", which contains four placeholders marked by question marks
		//Placeholder represents the four columns of the table
	
			PreparedStatement psmt=conn.prepareStatement("insert into student values(?,?,?,?)");
		                                  //	String q="insert into student values(102,'rohit',' java',475)";

			
			
			psmt.setInt(1, 1003);
			psmt.setString(2, "Shubham");
			psmt.setString(3, "Java");
			psmt.setDouble(4, 67.98);
			psmt.addBatch();
			
			
			psmt.setInt(1, 9632);
			psmt.setString(2, "Niranjan");
			psmt.setString(3, "SQL Developer");
			psmt.setDouble(4, 67.98);
		    psmt.addBatch();
			
			psmt.setInt(1, 20002);
			psmt.setString(2, "Chaitali");
			psmt.setString(3, "DBMS");
			psmt.setDouble(4, 67.98);
			psmt.addBatch();
			
			psmt.setInt(1, 1004);
			psmt.setString(2, "Harshada");
			psmt.setString(3, "DB");
			psmt.setDouble(4, 67.98);
			psmt.addBatch();			
			
			
		//values are set with actual values 	
		psmt.setInt(1,1005);
		psmt.setString(2,"Shubham");
		psmt.setString(3,"Cilent");
		psmt.setDouble(4, 67.98);
		psmt.addBatch();
		//After setting the values of the placeholders, the addBatch() method
		
		psmt.setInt(1,1006);
		psmt.setString(2,"nilesh");
		psmt.setString(3,"adv java");
		psmt.setDouble(4, 67.98);
		psmt.addBatch();
		
		psmt.setInt(1,1007);
		psmt.setString(2,"dhangar");
		psmt.setString(3,"xamp");
		psmt.setDouble(4, 67.98);
		psmt.addBatch();
		
		psmt.setInt(1,1008);
		psmt.setString(2,"Shubham");
		psmt.setString(3,"Cilent");
		psmt.setDouble(4, 67.98);
		psmt.addBatch();
		//After setting the values of the placeholders, the addBatch() method
		
		psmt.setInt(1,1009);
		psmt.setString(2,"nilesh");
		psmt.setString(3,"adv java");
		psmt.setDouble(4, 67.98);
		psmt.addBatch();
		
		psmt.setInt(1,1010);
		psmt.setString(2,"dhangar");
		psmt.setString(3,"xamp");
		psmt.setDouble(4, 67.98);
		psmt.addBatch();
			
		psmt.setInt(1, 1011);
		psmt.setString(2, "Abhishek");
		psmt.setString(3, "Power BI");
		psmt.setDouble(4, 67.98);
		psmt.addBatch();
		
		psmt.setInt(1, 1012);
		psmt.setString(2, "Mahesh");
		psmt.setString(3, "AWS");
		psmt.setDouble(4, 67.98);
		psmt.addBatch();
		
		
		psmt.setInt(1, 20147);
		psmt.setString(2, "Kalyani");
		psmt.setString(3, "Html");
		psmt.setDouble(4, 67.98);
		psmt.addBatch();
	
	
		/*String query1="UPDATE pramod SET stdname='pramod' where stdid=112";
		stmt.addBatch(query1);*/
		
		int [] a=stmt.executeBatch();
		a=psmt.executeBatch();
		System.out.println(a.length+" row affected....!");
		psmt.close();
		conn.close();
		//stmt.close();

	}

}