package tests;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import courseElements.Course;
import courseElements.Exercise;
import exercises.SimpleChoice;
import users.Student;

public class QuestionChoiceTest {

	private Course course;
	private Student student;
	private Student student2;
	private Exercise exerciseNotDone;
	private Exercise exerciseDone;
	private SimpleChoice question1;
	private SimpleChoice question2;
	
	@Before
	public void setUp() throws Exception{
		course = new Course ("Historia del Arte", "Paco", true);
		student = new Student ("Platanito", "nerd68", "Carlos", "Isasa", "1", "platanito@gmail.com");
		student2 = new Student ("Victor", "nerd69", "Victor", "Garcia", "2", "boss@gmail.com");
		course.addStudent(student);
		course.addStudent(student2);
		exerciseNotDone = new Exercise (course, true, false, LocalDateTime.now(), LocalDateTime.now().plusDays(10), "Exercise1", 80, 0);
		question1 = new SimpleChoice (exerciseNotDone, 4, "Who painted Mona Lisa?", false, "Leonardo da Vinci");
		question1.addPossibleAnswer("Leonardo da Vinci");
		question1.addPossibleAnswer("Goya");
		question1.addPossibleAnswer("Velazquez");
		exerciseDone = new Exercise (course, true, false, LocalDateTime.now(), LocalDateTime.now().plusDays(10), "Exercise2", 20, 0);
		question2 = new SimpleChoice (exerciseDone, 4, "Who painted Mona Lisa?", false, "Leonardo da Vinci");
		question2.addPossibleAnswer("Leonardo da Vinci");
		question2.addPossibleAnswer("Goya");
		question2.addPossibleAnswer("Velazquez");
		exerciseDone.solveExercise(student);
		
	}
	
	@Test
	public void testChangeOrder(){
		assertTrue(question1.changeOrder(true));
		assertEquals(question1.isRandomOrder(), true); /*The order must have changed*/
		assertFalse(question2.changeOrder(true)); /*Must fail because question2 is a question of an exercise that has been already solved*/
	}
	
	
	@Test
	public void testAddPossibleAnswer(){
		assertTrue(question1.addPossibleAnswer("Van Gogh"));
		assertTrue(question1.getPossibleAnswers().contains("Van Gogh"));
		assertEquals(question1.getPossibleAnswers().get(3), "Van Gogh"); /*The new possible answer is at the list of possible answers of the question*/
		assertFalse(question1.addPossibleAnswer("Van Gogh")); /*Must fail because that possible answer already exists*/
		assertFalse(question2.addPossibleAnswer("Van Gogh")); /*Must fail because question2 is a question of an exercise that has been already solved*/
	}

}
