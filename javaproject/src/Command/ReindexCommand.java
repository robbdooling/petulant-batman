/**
 * Handles reseting the index of the currently selected images
 */
package Command;

import Director.Director;
import State.*;
import Study.*;

public abstract class ReindexCommand implements Command {

	@Override
	public void execute() {
		Study currentStudy = Director.getStudy();
		int currentIndex = currentStudy.getIndex();
		int newIndex;
		if(direction() == -1 && (currentIndex - StateHolder.images()) < 0){
			newIndex = 0;
		}
		newIndex = currentIndex + (StateHolder.images() * direction());
		currentStudy.setIndex(newIndex);
	}
	abstract public int direction();
	//This determines the direction the command goes in. -1 is to the left, 1 is to the right
}
