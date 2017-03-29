package Exercises;
import CourseElements.Exercise;
import Users.Student;
public class TextAnswer extends Question {
	private String answer;
	
	public TextAnswer(Exercise exe, double weight, String QText, String answer) {
		super(exe, weight, QText);
		this.answer = answer;

	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		if (this.getExercise().isDone()){
			return;
		}
		
		this.answer = answer;
	}
	
	public AnswerQuestion solveQuestion(Student student, String answer){
		double mark;
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
