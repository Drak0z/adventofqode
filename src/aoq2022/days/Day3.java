package aoq2022.days;

public class Day3 extends GenericDay {
	private String max = null;

	public void setMax(String m) {
		this.max = m;
	}

	public String getMax() {
		return this.max;
	}

	/**
	 * Day 3 - What is their Primary motive??
	 * 
	 * So they now know we understand odd numbers and even numbers.. You think the
	 * many rovers and satellites making it to their planet would have tipped them
	 * off to this. None the less, they have decided to continue testing our basic
	 * math skills. This test is a bit more sinister.
	 * 
	 * Testing the humans!
	 * 
	 * They have sent us a communication letting us know they have sabotaged Santa's
	 * Toy Production line!
	 * 
	 * Santa's production line makes exactly 1,000,003 toys every day. The Martians
	 * noticed immediately that this was a prime number and thought they would test
	 * us on this.
	 * 
	 * Santa's toy line
	 * 
	 * The Martians have coated every Prime numbered toy to come off the production
	 * line today with an invisible film that will make the toy turn to dust. The
	 * film is triggered when the toy is held by a child and the child's laughter is
	 * heard... wow, that's pretty nasty.. this is who we are dealing with!
	 * 
	 * Prime dust!
	 * 
	 * We need to let Santa know how many toys are going to be affected. So... how
	 * many prime number toys are going to come off the production line today (count
	 * how many primes there are between 1 and 1,000,003 inclusive)?
	 * 
	 * Hint: Between 1 and 29 inclusive are 10 primes: (2, 3, 5, 7, 11, 13, 17, 19,
	 * 23, 29)
	 */
	@Override
	public String solve() {
		if (getMax() == null) {
			setMax(getRequest().queryMap().value("max"));
		}

		try {
			long max = Integer.parseInt(getMax());
			int sum = 0;
			for (long i = 1; i <= max; i++) {
				if (isPrime(i)) {
					sum ++;
				}
			}
			return "Sum of all primes until " + getMax() + " is: " + sum;
		} catch (Exception e) {
			return "Error! <br />" + e.getStackTrace();
		}
	}
	
	public boolean isPrime(long num) {
        if (num <= 1) {
            return false;
        }
        for (long i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
	}
}
