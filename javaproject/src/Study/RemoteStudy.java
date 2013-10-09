package Study;

import java.util.List;

/**
 * A shell for an implementation of a Study that is built from
 * a remote address.
 * @author msd7734
 *
 */
public class RemoteStudy implements Study {
	public RemoteStudy() { }
	
	@Override
	public List<String> getImgAddresses() {
		return null;
	}
	
	@Override
	public String[] getCurImgAddress() {
		return null;
	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setIndex(int newIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getMyPath() {
		return null;
	}
	
	@Override
	public void saveState() {
		return;
	}
	
	@Override
	public void saveState(int index) {
		return;
	}

	@Override
	public boolean canLeft() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canRight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getImages() {
		// TODO Auto-generated method stub
		return null;
	}
}
