package theatrebooking;
import java.sql.*;
import java.util.Scanner;

public class Bookingsystem {
	public static void main(String[] args) throws SQLException {
	display();
	}

	private static void display() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("WELCOME TO OUR THEATRE");
		System.out.println("Please Enter Your Name : ");
		String Customername = sc.nextLine();
		System.out.println("MOVIES AVAILABLE RIGHT NOW ");
		String query = "select * from movies";
		Connection con = booking.getConnection();
		Statement st = con.createStatement();
		ResultSet rs =  st.executeQuery(query);
		while(rs.next()) {
			System.out.println("Movie NAME : " + rs.getString(2));
		}
		System.out.println("Enter the Movie Name : ");
		String Name = sc .nextLine();
		String query2 = "SELECT SEATS_AVAILABLE FROM MOVIES WHERE MOVIE_NAME = ?";
		PreparedStatement pst = con.prepareStatement(query2);
		
		pst.setString(1, Name);
		ResultSet rs1 = pst.executeQuery();
        if (rs1.next()) {
            System.out.println("Seats Available for "+ Name + " : " + rs1.getInt("SEATS_AVAILABLE"));
        } else {
            System.out.println("Movie not found.");
        }
        availabledates.dates();
        System.out.println("Please Enter the Date");
        String date = sc.nextLine();
//        Connection con2 = booking.getConnection();
//		Statement st1 = con.createStatement();
        String query3= "select no_of_seats from bookings where movie_name=? and movie_date=?"; 
        PreparedStatement pst1 = con.prepareStatement(query3);
        pst1.setString(1, Name);
        pst1.setString(2, date);
        ResultSet rs2 = pst1.executeQuery();
        rs2.next();
        System.out.println("Available seats :"+ rs2.getString("no_of_seats"));
        System.out.println("Enter no of seats needed:");
        int n=sc.nextInt();
        while(n-->0) {
        	System.out.println("Enter seat: ");
        	String seat=sc.next();
        	 String sql = "UPDATE bookings " +
                     "SET no_of_seats = JSON_REMOVE(no_of_seats, JSON_UNQUOTE(JSON_SEARCH(no_of_seats, 'one', ?))) " +
                     "WHERE movie_name = ? AND movie_date = ?";
        	 PreparedStatement stmt = con.prepareStatement(sql);
        	 stmt.setString(1, seat);  // Seat to remove
             stmt.setString(2, Name);
             stmt.setString(3, date);
             int rowsUpdated = stmt.executeUpdate();
        }
        System.out.println("Booked successfully");
        
	}
	

}
