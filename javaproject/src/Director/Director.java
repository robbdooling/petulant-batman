package Director;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import State.StateHolder;
import Study.NoValidStudiesFoundException;
import Study.Study;
import Study.StudyBuilder;
import Study.StudyBuilder.StudyType;
/**
 * Simplifys talking to and stores the currently opened Study
 * @author rob
 *
 */
public class Director {
	private static List<Study> availStudies = null;
	private static String root = "";
	private static Study study = null;
	/**
	 * Gets the current study
	 * @return The current study loaded in
	 */
	public static Study getStudy(){
		return study;
	}
	/**
	 * Sets the study to a new value
	 * @param newStudy the new study to replace the current one with
	 */
	public static void setStudy(Study newStudy){
		study = newStudy;
	}
	/**
	 * Returns a list of images to load into the gui based on the currentstate
	 * @return the list of images to load
	 */
	public static List<String> getImages(){
		List<String> poop = null;
		if(StateHolder.images() == 1){
			poop = study.getImgAddresses().subList(study.getIndex(), (study.getIndex() + StateHolder.images()));
		}
		else if(StateHolder.images() == 4){
			if((study.getIndex() + 4) > (study.getImgAddresses().size()-1)){
				poop = study.getImgAddresses().subList(study.getIndex(), (study.getImgAddresses().size() - 1));
			}
			else{
				poop = study.getImgAddresses().subList(study.getIndex(), (study.getIndex() + StateHolder.images()));	
			}
		}
		return poop;
	}
	/**
	 * Set the root search directory to a different path
	 * @param newRoot new Root path
	 */
	public static void setRoot(String newRoot){
		root = newRoot;
	}
	/**
	 * Gets all of the valid directories that are studies
	 * @return All valid directories 
	 * @throws NoValidStudiesFoundException
	 */
	public static List<String> getAvailStudies() throws NoValidStudiesFoundException{
		availStudies =  Arrays.asList(StudyBuilder.getAvailableStudies(root, StudyType.local));
		List<String> stringStudies = new ArrayList<String>();
		for(Study curr: availStudies){
			stringStudies.add(curr.getMyPath());
		}
		return stringStudies;
	}
	/**
	 * Chooses a study based on an index of the getAvailStudies
	 * @param Index The index of the chosen study
	 */
	public static void choseStudy(int Index){
		study = availStudies.get(Index);
		StateHolder.empty();
		StateHolder.next();
	}
	/**
	 * Indicates if there is anything to the "left"
	 * @return boolean that indicates whether it's possible to move to the left
	 */
	public static boolean isLeft(){
		if(study == null){
			return false;
		}
		int currentIndex = study.getIndex();
		int step = StateHolder.images();
		return !((currentIndex - 2) < 0);
	}
	/**
	 * Indicates if there is anything to the "right"
	 * @return boolean that indicates whether it's possible to move to the right
	 */
	public static boolean isRight(){
		if(study == null){
			return false;
		}
		int currentIndex = study.getIndex();
		int step = StateHolder.images();
		int maxIndex = (study.getImgAddresses().size() - 1);
		
		return !((currentIndex + 2) > maxIndex);
	}
}