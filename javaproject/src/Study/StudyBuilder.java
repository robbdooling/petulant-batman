package Study;

/**
 * StudyBuilder.java
 * 
 * @author Matthew Dennis
 * 
 * Responsible for constructing a list of valid Study objects from a given address,
 * local or remote. For this project, only local studies are fully accessible. The
 * CmdBuilder or some other intermediary should be responsible for managing the
 * returned list of Studies and exposing what is necessary.
 * 
 * 4/29/13 Matt Dennis
 * - Initial implementation
 * 
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.*;

public class StudyBuilder {
	
	public enum StudyType { remote, local; }
	
	private String rootDir;
	private StudyType studyType;
	
	public StudyBuilder(String searchDir, StudyType src) {
		rootDir = searchDir;
		studyType = src;
	}
	

	
	public Study[] getAvailableStudies() throws NoValidStudiesFoundException {
		if (this.studyType == StudyType.remote) {
			//dummy object to pay lip service to concept of remote study
			return new RemoteStudy[]{};
		}
		
		else if (this.studyType == StudyType.local) {
			return findLocalStudies();
		}
		
		else { 
			//default behavior
			//return empty local study
			return new FileStudy[]{};
		}
	}
	
	private FileStudy[] findLocalStudies() throws NoValidStudiesFoundException {
		ArrayList<FileStudy> studies = new ArrayList<FileStudy>();
		File root = new File(rootDir);
		
		//For each directory in root
		for (File f : root.listFiles()) {
			if (f.isDirectory()) {
				
				File studyDir = new File(f.getPath());
					
				String[] jpgs = studyDir.list(
					new FilenameFilter() {
						@Override
						public boolean accept(File dir, String name) {
							Pattern p = Pattern.compile(".+\\.jpg");
							Matcher m = p.matcher(name);
							return m.matches();
						}
					}
				);
				
				//TODO
				//if no .jpg files found in dir, should we skip or make an empty study?
				//good practice would probably be to show empty, but disallow access
				//might make things easier to just skip it, though.
				//Also, a settings file may mark a study in the future, .jpg regex is
				//only temporary
				// - Matt
				if (jpgs.length > 0)
					studies.add(new FileStudy(Arrays.asList(getAbsolutePaths(studyDir, jpgs))));
				
			}
		}
		
		//we have no studies, up to the caller to decide what to do
		if (studies.size() == 0)
			throw new NoValidStudiesFoundException();
		
		return studies.toArray(new FileStudy[]{});
	}
	
	/**
	 * Gets the path as "absolutely" as the original root directory you gave to
	 * this StudyBuilder. Meant to be called on the .jpgs of a study.
	 * @param dir The directory of the Study
	 * @param paths Path names to Study images
	 * @return An array of path strings as a copy of paths, but with "absolute"
	 * paths
	 */
	private String[] getAbsolutePaths(File dir, String[] paths) {
		String[] result = new String[paths.length];
		
		for (int i=0; i<paths.length; i++) {
			result[i] = dir.getAbsolutePath() + File.separator +  paths[i];
			//result[i] = new File(paths[i]).getAbsolutePath();
		}
		
		return result;
	}
	
	///////////////////////
	// Getters and Setters
	///////////////////////
	
	public StudyType getStudyType() {
		return studyType;
	}
	
	public String getSearchDir() {
		return rootDir;
	}
	
	public void setStudyType(StudyType st) {
		studyType = st;
	}
	
	public void setSearchDir(String dir) {
		rootDir = dir;
	}
	
	public class NoValidStudiesFoundException extends Exception {
		NoValidStudiesFoundException() {
			super("No valid studies could be found in the given root directory.");
		}
	}
}
