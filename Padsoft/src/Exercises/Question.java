package Exercises;
import CourseElements.Exercise;
import java.util.ArrayList;
import java.util.List;

public abstract class Question {
	private double weight;
	private String questionText;
	private List<AnswerQuestion> answers;
	private int numAnswered;
	private int numDidntAnswer;
	private int numCorrect;
	private int numIncorrect;
	private Exercise exercise;
	
	protected Question (Exercise exercise, double weight, String questionText){
		this.weight = weight;
		this.questionText = questionText;
		this.answers = new ArrayList<AnswerQuestion>();
		this.numAnswered = 0;
		this.numDidntAnswer = exercise.getCourse().getStudents().size();
		this.numCorrect = 0;
		this.numIncorrect = 0;
		this.exercise = exercise;
		this.exercise.addQuestion(this);
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		if (this.exercise.isDone()){
			return;
		}
		this.weight = weight;
	}
	
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		if (this.exercise.isDone()){
			return;
		}
		this.questionText = questionText;
	}
	
	public List<AnswerQuestion> getAnswers() {
		return answers;
	}
	
	public boolean addAnswer(AnswerQuestion answer) {
		for (AnswerQuestion ans: this.answers){
			if (ans.getStudent().equals(answer.getStudent())){ /*The student has already answered that question*/
				for (AnswerExercise exe: this.exercise.getAnswers()){
					if (exe.getStudent().equals(answer.getStudent())){ /*The student has already finished the exercise*/
						return false;
					}
				}
				this.answers.remove(ans); /*We remove the old answer*/
				this.answers.add(answer);
				return true;
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
	
	public int getNumAnswered() {
		return numAnswered;
	}

	public int getNumCorrect() {
		return numCorrect;
	}
	
	public int getNumIncorrect() {
		return numIncorrect;
	}
	
	public int getNumDidntAnswer(){
		return numDidntAnswer;
	}
	
	public Exercise getExercise() {
		return exercise;
	}
	
	
}
