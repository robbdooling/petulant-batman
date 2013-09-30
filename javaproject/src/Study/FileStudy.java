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
}
