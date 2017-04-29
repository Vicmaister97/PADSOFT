package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import courseElements.Course;
import courseElements.Exercise;
import exercises.SimpleChoice;
import users.Student;

import java.time.LocalDate;

import org.junit.Before;

public class AnswerQuestionTest {

	private Course course;
	private Student student;
	private Student student2;
	private Exercise exerciseNotDone;
	private SimpleChoice question1;
	
	@Before
	public void setUp() throws Exception{
		course = new Course ("Historia del Arte", "Paco", true);
		student = new Student ("Platanito", "nerd68", "Carlos", "Isasa", "1", "platanito@gmail.com");
		student2 = new Student ("Victor", "nerd69", "Victor", "Garcia", "2", "boss@gmail.com");
		course.addStudent(student);
		course.addStudent(student2);
		exerciseNotDone = new Exercise (course, true, false, LocalDate.now(), LocalDate.now().plusDays(10), "Exercise1", 100, 0);
		question1 = new SimpleChoice (exerciseNotDone, 4, "Who painted Mona Lisa?", false, "Leonardo da Vinci");
		question1.addPossibleAnswer("Leonardo da Vinci");
		question1.addPossibleAnswer("Goya");
		question1.addPossibleAnswer("Velazquez");
		question1.solveQuestion(student, "Leonardo da Vinci");
		question1.solveQuestion(student2, "Goya");
		
	}

	@Test
	public void testIsCorrect() {
		assertTrue(question1.getAnswers().get(0).isCorrect()); /*That answer must be true*/
		assertFalse(question1.getAnswers().get(1).isCorrect()); /*That answer must be false*/
	}

}
