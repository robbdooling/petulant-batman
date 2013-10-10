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

import State.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.regex.*;

public class StudyFactory {
	
	public enum StudyType { remote, local; }
	
	private static String root; 
	private static List<Study> availStudies = null;
	
	private StudyFactory() { }
	
	/**
	 * Find all the studies at a given address.
	 * @param rootDir Some String for accessing a directory or remote address.
	 * @param studyType Determines the kind of study, local or remote, that is returned
	 * @return An array of Study objects
	 * @throws NoValidStudiesFoundException If no valid Study objects can be built
	 * at the given address
	 */
	
	public static void setRoot(String newRoot){
		root = newRoot;
	}
	public static List<String> getStudyNames() throws NoValidStudiesFoundException{
		availStudies =  Arrays.asList(getAvailableStudies(StudyType.local));
		List<String> stringStudies = new ArrayList<String>();
		for(Study curr: availStudies){
			stringStudies.add(curr.getMyPath());
		}
		return stringStudies;
	}
	public static void choseStudy(int Index){
		Study study = availStudies.get(Index);
		StateHolder.setStudy(study);
		State s = readState(study.getMyPath());
		if (s == null) {
			StateHolder.empty();
			StateHolder.next();
		}
		else {
			while (StateHolder.images() != s.images()) {
				StateHolder.next();
			}
		}
	}
	
	
	
	
	public static Study[] getAvailableStudies(StudyType studyType)
		throws NoValidStudiesFoundException {
		String rootDir = root;
		if (! new File(rootDir).exists())
			throw new NoValidStudiesFoundException(rootDir);
		
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
	
	/**
	 * Given an address, read the state of a Study from a .sav file
	 * @param studyDir An address where a 0.sav can be found and read
	 * @return The UI State read
	 */
	public static State readState(String studyDir) {
		
		File f = new File(studyDir + File.separator + "0.sav");
		
		try {
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String[] pair = sc.nextLine().split("=");
				if (pair[0].equals("state")) {
					if (pair[1].equals("one")) {
						return new OneState();
					}
					else if (pair[1].equals("four")) {
						return new FourState();
					}
					else if(pair[1].equals("zero")) {
						return new ZeroState();
					}
					else {
						System.err.println("Invalid state read.");
						return null;
					}
				}
			}
			sc.close();
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("No save file found.");
			return null;
		}
		
		System.err.println("Found no state information in study .sav file");
		return null;
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
				Arrays.sort(jpgs);
				
				
				File save = new File(studyDir.getPath() + File.separator + "0.sav");
				int studyStart = 0;
				
				//Format:
				//name=val
				try {
					Scanner sc = new Scanner(save);
					while (sc.hasNextLine()) {
						String[] pair = sc.nextLine().split("=");
						if (pair[0].equals("index")) {
							studyStart = Integer.parseInt(pair[1]);
						}
					}
					sc.close();
				}
				catch(FileNotFoundException fnfe) {
					studyStart = 0;
				}
				catch(NumberFormatException nfe) {
					studyStart = 0;
				}
				
				if (jpgs == null) {
					//no jpgs found
				}
				
				else if (jpgs.length > 0) {
					studies.add(new FileStudy(
						Arrays.asList( getAbsolutePaths(studyDir, jpgs) ),
						studyDir.getName(),
						studyDir.getAbsolutePath(),
						studyStart
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
