package Command;
/**
 * Command for moving selected images to the right
 * @author rob
 *
 */
public class RightCommand extends ReindexCommand implements Command {

	@Override
	public int direction() {
		return 1;
	}

}
