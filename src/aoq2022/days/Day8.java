package aoq2022.days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day8 extends GenericDay {

	/**
	 * Day 8 - Pondering Passphrases
	 * 
	 * From the antics last year, it will surprise nobody that the North Pole
	 * security standards have been, uncomfortably lax. In fact, the previous
	 * official password policy was:
	 * 
	 * Must have a password (phew) Password must consist of one dictionary word
	 * (what?!) Password should not be "Santa" (um.. sure) Numbers and special
	 * characters are prohibited. (but...) Passwords must end with a 🎄 (security +=
	 * 0) To help improve this situation, the North Pole security team has decided
	 * to roll out a new policy that requires the use of passphrases.
	 * 
	 * horse staple
	 * 
	 * The new guidelines are that passwords ...
	 * 
	 * must consist of at least three words and no more than 8 unique words
	 * separated by spaces passphrases must only consist of lowercase letters (due
	 * to another misguided effort to reduce operating costs, the numeric keys on
	 * most keyboards have been removed which makes it very difficult to type other
	 * characters in a passphrase). For example:
	 * 
	 * "santa" would be an invalid password because it's too short. "santa rudolf
	 * cupid" would be a valid password "santa rudolf santa cupid" would be invalid
	 * because a word is duplicated "santa spending some$$$" would be invalid
	 * because it contains invalid characters "cupid rudolf cupids" would be a valid
	 * password ("cupid" and "cupids" are not the same word) However, demonstrating
	 * an abundance of misguided and wholly uninformed caution, the security team
	 * has hastily generated an "official" passphrase list and decreed that all
	 * passphrases must be selected from this list.
	 * 
	 * face palm
	 * 
	 * The full passphrase list may be viewed here.
	 * 
	 * How many of the passphrases on the list are actually valid, according to
	 * their validation rules? (integer, no punctuation).
	 */
	@Override
	public String solve() {
		String filename = "day8.txt";
		Integer valid = 0;
		try (Scanner input = new Scanner(new File("src/aoq2022/input/" + filename))) {
			while (input.hasNextLine()) {
				String line = input.nextLine();
				if (line.matches("^[a-z ]*$")) { // This line consists of lowercase letters and spaces
					List<String> words = Arrays.asList(line.split(" "));
					Set<String> noDups = new HashSet<String>(words);
					if (words.size() == noDups.size() && words.size() >= 3 && words.size() <= 8) {
						// There were no duplicates, and the size is between 3 and 8
						valid++;
					}
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return "There are " + valid + " valid passphrases in the file";

	}
}
