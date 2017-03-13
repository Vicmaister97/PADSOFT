
public class TextAnswer extends Question {
	private String answer;
	private String correctanswer;
	public String getAnswer() {
		return answer;
	}
	protected TextAnswer(double weight, String questionText, String correctanswer) {
		super(weight, questionText);
		this.correctanswer = correctanswer;

	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getCorrectanswer() {
		return correctanswer;
	}
	public void setCorrectanswer(String correctanswer) {
		this.correctanswer = correctanswer;
	}
}
