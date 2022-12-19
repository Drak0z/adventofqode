package aoq2022.days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day18 extends GenericDay {
	/**
	* Day 18 - Mining for Cyber Missiles!
	* It's looking like our Cyber Missile approach might be just what Earth needs to head off this Martian disaster. Unfortunately, it looks like the Martians have firewalls between their ships so a single Cyber Missile won't cut it. Cyber Missiles, as you'd expect, operate on silicon. A lot of silicon. We're going to need to ramp up mining in a hurry.
	* 
	* We've built a map of silicon resources in the area and we need your help to determine how much silicon we're going to produce over the next 12 hours (we have a tight schedule here).
	* 
	* Here is a simplified map for a smaller area:
	* 
	* .$$$.#$$$$
	* ...$.$#$.#
	* #.$$..$...
	* .$.$$.$...
	* $....$.$#.
	* $$.$$$$#..
	* $$.$$$.##.
	* $..$$$##$.
	* .$$.$$...$
	* $#$$$.....
	* In the map $ is used to denote a silicon reserve, # is a mine, and . is an empty space.
	* 
	* Each hour, the map changes instantaneously based on the previous hour's map.
	* 
	* Silicon will "seep" into an empty space if that space is adjacent to three or more other spaces containing silicon.
	* We'll build a mine on any space containing silicon if that space is adjacent to three or more other spaces containing a mine. When this happens, this means we've mined a block of silicon. Yay!
	* We'll destroy a mine if it is not adjacent to at least one other mine AND one other space containing silicon (a destroyed mine just leaves an empty space).
	* We need to know how many blocks of silicon we'll be mining after a certain number of iterations (hours). He's an example of how things would play out with our small sample map.
	* 
	* Map after 0 iterations: - Silicon: 45, Munchers: 0, Mines: 11 // Mined 0 blocks of silicon
	* 
	* .$$$.#$$$$
	* ...$.$#$.#
	* #.$$..$...
	* .$.$$.$...
	* $....$.$#.
	* $$.$$$$#..
	* $$.$$$.##.
	* $..$$$##$.
	* .$$.$$...$
	* $#$$$.....
	* Map after 1 iterations: - Silicon: 68, Munchers: 0, Mines: 9 // Mined 1 blocks of silicon
	* 
	* .$$$$#$$$$
	* .$$$$$#$$.
	* ..$$$$$$..
	* .$$$$$$$..
	* $$$$$$$$#.
	* $$$$$$$#..
	* $$$$$$$##.
	* $$$$$$###.
	* $$$$$$...$
	* $.$$$$....
	* Map after 2 iterations: - Silicon: 74, Munchers: 0, Mines: 9 // Mined 2 blocks of silicon
	* 
	* .$$$$#$$$$
	* .$$$$$#$$$
	* .$$$$$$$$.
	* $$$$$$$$$.
	* $$$$$$$$#.
	* $$$$$$$#..
	* $$$$$$##..
	* $$$$$$###.
	* $$$$$$$..$
	* $$$$$$....
	* Map after 3 iterations: - Silicon: 77, Munchers: 0, Mines: 10 // Mined 3 blocks of silicon
	* 
	* .$$$$#$$$$
	* $$$$$$#$$$
	* $$$$$$$$$$
	* $$$$$$$$$.
	* $$$$$$$$#.
	* $$$$$$##..
	* $$$$$$##..
	* $$$$$$###.
	* $$$$$$$..$
	* $$$$$$$...
	* Map after 4 iterations: - Silicon: 77, Munchers: 0, Mines: 11 // Mined 5 blocks of silicon
	* 
	* $$$$$#$$$$
	* $$$$$$#$$$
	* $$$$$$$$$$
	* $$$$$$$$$$
	* $$$$$$$##.
	* $$$$$$##..
	* $$$$$##...
	* $$$$$$###.
	* $$$$$$$..$
	* $$$$$$$...
	* Map after 5 iterations: - Silicon: 74, Munchers: 0, Mines: 14 // Mined 8 blocks of silicon
	* 
	* $$$$$#$$$$
	* $$$$$$#$$$
	* $$$$$$$$$$
	* $$$$$$$$$$
	* $$$$$$###.
	* $$$$$###..
	* $$$$$##...
	* $$$$$####.
	* $$$$$$$..$
	* $$$$$$$...
	* Map after 6 iterations: - Silicon: 70, Munchers: 0, Mines: 16 // Mined 12 blocks of silicon
	* 
	* $$$$$#$$$$
	* $$$$$$#$$$
	* $$$$$$$$$$
	* $$$$$$$#$$
	* $$$$$####.
	* $$$$$##...
	* $$$$##....
	* $$$$$####.
	* $$$$$$#..$
	* $$$$$$$...
	* Map after 7 iterations: - Silicon: 65, Munchers: 0, Mines: 19 // Mined 17 blocks of silicon
	* 
	* $$$$$#$$$$
	* $$$$$$#$$$
	* $$$$$$$$$$
	* $$$$$$###$
	* $$$$$####.
	* $$$$##....
	* $$$$##....
	* $$$$###.#.
	* $$$$$##..$
	* $$$$$$$...
	* Map after 8 iterations: - Silicon: 59, Munchers: 0, Mines: 21 // Mined 23 blocks of silicon
	* 
	* $$$$$#$$$$
	* $$$$$$#$$$
	* $$$$$$##$$
	* $$$$$####$
	* $$$$###.#.
	* $$$$##....
	* $$$##.....
	* $$$$##....
	* $$$$###..$
	* $$$$$$$...
	* Map after 9 iterations: - Silicon: 51, Munchers: 0, Mines: 26 // Mined 31 blocks of silicon
	* 
	* $$$$$#$$$$
	* $$$$$###$$
	* $$$$$####$
	* $$$$#####$
	* $$$$##..#.
	* $$$##.....
	* $$$##.....
	* $$$##.....
	* $$$$###..$
	* $$$$$#$...
	* Map after 10 iterations: - Silicon: 41, Munchers: 0, Mines: 31 // Mined 41 blocks of silicon
	* 
	* $$$$$##$$$
	* $$$$#####$
	* $$$$##.##$
	* $$$$##..##
	* $$$##...#.
	* $$$##.....
	* $$##......
	* $$$##.....
	* $$$####..$
	* $$$$###...
	* Map after 11 iterations: - Silicon: 33, Munchers: 0, Mines: 29 // Mined 49 blocks of silicon
	* 
	* $$$$####$$
	* $$$$#####$
	* $$$##...##
	* $$$##...##
	* $$$##.....
	* $$##......
	* $$##......
	* $$##......
	* $$$##....$
	* $$$##.....
	* Map after 12 iterations: - Silicon: 26, Munchers: 0, Mines: 25 // Mined 56 blocks of silicon
	* 
	* $$$$#..##$
	* $$$##..###
	* $$$##...##
	* $$##......
	* $$##......
	* $$##......
	* $##.......
	* $$##......
	* $$##.....$
	* $$$#......
	* However. It gets more complicated than this. The North Pole biological weapons department has been experimenting with a new bacteria they affectionately called the "Silicon Muncher #37". Unfortunately, these "Munchers" have escaped the lab by hitching a ride on a scientist's iPhone!
	* 
	* Muchers
	* 
	* A Muncher will take over a space containing silicon if that space (devouring all silicon in that space with a ferocity that makes us blush) is adjacent to one or more other Munchers and that space is not adjacent to three or more mines. The implication is that both a Muncher and mine would form at the same instance, and the mine would flatten the crap out of the Muncher (killing it instantly and leaving a greasy water-repellant stain).
	* A Muncher left to his own without any adjacent silicon will starve and die (returning the space to an empty space)
	* To give you a feel for how destructive these Munchers are, look at what happens if we let a single Mucher (denoted by @) loose in the above map.
	* 
	* Map after 0 iterations: - Silicon: 45, Munchers: 1, Mines: 11 // Mined 0 blocks of silicon
	* 
	* .$$$.#$$$$
	* ...$.$#$.#
	* #.$$..$...
	* .$.$$.$...
	* $...@$.$#.
	* $$.$$$$#..
	* $$.$$$.##.
	* $..$$$##$.
	* .$$.$$...$
	* $#$$$.....
	* Map after 1 iterations: - Silicon: 61, Munchers: 7, Mines: 9 // Mined 1 blocks of silicon
	* 
	* .$$$$#$$$$
	* .$$$$$#$$.
	* ..$$$$$$..
	* .$$@@$$$..
	* $$$$@@$$#.
	* $$$@@@$#..
	* $$$$$$$##.
	* $$$$$$###.
	* $$$$$$...$
	* $.$$$$....
	* Map after 2 iterations: - Silicon: 51, Munchers: 23, Mines: 9 // Mined 2 blocks of silicon
	* 
	* .$$$$#$$$$
	* .$$$$$#$$$
	* .$@@@@$$$.
	* $$@@@@@$$.
	* $$@@@@@$#.
	* $$@@@@@#..
	* $$@@@@##..
	* $$$$$$###.
	* $$$$$$$..$
	* $$$$$$....
	* Map after 3 iterations: - Silicon: 36, Munchers: 34, Mines: 8 // Mined 2 blocks of silicon
	* 
	* .$$$$#$$$$
	* $@@@@@#$$$
	* $@@@@@@@$$
	* $@@..@@@$.
	* $@@...@@#.
	* $@@...@#..
	* $@@@@@#...
	* $@@@@@###.
	* $$$$$$$..$
	* $$$$$$$...
	* Map after 4 iterations: - Silicon: 14, Munchers: 42, Mines: 6 // Mined 2 blocks of silicon
	* 
	* .@@@@#@$$$
	* @@@@@@#@@$
	* @@....@@@$
	* @@.....@@$
	* @@.....@#.
	* @@........
	* @@........
	* @@@@@@###.
	* @@@@@@@..$
	* $$$$$$$...
	* OMG. Yikes. We can only imagine what this is going to do to the pricing of the iPhone 15! These little bastards are eating all our silicon reserves. Obviously, we're in a race against time to get the silicon resources that we need.
	* 
	* Fortunately, we have a bit more time because our real map is a lot bigger. But is it big enough?!
	* 
	* Using this full map, how much silicon will we mine after 12 iterations (12 hours)? (as an integer, no punctuation)
	*/
	private class Map {
		char[][] map;
		int width;
		int height;
		int mined;
		int iterations;
		
		Map(List<String> init) {
			this.mined = 0;
			this.iterations = 0;
			this.height = init.size();
			this.width = init.get(0).length();
			this.map = new char[height][width];
			for (int y = 0; y < init.size(); y++) {
				for (int x = 0; x < init.get(y).length(); x++) {
					map[y][x] = init.get(y).charAt(x);
				}
			}
		}
		
		private int countAround(char find, int targetX, int targetY) {
			//System.out.println("  Count Around: " + find + " @ " + targetX + " " + targetY);
			int total = 0;
			for (int y = targetY-1; y<=targetY+1; y++) {
				for (int x = targetX-1; x<=targetX+1; x++) {
					//System.out.println("    [" + x + "," + y + "]?");
					if (x >= 0 && x < this.width && y >= 0 && y < this.height && (x != targetX || y != targetY) && map[y][x] == find) {
						total ++;
					}
				}
			}
			return total;
		}
		
		private int count(char find) {
			int total = 0;
			for (int y = 0; y<height; y++) {
				for (int x = 0; x<width; x++) {
					if (map[y][x] == find) {
						total ++;
					}
				}
			}
			return total;
		}
		
		public void iterate() {
			iterations++;
			char[][] tempMap = new char[height][width];
			for (int y = 0; y < map.length; y++) {
				for (int x = 0; x < map[y].length; x++) {
					//System.out.println("[" + x + "," + y + "]: " + map[y][x] + " || " + countAround('$', x, y) + " || " + countAround('#', x, y));
					switch(map[y][x]) {
					case '.':
						if (countAround('$', x, y) >= 3) {
							tempMap[y][x] = '$';
						} else {
							tempMap[y][x] = '.';
						}
						break;
					case '$':
						if (countAround('#', x, y) >= 3) {
							tempMap[y][x] = '#';
							this.mined++;
						} else if (countAround('@', x, y) >= 1) {
							tempMap[y][x] = '@';
						} else {
							tempMap[y][x] = '$';
						}
						break;
					case '#':
						if ((countAround('#', x, y) >= 1) && (countAround('$', x, y) >= 1)) {
							tempMap[y][x] = '#';
						} else {
							tempMap[y][x] = '.';
						}
						break;
					case '@':
						if ((countAround('$', x, y) <= 0)) {
							tempMap[y][x] = '.';
						} else {
							tempMap[y][x] = '@';
						}
						break;
					}
				}
			}
			map = tempMap;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int y = 0; y < map.length; y++) {
				for (int x = 0; x < map[y].length; x++) {
					sb.append(map[y][x]);
				}
				sb.append(System.lineSeparator());
			}
			return sb.toString();
		}
		
		public String getInfo() {
			StringBuilder sb = new StringBuilder();
			sb.append("Map after " + iterations + " iterations: ");
			sb.append("- Silicon: " + count('$'));
			sb.append(", Munchers: " + count('@'));
			sb.append(", Mines: " + count('#'));
			sb.append(" // Mined " + mined + " blocks of silicon");
			return sb.toString();			
		}
	}
	
	@Override
	public String solve() {
		String filename = "day18.txt";
		List<String> mapInput = new ArrayList<String>();
		try (Scanner input = new Scanner(new File("src/aoq2022/input/" + filename))) {
			while (input.hasNextLine()) {
				String line = input.nextLine();
				mapInput.add(line);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		/*
		List<String> testInput = new ArrayList<String>();

		testInput.add(".$$$.#$$$$");
		testInput.add("...$.$#$.#");
		testInput.add("#.$$..$...");
		testInput.add(".$.$$.$...");
		testInput.add("$....$.$#.");
		testInput.add("$$.$$$$#..");
		testInput.add("$$.$$$.##.");
		testInput.add("$..$$$##$.");
		testInput.add(".$$.$$...$");
		testInput.add("$#$$$.....");
		
		testInput.add(".$$$.#$$$$");
		testInput.add("...$.$#$.#");
		testInput.add("#.$$..$...");
		testInput.add(".$.$$.$...");
		testInput.add("$...@$.$#.");
		testInput.add("$$.$$$$#..");
		testInput.add("$$.$$$.##.");
		testInput.add("$..$$$##$.");
		testInput.add(".$$.$$...$");
		testInput.add("$#$$$.....");
		
		Map m = new Map(testInput);
		*/
		Map m = new Map(mapInput);
		
		System.out.println(m.toString());
		System.out.println(m.getInfo());

		for (int i = 0; i < 12; i++) {
			m.iterate();
			System.out.println(m.toString());
			System.out.println(m.getInfo());
		}
		return "";

	}

}
