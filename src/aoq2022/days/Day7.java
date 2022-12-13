package aoq2022.days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day7 extends GenericDay {
	/**
	 * Day 7 - Martians Among Us?!?
	 * 
	 * There are Martians on earth posing as people!
	 *  
	 * We have gotten word that there are many of them. We don't know how many yet
	 * but the clues are in Santa's Naughty or Nice list. Santa has graciously given
	 * us a portion of the list with 20,000 names. In trying to be fair he has
	 * stripped out the naughty or nice information and left just the names.
	 * Actually, he likes to be the only one able to use that information to extort
	 * things from people.
	 * 
	 * The Martians have come up with a clever yet simple way of identifying
	 * themselves.
	 * 
	 * All of the Martians have the letters M A R S appearing in their names. The
	 * letters must be in order but can have other letters between them. The letters
	 * M, A, R and S must appear only once in the name. There may even be a few
	 * Martians playing the game here.. That probably deserves a heart or at the
	 * very least an admission of guilt in the comments :)
	 * 
	 * How many Martians are there on Santa's list?
	 * 
	 * Hint:
	 * 
	 * Mark Phillips is a Martian
	 * 
	 * Jason Marshall is not a Martian because there are duplicate "a" and "s"
	 * characters
	 * 
	 * Elon Musk, evidence to the contrary, is not a Martian because his name does
	 * not include M A R S
	 */
	
	/**
	 * Reusable thing to find suspect names amongst us!
	 * @param name
	 * @return boolean: is this a martian?
	 */
	public static boolean isMartian(String name) {
		return "mars".equals(name.toLowerCase().replaceAll("[^mars]", ""));
	}
	@Override
	public String solve() {
		String filename = "day7.txt";
		Integer martians = 0;
		Integer martians2 = 0;
		try (Scanner input = new Scanner(new File("src/aoq2022/input/" + filename))) {
			while (input.hasNextLine()) {
				String line = input.nextLine();
				String lcLine = line.toLowerCase();
				/* number of occurrences of m, a, r and s need to be exactly 1
				 * m needs to appear before a, needs to be before r, needs to be before s
				 */
				if (lcLine.chars().filter(ch -> ch == 'm').count() == 1
						&& lcLine.chars().filter(ch -> ch == 'a').count() == 1
						&& lcLine.chars().filter(ch -> ch == 'r').count() == 1
						&& lcLine.chars().filter(ch -> ch == 's').count() == 1
						&& lcLine.indexOf('m') < lcLine.indexOf('a') && lcLine.indexOf('a') < lcLine.indexOf('r')
						&& lcLine.indexOf('r') < lcLine.indexOf('s')) {
					martians++;
					System.out.println("MARTIAN! : " + line);
				}
				
				/* Cleaner version; replacing all characters except for mars should result in mars */
				if (isMartian(line)) {
					martians2++;
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return "DANGER! There are (v1: " + martians + ") (v2: " + martians2 + ") martians on Santa's list!";

	}
}
