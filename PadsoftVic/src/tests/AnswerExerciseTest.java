package tests;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import courseElements.Course;
import courseElements.Exercise;
import exercises.SimpleChoice;
import users.Student;

public class AnswerExerciseTest {

	private Course course;
	private Student student;
	private Exercise exercise;
	private SimpleChoice question1;
	private SimpleChoice question2;
	
	@Before
	public void setUp() throws Exception{
		course = new Course ("Historia del Arte", "Paco", true);
		student = new Student ("Platanito", "nerd68", "Carlos", "Isasa", "1", "platanito@gmail.com");
		course.addStudent(student);
		exercise = new Exercise (course, true, false, LocalDateTime.now(), LocalDateTime.now().plusDays(10), "Exercise1", 100, 1);
		question1 = new SimpleChoice (exercise, 6, "Who painted Mona Lisa?", false, "Leonardo da Vinci");
		question1.addPossibleAnswer("Leonardo da Vinci");
		question1.addPossibleAnswer("Goya");
		question1.addPossibleAnswer("Velazquez");
		question2 = new SimpleChoice (exercise, 4, "Who painted Guernica?", false, "Picasso");
		question2.addPossibleAnswer("Picasso");
		question2.addPossibleAnswer("Goya");
		question2.addPossibleAnswer("Velazquez");
		question1.solveQuestion(student, "Leonardo da Vinci");
		question2.solveQuestion(student, "Goya");
		exercise.solveExercise(student);
		
	}
	@Test
	public void testCalculateMark() {
		assertTrue(exercise.getAnswers().get(0).getMark() == 5);
	}

}
