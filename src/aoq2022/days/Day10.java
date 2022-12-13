package aoq2022.days;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Day10 extends GenericDay {

	/**
	 * Day 10 - Wait, how many days do they have in a year??
	 * 
	 * The Martians have sent a message letting us know that it wasn't just the
	 * Probes that had them ticked off. Their message said 67 years ago today. They
	 * received something from earth a long while back that started this all for
	 * them. The first signs of human aggression. We checked and Dec 10, 1955 wasn't
	 * anything special.
	 * 
	 * Cause Science!
	 * 
	 * Our crack team of scientists (Santa's elves) have realized they mean Martian
	 * years. Each Martian year is 686 Earth days long.
	 * 
	 * To help our historians, can you narrow it down to a year and month? Please
	 * give your answer as yyyy-mm. For February 1993 would be written as 1993-02.
	 * 
	 * Sending signals!
	 * 
	 * Don't forget all the (Earth) leap years that have occurred in the past 67
	 * Martian years. Date libraries are probably your friend!
	 */
	@Override
	public String solve() {
		double martianDays = 686 * 67;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse("2022-12-10"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_MONTH, (int) (Math.round(martianDays) * -1));
		return sdf.format(cal.getTime());

	}
}
