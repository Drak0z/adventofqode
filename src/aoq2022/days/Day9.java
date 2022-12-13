package aoq2022.days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day9 extends GenericDay {

	/**
	 * Day 9 - Ham Radio
	 * 
	 * Defence Command has some concerns that the Martian's are able to intercept
	 * and understand our radio broadcasts and this obviously could mean disaster
	 * for the defence effort. After all, we've been blasting our TV programs, and
	 * radio programs into space for decades now. Who knows what they've learned
	 * from that?!
	 * 
	 * Lt. McDonald, formerly the infamous farmer, suggested that "Why n't speak
	 * pig? Hardly 'nybody understands pig and we ne'r broadcast pig speak! ee i ee
	 * i o".
	 * 
	 * Honestly, we're looking for any advantage we can get here, so military brass
	 * wants us to pursue the feasibility of this. "Ham Radio", so to speak.
	 * 
	 * It turns out that Lt. McDonald is an expert in farm noises and has given us
	 * these tips:
	 * 
	 * It turns out that pigs are great at speaking English, albeit rather
	 * inefficiently, through a series of grunts and other odd noises. Pigs separate
	 * words with one of the following sounds: hufff, huff, wheez, wheeze, squee
	 * Pigs make patterns of sounds which correspond to an English word. An English
	 * word may have multiple representations. For example "apple" might be both
	 * "eeee eeee oink eee eee" and "oink ooink eee". Pigs don't differentiate
	 * between upper and lowercase characters. Pigs ignore punctuation. They don't
	 * have time for that *#&$. To help us build an effective Pig/English
	 * dictionary, Lt. McDonald has had one of his bi-lingual swine translate this
	 * text from English into pig.
	 * 
	 * Use the above texts to build a Pig/English dictionary and use that to decode
	 * the following phrase.
	 * 
	 * ooo squeeee ooink sque squeeee eeee huff shooort ooink ooo snort ooo sque ooo
	 * hufff ooo ooo oink ooink huff squeeee sque oink eee shooort eee ooink huff
	 * eeee shooort sque eyyyee shooort eeee snort wheez eieie sque ooink snort sque
	 * wheeze sque ooo eieie ooink shooort oink eeee hufff squeeee oink sque shooort
	 * eeee sque snort wheeze squeeee ooo ooink ee oink eyyyee shooort hufff ooo
	 * eieie eee squeeee ooink ooo eyyyee huff shooort squeeee ooo eieie eieie huff
	 * snort eieie ee snort eieie eee eieie wheeze snort eee shooort shooort sque
	 * squeeee
	 * 
	 * Submit the decoded phrase in lowercase (single space between words, no
	 * punctuation).
	 * 
	 * Piglish. The martians will never see it coming!
	 */
	@Override
	public String solve() {
		String filenameEn = "day9.en.txt";
		String filenamePig = "day9.pig.txt";
		
		List<String> pigSpace = Arrays.asList("hufff", "huff", "wheez", "wheeze", "squee");
		
		List<String> englishWords = new ArrayList<String>();
		List<String> pigWords = new ArrayList<String>();
		
		Map<String, String> pigToEnglish = new HashMap<String, String>();
		
		try (Scanner input = new Scanner(new File("src/aoq2022/input/" + filenameEn))) {
			while (input.hasNextLine()) {
				String line = input.nextLine();
				String simpleLcLine = line.toLowerCase().trim();
				if (simpleLcLine.length() > 0) {
					englishWords.addAll(Arrays.asList(simpleLcLine.split(" ")));
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try (Scanner input = new Scanner(new File("src/aoq2022/input/" + filenamePig))) {
			while (input.hasNextLine()) {
				String line = input.nextLine();
				String simpleLcLine = line.toLowerCase().replaceAll("[^a-z ]", "");
				
				pigWords.addAll(Arrays.asList(simpleLcLine.split(" ")));
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int en = 0;
		List<String> pigBook = new ArrayList<String>();
		StringBuffer pigWord = new StringBuffer();
		for (int pig = 0; pig < pigWords.size(); pig++) {
			String pW = pigWords.get(pig);
			if (pigSpace.contains(pW)) {
				// this is a space
				//pigToEnglish.put(pigWord.toString(), englishWords.get(en));
				pigBook.add(pigWord.toString());
				en++;
				pigWord = new StringBuffer();
			} else {
				if (pigWord.length() > 0) pigWord.append(" ");
				pigWord.append(pW);
			}
		}
		if (pigWord.length() > 0) {
			// Let's not forget the last word
			pigToEnglish.put(pigWord.toString(), englishWords.get(en));
		}
		
		System.out.println("PigBook: " + pigBook.size() + " <> " + englishWords.size());
		
		String translateInput = "ooo squeeee ooink sque squeeee eeee huff shooort ooink ooo snort ooo sque ooo hufff ooo ooo oink ooink huff squeeee sque oink eee shooort eee ooink huff eeee shooort sque eyyyee shooort eeee snort wheez eieie sque ooink snort sque wheeze sque ooo eieie ooink shooort oink eeee hufff squeeee oink sque shooort eeee sque snort wheeze squeeee ooo ooink ee oink eyyyee shooort hufff ooo eieie eee squeeee ooink ooo eyyyee huff shooort squeeee ooo eieie eieie huff snort eieie ee snort eieie eee eieie wheeze snort eee shooort shooort sque squeeee";
		List<String> input = Arrays.asList(translateInput.split(" "));
		pigWord = new StringBuffer();
		List<String> output = new ArrayList<String>();
		for (int pig = 0; pig < input.size(); pig++) {
			String pW = input.get(pig);
			if (pigSpace.contains(pW)) {
				// this is a space
				int pos = pigBook.indexOf(pigWord.toString());
				output.add(englishWords.get(pos).replaceAll("[^a-z0-9 ]", ""));
				pigWord = new StringBuffer();
			} else {
				if (pigWord.length() > 0) pigWord.append(" ");
				pigWord.append(pW);
			}
		}
		if (pigWord.length() > 0) {
			int pos = pigBook.indexOf(pigWord.toString());
			output.add(englishWords.get(pos).replaceAll("[^a-z0-9 ]", ""));
			pigWord = new StringBuffer();
		}
		
		
		return String.join(" ", output);

	}
}
