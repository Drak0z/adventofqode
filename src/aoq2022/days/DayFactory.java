package aoq2022.days;

import java.lang.reflect.InvocationTargetException;

public class DayFactory {

	public static GenericDay getDay(int d) {
		String className = null;
		if (d < 0) {
			className = "Neg" + d;
		} else {
			className = "" + d;
		}
		try {
			return (GenericDay) Class.forName("aoq2022.days.Day" + className).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			System.out.println("The class for this day failed to generate. Stack: " + e.getStackTrace());
		}

		return null;
	}

}
