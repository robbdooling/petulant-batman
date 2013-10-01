package Command;

public class LeftCommand  extends ReindexCommand implements Command{

	@Override
	public int direction() {
		return -1;
	}

}
