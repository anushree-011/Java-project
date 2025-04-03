package theatrebooking;
import java.time.LocalDate;
public class availabledates {
	static Object today;
	public static void dates() {
		LocalDate today = LocalDate.now();
		System.out.println(today);
		for (int i = 1; i <= 5; i++) {
	        LocalDate nextDate = today.plusDays(i);
	        System.out.println(nextDate);
		}
	}
	
	public static void seatsavailable() {
		System.out.println(today);
	}
}
