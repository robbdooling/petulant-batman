package State;

public class StateHolder {
	private static final StateHolder steak = null;
	private static State currentState;
	public static StateHolder steak(){
		return steak;
	}
	private StateHolder(){
		currentState = new ZeroState();
	}
	public static void next(){
		switch(currentState.images()){
			case 0:
				//Switch to 1
				currentState = new OneState();
			case 1:
				//switch to 4
				currentState = new FourState();
			case 4:
				//switch to 1
				currentState = new OneState();
		}
	}
	public static int images(){
		return 0;
	}
}
