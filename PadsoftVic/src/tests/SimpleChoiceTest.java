package tests;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import courseElements.Course;
import courseElements.Exercise;
import exercises.SimpleChoice;
import users.Student;

public class SimpleChoiceTest {

	private Course course;
	private Student student;
	private Student student2;
	private Student student3;
	private Exercise exerciseNotDone;
	private Exercise exerciseDone;
	private SimpleChoice question1;
	private SimpleChoice question2;
	
	@Before
	public void setUp() throws Exception{
		course = new Course ("Historia del Arte", "Paco", true);
		student = new Student ("Platanito", "nerd68", "Carlos", "Isasa", "1", "platanito@gmail.com");
		student2 = new Student ("Victor", "nerd69", "Victor", "Garcia", "2", "boss@gmail.com");
		student3 = new Student ("Paco", "nerd70", "Paco", "Martinez", "3", "noboss@gmail.com");
		course.addStudent(student);
		course.addStudent(student2);
		exerciseNotDone = new Exercise (course, true, false, LocalDateTime.now(), LocalDateTime.now().plusDays(10), "Exercise1", 20, 0);
		question1 = new SimpleChoice (exerciseNotDone, 4, "Who painted Mona Lisa?", false, "Leonardo da Vinci");
		question1.addPossibleAnswer("Leonardo da Vinci");
		question1.addPossibleAnswer("Goya");
		question1.addPossibleAnswer("Velazquez");
		exerciseDone = new Exercise (course, true, false, LocalDateTime.now(), LocalDateTime.now().plusDays(10), "Exercise2", 80, 0);
		question2 = new SimpleChoice (exerciseDone, 4, "Who painted Mona Lisa?", false, "Leonardo da Vinci");
		question2.addPossibleAnswer("Leonardo da Vinci");
		question2.addPossibleAnswer("Goya");
		question2.addPossibleAnswer("Velazquez");
		exerciseDone.solveExercise(student);
		
	}
	@Test
	public void testSetCorrectAnswer() {
		assertTrue(question1.setCorrectAnswer("Goya"));
		assertEquals(question1.getCorrectAnswer(), "Goya"); /*The correct answer was changed*/
		assertFalse(question1.setCorrectAnswer("Van Gogh"));
		assertFalse(question2.setCorrectAnswer("Van Gogh")); /*Must fail because question2 is a question of an exercise that has been already solved*/
		assertFalse(question2.setCorrectAnswer("Goya")); /*Must fail because question2 is a question of an exercise that has been already solved, although it can be the correct answer*/
	}
	
	////@Test
	public void testAddPossibleAnswer(){
		assertTrue(question1.addPossibleAnswer("Van Gogh"));
		assertTrue(question1.getPossibleAnswers().contains("Van Gogh"));
		assertEquals(question1.getPossibleAnswers().get(3), "Van Gogh"); /*The new possible answer is at the list of possible answers of the question*/
		assertFalse(question1.addPossibleAnswer("Van Gogh")); /*Must fail because that possible answer already exists*/
		assertFalse(question2.addPossibleAnswer("Van Gogh")); /*Must fail because question2 is a question of an exercise that has been already solved*/
	}
	
	@Test
	public void testRemovePossibleAnswer(){
		assertFalse(question1.removePossibleAnswer("Emilio")); /*It's not a possible answer of the question*/
		assertTrue(question1.removePossibleAnswer("Goya"));
		assertFalse(question1.getPossibleAnswers().contains("Goya")); /*Goya has been removed as a possible answer*/
		assertTrue(question1.removePossibleAnswer("Leonardo da Vinci"));
		assertEquals(question1.getCorrectAnswer(), null); /*We have removed the answer of the question*/
		assertFalse(question2.removePossibleAnswer("Van Gogh")); /*Must fail because question2 is a question of an exercise that has been already solved and Van Gogh isn't a possible answer*/
		assertFalse(question2.removePossibleAnswer("Goya")); /*Must fail because question2 is a question of an exercise that has been already solved although Goya is a possible answer*/
	}
	
	@Test
	public void testSolveQuestion(){
		assertEquals(question1.solveQuestion(student, "Picasso"), null); /*The answer isn't a possible answer of the question*/
		assertEquals(question1.solveQuestion(student, "Leonardo da Vinci"), question1.getAnswers().get(0)); /*The answer was stored in the list of QuestionAnswers of the exercise*/
		assertTrue(question1.getAnswers().get(0).isCorrect()); /*Must be correct*/
		assertEquals(question1.solveQuestion(student2, "Goya"), question1.getAnswers().get(1)); /*The answer was stored in the list of QuestionAnswers of the exercise*/
		assertFalse(question1.getAnswers().get(1).isCorrect()); /*Must be incorrect*/
		assertEquals(question1.solveQuestion(student3, "Goya"), null); /*Student3 isn't in the course, so he can't answer*/
	}
	
	

}
