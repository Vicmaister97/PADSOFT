/**
 * 
 * @author Carlos Isasa Martin and Victor Garcia Carrera
 */

package Exercises;
import CourseElements.Exercise;
import Users.Student;

public class TrueFalse extends QuestionChoice{
private String correctAnswer;
	
	public TrueFalse(Exercise exe, double weight, String QText, boolean randOrd, String correct){
		super(exe, weight, QText, randOrd);
		this.correctAnswer = correct;
		super.addPossibleAnswer("True");
		super.addPossibleAnswer("False"); /*We create the True/False Question with those 2 options, which mustn't change*/
	}

	/**
	 * @return the correct Answer of the question
	 */
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * @param correctanswer
	 * @return true if the correct answer was changed or false if not
	 */
	public boolean setCorrectAnswer(String correctanswer) {
		if (this.getExercise().isDone()){
			return false;
		}
		
		if(this.getPossibleAnswers().contains(correctanswer)){ /*Our answer is one of the possible answers of the question*/
			this.correctAnswer = correctanswer;
			return true;
		}
		return false;
	}
	
	/**
	 * @param student
	 * @param answer
	 * @return an object AnswerQuestion if the question was solved or null if not
	 */
	public AnswerQuestion solveQuestion(Student student, String answer){
		double mark;
		if (this.getPossibleAnswers().contains(answer) == false){ /*The answer isn't a possible answer*/
			return null;
		}
		
		if (this.getExercise().getCourse().getStudents().contains(student) == false){ /*The student isn't part of the course*/
			return null;
		}
		
		QuestionOption choice = new QuestionOption(answer);
		AnswerQuestion finalAnswer;
		
		if (this.correctAnswer.equals(answer)){ /*The answer is correct*/
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
