package Study;

import java.util.List;
/**
 * Study that represents no study loaded
 * Prevents the need for code to check if the current study is null
 * @author Robert Lowe
 *
 */
public class NoStudy implements Study{

	@Override
	public List<String> getImgAddresses() {
		return null;
	}

	@Override
	public String[] getCurImgAddress() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getMyPath() {
		return null;
	}

	@Override
	public void saveState() {
		
	}

	@Override
	public void saveState(int index) {
		
	}

	@Override
	public int getIndex() {
		return 0;
	}

	@Override
	public void setIndex(int newIndex) {
		
	}

	@Override
	public boolean canLeft() {
		return false;
	}

	@Override
	public boolean canRight() {
		return false;
	}

	@Override
	public List<String> getImages() {
		return null;
	}

}
