package Command;
/**
 * Command for moving selected images to the left
 * @author rob
 *
 */
public class LeftCommand  extends ReindexCommand implements Command{

	@Override
	public int direction() {
		return -1;
	}

}
