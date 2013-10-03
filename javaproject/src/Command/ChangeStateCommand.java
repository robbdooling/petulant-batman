package Command;

import State.StateHolder;
/**
 * This command changes the state of the statemachine
 * @author rob
 *
 */
public class ChangeStateCommand implements Command {

	@Override
	public void execute() {
		StateHolder.next();
	}

}
