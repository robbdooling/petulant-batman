package Study;

import java.util.List;

public interface Study {
	/**
	 * Get a collection of Strings representing absolute pathnames to .jpg
	 * images in a study.
	 * @return List of Strings
	 */
	public List<String> getImgAddresses();
	
	/**
	 * Get the absolute path for the current image(s) being looked at
	 * @return
	 */
	public String[] getCurImgAddress();
	
	/**
	 * Get the name of this study (usually the directory name)
	 * @return Name String
	 */
	public String getName();
	
	public String getMyPath();
	
	//Unclear if Study should be handling its current index or not...
	//So for now, we have two possiblities
	//I'm so, so sorry
	public void saveState();
	public void saveState(int index);
	
	public int getIndex();
	public void setIndex(int newIndex);

}
