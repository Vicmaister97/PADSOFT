/**
 * 
 * @author Carlos Isasa Martin and Victor Garcia Carrera
 */

package Exercises;
import CourseElements.Exercise;
import Users.Student;
import java.util.ArrayList;
import java.util.List;

public class MultipleChoice extends QuestionChoice {
	private List<String> correctAnswers;
	
	public MultipleChoice (Exercise exe, double weight, String QText, boolean randOrd, List<String> answers){
		super(exe, weight, QText, randOrd);
		this.correctAnswers = new ArrayList<>(answers);
	}

	/**
	 * @return the correct answers of the question
	 */
	public List<String> getCorrectAnswers() {
		return correctAnswers;
	}
	

	/**
	 * @param correctanswer
	 * @return true if the answer was correctly added to the answers of the question or false if not
	 */
	public boolean addCorrectAnswer(String correctanswer) {
		if (this.getExercise().isDone()){
			return false;
		}
		
		if(this.getPossibleAnswers().contains(correctanswer)){ /*Our answer is one of the possible answers of the question*/
			this.correctAnswers.add(correctanswer);
			return true;
		}
		return false;
	}
	
	/**
	 * @param noMoreCorrectAnswer
	 * @return true if the correct answer was removed or false not
	 */
	public boolean removeCorrectAnswer(String noMoreCorrectAnswer){
		if (this.getExercise().isDone()){
			return false;
		}
		
		if (this.correctAnswers.remove(noMoreCorrectAnswer)){
			return true;
		}
		return false;
	}
	
	/**
	 * @param noMorePossibleAnswer
	 * @return true if the possible answer was removed or false if not
	 */
	public boolean removePossibleAnswer(String noMorePossibleAnswer){
		if (this.getExercise().isDone()){
			return false;
		}
		
		if (this.getPossibleAnswers().remove(noMorePossibleAnswer)){ /*The possible answer was in the list and it was removed correctly*/
			this.correctAnswers.remove(noMorePossibleAnswer);
			return true;
		}
		return false;
	}
	
	
	public AnswerQuestion solveQuestion(Student student, List<String> answer){
		double mark = 0;
		for (String ans : answer){
			if (this.getPossibleAnswers().contains(ans) == false){ /*One of the answers isn't a possible answer*/
				return null;
			}
		}
		
		if (this.getExercise().getCourse().getStudents().contains(student) == false){ /*The student isn't part of the course*/
			return null;
		}
		
		QuestionOption choice = new QuestionOption(answer);
		AnswerQuestion finalAnswer;
		
		if (this.getCorrectAnswers().size() == answer.size()){ /*The same number of answers*/
			for (String correct: this.getCorrectAnswers()){
				if (answer.contains(correct) == false){ /*There is a correct answer of the question missing*/
					mark = 0;
					break;
				}
				mark = this.getWeight();
			}
			
		}
		else{ /*The question is incorrect, different number of answers*/
			mark = 0;
		}
		finalAnswer = new AnswerQuestion(this, student, choice, mark);
		this.addAnswer(finalAnswer);
		return finalAnswer;
	}
	
	
}