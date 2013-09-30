package Study;

import java.util.List;

public class FileStudy implements Study {
	
	private List<String> imgAddresses;
	
	public FileStudy(List<String> imgAddresses) {
		this.imgAddresses = imgAddresses;
	}

	@Override
	public List<String> getImgAddresses() {
		return imgAddresses;
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
	public String getLocation() {
		// TODO Auto-generated method stub
		return null;
	}
}
