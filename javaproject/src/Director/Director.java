package Director;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import State.StateHolder;
import Study.NoValidStudiesFoundException;
import Study.Study;
import Study.StudyBuilder;
import Study.StudyBuilder.StudyType;
public class Director {
	private static List<Study> availStudies = null;
	private static String root = "";
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
	public static void setRoot(String newRoot){
		root = newRoot;
	}
	public static List<String> getAvailStudies() throws NoValidStudiesFoundException{
		availStudies =  Arrays.asList(StudyBuilder.getAvailableStudies(root, StudyType.local));
		List<String> stringStudies = new ArrayList<String>();
		for(Study curr: availStudies){
			stringStudies.add(curr.getMyPath());
		}
		return stringStudies;
	}
	public static void choseStudy(int Index){
		study = availStudies.get(Index);
	}
	public static boolean isLeft(){
		if(study == null){
			return false;
		}
		int currentIndex = study.getIndex();
		int step = StateHolder.images();
		return ((currentIndex - step) < 0);
	}
	public static boolean isRight(){
		if(study == null){
			return false;
		}
		int currentIndex = study.getIndex();
		int step = StateHolder.images();
		int maxIndex = study.getImgAddresses().size();
		
		return ((currentIndex + step) > maxIndex);
	}
}