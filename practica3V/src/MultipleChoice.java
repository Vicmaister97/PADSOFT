import java.util.ArrayList;
import java.util.List;

public class MultipleChoice extends QuestionChoice {
	private List<String> correctAnswers;
	
	protected MultipleChoice (Exercise exe, double weight, String QText, boolean randOrd, List<String> answers){
		super(exe, weight, QText, randOrd);
		this.correctAnswers = new ArrayList<>(answers);
	}

	public List<String> getCorrectAnswers() {
		return correctAnswers;
	}
	

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
	
	public boolean removeCorrectAnswer(String noMoreCorrectAnswer){
		if (this.getExercise().isDone()){
			return false;
		}
		
		if (this.correctAnswers.remove(noMoreCorrectAnswer)){
			return true;
		}
		return false;
	}
	
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