package aoq2022.days;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeSet;

public class Day19 extends GenericDay {
	/**
	 * Day 19 - Robot Telephone (aka. PLAN "B")
	 * 
	 * With only mere days to go before the invasion hits, RobotPrime thinks it's
	 * probably time to plan for "Plan B" and set up some sort of communication
	 * process to be used if Mars wipes out humanity's most precious (to robots)
	 * resource. The Internet! (The fate of humanity is a second-order concern for
	 * RobotPrime.).
	 * 
	 * You've heard of quantum entanglement, right? Well, that's good because this
	 * is very scientific and technical.
	 * 
	 * Each Robot, as part of its construction, contains one more semi-entangled
	 * electrons which can be used for low bandwidth ultra-secure Robot-to-Robot
	 * communication. Due to budget cuts, the decision was to use semi-entangled vs.
	 * fully-entangled electronics. Semi-entangled electronics only support one-way
	 * communication.
	 * 
	 * During manufacturing, we have recorded which robots are entangled, and the
	 * bandwidth (in bits/sec) of each link.
	 * 
	 * For example. Consider the following manufacturing records:
	 * 
	 * AliceBot -> BobBot: 1200 bits/sec AliceBot -> CharlieBot: 9600 bits/sec
	 * BobBot -> CharlieBot: 1200 bits/sec CharlieBot -> Dudley: 9600 bits/sec
	 * EchoBot -> BobBot: 2400 bits/sec Dudley -> EchoBot: 4800 bits/sec AliceBot ->
	 * Dudley: 1200 bits/sec The above would indicate that Alice contains the
	 * transmitting side of 3 semi-entangled electronics (to BobBot, CharlieBot, and
	 * Dudley). (i.e. Alice can send to those three bots, but nobody can send to
	 * Alice)
	 * 
	 * RobotPrime needs your help to determine the difference in time between the
	 * best-case and worst-case message transmission times across the robots that
	 * would ensure each robot receives the message once.
	 * 
	 * From our manufacturing data, we can generate the following possible valid
	 * paths.
	 * 
	 * AliceBot > BobBot > CharlieBot > Dudley > EchoBot AliceBot > CharlieBot >
	 * Dudley > EchoBot > BobBot AliceBot > Dudley > EchoBot > BobBot > CharlieBot
	 * Now, if we were to transmit a 9600-bit message, we would generate these
	 * times.
	 * 
	 * AliceBot > BobBot > CharlieBot > Dudley > EchoBot: 19.0s AliceBot >
	 * CharlieBot > Dudley > EchoBot > BobBot: 8.0s AliceBot > Dudley > EchoBot >
	 * BobBot > CharlieBot: 22.0s Which gives us a best-case time of 8 seconds, and
	 * a worst-case time of 22 seconds to transmit our tiny message. The number
	 * Prime is looking for is 14.
	 * 
	 * However... That was just an example. We have more robots and Prime isn't one
	 * to spare bytes for an important message.
	 * 
	 * What is the difference between the best-case and worst-case transmission
	 * times for this message across all the robots captured in these manufacturing
	 * details? (integer, rounded up, no punctuation)
	 */
	
	// @TODO: Clean up this mess... It is terrible :D
	private class Robot {
		String name;
		Map<String, List<Integer>> connections;

		Robot(String name) {
			this.name = name;
			this.connections = new HashMap<String, List<Integer>>();
		}

		public String getName() {
			return this.name;
		}

		public Map<String, List<Integer>> getConnections() {
			return this.connections;
		}

		public void addConnection(String robot, Integer speed) {
			List<Integer> speeds = connections.get(robot);
			if (speeds == null)
				speeds = new ArrayList<Integer>();
			speeds.add(speed);
			connections.put(robot, speeds);
		}

		public String toString() {
			return this.name + " (" + this.connections.size() + ")";
		}
	}

	private class Solution implements Comparable<Solution> {
		List<String> pathNames;
		List<Integer> pathSizes;
		Robot root;
		Integer length = 0;

		Solution(Robot root, List<String> pathNames, List<Integer> pathSizes) {
			this.pathNames = pathNames;
			this.pathSizes = pathSizes;
			this.root = root;
			for (Integer l : pathSizes) {
				length += l;
			}
		}

		public double getDuration(Long fileSize) {
			return getDuration(fileSize, false);
		}

