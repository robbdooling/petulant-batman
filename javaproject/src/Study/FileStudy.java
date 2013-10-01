package Study;

import java.util.List;

public class FileStudy implements Study {
	
	private List<String> imgAddresses;
	private String name;
	
	public FileStudy(List<String> imgAddresses, String name) {
		this.imgAddresses = imgAddresses;
		this.name = name;
	}

	@Override
	public List<String> getImgAddresses() {
		return imgAddresses;
	}
	
	@Override
	public String getName() {
		return name;
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
