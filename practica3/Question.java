
public abstract class Question {
	private double weight;
	private boolean correct;
	private String questionText;
	protected Question (double weight, String questionText){
		this.weight = weight;
		this.questionText = questionText;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
}