		public double getDuration(Long fileSize, boolean print) {
			if (print)
				System.out.println("Duration between: " + this.getFirst() + " and " + this.getLast());
			double result = 0;
			double fileSizeD = (double) fileSize;
			String name = root.getName();
			for (int i = 0; i < pathNames.size(); i++) {
				String newName = pathNames.get(i);
				Integer speed = pathSizes.get(i);
				double duration = fileSizeD / speed;
				if (print)
					System.out.println(" ** " + name + " -> " + newName + " @ " + speed + " bits/s = " + duration);
				result += duration;
				name = newName;
			}
			if (print)
				System.out.println("Total: " + result + "s");
			return result;
		}

		@Override
		public int compareTo(Solution o) {
			double thisDuration = this.getDuration(9600L);
			double otherDuration = o.getDuration(9600L);
			if (thisDuration < otherDuration)
				return -1;
			if (thisDuration > otherDuration)
				return 1;
			return 0;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(root.getName());
			for (int i = 0; i < pathNames.size(); i++) {
				sb.append(" -> " + pathNames.get(i));
			}
			return sb.toString();
		}

		public String getFirst() {
			return root.getName();
		}

		public String getLast() {
			return pathNames.get(pathNames.size() - 1);
		}
	}

	// do something recursive
	// If I have no more robots to visit, check if my current solution is viable and
	// add to possible solutions
	// If I have at least one more robot to visit, explore the connections
	private TreeSet<Solution> recursiveSearch(Robot root, Robot r, TreeSet<Solution> solutions, List<String> pathNames,
			List<Integer> pathSizes, Map<String, Robot> robots) {
		if (pathNames.size() == robots.size() - 1) { // -1 because we start at root
			Solution s = new Solution(root, pathNames, pathSizes);
			solutions.add(s);
			return solutions;
		}
		Map<String, List<Integer>> connections = r.getConnections();
		for (Entry<String, List<Integer>> entry : connections.entrySet()) {
			String name = entry.getKey();
			if (root.getName().equals(name) || pathNames.contains(name)) {
				continue; // we have visited this robot before on this journey
			}
			
			// If we get here, we have not visited this robot before
			// But we may have multiple speeds to connect to this robot
			List<Integer> speeds = entry.getValue();
			for (Integer speed : speeds) { // Bloody governmental redundancies
				List<String> newPathNames = new ArrayList<String>(pathNames);
				List<Integer> newPathSizes = new ArrayList<Integer>(pathSizes);
				Robot newRobot = robots.get(name);
				newPathNames.add(name);
				newPathSizes.add(speed);
				recursiveSearch(root, newRobot, solutions, newPathNames, newPathSizes, robots);
			}
		}
		return solutions;
	}

	@Override
	public String solve() {
		Map<String, Robot> robots = new HashMap<String, Robot>();
		TreeSet<Solution> solutions = new TreeSet<Solution>();
		Long fileSize = 0L;
		try {
			fileSize = Files.size(Paths.get("src/aoq2022/input/day19_message.wav")) * 8; // bytes to bits
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String filename = "day19_robots.txt";
		try (Scanner input = new Scanner(new File("src/aoq2022/input/" + filename))) {
			while (input.hasNextLine()) {
				String line = input.nextLine();
				String[] lineArr = line.replaceAll("[:\\->]", "").replaceAll("  ", " ").split(" ");
				// lineArr[0]: from
				// lineArr[1]: to
				// lineArr[2]: qty
				// lineArr[3]: uom, always assumed bits/second
				Robot r = robots.get(lineArr[0]);
				if (r == null) {
					r = new Robot(lineArr[0]);
				}
				r.addConnection(lineArr[1], Integer.parseInt(lineArr[2]));
				robots.put(lineArr[0], r);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("Number of unique robots: " + robots.size());
		for (Robot r : robots.values()) {
			recursiveSearch(r, r, solutions, new ArrayList<String>(), new ArrayList<Integer>(), robots);
		}

		System.out.println("Found " + solutions.size() + " solutions");
		System.out.println("Total filesize: " + fileSize);
		if (solutions.size() == 0)
			return "DANGER WILL ROBINSON";

		Solution longest = solutions.last();
		Solution shortest = solutions.first();

		double longestDuration = longest.getDuration(fileSize);
		System.out.println("Longest: " + longest + " duration: " + longestDuration);
		double shortestDuration = shortest.getDuration(fileSize);
		System.out.println("Shortest: " + shortest + " duration: " + shortestDuration);
		double delta = (longestDuration - shortestDuration);
		System.out.println("Delta: " + delta + ", rounded: " + Math.round(delta));
		return "Rounded delta between longest (" + longestDuration + ") and shortest (" + shortestDuration + "): "
				+ Math.round(delta);

	}

}
