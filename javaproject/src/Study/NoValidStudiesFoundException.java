package Study;

public class NoValidStudiesFoundException extends Exception {
	NoValidStudiesFoundException(String dir) {
		super("No valid studies could be found in the root directory " + dir);
	}
}
