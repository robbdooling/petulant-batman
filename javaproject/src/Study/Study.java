package Study;

import java.util.List;

public interface Study {
	/**
	 * Get a collection of Strings representing absolute pathnames to .jpg
	 * images in a study.
	 * @return List of Strings
	 */
	public List<String> getImgAddresses();
	
	public String getName();
	
	public int getIndex();
	public void setIndex(int newIndex);
	public String getLocation();
}
