package kenijey.harshencastle.objecthandlers;

public class HarshenAPIError extends RuntimeException
{
	public HarshenAPIError(String message, Throwable cause) {
		super(message, cause);
	}
}
