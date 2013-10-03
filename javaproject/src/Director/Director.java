package Director;

import java.util.List;

import State.StateHolder;
import Study.Study;
public class Director {
	private static Study study = null;
	public static Study getStudy(){
		return study;
	}
	public static void setStudy(Study newStudy){
		study = newStudy;
	}
	public static List<String> getImages(){
		return study.getImgAddresses().subList(study.getIndex(), (study.getIndex() + StateHolder.images() - 1));
		
	}
}