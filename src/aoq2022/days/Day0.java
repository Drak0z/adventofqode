package aoq2022.days;

public class Day0 extends GenericDay {
	/**
	 * Alright recruits! You have been trained on a number of things. Our last bit
	 * of training, and to some it would seem the most important, is how to enter a
	 * correct answer.
	 * 
	 * What is the answer to: (256 x 8) - (182/7) =
	 * 
	 * (Most questions will involve a hint so that you can do a proof of your code.
	 * It will appear similar to the format below)
	 * 
	 * Hint: This number should be familiar to you. Especially if you have written a
	 * check recently, set a clock in long date format, watched a ball drop in Times
	 * Square 11 months ago... ummm... hmm... looked on a current calendar
	 * recently...
	 * 
	 * Please enter the answer as just an integer.
	 */
	@Override
	public String solve() {
		Integer i = (256 * 8) - (182 / 7);
		return "The answer to (256 * 8) - (182/7) is: " + i.toString();
	}
}
