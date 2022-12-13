package aoq2022.days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day4 extends GenericDay {
	public Day4() {
	}

	/**
	 * Day 4 - The Mars Code, More or Less.?.
	 * 
	 * Transmission received! We have intercepted a message from Mars!
	 * 
	 * Ok, maybe we shouldn't be so excited. We have received many and there are
	 * surely more to come (this is only Day 4). Our team of cryptographers have
	 * found out the following information:
	 * 
	 * We don't know what the number is for but it will be important to us at a
	 * later time (see what we did there? Keeping you coming back!) * The message
	 * contained a grid of numbers 50 wide by 20 high * We need to add or subtract
	 * each digit to get our number * We start by adding * The number 3 counts as 0
	 * but flips the sign (from addition to subtraction or from subtraction to
	 * addition) 
	 * 17589678139269548347393875563168772616739554571387
	 * 85159694522215194946156673115191343322931592871189
	 * 24181957893438792693138664463844174162336128957772
	 * 45983775831896357247794793878144347296917469741844
	 * 98361779829931574221636858981264166417433311117525
	 * 89521989676436859554642211345273629246644142688193
	 * 27914172227977615173961376748987732651592689621825
	 * 59956838642449938482567441129944149453549762538217
	 * 56574316455624988333574686591239564426254716897898
	 * 25176717399517693273578473462723943115913722683616
	 * 87326519363449365844191889225836849732695982825934
	 * 41643789313751643712857166289334739868822136492521
	 * 17885652276464226495741655898845496455973188281637
	 * 88481269455728331732892474553172362558333948738737
	 * 15755689457872768173771642999673512843623491684775
	 * 32422293293491596131276514115476492771221375952474
	 * 62134486679554622372968149192811883973211684439127
	 * 14662792599842287663269727673522865646973257671413
	 * 16437291918144272429742551565129498479336223358832
	 * 67849658249211134387664747769414715135272757256742
	 * 
	 * Hint: If we were given the following small string of numbers (15836633945)
	 * with the same instructions, the answer is -16.
	 * 
	 * Please enter your answer as a regular or negative integer.
	 */
	@Override
	public String solve() {
		Integer addSub = 1;
		Integer sum = 0;
		// Test, should come back with -16: String filename = "day4_test.txt";
		String filename = "day4.txt";

		try (Scanner input = new Scanner(new File("src/aoq2022/input/" + filename))) {
			while (input.hasNextLine()) {
				String line = input.nextLine();
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == '3')
						addSub *= -1;
					else
						sum += addSub * Integer.parseInt(line.charAt(i) + "");
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(sum);
		return "Secret message number stored in " + filename + " is: " + sum.toString();

	}
}
