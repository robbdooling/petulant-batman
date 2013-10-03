package Study;

import java.util.List;
import java.io.FileWriter;

public class FileStudy implements Study {
	
	
	private List<String> imgAddresses;
	private String name;
	private String myPath;
	private int curIndex;
	private int bufferSize;
	
	public FileStudy(List<String> imgAddresses, String name, String myPath, int startIndex) {
		this.imgAddresses = imgAddresses;
		this.myPath = myPath;
		this.name = name;
		this.curIndex = startIndex;
		
		//something may have to intervene to help set this properly
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
	public void saveState() {
		
		return;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getMyPath() {
		return myPath;
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
