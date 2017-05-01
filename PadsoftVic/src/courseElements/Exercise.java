package courseElements;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import users.Student;
import exercises.*;


public class Exercise extends CourseElement {

	private static final long serialVersionUID = -9194791072727488665L;
	private boolean randomOrder;
	private Course course;
	private LocalDate iniDate;
	private LocalDate endDate;
	private double weightE; /*It's going to be the percentage of the exercise on the global mark*/
	private double score; /*Here we store the total amount of points of an exercise, depending on the weight of the questions*/
	private String name;
	private List<Question> questions;
	private List<AnswerExercise> answers;
	private double penalisation;
	private boolean done; /*This attribute shows whether an exercise has been done or not yet*/
	
	public Exercise (Course course, boolean visibility, boolean random, LocalDate ini, LocalDate end, String name, double weight, double penalisation){
		super(visibility);
		this.course = course;
		this.randomOrder = random;
		this.setIniDate(ini);
		this.setEndDate(end);
		this.name = name;
		this.weightE = weight;
		this.score = 0;
		this.questions = new ArrayList<Question>();
		this.answers = new ArrayList<AnswerExercise>();
		this.penalisation = penalisation;
		this.done = false; /*When we create the exercise, no one has done it yet*/
		course.addElement(this);
	}

	public boolean isRandomOrder() {
		return randomOrder;
	}

	public boolean setRandomOrder(boolean randomOrder) {
		if (this.isDone()){ /*Someone has already answered the exercise*/
			return false;
		}
		this.randomOrder = randomOrder;
		return true;
	}

	public LocalDate getIniDate() {
		return iniDate;
	}
	
	public boolean setIniDate(LocalDate iniDate) {
		if (this.isDone() == false){ /*No one has answered the exercise yet*/
			this.iniDate = iniDate;
			return true;
		}
		return false;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public boolean setEndDate(LocalDate endDate) {
		if (this.iniDate.isBefore(endDate)){
			this.endDate = endDate;
			return true;
		}
		return false;
	}

	public double getWeightE() {
		return weightE;
	}

	public boolean setWeightE(double weightE) {
		if (this.isDone() == false){ /*No one has answered the exercise yet*/
			this.weightE = weightE;
			return true;
		}
		return false;
	}
	
	public double getScore() {
		return score;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public boolean addQuestion(Question question) {
		if (this.isDone()){ /*Someone has already answered the exercise*/
			return false;
		}
		
		this.questions.add(question);
		this.score += question.getWeight();
		return true;
	}
	
	public boolean removeQuestion (Question question){
		if (this.isDone()){ /*Someone has already answered the exercise*/
			return false;
		}
		
		if (this.questions.remove(question)){
			return true;
		}
		return false;
	}

	public double getPenalisation() {
		return penalisation;
	}

	public boolean setPenalisation(double penalisation) { /*The penalisation for a wrong answer is a positive number or 0*/
		if (this.isDone()){ /*Someone has already answered the exercise*/
			return false;
		}
		
		if (penalisation < 0){
			this.penalisation = penalisation*(-1);
		}
		this.penalisation = penalisation;
		return true;
	}

	public List<AnswerExercise> getAnswers() {
		return answers;
	}

	public boolean addAnswer(AnswerExercise answer) {
		for (AnswerExercise ans: this.answers){
			if (ans.getStudent().equals(answer.getStudent())){ /*The student has already answered the exercise*/
				return false;
			}
		}
		if (this.answers.add(answer)){
			this.done = true;
			return true;
		}
		return false;		
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		if (this.isDone()){ /*Someone has already answered the exercise*/
			return false;
		}
		this.name = name;
		return true;
	}

	public boolean isDone() {
		return done;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}

	public void setVisible(boolean visible){
		if (this.isDone() == false){ /* No one has answered the exercise yet*/
			this.visible = visible;
		}
	}

	public Course getCourse() {
		return course;
	}

	public AnswerExercise solveExercise(Student student){
		if(this.getCourse().getStudents().contains(student)==false){ /*The student isn't part of the course*/
			return null;
		}
		
		if(this.isVisible() == false){ /*If the Exercise isn't visible for students*/
			return null;
		}
		
		if((LocalDate.now().isBefore(this.iniDate)) || (LocalDate.now().isAfter(this.endDate))){
			return null; /*We can't solve an Exercise before the iniDate or after the endDate*/
		}
		
		AnswerExercise finalAns = new AnswerExercise(this, student);
		if (this.addAnswer(finalAns)==false){ /*The student has already answered the Exercise*/
			return null;
		}
		setDone(true);
		return finalAns;
	}
	
}
