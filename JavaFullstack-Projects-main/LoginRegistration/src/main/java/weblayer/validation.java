package weblayer;
import jakarta.servlet.RequestDispatcher;
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
@WebServlet("/validation")
public class validation extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter object = response.getWriter();){
			
			response.setContentType("text/html");
			
			String usname = request.getParameter("uname");
			String passwo = request.getParameter("passw");
			
			if(usname.equals("admin") && passwo.equals("admin@123")) {
				object.println("welcome admin");
				RequestDispatcher rd = request.getRequestDispatcher("admin.html");
				rd.forward(request, response);
				
			}else {
				Class.forName("com.mysql.cj.jdbc.Driver");
				final String URL = "jdbc:mysql://localhost:3306/loginform";
			    final String USERNAME = "root";
			    final String PASSWORD = "120405brthy";
			    
			    String query = "select * from logintable";
			    Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			    Statement st = conn.createStatement();
			    ResultSet rs = st.executeQuery(query);
			    
			    int f=0;
			    while(rs.next()) {
			    	if(usname.equals(rs.getString(1)) && passwo.equals(rs.getString(2))) {
			    		f=1;
			    		object.println("Welcome User");
			    		RequestDispatcher rd = request.getRequestDispatcher("user.html");
			    		rd.forward(request, response);
			    	}
			    }
			    if(f==0) {
			    	object.println("Sorry the user is not Registered Or The username may be invalid");
			    	RequestDispatcher rd = request.getRequestDispatcher("index.html");
			    	rd.include(request, response);
			    }
		}
	    
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
