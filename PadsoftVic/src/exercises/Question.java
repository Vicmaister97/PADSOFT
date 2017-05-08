/**
 * 
 * @author Carlos Isasa Martin and Victor Garcia Carrera
 */

package exercises;

import java.util.ArrayList;
import java.util.List;
import courseElements.Exercise;

public abstract class Question implements java.io.Serializable{

	private static final long serialVersionUID = -6568277018396498456L;
	private double weight;
	private String questionText;
	private ArrayList<AnswerQuestion> answers;
	private int numAnswered;
	private int numDidntAnswer;
	private int numCorrect;
	private int numIncorrect;
	private Exercise exercise;
	
	protected Question (Exercise exercise, double weight, String questionText){
		this.questionText = questionText;
		this.answers = new ArrayList<AnswerQuestion>();
		this.numAnswered = 0;
		this.numDidntAnswer = exercise.getCourse().getStudents().size();
		this.numCorrect = 0;
		this.numIncorrect = 0;
		this.exercise = exercise;
		this.setWeight(weight);
		exercise.addQuestion(this);
	}
	/**
	 * @return the weight of a question
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * @param weight
	 * @return true if the weight of the question has been changed or false if not
	 */
	public boolean setWeight(double weight) {
		if (this.exercise.isDone()){
			return false;
		}
		if(weight < 0){
			this.weight = weight*(-1);
		}
		else{
			this.weight = weight;
		}
		return true;
	}
	
	/**
	 * @return the text of the question
	 */
	public String getQuestionText() {
		return questionText;
	}
	/**
	 * @param questionText
	 * @return true if the question text has been changed or not
	 */
	public boolean setQuestionText(String questionText) {
		if (this.exercise.isDone()){
			return false;
		}
		this.questionText = questionText;
		return true;
	}
	
	/**
	 * @return a list of objects AnswerQuestion, the answers of the question
	 */
	public ArrayList<AnswerQuestion> getAnswers() {
		return answers;
	}
	
	/**
	 * @param answer
	 * @return true if the answer (object AnswerQuestion) has been added to the list of answers
	 * of a question or false if not
	 */
	public boolean addAnswer(AnswerQuestion answer) {
		for (AnswerQuestion ans: this.answers){
			if (ans.getStudent().equals(answer.getStudent())){ /*The student has already answered that question*/
				return false;
			}
		}
		
		if (this.answers.add(answer)){
			this.numAnswered++;
			this.numDidntAnswer--;
			if (answer.isCorrect()){
				this.numCorrect++;
			}
			else{
				this.numIncorrect++;
			}
			return true;
		}
		return false;
	}
	
	public void removeAnswer(AnswerQuestion answer){
		this.answers.remove(answer);
	}
	
	/**
	 * @return the number of students that have answered that question
	 */
	public int getNumAnswered() {
		return numAnswered;
	}

	/**
	 * @return the number of students that have answered correctly the question
	 */
	public int getNumCorrect() {
		return numCorrect;
	}
	
	/**
	 * @return the number of students that have answered incorrectly the question
	 */
	public int getNumIncorrect() {
		return numIncorrect;
	}
	
	/**
	 * @return the number of students that didn't answer the question
	 */
	public int getNumDidntAnswer(){
		return numDidntAnswer;
	}
	
	/**
	 * @return the exercise where the question is
	 */
	public Exercise getExercise() {
		return exercise;
	}
	
	
}
