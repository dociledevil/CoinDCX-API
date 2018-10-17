package utils;

public class CoindcxApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	ErrorCodes code;
	
	public CoindcxApiException(int code) {
		this.code=ErrorCodes.valueOf("CODE"+code);
	}
	@Override
	public String toString() {
		return "CoindcxApiException [" + code.meaning + "]";
	}
}
