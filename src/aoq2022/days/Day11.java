package aoq2022.days;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Day11 extends GenericDay {

	/**
	 * Day 11 - Oh no.. We did what with dot to dots??
	 * 
	 * Our intel team, a number of elves back at Santa's village (don't judge!),
	 * have been looking into why the Martians are so annoyed with us in the first
	 * place.
	 * 
	 * Apparently it really started with the Mars Rover Spirit, sent in 2004. The
	 * Martians thought it would be good to learn our English language (as that
	 * seems to be the one with the most chatter out in space) and thought they
	 * would apply it to what they could observe on their own planet. Spirit's
	 * tracks were the obvious place we would use to spell out a message.
	 * 
	 * Spirit's series of vectors unfortunately looked like cursive, a style of
	 * handwriting favoured by people who know how to use an antique phone's rotary
	 * dial.
	 * 
	 * Each vector tells the probe which cardinal direction to go from its current
	 * position. For example, these vectors produces a non-trademark-infringing Nike
	 * swoosh:
	 * 
	 * A blocky swoosh
	 * 
	 * Given these vectors, What message did the Spirit Rover write out on the Mars
	 * surface? (Please answer in all lower-case)
	 */
	@Override
	public String solve() {

		try {
			String filename = "day11.txt";
			ArrayList<Float[]> coords = new ArrayList<Float[]>();
			Float minX = Float.MAX_VALUE;
			Float maxX = Float.MIN_VALUE;
			Float minY = Float.MAX_VALUE;
			Float maxY = Float.MIN_VALUE;

			Float x = 0f;
			Float y = 0f;
			Float dx = 0f;
			Float dy = 0f;
			try (Scanner input = new Scanner(new File("src/aoq2022/input/" + filename))) {
				while (input.hasNextLine()) {
					String line = input.nextLine();
					String simpleLcLine = line.replaceAll("[()]", "");
					String[] split = simpleLcLine.split(",");
					dx = Float.parseFloat(split[0].trim())*10;
					dy = Float.parseFloat(split[1].trim())*10*-1;
					
					x+=dx;
					y+=dy;
					
					if (x > maxX) maxX = x;
					if (x < minX) minX = x;
					if (y > maxY) maxY = y;
					if (y < minY) minY = y;
					
					Float[] coor = {dx, dy};
					coords.add(coor);
				}
				input.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			/*
			 * ig2.setBackground(Color.WHITE); ig2.fillRect(0, 0, 200, 200);
			 */

			int width = Math.round(maxX-minX);
			int height = Math.round(maxY-minY);
			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D ig2 = bi.createGraphics();

			
			BasicStroke bs = new BasicStroke(2);
			ig2.setStroke(bs);
			ig2.setPaint(Color.green);
			
			x=Math.min(minX, 0)*-1;
			y=Math.min(minY, 0)*-1;
			
			ig2.setPaint(Color.red);
			ig2.draw(new Rectangle2D.Double(minX, minY, maxX, maxY));
			

			for(int i = 0; i < coords.size(); i++) {
				dx = coords.get(i)[0];
				dy = coords.get(i)[1];
				ig2.draw(new Line2D.Double(x, y, x+dx, y+dy));
				x += dx;
				y += dy;				
			}


			ImageIO.write(bi, "PNG", new File("src/aoq2022/output/day11.png"));

		} catch (IOException ie) {
			ie.printStackTrace();
		}
		return "";

	}
}
