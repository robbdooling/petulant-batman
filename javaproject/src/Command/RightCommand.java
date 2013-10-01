package Command;

public class RightCommand extends ReindexCommand implements Command {

	@Override
	public int direction() {
		return 1;
	}

}
