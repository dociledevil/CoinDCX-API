package utils;

public enum ErrorCodes {

	CODE400("Bad Request"),CODE401("Unauthorized"),CODE404("Not Found"),CODE429("Too Many Requests"),
	CODE500("Internal Server Error"),CODE503("Service Unavailable "),CODE422("Invalid request");
	String meaning;
	ErrorCodes(String meaning)
	{
		this.meaning=meaning;
	}
}
