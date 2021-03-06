
public class AnswerQuestion {
	private Question question;
	private Student student;
	private QuestionOption answer;
	private double mark; /*0 if the answer of the question is wrong*/
	
	protected AnswerQuestion(Question question, Student student, QuestionOption answer, double mark){
		this.question = question;
		this.student = student;
		this.answer = answer;
		this.mark = mark;
	}
	
	public boolean isCorrect(){
		if (question.getWeight() == mark){
			return true;
		}
		
		return false;
	}

	public Question getQuestion() {
		return question;
	}

	public Student getStudent() {
		return student;
	}

	public QuestionOption getAnswer() {
		return answer;
	}

	public double getMark() {
		return mark;
	}
	
	
}