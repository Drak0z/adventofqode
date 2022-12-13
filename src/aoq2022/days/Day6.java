package aoq2022.days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6 extends GenericDay {
	public Day6() {
	}

	/**
	 * Day 6 - Martian Signal Sifter
	 * 
	 * The crack team over at NOPAD (NOrth Pole Air Defence) has been picking up a
	 * mysterious series of radio transmissions coming from, roughly, a Mars-ish
	 * direction. These transmissions could be anything... random space noise,
	 * martian sit-coms, martian math tests, Elon Musk repurposing Starlink to troll
	 * Earth, or perhaps martian telemetry data. Most likely, these transmissions
	 * are a bit of all of these things.
	 * 
	 * The Martian Streams
	 * 
	 * We need your help to help identify which the the following digit streams is
	 * an actual martian telemetry broadcast so that we can forward that to the
	 * number crunchers in Blinky Park, Santa's top-secret codebreaker office, for
	 * decipherment.
	 * 
	 * We don't know a lot about martian broadcasts, but we do know that they don't
	 * know a lot about checksums.
	 * 
	 * Here is what we know of Martian checksums...
	 * 
	 * The last 10 digits of their broadcasts are the checksum digits (left
	 * zero-padded) The checksum is the sum the digits that match the next digit in
	 * the list The stream is treated as circular (i.e. the last digit can match the
	 * first digit) So, for example:
	 * 
	 * 1223, would checksum to 2 because the second digit (2) matches the third
	 * digit (2). 1221, would yield a checksum of 3 because because the second digit
	 * (2) matches the third digit (2), and the fourth digit (1) also matches the
	 * first digit (1) - remember the circular stream thing... 1111, would yield
	 * because each digit matches the next. 1234, would yield 0 because no digit
	 * matches the next. finally, 91212121212129, would yield 9 because only the
	 * last and first digits match.
	 * 
	 * In an actual transmission, of course, you'll have the ten digit checksum
	 * appended like so:
	 * 
	 * 12230000000002 999990000000040 Clear as mud?
	 * 
	 * Great!
	 * 
	 * The full set of streams are available here (250Kb).
	 * 
	 * Each line in the file (terminated by a newline) is a single stream The first
	 * 10 digits of the stream are the steam serial number (the serial number is not
	 * part of the stream and should not be included in the checksum) The last 10
	 * digits of the stream are the Martian checksum Everything is the data We need
	 * you to sift through the streams and let us know the SERIAL NUMBER of the one
	 * stream that has a valid martian checksum.
	 */
	@Override
	public String solve() {
		String filename = "day6.real.txt";

		try (Scanner input = new Scanner(new File("src/aoq2022/input/" + filename))) {
			while (input.hasNextLine()) {
				// Every line of data contains a serial (first 10 chars), a checksum (last 10 chars),
				// and stream (the middle bit)
				String data = input.nextLine();
				String serial = data.substring(0, 10);
				String stream = data.substring(10, data.length() - 10);
				String checksum = data.substring(data.length() - 10);

				// The checksum is calculated by summing all subsequent matching 
				// numbers in the stream, including a loop around the first and last number
				Integer check = 0;
				Integer prevNr = Integer.parseInt(stream.charAt(stream.length() - 1) + "");
				for (int i = 0; i < stream.length(); i++) {
					Integer nr = Integer.parseInt(stream.charAt(i) + "");
					if (prevNr == nr) {
						check += nr;
					}
					prevNr = nr;
				}

				if (Integer.parseInt(checksum) == check) {
					return "Serial number of the line with valid checksum in " + filename + " is: " + serial;
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "DANGER WILL ROBINSON"; 

	}
}
