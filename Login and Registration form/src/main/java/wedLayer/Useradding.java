package wedLayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Useradding")
public class Useradding extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
	      try(PrintWriter object=response.getWriter()){
	    	  String username = request.getParameter("u");
		      String password = request.getParameter("p");
		      String ConfirmPass = request.getParameter("cp");
	      
	      
	   
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    String url = "jdbc:mysql://localhost:3306/register";
		  	  String user = "root"; 
		  	  String pass= your_password;
		  
		    Connection conn = DriverManager.getConnection(url, user, pass);
		    String query1="insert into registeruser values(?,?,?);";
		    PreparedStatement pstmt = conn.prepareStatement(query1);

		           // Set parameter values
		           pstmt.setString(1, username);
		           pstmt.setString(2, password);
		           pstmt.setString(3, ConfirmPass);

		           int value = pstmt.executeUpdate();
		    if(value==1) {
		    	object.println("Record inserted");
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}}
	

