package webLayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.RequestDispatcher;

@WebServlet("/Validation")
public class Validation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		try(PrintWriter out=response.getWriter();){
		  response.setContentType("text/html");
		  String username = request.getParameter("uname");
	      String password = request.getParameter("pwd");
	      Class.forName("com.mysql.cj.jdbc.Driver");
		  String url = "jdbc:mysql://localhost:3306/register";
		  String user = "root"; 
		  String pass= "anushree@1107";
		  
		  Connection conn = DriverManager.getConnection(url, user, pass);
          Statement smt=conn.createStatement();
	      ResultSet rs=smt.executeQuery("select * from registeruser");
	      
	      while(rs.next()) {
	    	  String dbuser=rs.getString("user_name");
	    	  String dbpass=rs.getString("password");
	          if(username.equals(dbuser) && password.equals(dbpass)) {
	        	  out.println("Welcome user");
	        	  javax.servlet.RequestDispatcher rd=request.getRequestDispatcher("welcome.html");
	        	  rd.forward(request, response);
	        	  
	          }
	          else
	          {
	        	  out.println("User name or password is incorrect");
	        	  javax.servlet.RequestDispatcher rd=request.getRequestDispatcher("index.html");
	        	  rd.include(request, response);
	        	  break;
	          }
	      }
	      }
	      catch (SQLException e) {
		
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    
}}
