package Command;

import Director.Director;

public class SaveStudyCommand implements Command {

	@Override
	public void execute() {
		Director.getStudy().saveState();

	}

}
