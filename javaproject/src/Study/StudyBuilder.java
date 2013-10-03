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
	
	
	private StudyBuilder() { }
	
	public static Study[] getAvailableStudies(String rootDir, StudyType studyType)
		throws NoValidStudiesFoundException {
		
		if (studyType == StudyType.remote) {
			//dummy object to pay lip service to concept of remote study
			return new RemoteStudy[]{};
		}
		
		else if (studyType == StudyType.local) {
			return findLocalStudies(rootDir, studyType);
		}
		
		else { 
			//default behavior
			//return empty local study
			return new FileStudy[]{};
		}
	}
	
	private static FileStudy[] findLocalStudies(String rootDir, StudyType studyType)
	throws NoValidStudiesFoundException {
		
		//This should probably return paths, not FileStudys. If it's given to the UI
		//we don't want the UI to be dealing with a Study object
		//-Rob
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
				
				//TODO: Get studyStart through reading savefile
				int studyStart = 0;
				
				if (jpgs.length > 0) {
					studies.add(new FileStudy(
						Arrays.asList( getAbsolutePaths(studyDir, jpgs) ),
						studyDir.getName(),
						0
					));
				}
				
			}
		}
		
		//we have no studies, up to the caller to decide what to do
		if (studies.size() == 0)
			throw new NoValidStudiesFoundException(rootDir);
		
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
	private static String[] getAbsolutePaths(File dir, String[] paths) {
		String[] result = new String[paths.length];
		
		for (int i=0; i<paths.length; i++) {
			result[i] = dir.getAbsolutePath() + File.separator +  paths[i];
			//result[i] = new File(paths[i]).getAbsolutePath();
		}
		
		return result;
	}	
	
}
