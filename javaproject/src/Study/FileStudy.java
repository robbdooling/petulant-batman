package Study;

import State.*;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

		try {
			File save = new File(myPath + File.separator + "0.sav");
			if (!save.exists()) {
				save.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(save, false);
			//don't forget to implement reading ZeroState in studybuilder
			byte[] indexEntry = new String("index=" +
				this.curIndex + "\n")
				.getBytes();
			
			byte[] stateEntry = new String("state=" +
				stateToString(StateHolder.getCurrentState()) + "\n")
				.getBytes();
			
			out.write(indexEntry);
			out.write(stateEntry);
			out.close();
		}
		catch (IOException ioe) {
			System.err.println("IOException in saveState()");
		}
	}
	
	@Override
	public void saveState(int index) {
		//because we don't know if the Study should manage its index yet,
		//take an index int in case it's handled externally
		
		try {
			File save = new File(myPath + File.separator + "0.sav");
			if (!save.exists()) {
				save.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(save, false);
			//don't forget to implement reading ZeroState in studybuilder
			byte[] indexEntry = new String("index=" +
				index + "\n")
				.getBytes();
			
			byte[] stateEntry = new String("state=" +
				stateToString(StateHolder.getCurrentState()) + "\n")
				.getBytes();
			
			out.write(indexEntry);
			out.write(stateEntry);
			out.close();
		}
		catch (IOException ioe) {
			System.err.println("IOException in saveState()");
		}
	}
	
	private String stateToString(State state) {
		if (state instanceof OneState) {
			return "one";
		}
		else if (state instanceof FourState) {
			return "four";
		}
		else {
			return "zero";
		}
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
