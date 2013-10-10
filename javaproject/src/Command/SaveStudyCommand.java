package Command;

import State.StateHolder;
/**
 * Command that forces the Study to save it's state
 * @author rob
 *
 */
public class SaveStudyCommand implements Command {

	@Override
	public void execute() {
		StateHolder.getStudy().saveState();

	}

}
