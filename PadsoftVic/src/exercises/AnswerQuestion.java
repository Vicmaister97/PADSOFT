/**
 * 
 * @author Carlos Isasa Martin and Victor Garcia Carrera
 */

package exercises;
import users.Student;
public class AnswerQuestion implements java.io.Serializable{

	private static final long serialVersionUID = -7963799671143022243L;
	private Question question;
	private Student student;
	private QuestionOption answer;
	private double mark; /*0 if the answer of the question is wrong*/
	
	public AnswerQuestion(Question question, Student student, QuestionOption answer, double mark){
		this.question = question;
		this.student = student;
		this.answer = answer;
		this.mark = mark;
	}
	
	/**
	 * @return true if the answer of the question is correct or false if not
	 */
	public boolean isCorrect(){
		if (question.getWeight() == mark){
			return true;
		}
		
		return false;
	}

	/**
	 * @return the question of the answer
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @return the student that have answered this
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @return the answer that the student "wrote"
	 */
	public QuestionOption getAnswer() {
		return answer;
	}

	/**
	 * @return the mark of the answer of the question
	 */
	public double getMark() {
		return mark;
	}
	
	
}