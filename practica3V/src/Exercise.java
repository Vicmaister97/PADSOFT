import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.Period;

public class Exercise extends CourseElement {
	private boolean randomOrder;
	private LocalDate iniDate;
	private LocalDate endDate;
	private double weightE;
	private List<Question> questions;
	private List<AnswerExercise> answers;
	private double penalisation;
	
	public Exercise (boolean random, LocalDate ini, LocalDate end, double weight, List<Question> questions, double penalisation, boolean visibility){
		super(visibility);
		this.randomOrder = random;
		this.setIniDate(ini);
		this.setEndDate(end);
		this.weightE = weight;
		this.questions = new ArrayList<Question>(questions);
		this.answers = new ArrayList<AnswerExercise>();
		this.penalisation = penalisation;
	}

	public boolean isRandomOrder() {
		return randomOrder;
	}

	public void setRandomOrder(boolean randomOrder) {
		this.randomOrder = randomOrder;
	}

	public LocalDate getIniDate() {
		return iniDate;
	}

	public void setIniDate(LocalDate iniDate) {
		if (this.getQuestions().isEmpty() == false){ /*There are already questions created*/
			for (Question q: this.getQuestions()){
				if (q.getAnswers().isEmpty() == false){ /*There is someone who answered a question of the exercise*/
					return;
				}
			}
		}
		this.iniDate = iniDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		long days = Period.between(this.iniDate, endDate).getDays();
		if (days < 0){
			this.endDate = endDate;
		}
	}

	public double getWeightE() {
		return weightE;
	}

	public void setWeightE(double weightE) {
		this.weightE = weightE;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public double getPenalisation() {
		return penalisation;
	}

	public void setPenalisation(double penalisation) { /*The penalisation for a wrong answer is a positive number or 0*/
		if (penalisation < 0){
			this.penalisation = penalisation*(-1);
		}
		this.penalisation = penalisation;
	}

	public List<AnswerExercise> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerExercise> answers) {
		this.answers = answers;
	}
	
	
	
}
