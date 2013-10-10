package State;

/**
 * A UI State which is used when there are no images to display.
 * @author msd7734
 *
 */
public class ZeroState implements State {

	@Override
	public int images() {
		return 0;
	}

	@Override
	public State next() {
		return new OneState();
}

}
