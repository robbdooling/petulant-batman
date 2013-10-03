package State;

/**
 * A class that encapsulates UI State operations.
 * @author msd7734
 *
 */
public class StateHolder {
	private static State currentState = new ZeroState();
	
	/**
	 * Cycle the current state to the next logical state.
	 */
	public static void next(){
		if(currentState.images() == 0){
			//Switch to 1
			currentState = new OneState();
		}else if(currentState.images() == 1){
			//switch to 4
			currentState = new FourState();
		}else if(currentState.images() == 4){
			//switch to 1
			currentState = new OneState();
		}
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
