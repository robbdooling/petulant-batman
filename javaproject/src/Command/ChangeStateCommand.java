package Command;

import State.StateHolder;

public class ChangeStateCommand implements Command {

	@Override
	public void execute() {
		StateHolder.next();
	}

}
