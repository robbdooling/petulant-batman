package Study;

import java.util.List;

/**
 * Defines the methods common to local and remote Study objects.
 * A study is mean to hold a collection of addresses to allow
 * a UI to load images. Also provides a way to save its settings
 * to a .sav file.
 * @author msd7734
 *
 */
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
	
	/**
	 * Return the location of this Study
	 * @return A String address 
	 */
	public String getMyPath();
	
	/**
	 * Save this Study's information on current image address and
	 * the display state of the application.
	 */
	public void saveState();
	public void saveState(int index);
	
	/**
	 * Get the index of the current image to display
	 * @return An integer index
	 */
	public int getIndex();
	
	/**
	 * Set the index of the current image to display
	 * @param newIndex The new index
	 */
	public void setIndex(int newIndex);

}
