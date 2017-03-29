package Exercises;
import Users.Student;
import CourseElements.Exercise;
import java.util.ArrayList;
import java.util.List;

public class AnswerExercise {
	private Exercise exercise;
	private Student student;
	private List<AnswerQuestion> answers; /*We store the answers of the questions of the student on that exercise*/
	private double mark; /*The mark of an exercise is always a number between 0 and 10*/
	
	public AnswerExercise (Exercise exercise, Student student){
		this.exercise = exercise;
		this.student = student;
		this.answers = new ArrayList<AnswerQuestion>();
		for (Question q: this.exercise.getQuestions()){ /*We look through all the questions of the exercise*/
			for (AnswerQuestion answers: q.getAnswers()){ /*For every question, we look for the answer of the given student*/
				if (answers.getStudent().equals(student)){ /*We choose the answer of the given student*/
					this.answers.add(answers);
					break;
				}
			}
		}
		this.mark = calculateMark();
	}

	public Exercise getExercise() {
		return exercise;
	}

	public Student getStudent() {
		return student;
	}

	public List<AnswerQuestion> getAnswers() {
		return answers;
	}

	public double getMark() {
		return mark;
	}

	public double calculateMark(){
		double exerciseMark = 0;
		
		for (AnswerQuestion ans: this.answers){ /*We calculate the mark based on the answers of the questions of the student in that exercise*/
			exerciseMark += ans.getMark();
			if (ans.isCorrect() == false){ /*The answer of the student to that question was wrong*/
				exerciseMark -= this.exercise.getPenalisation(); /*We apply the penalisation*/
			}
		}
		
		if (exerciseMark < 0){ /*The student made so many mistakes that his mark is under 0 because of the penalisation*/
			exerciseMark = 0;
		}
		return ((exerciseMark * 10) / this.exercise.getScore());
		
	}
	
	
}
