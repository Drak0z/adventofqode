package aoq2022.days;
import spark.Request;

public abstract class GenericDay {
	private Request request;

	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	
	public abstract String solve();

}
