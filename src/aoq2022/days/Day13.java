package aoq2022.days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import aoq2022.util.Util;

public class Day13 extends GenericDay {

	/**
	 * Day 13 - The North Pole Trust. Yes it's a real bank!
	 * 
	 * We have discovered that the Martians have opened a bank account on earth to
	 * fund their dastardly deeds! Thinking that they will need to do some damage
	 * here on earth they opened an account at the North Pole Trust.
	 * 
	 * At the North Pole Trust, they are firm believers in security. For this
	 * reason, they have 10-digit PINs for accessing your accounts at the ATM.
	 * However, for "security", the bank only logs a random (sequential but not
	 * consecutive) selection of 3 digits from the entered PIN (maybe the 3rd, 6th,
	 * and 9th digits).
	 * 
	 * What we know about PIN policy at North Pole Trust:
	 * 
	 * Digits can only be used once PINs are 10 digits long Through excessive
	 * sneakiness, we have stolen the bank's authentication log file. We need to
	 * find out the 10-digit PIN of the Martian.
	 * 
	 * The PIN data included in each log message is a random (sequential but not
	 * consecutive) selection of digits from the entered PIN i.e. The log might show
	 * "370", "539", "597" or "218" for the pin "5397420168" Be sure to examine the
	 * log data and extract only relevant log messages! What is the full 10-digit
	 * PIN for our Martian account holder "Mary Willis" (those crazy Martian names!)
	 */

	private class PinFinder {
		// private Map<Character, Set<Character>> pinSet;
		private Set<String> seqSet;
		private int[] pin = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		PinFinder() {
			this.seqSet = new HashSet<String>();
			// this.pinSet = new HashMap<Character, Set<Character>>();
		}

		public void addSequence(String seq) {
			this.seqSet.add(seq);
		}

		public void bubbleSort() {
			for (int i = 0; i < pin.length; i++) {
				for (int j = i + 1; j < pin.length; j++) {
					char iC = Character.forDigit(pin[i], 10);
					char jC = Character.forDigit(pin[j], 10);
					// now let's loop through our hash set, and find any string which contains both
					// of our numbers
					for (String s : seqSet) {
						if (s.indexOf(iC) != -1 && s.indexOf(jC) != -1) {
							// we have our characters which we can compare
							if (s.indexOf(jC) < s.indexOf(iC)) {
								// the character at j is meant to be before i
								int temp = pin[i];
								pin[i] = pin[j];
								pin[j] = temp;
							}
							break; // we have swapped something, we can break out of this for loop?
						}
					}

				}
			}
		}

		public String toString() {
			StringBuilder pinSb = new StringBuilder();
			for (int i = 0; i < pin.length; i++) {
				pinSb.append(pin[i]);
			}

			return "Probable pin: " + pinSb.toString();
		}
	}

	@Override
	public String solve() {
		long startTime = System.nanoTime();

		boolean test = false;
		String filename = test ? "day13_test.txt" : "day13.txt";
		Map<String, PinFinder> accountList = new HashMap<String, PinFinder>();

		try (Scanner input = new Scanner(new File("src/aoq2022/input/" + filename))) {
			while (input.hasNextLine()) {
				String line = input.nextLine();
				String[] record = line.split(Pattern.quote("|"));

				// record[0] = date/time
				// record[1] = account holder
				// record[2] = SUCCESS/FAIL
				// record[3] = pin sequence
				// String dateTime = record[0].trim();
				String accountName = record[1].trim();
				String successFail = record[2].trim();
				String pinSequence = record[3].trim();
				if (!"SUCCESS".equals(successFail))
					continue; // This was not a successful attempt
				if (accountList.containsKey(accountName)) {
					accountList.get(accountName).addSequence(pinSequence);
				} else {
					PinFinder pf = new PinFinder();
					pf.addSequence(pinSequence);
					accountList.put(accountName, pf);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("Done reading file. Time since start: " + Util.getHumanReadableTime(System.nanoTime() - startTime));
		
		// Everything is now read..
		for (String name : accountList.keySet()) {
			if (Day7.isMartian(name)) {
				PinFinder pf = accountList.get(name);
				long tempTime = System.nanoTime();
				pf.bubbleSort();
				long tempEndTime = System.nanoTime();
				System.out
						.println("Name: " + name + " is a probable martian, and PinFinder result (after sort): " + pf + " Calculating pin took: " + Util.getHumanReadableTime(tempEndTime - tempTime) + "");
			}
		}
		System.out.println("End-to-end time: " + Util.getHumanReadableTime(System.nanoTime() - startTime) + "s");
		return "";

	}
	
	
}
