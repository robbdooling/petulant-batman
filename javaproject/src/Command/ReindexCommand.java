/**
 * Handles reseting the index of the currently selected images
 */
package Command;

import Director.Director;
import Study.Study;

public abstract class ReindexCommand implements Command {

	@Override
	public void execute() {
		Study currentStudy = Director.D().getStudy();
		int currentIndex = currentStudy.getIndex();
		
	}
	abstract public int direction();
	//This determines the direction the command goes in. -1 is to the left, 1 is to the right
}
