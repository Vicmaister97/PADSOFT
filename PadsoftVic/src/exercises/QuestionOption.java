/**
 * 
 * @author Carlos Isasa Martin and Victor Garcia Carrera
 */

package exercises;
import java.util.ArrayList;
import java.util.List;

public class QuestionOption implements java.io.Serializable{

	private static final long serialVersionUID = 870530495568180585L;
	private String choice;
	private List<String> multChoices;
	private boolean multiple;
	
	public QuestionOption(String option){
		this.choice = option;
		this.multiple = false;
	}
	
	public QuestionOption(List<String> options){
		this.multChoices = new ArrayList<String>(options);
		this.multiple = true;
	}

	/**
	 * @return the answer of the question
	 */
	public String getChoice() {
		return choice;
	}

	/**
	 * @param choice
	 */
	public void setChoice(String choice) {
		this.choice = choice;
	}

	/**
	 * @return the answers (List of Strings) of the question
	 */
	public List<String> getMultChoices() {
		return multChoices;
	}

	/**
	 * @param multChoices
	 */
	public void setMultChoices(List<String> multChoices) {
		this.multChoices = multChoices;
	}

	/**
	 * @return false if the question is a SimpleChoice question true if it's a MultipleChoice question
	 */
	public boolean isMultiple() {
		return multiple;
	}
	
	
}
