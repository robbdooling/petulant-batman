package State;

import java.util.ArrayList;

public class StateHolder {
	private State currentState;
	private StateHolder(){
		currentState = new ZeroState();
	}
	public void next(){
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
	public int images(){
		return 0;
	}
}
