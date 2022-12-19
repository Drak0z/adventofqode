package aoq2022.days;

public class Day14 extends GenericDay {

	/**
	 * Day 14 - Robots Games
	 * 
	 * You've walked into the robot barracks and a group of robots are playing some
	 * weird game that they call "expand-o-num". They claim they are doing this to
	 * unwind (This is only a figure of speech. Since 2015 our robots no longer
	 * require winding and, instead, contain small nuclear reactors).
	 * 
	 * 
	 * You have some concerns that this game is going to take too long and distract
	 * the robots from other work they should be doing.
	 * 
	 * The game works like this:
	 * 
	 * 
	 * The first robot says "1", because they always start at "1"
	 * The next robot says "11", because there is 1 "1" in the previous number
	 * The next robot says "21", because there are 2 "1"'s in the previous number
	 * The next robot says "1211", because there is 1 "2" and 1 "1" in the previous
	 * number
	 * 
	 * Already this game is seeming monotonous.
	 * 
	 * The next robot says "111221", because there are 1 "1" and 1 "2" and 2 "1"'s
	 * in the previous number The next robot says "312211", because there are 3
	 * "1"'s and 2 "2"'s and 1 "1" in the previous number and so on. There is a
	 * 5-second pause between rounds and it takes a robot 2 seconds to say a single
	 * digit.
	 * 
	 * If the robots were playing a 1-round game, the entire game would take 2
	 * seconds.
	 * If the robots were playing a 2-round game, the entire game would take 11
	 * seconds.
	 * If the robots were playing a 3-round game, the entire game would take 20
	 * seconds.
	 * If the robots were playing a 4-round game, the entire game would take 33
	 * seconds.
	 * The robots are planning to play 47 rounds of this silly game. How long is
	 * that going to take (in seconds - no punctuation!)?
	 */
	private long calculateRound(String prevRound, int target) {
		if (target <= 0) return -5;
		if (prevRound == null) prevRound = "";
		StringBuilder currRound = new StringBuilder();
		char c = 'x';
		int n = 0;
		if (prevRound.length() == 0) {
			currRound.append(1);
		} else {
			for (int i = 0; i < prevRound.length(); i++) {
				if (prevRound.charAt(i) != c) {
					if (n > 0) {
						currRound.append(n);
						currRound.append(c);
						n = 0;
					}
				}
				c = prevRound.charAt(i);
				n++;
			}
			currRound.append(n);
			currRound.append(c);
		}
		System.out.println("  target: " + target + " prevRound: [" + prevRound + "] currRound: [" + currRound + "]");
		return currRound.length() * 2 + 5 + calculateRound(currRound.toString(), target-1); 
		
	}

	@Override
	public String solve() {
		System.out.println("47: " + calculateRound(null, 47));
		
		return "Playing this silly game for 47 rounds takes the robots: " + calculateRound(null, 47) + " seconds";

	}

}
