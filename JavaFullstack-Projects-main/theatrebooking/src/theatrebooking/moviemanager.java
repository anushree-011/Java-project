package theatrebooking;
import java.sql.*;
import java.util.Scanner;
public class moviemanager {
	public static void addmovie(String movie_name,String mrelease_date,int seats) throws SQLException {
		String query = "INSERT INTO movies (movie_name, movie_out_date, seats_available) VALUES (?, ?, ?)";
		Connection con = booking.getConnection();
		PreparedStatement pst = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		pst.setString(1,movie_name);
		pst.setString(2,mrelease_date);
		pst.setInt(3, seats);
		int rows = pst.executeUpdate();
		ResultSet rs = pst.getGeneratedKeys();
        int movie_id = -1;
        if (rs.next()) {
            movie_id = rs.getInt(1); // First column = generated movie_id
        }
		System.out.println("Movie_id is : "+movie_id);
		System.out.println("Movie_Name is : "+movie_name);
		System.out.println("Release_date is : "+mrelease_date);
		System.out.println("Seats available : "+seats);
		System.out.println("No of Rows Affected : " + rows);
	}
	public static void main(String[] args) throws SQLException {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the no of movies you want to Entry Today : ");
			int N = sc.nextInt();
			sc.nextLine();
			while(N--> 0) {
				System.out.println("Enter the Movie Name : ");
				String movie = sc.nextLine();
				System.out.print("Enter Release date (yyyy-MM-dd): ");
				String date = sc.nextLine();
				System.out.println("Enter the Seats Availability : ");
				int seats = sc.nextInt();
				sc.nextLine();
				addmovie(movie,date,seats);
			}
			sc.close();
		}
	}
