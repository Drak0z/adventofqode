package aoq2022.days;

public class Day1 extends GenericDay {

	
	private String max = null;

	public void setMax(String m) {
		this.max = m;
	}

	public String getMax() {
		return this.max;
	}

	public Day1(String max) {
		setMax(max);
	}

	/**
	 * Santa is pleased and the time has come for you to save the world from the
	 * impending Martian invasion (no pressure).
	 * 
	 * No Doom!
	 * 
	 * They are uncertain of our intelligence. They are still trying to figure this
	 * out. They have sent us this first message and I think it is just to test our
	 * basic logic skills. Landing several probes on their planet would suggest we
	 * can do math but lets humor them.
	 * 
	 * Are they smart?
	 * 
	 * They issued us a message saying that they would disintegrate the North Pole
	 * with a Thermic Laser unless we answer the following question. Before we get
	 * to the question though a couple of notes:
	 * 
	 * We don't know that they can actually disintegrate anything Not sure what a
	 * "Thermic Laser" is... sounds like something made up.. UFO Melting City
	 * 
	 * The question we received is:
	 * 
	 * What is the sum all of the odd odd numbers from 1 to 1000?
	 * 
	 * Hint: The grand total of the odd odd numbers from 1 to 10 is: 15
	 * 
	 * The odd numbers from 1 to 10 are obviously: 1, 3, 5, 7, 9
	 * 
	 * However, we want the sum of the odd odd numbers: 1st number, 2nd Number, 3rd
	 * Number, 4th Number, 5th Number Odd, Even, Odd, Even, Odd 1 (odd), 3 (even), 5
	 * (odd), 7(even), 9 (odd) 1 + 5 + 9 = 15
	 * 
	 * Martians... Pfft.
	 */
	@Override
	public String solve() {

		if (getMax() == null) {
			return "Use setMax first!";
		}

		try {
			int max = Integer.parseInt(getMax());
			Integer n = 0;
			Integer odd = 0;
			Integer sum = 0;

			while (n < max) {
				n++;
				if (n % 2 == 1) {
					if ((odd = (odd + 1) % 2) == 1) {
						sum += n;
					}
				}
			}
			return "Sum of all odd odds until " + getMax() + " is: " + sum;
		} catch (Exception e) {
			return "Error! <br />" + e.getStackTrace();
		}
	}
}
