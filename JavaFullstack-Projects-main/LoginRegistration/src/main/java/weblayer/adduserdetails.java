package weblayer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
@WebServlet("/adduserdetails")
public class adduserdetails extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter object = response.getWriter();){
		String usname = request.getParameter("u");
		String passwo = request.getParameter("p");
		String cpass = request.getParameter("cp");
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		final String URL = "jdbc:mysql://localhost:3306/loginform";
	    final String USERNAME = "root";
	    final String PASSWORD = "120405brthy";
	    
	    Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
	    String query = "insert into logintable values(?,?);";
	    PreparedStatement st = conn.prepareStatement(query);
	    st.setString(1,usname);
	    st.setString(2, passwo);
	    int row = st.executeUpdate();
	    
	    object.println("No of rows affected " + row);
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
