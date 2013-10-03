package Study;
/**
 * An Exception to throw when a StudyBuilder is given a root directory
 * from which no valid Studies can be built.
 * @author msd7734
 *
 */
public class NoValidStudiesFoundException extends Exception {
	NoValidStudiesFoundException(String dir) {
		super("No valid studies could be found in the root directory " + dir);
	}
}
