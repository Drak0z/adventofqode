package aoq2022.days;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Day17 extends GenericDay {
	/**
	 * Day 17 - Ice Signals... Really Guys??
	 * 
	 * Our top elf scientists, lead by Fred the elf (you may remember this character
	 * from last year), have let us know that the Martians have been sending us
	 * signals for thousands of years. 20,164 to be exact! How can we possibly
	 * gather data sent to us from over twenty thousand years ago you ask? We asked
	 * too... apparently it is stored in the ice at the north pole (this sounds as
	 * dodgy as thermic lasers!).
	 * 
	 * The elves have taken ice core samples and examined it with a thermic laser (I
	 * knew this was fishy!!). Each year the Martians were effecting the
	 * temperatures at the North Pole in order to manipulate the width of the ice
	 * for each year. Until recently we would have thought this was merely white
	 * noise or radiation emitted from Mars.
	 * 
	 * We now know it is a message in the form of an image. Unbeknownst to us (and
	 * the team at Microsoft who created bitmaps), bitmaps were created by the
	 * Martians and implanted in the minds of the people at Microsoft to ensure that
	 * we could eventually decode this message. So yeah this data is a bitmap! As
	 * far as Fred can tell it is a perfect square and a grayscale image so as to
	 * send less data. Fred has informed us that they have the ice layer data in a
	 * file for us.
	 * 
	 * About the data:
	 * 
	 * we think the image is black and white
	 * 
	 * we believe it is square
	 * 
	 * 0 should be black
	 * 
	 * 765 should be white
	 * 
	 * Can you find a discernable message in the data? If so, please write it
	 * exactly as found in the answer below.
	 */
	@Override
	public String solve() {
		String filename = "day17.txt";
		List<Integer> pixels = new ArrayList<Integer>();
		try (Scanner input = new Scanner(new File("src/aoq2022/input/" + filename))) {
			while (input.hasNextLine()) {
				String line = input.nextLine();
				String[] split = line.replaceAll(" ", "").split(",");
				for (int i = 0; i < split.length; i++) {
					pixels.add(Integer.parseInt(split[i]));
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// we believe it is square
		int width = (int) Math.round(Math.sqrt(pixels.size()));
		int height = width;
		
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		for (int i = 0; i < pixels.size(); i++) {
			int x = i % width;
			int y = i / width;

			/*
			 * 0 should be black
			 * 
			 * 765 should be white
			 */
			float greyScale = pixels.get(i) / 765f;
			Color c = new Color (greyScale, greyScale, greyScale);
			bi.setRGB(x, y, c.getRGB());
		}
		/*
		 
		 * 
		 * Graphics2D ig2 = bi.createGraphics();
		 * 
		 * 
		 * BasicStroke bs = new BasicStroke(2); ig2.setStroke(bs);
		 * ig2.setPaint(Color.green);
		 * 
		 * x=Math.min(minX, 0)*-1; y=Math.min(minY, 0)*-1;
		 * 
		 * ig2.setPaint(Color.red); ig2.draw(new Rectangle2D.Double(minX, minY, maxX,
		 * maxY));
		 * 
		 * 
		 * 
		 * 
		 
		 * 
		 */
		try {
			ImageIO.write(bi, "PNG", new File("src/aoq2022/output/day17.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}

}
