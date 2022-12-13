package aoq2022;
import static spark.Spark.get;

import aoq2022.days.DayFactory;
import aoq2022.days.GenericDay;

public class Main {

	public static void main(String[] args) {
		get("/aoq/2022", (request, response) -> {
			return "Landing page!";
		});
		get("/aoq/2022/day/:d", (request, response) -> {
			GenericDay d = DayFactory.getDay(Integer.parseInt(request.params(":d")));
			if (d == null)
				return "ERROR";
			
			d.setRequest(request);
			return d.solve();
		});

	}
}