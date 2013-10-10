package Testing;

import Study.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class StudyBuilderTester {
	
	public static String OS = System.getProperty("os.name");
	public static final boolean WINDOWS = isWindows();
	
	public static boolean isWindows() {
		return (OS.toLowerCase().indexOf("win") >= 0 );
	}
	
	public static void main(String[] args) {
		boolean test = false;
		
		if (test) {
			File f = new File("Z:\\workspace");
			System.out.println(f.getPath());
			
			File f2 = new File(f.getPath()+"petulant-batman");
			System.out.println(f2.getPath());
		}
		
		if (WINDOWS) {
			//StudyBuilder sb = new StudyBuilder("C:\\Studies", StudyBuilder.StudyType.local);
			String root = "C:\\";
			StudyFactory.StudyType type = StudyFactory.StudyType.local;
			List<Study> studies;
			
			try {
				StudyFactory.setRoot(root);
				studies = Arrays.asList(StudyFactory.getAvailableStudies(type));
				for (Study s : studies) {
					for (String img : s.getImgAddresses()) {
						System.out.println(img);
					}
				}
			}
			catch (NoValidStudiesFoundException nvsfe) {
				System.out.println(nvsfe);
			}
		}
		
		else {
			System.err.println("Please write a test for " + OS + "!");
		}
	}
}
