package theatrebooking;
import java.sql.*;

public class available {
    String seat;
    boolean flag;
    available(String s, boolean f){
    	this.seat=s;
    	this.flag=f;
    }
public static void displaytheatreinfo() throws SQLException {
	String query = "Select * from available_seats";
	Connection con = booking.getConnection();
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery(query);
	int a=1;
	while(rs.next() ) {
		if(a<6 && rs.getInt(2)==1) {
		System.out.print(rs.getString(1) + " ");
		a++;
		    if(a==6) {
			    System.out.println();
			    a=1;}
		}
		else {
			System.out.print("*");
		}
		
		if(rs.getInt(2)==0)
			System.out.println("Already Booked");
	}
	

}

}

