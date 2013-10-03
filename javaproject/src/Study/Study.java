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
	
	public void saveState();
	
	public int getIndex();
	public void setIndex(int newIndex);

}
