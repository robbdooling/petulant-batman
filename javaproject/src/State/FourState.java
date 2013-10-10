package State;

/**
 * A UI state that displays four images.
 * @author msd7734
 *
 */
public class FourState implements State {

	@Override
	public int images() {
		return 4;
	}

	@Override
	public State next() {
		return new OneState();
	}

}
