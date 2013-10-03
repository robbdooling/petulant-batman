package Study;

import java.util.List;

public class FileStudy implements Study {
	
	
	private List<String> imgAddresses;
	private String name;
	private int curIndex;
	private int bufferSize;
	
	public FileStudy(List<String> imgAddresses, String name, int startIndex) {
		this.imgAddresses = imgAddresses;
		this.name = name;
		this.curIndex = startIndex;
		
		//should figure out how to set this properly
		bufferSize = 1;
	}

	@Override
	public List<String> getImgAddresses() {
		return imgAddresses;
	}
	
	@Override
	public String[] getCurImgAddress() {
		return imgAddresses.subList(curIndex, curIndex + bufferSize)
			.toArray(new String[]{});
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getIndex() {
		return curIndex;
	}

	@Override
	public void setIndex(int newIndex) {
		if (newIndex + (bufferSize-1) >= imgAddresses.size()) {
			System.err.println("Attempted to set study current image index out of bounds.");
			throw new IndexOutOfBoundsException();
		}
		
		else {
			curIndex = newIndex;
		}
	}
}
