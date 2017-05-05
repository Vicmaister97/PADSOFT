/**
 * 
 * @author Carlos Isasa Martin and Victor Garcia Carrera
 */

package exercises;

import java.util.ArrayList;
import java.util.List;
import courseElements.Exercise;

public abstract class QuestionChoice extends Question{

	private static final long serialVersionUID = -298635877263262593L;
	private List<String> possibleAnswers;
	private boolean randomOrder;
	
	public QuestionChoice(Exercise exe, double weight, String QText, boolean randOrder){
		super(exe, weight, QText);
		this.possibleAnswers = new ArrayList<String>();
		this.randomOrder = randOrder;
	}

	/**
	 * @return the possible answers of a question
	 */
	public List<String> getPossibleAnswers() {
		return possibleAnswers;
	}

	/**
	 * @return the value of the attribute randomOrder of the question
	 */
	public boolean isRandomOrder() {
		return randomOrder;
	}

	/**
	 * @param randomOrder
	 * @return true if the order has been changed or false if not
	 */
	public boolean changeOrder(boolean randomOrder) {
		if (this.getExercise().isDone()){
			return false;
		}
		this.randomOrder = randomOrder;
		return true;
	}
	
	/**
	 * @param possibleAnswer
	 * @return true if the possible answer has been added to the list of possible answers of the question
	 * or false if not
	 */
	public boolean addPossibleAnswer(String possibleAnswer){
		if (this.getExercise().isDone()){
			return false;
		}
		
		if (this.getPossibleAnswers().contains(possibleAnswer)){ /*That possible answer already exists*/
			return false;
		}
		this.getPossibleAnswers().add(possibleAnswer);
		return true;
	}
	
}
