package aoq2022.days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day12 extends GenericDay {

	/**
	 * Day 12 - Blinky Park's Blighted 2nd Factor
	 * 
	 * You need to drop off some Martial signal interception data with those wizards
	 * at Blinky Park. Unfortunately, those same "wizards" keep coming up with
	 * ingenious ways of keeping their offices secure (and keeping you out of them).
	 * This time it appears as though they've implemented some sort of weird 2FA
	 * (the Blighty Labs Door-O-Matic 2FA system) mechanism attached to the front
	 * door's card reader. Unfortunately, the screen attached to the card reader is
	 * smashed and smells sickening of "magic smoke" (probably a previous visitor
	 * venting their frustration at this latest hurdle).
	 * 
	 * You swipe your key card repeatedly and there is a beep of acknowledgment, but
	 * nothing more. The system seems to be expecting you to type something into
	 * that numeric keypad, but what?!!
	 * 
	 * Fortunately, you've brought along your trusty alligator clips and serial
	 * debugger and have managed to read these messages from the wire connecting the
	 * keycard reader to the smashed screen.
	 * 
	 * After perusing the Internet for a bit, you've found the owner's manual is
	 * still available for download. Lucky! Hopefully, that will help you make some
	 * sense of this and get this blasted Blinky door open.
	 * 
	 * What is the code that you need to enter into the keypad to open this door?
	 */

	/**
	 * Helper class for our display. If we're running in test mode, then we run with a 7x3, else we run with a 40x6
	 * @author hpdav
	 *
	 */
	private class Display {
		private boolean[][] display;
		public boolean test = false;

		Display() {
			this.init();
		} // constructor

		public void init() {
			this.display = test ? new boolean[7][3] : new boolean[40][6];
			for (int x = 0; x < this.display.length; x++) {
				for (int y = 0; y < this.display[x].length; y++) {
					this.display[x][y] = false;
				}
			}
		} // init

		// We're turning the top left AxB on
		public void on(String s) {
			String[] coords = s.split("x");
			int x = 0;
			int y = 0;
			int dx = Integer.parseInt(coords[0]);
			int dy = Integer.parseInt(coords[1]);
			onOff(x, y, dx, dy, true);
		}

		// We're turning the bottom right AxB off
		public void off(String s) {
			String[] coords = s.split("x");
			int dx = Integer.parseInt(coords[0]);
			int dy = Integer.parseInt(coords[1]);
			int x = this.display.length - dx;
			int y = this.display[0].length - dy;
			dx = this.display.length;
			dy = this.display[0].length;
			onOff(x, y, dx, dy, false);
		}

		private void onOff(int startx, int starty, int dx, int dy, boolean on) {
			for (int x = startx; x < dx; x++) {
				for (int y = starty; y < dy; y++) {
					this.display[x][y] = on;
				}
			}
		}

		// We rotate either col or row
		public void rotate(String row_col, String location, String rotations) {
			int r = Integer.parseInt(rotations);
			int dim = Integer.parseInt(location.split("=")[1]);
			switch (row_col) {
			case "row":
				rotateRow(dim, r);
				break;
			case "column":
				rotateCol(dim, r);
				break;
			default:
				// do nothing but panic
			}
				
		}
		
		private void rotateRow(int y, int r) {
			boolean[] newRow = new boolean[this.display.length];
			for (int i = 0; i < newRow.length; i++) {
				newRow[(i + r) % newRow.length] = this.display[i][y];
			}
			for (int i = 0; i < newRow.length; i++) {
				this.display[i][y] = newRow[i];
			}
		}

		private void rotateCol(int x, int r) {
			boolean[] newCol = new boolean[this.display[x].length];
			for (int i = 0; i < newCol.length; i++) {
				newCol[(i + r) % newCol.length] = this.display[x][i];
			}
			for (int i = 0; i < newCol.length; i++) {
				this.display[x][i] = newCol[i];
			}
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int y = 0; y < this.display[0].length; y++) {
				for (int x = 0; x < this.display.length; x++) {
					sb.append(this.display[x][y] ? "#" : ".");
				}
				sb.append(System.lineSeparator());
			}
			return sb.toString();
		}

	}

	@Override
	public String solve() {
		Display d = new Display();
		String filename = d.test ? "day12_test.txt" : "day12.txt";

		try (Scanner input = new Scanner(new File("src/aoq2022/input/" + filename))) {
			while (input.hasNextLine()) {
				String line = input.nextLine();
				String[] instructions = line.split(" ");
				switch(instructions[0]) {
				case "on":
					d.on(instructions[1]);
					break;
				case "off":
					d.off(instructions[1]);
					break;	
				case "rotate":
					d.rotate(instructions[1], instructions[2], instructions[4]);
					break;
				default:
					// we should never get here
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(d);

		return "";

	}
}
