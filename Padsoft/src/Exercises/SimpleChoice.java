package Exercises;
import CourseElements.Exercise;
import Users.Student;
public class SimpleChoice extends QuestionChoice{
	private String correctAnswer;
	
	public SimpleChoice (Exercise exe, double weight, String QText, boolean randOrd, String correct){
		super(exe, weight, QText, randOrd);
		this.correctAnswer = correct;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

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
	
	public boolean removePossibleAnswer(String noMorePossibleAnswer){
		if (this.getExercise().isDone()){
			return false;
		}
		
		if (this.getPossibleAnswers().remove(noMorePossibleAnswer)){ /*The possible answer was in the list and it was removed correctly*/
			if (this.getCorrectAnswer().equals(noMorePossibleAnswer)){ /*The possible answer that was removed was the correct answer of the question*/
				this.correctAnswer = null; /*The teacher must be the one that chooses after this the new correct answer*/
			}
			return true;
		}
		return false;
	}
	
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
