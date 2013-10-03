package Command;

import Director.Director;

public class OpenStudyCommand implements Command {

	@Override
	public void execute() {
		String StudyPath = Director.newStudyLocation();
		
	}

}
