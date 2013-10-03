package Command;

import Director.Director;
/**
 * Command that forces the Study to save it's state
 * @author rob
 *
 */
public class SaveStudyCommand implements Command {

	@Override
	public void execute() {
		Director.getStudy().saveState();

	}

}
