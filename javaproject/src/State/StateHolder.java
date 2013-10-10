package State;

import Study.NoStudy;
import Study.Study;

/**
 * A class that encapsulates UI State operations.
 * @author msd7734
 *
 */
public class StateHolder {
	private static State currentState = new ZeroState();
	private static Study currentStudy = new NoStudy();
	/**
	 * Cycle the current state to the next logical state.
	 */
	public static void next(){
		currentState = currentState.next();
	}
	/**
	 * Retrieves the current study being operated on
	 * @return The current study
	 */
	public static Study getStudy(){
		return currentStudy;
	}
	
	/**
	 * Changes the current study being operated on
	 * @param newStudy The new study to change to
	 */
	public static void setStudy(Study newStudy){
		currentStudy = newStudy;
	}
	
	/**
	 * Force the state to display 4 images.
	 */
	public static void setFour(){
		currentState = new FourState();
	}
	
	/**
	 * Wrap the images() method of State
	 * @return Return the number of images the state wants to display
	 */
	public static int images(){
		return currentState.images();
	}
	
	/**
	 * Force the state to display an empty state.
	 */
	public static void empty(){
		currentState = new ZeroState();
	}
}
