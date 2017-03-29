import java.util.ArrayList;
import java.util.List;

public abstract class QuestionChoice extends Question{
	private List<String> possibleAnswers;
	private boolean randomOrder;
	
	protected QuestionChoice(double weight, String QText, boolean randOrder){
		super(weight, QText);
		this.possibleAnswers = new ArrayList<String>();
		this.randomOrder = randOrder;
	}

	public List<String> getPossibleAnswers() {
		return possibleAnswers;
	}

	public boolean isRandomOrder() {
		return randomOrder;
	}

	public void changeOrder(boolean randomOrder) {
		this.randomOrder = randomOrder;
	}
	
	
}