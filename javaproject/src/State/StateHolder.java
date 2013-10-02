package State;

public class StateHolder {
	private static State currentState = new ZeroState();
	
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
	public static void setFour(){
		currentState = new FourState();
	}
	public static int images(){
		return currentState.images();
	}
	public static void empty(){
		currentState = new ZeroState();
	}
}
