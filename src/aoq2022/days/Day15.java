package aoq2022.days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day15 extends GenericDay {

	/**
	 * Day 15 - Cyber Missiles
	 * 
	 * As we prepare for the coming invasion, logistics is becoming a problem. We
	 * have to move a achwack of materials, robots, toiletries, and people back and
	 * forth and every which way. It's complicated...
	 * 
	 * One of our defensive plans is to launch a B1700 "Cyber Missile" at the
	 * oncoming armada and attempt to infect and destroy their computer and guidance
	 * systems. Our assumption is that x86 architecture, because it's so darn
	 * intuitive, is probably the standard architecture across the universe and a
	 * well aimed Cyber Missile should just work on their platform with no problem.
	 * Who would standardize on ARM?
	 * 
	 * 
	 * 
	 * Anyways. We have a transport blimp heading from the South Pole to the North
	 * Pole and we need to find a spot on that blimp for the B1700.
	 * 
	 * The blimp has 128 rows, with 8 seats per row. The B1700 must be placed in a
	 * single row, and will occupy 3 consecutive seats. The blimp is nearly full, so
	 * we need to know which row will fit this sucker. Fortunately, we have every
	 * occupant's seating info in the form of tickets. We should be able to figure
	 * out which seats are occupied and, from there, our suitable row.
	 * 
	 * The tickets use binary space partitioning. A seat might be specified like
	 * FBFBBFFRLR, where
	 * 
	 * F means Front B means Back L means Left R means Right The first 7 characters
	 * will either be F or B. These specify exactly one of the 128 rows on the
	 * transport (numbered 0 through 127). Each letter tells you which half of a
	 * region the given seat is in. Start with the whole list of rows; the first
	 * letter indicates whether the seat is in the front (0 through 63) or the back
	 * (64 through 127). The next letter indicates which half of that region the
	 * seat is in, and so on until you're left with exactly one row.
	 * 
	 * For example, consider just the first seven characters of FBFBBFFRLR:
	 * 
	 * Start by considering the whole range, rows 0 through 127. F means to take the
	 * lower half, keeping rows 0 through 63. B means to take the upper half,
	 * keeping rows 32 through 63. F means to take the lower half, keeping rows 32
	 * through 47. B means to take the upper half, keeping rows 40 through 47. B
	 * keeps rows 44 through 47. F keeps rows 44 through 45. The final F keeps the
	 * lower of the two, row 44. The last three characters will be either L or R.
	 * These specify exactly one of the 8 columns of seats on the transport
	 * (numbered 0 through 7). The same for rows applies here, only with three
	 * steps. L means to keep the lower half, while R means keep the upper half.
	 * 
	 * For example, consider just the last 3 characters of FBFBBFFRLR:
	 * 
	 * Start by considering the whole range, columns 0 through 7. R means to take
	 * the upper half, keeping columns 4 through 7. L means to take the lower half,
	 * keeping columns 4 through 5. The final R keeps the upper of the two, column
	 * 5. So, decoding FBFBBFFRLR reveals that it is the seat at row 44, column 5.
	 * 
	 * Here are a few other examples:
	 * 
	 * FFFBBFFLLL: row 12, seat 0 FBBFFBFRRR: row 50, seat 8 BBFFBBFRLL: row 102,
	 * seat 4 BFFBBFBLRR: row 77, seat 3 The full list of tickets for occupied seats
	 * can be downloaded here. What row of the cargo transport has space for the
	 * B1700 Cyber Missile? (integer only. no punctuation)
	 */

	private class Plane {
		boolean[][] seats = new boolean[128][8];

		Plane() {
			Arrays.stream(seats).forEach(a -> Arrays.fill(a, false));
		}

		public void occupySeat(int row, int col) {
			seats[row][col] = true;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < seats.length; i++) {
				sb.append(String.format("%1$" + 3 + "s", i));
				sb.append(": ");
				for (int j = 0; j < seats[i].length; j++) {
					sb.append(seats[i][j] ? 'X' : '.');
				}
				sb.append(System.lineSeparator());
			}

			return sb.toString();
		}

		public int findRow(boolean findOccupy, int findNum) {
			for (int i = 0; i < seats.length; i++) {
				int n = 0;
				for (int j = 0; j < seats[i].length; j++) {
					if (seats[i][j] == findOccupy)
						n++;
					else
						n = 0;
					if (n == findNum)
						return i;
				}  
			}
			return -1;
		}

		public void seat(String input) {
			int minRow = 0;
			int maxRow = 127;
			int minCol = 0;
			int maxCol = 7;
			for (int i = 0; i < input.length(); i++) {

				int dRow = (maxRow - minRow) / 2 + 1;
				int dCol = (maxCol - minCol) / 2 + 1;
				switch (input.charAt(i)) {
				case 'F':
					maxRow -= dRow;
					break;
				case 'B':
					minRow += dRow;
					break;
				case 'L':
					maxCol -= dCol;
					break;
				case 'R':
					minCol += dCol;
					break;
				}
			}
			if (minRow != maxRow || minCol != maxCol)
				System.err.println("Ruh roh! " + minCol + " != " + maxCol + " || " + minRow + " != " + maxRow);
			occupySeat(minRow, minCol);

		}
	}

	@Override
	public String solve() {
		Plane p = new Plane();
		String filename = "day15.txt";

		try (Scanner input = new Scanner(new File("src/aoq2022/input/" + filename))) {
			while (input.hasNextLine()) {
				String line = input.nextLine();
				p.seat(line);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println(p);
		return "3 unoccupied seats next to each other on row: " + p.findRow(false, 3);

	}

}
