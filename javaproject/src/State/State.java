package State;

/**
 * An interface to define a UI state. The purpose of this state
 * is to describe how many images the UI should expect to display
 * at once.
 * @author msd7734
 *
 */
public interface State {
	/**
	 * How many images the UI should expect to display at once.
	 * @return An int - the number of images
	 */
	public int images();
}