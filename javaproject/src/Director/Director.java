package Director;

import java.util.ArrayList;

import Study.Study;
public class Director {
	private static Study study = null;
	public static Study getStudy(){
		return study;
	}
	public static void setStudy(Study newStudy){
		study = newStudy;
	}
	public static ArrayList<String> getImages(){
	
		return null;
		
	}
}