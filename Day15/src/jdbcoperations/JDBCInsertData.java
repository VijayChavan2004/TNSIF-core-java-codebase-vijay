package jdbcoperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCInsertData {

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
			
//			//step 4: write a query
//			
//			String q="insert into student values(104, 'Aditya','Java',89.97)";
//			
//			
//			boolean s=stmt.execute(q);
			
			Scanner sc=new Scanner(System.in);
			int id=0;
			String name="";
			String subject="";
		
			double duration=0.0;
			PreparedStatement psmt=null;
			
			System.out.println("Enter the Student Id: ");
			id=sc.nextInt();
			System.out.println("Enter the Student name: ");
			name=sc.next();
			System.out.println("Enter the Subject: ");
			subject=sc.next();
			System.out.println("Enter the course duration: ");
			duration=sc.nextDouble();
			psmt=con.prepareStatement("insert into student values(?,?,?,?)");
			psmt.setInt(1,id);
			psmt.setString(2, name);
			psmt.setString(3, subject);
			psmt.setDouble(4, duration);
			int row =psmt.executeUpdate();
			System.out.println(row+" row affected");
		}
		catch(Exception e)
		{
			System.err.println(e);
		}

	}

}
