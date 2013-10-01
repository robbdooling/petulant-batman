/**
 * Handles reseting the index of the currently selected images
 */
package Command;

public abstract class ReindexCommand implements Command {

	@Override
	public void execute() {
				
	}
	abstract public int direction();
	//This determines the direction the command goes in. -1 is to the left, 1 is to the right
}
