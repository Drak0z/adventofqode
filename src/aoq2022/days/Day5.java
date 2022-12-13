package aoq2022.days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day5 extends GenericDay {
	public Day5() {
	}

	/**
	 * Day 5 - Flummoxing Flighty Floors
	 * 
	 * Now that you have the serial number for the Martian transmission, you need to
	 * bring it to the number crunchers in Blinky Park. However, being a SUPER
	 * SECRET organization, they have some pretty tight security and are very
	 * difficult to pin down. In fact, their office changes floors every single day
	 * and, each day, the Blinky Park security team issues the secret elevator
	 * instructions to those who need to know (and today, that's YOU!)
	 * 
	 * Robots in an Elevator!
	 * 
	 * The elevator instructions are shared as a big string:
	 * 
	 * an open parenthesis "(" means go up one floor a close parenthesis ")" means
	 * go down one floor sometimes, a parenthesis is proceeded by a single digit
	 * which is a multiplier for the next character (i.e. "5(" would mean "(((((")
	 * The instructions assume you are starting on the ground floor (floor 0) Floors
	 * >= 0 are above ground, and Floor < 0 are basement (a fact that is largely
	 * irrelevant to the completion of this task) The elevator only has "up one
	 * floor" and "down one floor" buttons. It's a good thing you are a robot, or
	 * pressing these buttons might become tedius! The answer you submit should be
	 * the floor on which Blinky Park is located.
	 * 
	 * Just to be sure we're on the same page, we've pulled the following examples
	 * from the "Blinky Park Standard Security Procedures and Operating Governance
	 * Committee Guidelines for the Prevention of Unexpected Migration of Secrets"
	 * (BPSSPOGCGPUMS).
	 * 
	 * "((" or "2(" would both mean go up two floors - i.e. Blinky Park is located
	 * on floor 2 "(())" or "()()" or "2(2)" would all mean Blinky Park is located
	 * on floor 0 "((())()(())" would mean Blinky Park is located on floor 1 Today's
	 * full set of codes can be download here.
	 * 
	 * What floor are we heading to?
	 */
	@Override
	public String solve() {
		Integer floor = 0;
		Integer digit = 1;
		String filename = "day5.txt";

		try (Scanner input = new Scanner(new File("src/aoq2022/input/" + filename))) {
			while (input.hasNextLine()) {
				String line = input.nextLine();
				for (int i = 0; i < line.length(); i++) {
					switch (line.charAt(i)) {
					case '(':
						floor += digit;
						digit = 1;
						break;
					case ')':
						floor -= digit;
						digit = 1;
						break;
					default:
						try {
							if (digit != 1) { /* error? */ }
							digit = Integer.parseInt(line.charAt(i) + "");
						} finally {}
					}
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return "Secret level stored in " + filename + " is at: " + floor.toString();

	}
}
