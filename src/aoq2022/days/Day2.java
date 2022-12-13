package aoq2022.days;

public class Day2 extends GenericDay {
	private String max = null;

	public void setMax(String m) {
		this.max = m;
	}

	public String getMax() {
		return this.max;
	}

	/**
	 * Day 2 - Even If we Answer this Correctly, Twitter May Still Be In Trouble 
	 * 
	 * Ok so the Martians think that we are sufficiently smart enough to take over
	 * and rule... yay?!? Or at least when it comes to odd numbers.. seeing as they
	 * would like to attack us on some even numbered days as well they thought it
	 * best to see if we understand even numbers also.
	 * 
	 * Stick em up!
	 * 
	 * They have decided to see if we can figure out the following question:
	 * 
	 * BTW if you are wondering why we have voluntarily decided to comply and take
	 * these demeaning tests from the Martians, the answer is two fold:
	 * 
	 * If we don't do it they said they will send a delegate to take over Earth's
	 * beloved communication service "Twitter" and run it into the ground! Wait?..
	 * What?.. If we don't do it, we won't have much of a programming challenge on
	 * our hands! Twittered!
	 * 
	 * Ok... so the question:
	 * 
	 * Find the sum of all of the even numbers between 1 and 1000 (inclusive) that
	 * contain one or more 4's in it:
	 * 
	 * Hint: From 1 to 20 there are 2 even numbers with 4's in them (4 and 14). The
	 * sum is 18.
	 */
	@Override
	public String solve() {
		if (getMax() == null) {
			setMax(getRequest().queryMap().value("max"));
		}

		try {
			int max = Integer.parseInt(getMax());
			int sum = 0;
			for (Integer i = 1; i <= max; i++) {
				if (i%2 == 0 && i.toString().contains("4")) {
					sum += i;
				}
			}
			return "Sum of all evens with a 4 in them until " + getMax() + " is: " + sum;
		} catch (Exception e) {
			return "Error! <br />" + e.getStackTrace();
		}
	}
}
