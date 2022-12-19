package aoq2022.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Day16 extends GenericDay {
	private class Ingredient {
		private String name; 

		private long tastiness;
		private long crunchiness;
		private long muscleyness;
		private long color;
		private long calories;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public long getTastiness() {
			return tastiness;
		}
		public void setTastiness(long tastiness) {
			this.tastiness = tastiness;
		}
		public long getCrunchiness() {
			return crunchiness;
		}
		public void setCrunchiness(long crunchiness) {
			this.crunchiness = crunchiness;
		}
		public long getMuscleyness() {
			return muscleyness;
		}
		public void setMuscleyness(long muscleyness) {
			this.muscleyness = muscleyness;
		}
		public long getColor() {
			return color;
		}
		public void setColor(long color) {
			this.color = color;
		}
		
		public long getCalories() {
			return calories;
		}
		public void setCalories(long calories) {
			this.calories = calories;
		}
		
		public Ingredient(String name, long tastiness, long crunchiness, long muscleyness, long color, long calories) {
			this.setName(name);
			this.setTastiness(tastiness);
			this.setCrunchiness(crunchiness);
			this.setMuscleyness(muscleyness);
			this.setColor(color);
			this.setCalories(calories);
		}
	}
	/**
	 * Day 16 - The Ultimate Protein Smoothie
	 * 
	 * With the coming invasion, the elves have started bulking up and are looking
	 * to create the perfect high-energy smoothie!
	 * 
	 * The standard elf MagicBullet(TM) blender holds 100ml of fluid (elves are
	 * small, remember).
	 * 
	 * Recipe ingredients are scored in these 4 categories:
	 * 
	 * tastiness (how much it improves the overall taste of the smoothie)
	 * crunchiness (how much crunch it adds to the smoothie) muscleyness (how much
	 * it helps the elf bulk up) color (how pleasing is the color to look at) In
	 * addition, each ingredient also is scored on the number of calories per unit.
	 * 
	 * We need you to find the optimal smoothie recipe to help these elves get
	 * absolutely ripped!
	 * 
	 * Each recipe is scored by adding up the totals per category (scaled by the
	 * quantity of that ingredient) and multiplying them together. If the category
	 * total (the sum of all the category scores for all the ingredients) is less
	 * than zero, it is treated as a zero.
	 * 
	 * Some rules:
	 * 
	 * The blender must be full (100ml of ingredients exactly) Elves hate fractional
	 * measurements. Whole number ingredient amounts only! For example: Consider
	 * only the following ingredients.
	 * 
	 * Sugar: tastiness 2, crunchiness 0, muscleyness 2, color -3, calories 2
	 * Chocolate: tastiness 0, crunchiness 2, muscleyness -1, color 2, calories 8 If
	 * we were to mix up a smoothie with 37 parts sugar and 63 parts chocolate we
	 * would get:
	 * 
	 * tastiness = (37 * 2) + (63 * 0) = 74 crunchiness = (37 * 0) + (63 * 2) = 126
	 * muscleyness = (37 * 2) + (63 * -1) = 11 color = (37 * -3) + (63 * 2) = 15
	 * Yielding a total smoothie score of (74 * 126 * 11 * 15) = 1538460, and a
	 * total of (37 * 2 + 63 * 8) = 578 calories.
	 * 
	 * Note, however, elves can consume no more than 250 calories in a single
	 * session. This particular smoothie recipe would be super fatal.
	 * 
	 * The real list of smoothie ingredients is here:
	 * 
	 * Candy canes: tastiness 3, crunchiness 3, muscleyness -1, color 1, calories 3
	 * Wrapping paper: tastiness 1, crunchiness -2, muscleyness -1, color -1,
	 * calories 1 Shoe polish: tastiness 3, crunchiness 0, muscleyness 1, color 3,
	 * calories 2 Last year's reindeer: tastiness -2, crunchiness 5, muscleyness 5,
	 * color -3, calories 5 What is the highest-scoring smoothie you can mix up
	 * using the above list of 4 ingredients that is no more than 250 calories?
	 * (answer as an Long, without punctuation!)
	 */
	
	private class Solution implements Comparable<Solution> {
		List<Long> quantities;
		List<Ingredient> ingredients;
		
		public Solution(List<Long> quantities, List<Ingredient> ingredients) {
			this.quantities = quantities;
			this.ingredients = ingredients;
		}
		
		public boolean isViable() {
			return (getMl() == 100) && getCalories() <= 250 && getScore() > 0;
		}
		
		public long getMl() {
			long ml = 0;
			for (int i = 0; i < quantities.size(); i++) {
				ml += quantities.get(i);
			}
			return ml;
		}
		
		public long getCalories() {
			long result = 0;
			for (int i = 0; i < quantities.size(); i++) {
				result += quantities.get(i) * ingredients.get(i).getCalories();
			}
			return result;
		}
		
		public long getScore() {
			long t = 0;
			long c = 0;
			long m = 0;
			long co = 0;
			for (int i = 0; i < quantities.size(); i++) {
				t += quantities.get(i) * ingredients.get(i).getTastiness();
				c += quantities.get(i) * ingredients.get(i).getCrunchiness();
				m += quantities.get(i) * ingredients.get(i).getMuscleyness();
				co += quantities.get(i) * ingredients.get(i).getColor();
			}
			return t*c*m*co;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Solution: [");
			for (int i = 0; i < quantities.size(); i++) {
				if (i > 0) sb.append(" + ");
				sb.append(quantities.get(i) + " x " + ingredients.get(i).getName());
			}
			sb.append("] Calories: " + getCalories());
			sb.append(", Score: " + getScore());
			return sb.toString();
		}

		@Override
		public int compareTo(Solution o) {
			long tScore = getScore();
			long oScore = o.getScore();
			if (tScore < oScore) return -1;
			if (tScore > oScore) return 1;
			return 0;
		}
		
		
	}
	

	@Override
	public String solve() {
		Ingredient candyCane = new Ingredient("Candy Cane", 3, 3, -1, 1, 3);
		Ingredient wrappingPaper = new Ingredient("Wrapping Paper", 1, -2, -1, -1, 1);
		Ingredient shoePolish = new Ingredient("Shoe Polish", 3, 0, 1, 3, 2);
		Ingredient lastYearsReindeer = new Ingredient ("Last year's reindeer", -2, 5, 5, -3, 5);
		
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(candyCane);
		ingredients.add(wrappingPaper);
		ingredients.add(shoePolish);
		ingredients.add(lastYearsReindeer);
		
		Solution r = null;
		//long startTime = System.nanoTime();
		TreeSet<Solution> solutions = new TreeSet<Solution>();
		

		for (long cc = 0; cc <= 100; cc++) {
			for (long wp = 0; wp <= 100-cc; wp++) {
				for (long sp = 0; sp <= 100-cc-wp; sp++) {
					for (long lr = 0; lr <= 100-cc-wp-sp; lr++) {
						if (cc+wp+sp+lr == 100) {
							List<Long> quantities = Arrays.asList(cc,wp,sp,lr);
							Solution s = new Solution(quantities, ingredients);
							if (s.isViable()) {
								solutions.add(s);
							}
						}
					}
				}
			}
		}
		
		r = solutions.last();
		//System.out.println("Took: " + Util.getHumanReadableTime(System.nanoTime() - startTime));
		return r.toString();

	}

}
