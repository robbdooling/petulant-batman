package Director;

import Study.Study;
public class Director {
	private static Study study = null;
	public static Study getStudy(){
		return study;
	}
	public static void setStudy(Study newStudy){
		study = newStudy;
	}
}