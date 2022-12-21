package aoq2022.days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day20 extends GenericDay {
	/**
	 * Day 20 - The Return of Fred the Elf!
	 * 
	 * The Martians have taken several elves hostage! They sent down a ship with
	 * troops to Santa's village.
	 * 
	 * The elves were all terrified by this. They ran and screamed... Except one.
	 * Fred the elf (you may remember this persnickety elf from last year), still
	 * harboring contempt for all things Christmas, volunteered to be a hostage.
	 * Also said he was ok with some torture if required.
	 * 
	 * The Martians explained they had stolen Santa's laptop and needed to break
	 * into it.
	 * 
	 * The Martians were able to extract Santa's hashed password of nodflgru. Fred,
	 * always helpful, explains that rather than using a "real" hashing function
	 * such as SHA-256 or, better yet, a specifically designed password hashing
	 * function such as Argon2, Santa's laptop (running the Elven fork of Solaris)
	 * is using the elf designed hashing function SHA-DES (they thought it sounded
	 * cool).
	 * 
	 * To compute the SHA-DES hash of a password, you start with the plain text
	 * password and apply a series of operations to it:
	 * 
	 * swap position X with position Y means that the letters at indexes X and Y
	 * (counting from 0) should be swapped. swap letter X with letter Y means that
	 * the letters X and Y should be swapped (regardless of where they appear in the
	 * string). rotate left/right X steps means that the whole string should be
	 * rotated; for example, one right rotation would turn abcd into dabc. rotate
	 * based on position of letter X means that the whole string should be rotated
	 * to the right based on the index of letter X (counting from 0) as determined
	 * before this * instruction does any rotations. Once the index is determined,
	 * rotate the string to the right one time, plus a number of times equal to that
	 * index, plus one additional time if the index was at least 4. reverse
	 * positions X through Y means that the span of letters at indexes X through Y
	 * (including the letters at X and Y) should be reversed in order. move position
	 * X to position Y means that the letter which is at index X should be removed
	 * from the string, then inserted such that it ends up at index Y. Consider, for
	 * example, the following hash function instructions for a simplified hashing
	 * function.
	 * 
	 * rotate based on position of letter y swap letter d with letter n swap
	 * position 3 with position 4 reverse positions 0 through 4 rotate right 3 steps
	 * swap position 4 with position 0 rotate based on position of letter d move
	 * position 0 to position 2 rotate based on position of letter c swap letter a
	 * with letter n When applied to the password 'candy', they produce the hashed
	 * password 'cnayd'
	 * 
	 * rotate based on position of letter y ==> ycand swap letter d with letter n
	 * ==> ycadn swap position 3 with position 4 ==> ycand reverse positions 0
	 * through 4 ==> dnacy rotate right 3 steps ==> acydn swap position 4 with
	 * position 0 ==> ncyda rotate based on position of letter d ==> cydan move
	 * position 0 to position 2 ==> ydcan rotate based on position of letter c ==>
	 * canyd swap letter a with letter n ==> cnayd
	 * 
	 * Of course, Santa's cryptographers designed a much more complex set of hashing
	 * instructions. Here is the full set of hashing instructions used to generate
	 * the actual password hash for Santa's laptop.
	 * 
	 * What is Santa's laptop password (and, coincidentally, the holiday beverage he
	 * hopes one day to mix up)?
	 */

	/**
	 * - swap position X with position Y - means that the letters at indexes X and Y
	 * (counting from 0) should be swapped.
	 * 
	 * @param input
	 * @param x
	 * @param y
	 * @return
	 */
	public String swap(String input, int x, int y) {
		StringBuilder output = new StringBuilder(input);
		char temp = output.charAt(x);
		output.setCharAt(x, output.charAt(y));
		output.setCharAt(y, temp);
		return output.toString();
	}

	/**
	 * - rotate left/right X steps - means that the whole string should be rotated;
	 * for example, one right rotation would turn abcd into dabc.
	 * 
	 * @param input
	 * @param direction
	 * @param r
	 * @return
	 */
	public String rotate(String input, int direction, int r) {
		r *= direction;

		StringBuilder output = new StringBuilder(input);
		;
		for (int i = 0; i < input.length(); i++) {
			int q = (i + input.length() + r) % input.length();
			output.setCharAt(q, input.charAt(i));
		}
		return output.toString();
	}

	public String unrotateBased(String input, int direction, String c) {
		String unrotatedTest = rotate(input, direction * -1, 4); // rotate left by 4 (because... that worked?)
		String rotateTest = "";
		int s = 0;
		// keep rotating until our "unrotated" value comes up with the original input
		while (!rotateTest.equals(input) && s <= input.length()) {
			s++;
			unrotatedTest = rotate(unrotatedTest, direction, s);
			rotateTest = rotateBased(unrotatedTest, direction, c);
		}
		return unrotatedTest;
	}

	/**
	 * rotate based on position of letter X - means that the whole string should be
	 * rotated to the right based on the index of letter X (counting from 0) as
	 * determined before this * instruction does any rotations. Once the index is
	 * determined, rotate the string to the right one time, plus a number of times
	 * equal to that index, plus one additional time if the index was at least 4.
	 * 
	 * @param input
	 * @param direction
	 * @param c
	 * @return
	 */
	public String rotateBased(String input, int direction, String c) {
		int i = input.indexOf(c);
		if (i >= 4)
			i++;
		i++;
		return rotate(input, direction, i);
	}

	/**
	 * - move position X to position Y - means that the letter which is at index X
	 * should be removed from the string, then inserted such that it ends up at
	 * index Y.
	 * 
	 * @param input
	 * @param x
	 * @param y
	 * @return
	 */
	public String move(String input, int x, int y) {
		StringBuilder output = new StringBuilder(input);
		char temp = input.charAt(x);
		output.deleteCharAt(x);
		output.insert(y, temp);
		return output.toString();
	}

	/**
	 * - reverse positions X through Y - means that the span of letters at indexes X
	 * through Y (including the letters at X and Y) should be reversed in order.
	 * 
	 * @param input
	 * @param x
	 * @param y
	 * @return
	 */
	public String reverse(String input, int x, int y) {
		if (x > y)
			return input;

		StringBuilder leftSb = new StringBuilder(input.substring(0, x));
		StringBuilder middleSb = new StringBuilder(input.substring(x, y + 1));
		StringBuilder rightSb = new StringBuilder(input.substring(y + 1));
		middleSb.reverse();

		return leftSb.toString() + middleSb.toString() + rightSb.toString();
	}

	public String parse(String input, String instruction) {
		/*
		 * - swap position X with position Y - means that the letters at indexes X and Y
		 * (counting from 0) should be swapped.
		 * 
		 * - swap letter X with letter Y - means that the letters X and Y should be
		 * swapped (regardless of where they appear in the string).
		 * 
		 * - rotate left/right X steps - means that the whole string should be rotated;
		 * for example, one right rotation would turn abcd into dabc.
		 * 
		 * - rotate based on position of letter X - means that the whole string should
		 * be rotated to the right based on the index of letter X (counting from 0) as
		 * determined before this * instruction does any rotations. Once the index is
		 * determined, rotate the string to the right one time, plus a number of times
		 * equal to that index, plus one additional time if the index was at least 4.
		 * 
		 * - reverse positions X through Y - means that the span of letters at indexes X
		 * through Y (including the letters at X and Y) should be reversed in order.
		 * 
		 * - move position X to position Y - means that the letter which is at index X
		 * should be removed from the string, then inserted such that it ends up at
		 * index Y.
		 */
		String[] instructions = instruction.split(" ");
		String output = "";
		int x = -1;
		int y = -1;
		switch (instructions[0]) {
		case "swap":
		case "unswap": // swap is directly reversible
			switch (instructions[1]) {
			case "position":
				x = Integer.parseInt(instructions[2]);
				y = Integer.parseInt(instructions[5]);
				break;
			case "letter":
				x = input.indexOf(instructions[2]);
				y = input.indexOf(instructions[5]);
				break;
			}
			output = swap(input, x, y);
			break;
		case "reverse":
		case "unreverse": // reverse is directly reversible
			x = Integer.parseInt(instructions[2]);
			y = Integer.parseInt(instructions[4]);
			output = reverse(input, x, y);
			break;
		case "unmove":
			y = Integer.parseInt(instructions[2]);
			x = Integer.parseInt(instructions[5]);
			output = move(input, x, y);
			break;
		case "move":
			x = Integer.parseInt(instructions[2]);
			y = Integer.parseInt(instructions[5]);
			output = move(input, x, y);
			break;
		case "unrotate":
		case "rotate":
			int r = -1;
			int direction = 1; // rotate right
			switch (instructions[1]) {
			case "based": // undoing this one is hard....
				direction = 1;
				String c = instructions[6];
				if ("unrotate".equals(instructions[0])) {
					output = unrotateBased(input, direction, c);
					;
				} else {
					output = rotateBased(input, direction, c);
				}
				break;
			case "left":
			case "right":
				direction = "right".equals(instructions[1]) ? 1 : -1;
				r = Integer.parseInt(instructions[2]);
				if ("unrotate".equals(instructions[0]))
					direction *= -1; // left = right and right = left to undo
				output = rotate(input, direction, r);
				break;
			}
			break;
		default: // we should never get here
		}
		return output;
	}

	public static Set<String> permutationFinder(String str) {
		Set<String> perm = new HashSet<String>();
		// Handling error scenarios
		if (str == null) {
			return null;
		} else if (str.length() == 0) {
			perm.add("");
			return perm;
		}
		char initial = str.charAt(0); // first character
		String rem = str.substring(1); // Full string without first character
		Set<String> words = permutationFinder(rem);
		for (String strNew : words) {
			for (int i = 0; i <= strNew.length(); i++) {
				perm.add(charInsert(strNew, initial, i));
			}
		}
		return perm;
	}

	public static String charInsert(String str, char c, int j) {
		String begin = str.substring(0, j);
		String end = str.substring(j);
		return begin + c + end;
	}

	@Override
	public String solve() {
		String hashTarget = "nodflgru";

		String filename = "day20.txt";
		List<String> instructions = new ArrayList<String>();
		try (Scanner input = new Scanner(new File("src/aoq2022/input/" + filename))) {
			while (input.hasNextLine()) {
				String line = input.nextLine();
				instructions.add(line);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Method 1: Brute force, all permutations of the input string
		String inputString = "nodflgru";
		List<String> bruteForceOutput = new ArrayList<String>();
		Set<String> permutations = permutationFinder(inputString);
		for (String input : permutations) {
			String tempPassword = input;
			for (String line : instructions) {
				tempPassword = parse(tempPassword, line);
			}
			if (tempPassword.equals(hashTarget)) {
				bruteForceOutput.add(tempPassword);
			}
		}

		// Method 2: "un"parse everything using the inverse where possible, or a funky
		// hacky thing for the "rotate based on position"
		Collections.reverse(instructions);
		String tempPassword = hashTarget;
		for (String line : instructions) {
			tempPassword = parse(tempPassword, "un" + line);
		}
		String unparseOutput = tempPassword;
		StringBuilder sb = new StringBuilder();
		sb.append("Possible solutions: ");
		for (String s : bruteForceOutput) {
			sb.append("[BruteForce]: " + s + ", ");
		}
		sb.append("[Unparse]: " + unparseOutput);
		System.out.println(sb.toString());
		return sb.toString();
	}

}
