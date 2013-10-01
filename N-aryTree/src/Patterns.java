import java.util.regex.Pattern;


public interface Patterns {
	
	public static final Pattern IP = Pattern.compile("\\b([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\b");

	public static final Pattern USER = Pattern.compile("\\b\\d+[a-zA-Z]+\\w*\\b|\\b[a-zA-Z]+\\d+\\w*\\b");

	public static final Pattern WORD =Pattern.compile("\\b[a-zA-Z]+\\b");

	public static final Pattern TMSP = Pattern.compile("\\b\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:\\d\\d:\\d\\d,\\d\\d\\d\\b");


}
