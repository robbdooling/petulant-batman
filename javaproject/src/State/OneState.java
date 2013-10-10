package State;

/**
 * A UI state which displays one image.
 * @author msd7734
 *
 */
public class OneState implements State {

	@Override
	public int images() {
		return 1;
	}

	@Override
	public State next() {
		return new FourState();
		
	}

}
