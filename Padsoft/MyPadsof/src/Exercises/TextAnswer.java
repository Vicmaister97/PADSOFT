/**
 * 
 * @author Carlos Isasa Martin and Victor Garcia Carrera
 */

package Exercises;
import CourseElements.Exercise;
import Users.Student;
public class TextAnswer extends Question {
	private String answer;
	
	public TextAnswer(Exercise exe, double weight, String QText, String answer) {
		super(exe, weight, QText);
		this.answer = answer;

	}
	
	/**
	 * @return the answer of the question
	 */
	public String getAnswer() {
		return answer;
	}
	
	/**
	 * @param answer
	 * @return true if the correct answer of the question has been changed or false if not
	 */
	public boolean setAnswer(String answer) {
		if (this.getExercise().isDone()){
			return false;
		}
		
		this.answer = answer;
		return true;
	}
	
	/**
	 * @param student
	 * @param answer
	 * @return an object AnswerQuestion if the question has been solved or null if not
	 */
	public AnswerQuestion solveQuestion(Student student, String answer){
		double mark;
		if (this.getExercise().getCourse().getStudents().contains(student) == false){ /*The student isn't part of the course*/
			return null;
		}
		QuestionOption choice = new QuestionOption(answer);
		AnswerQuestion finalAnswer;
		
		if (this.answer.equals(answer) == true){ /*The answer is correct*/
			mark = this.getWeight();
		}
		else{
			mark = 0;
		}
		finalAnswer = new AnswerQuestion(this, student, choice, mark);
		this.addAnswer(finalAnswer);
		return finalAnswer;
	}

	
}
