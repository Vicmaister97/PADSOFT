package Exercises;
import java.util.ArrayList;
import java.util.List;

public class QuestionOption {
	private String choice;
	private List<String> multChoices;
	private boolean multiple;
	
	public QuestionOption(String option){
		this.choice = option;
		this.multiple = false;
	}
	
	public QuestionOption(List<String> options){
		this.multChoices = new ArrayList<String>(options);
		this.multiple = true;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public List<String> getMultChoices() {
		return multChoices;
	}

	public void setMultChoices(List<String> multChoices) {
		this.multChoices = multChoices;
	}

	public boolean isMultiple() {
		return multiple;
	}
	
	
}
