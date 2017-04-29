package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import courseElements.Course;
import courseElements.Exercise;
import exercises.MultipleChoice;
import users.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

public class MultipleChoiceTest {

	private Course course;
	private Student student;
	private Student student2;
	private Student student3;
	private Student student4;
	private Student student5;
	private Exercise exerciseNotDone;
	private Exercise exerciseDone;
	private MultipleChoice question1;
	private MultipleChoice question2;
	private List<String> answersCorrect;
	private List<String> answersMixed;
	private List<String> answersNotEnough;
	private List<String> answersSoMany;
	private List<String> answersRandom;

	
	@Before
	public void setUp() throws Exception{
		course = new Course ("Historia del Arte", "Paco", true);
		student = new Student ("Platanito", "nerd68", "Carlos", "Isasa", "1", "platanito@gmail.com");
		student2 = new Student ("Victor", "nerd69", "Victor", "Garcia", "2", "boss@gmail.com");
		student3 = new Student ("Paco", "nerd70", "Paco", "Martinez", "3", "noboss@gmail.com");
		student4 = new Student ("Pacoo", "nerd700", "Pacoo", "Martinezz", "4", "nobosss@gmail.com");
		student5 = new Student ("Pacooo", "nerd7000", "Pacooo", "Martinezz", "5", "nobossss@gmail.com");
		course.addStudent(student);
		course.addStudent(student2);
		course.addStudent(student3);
		course.addStudent(student4);
		exerciseNotDone = new Exercise (course, true, false, LocalDate.now(), LocalDate.now().plusDays(10), "Exercise1", 100, 0);
		answersCorrect = new ArrayList<String>();
		answersMixed = new ArrayList<String>();
		answersNotEnough = new ArrayList<String>();
		answersSoMany = new ArrayList<String>();
		answersRandom = new ArrayList<String>();
		answersCorrect.add("Paco");
		answersCorrect.add("Alberto");
		answersMixed.add("Paco");
		answersMixed.add("Marta");
		answersNotEnough.add("Paco");
		answersSoMany.add("Paco");
		answersSoMany.add("Alberto");
		answersSoMany.add("Marta");
		answersRandom.add("Enrique");
		answersRandom.add("Paco");
		question1 = new MultipleChoice (exerciseNotDone, 4, "Which are male names?", false, answersCorrect);
		question1.addPossibleAnswer("Paco");
		question1.addPossibleAnswer("Alberto");
		question1.addPossibleAnswer("Marta");
		exerciseDone = new Exercise (course, true, false, LocalDate.now(), LocalDate.now().plusDays(10), "Exercise1", 100, 0);
		question2 = new MultipleChoice (exerciseDone, 4, "Which are male names?", false, answersCorrect);
		question2.addPossibleAnswer("Paco");
		question2.addPossibleAnswer("Alberto");
		question2.addPossibleAnswer("Marta");
		exerciseDone.solveExercise(student);
		
	}
	@Test
	public void testAddCorrectAnswer() {
		assertTrue(question1.addCorrectAnswer("Marta"));
		assertTrue(question1.getCorrectAnswers().contains("Marta")); /*The correct answers have been changed, the new one is stored*/
		assertFalse(question1.addCorrectAnswer("Enrique")); /*It's not a possible answer*/
		assertFalse(question2.addCorrectAnswer("Enrique")); /*Must fail because question2 is a question of an exercise that has been already solved*/
		assertFalse(question2.addCorrectAnswer("Marta")); /*Must fail because question2 is a question of an exercise that has been already solved, although it can be a correct answer*/
	}
	
	@Test
	public void testRemoveCorrectAnswer(){
		assertFalse(question1.removeCorrectAnswer("Marta")); /*It's not a correct answer of the question*/
		assertFalse(question1.removeCorrectAnswer("Enrique")); /*It's not a possible answer of the question*/
		assertTrue(question1.removeCorrectAnswer("Paco"));
		assertFalse(question1.getCorrectAnswers().contains("Paco")); /*Paco has been removed as a correct answer*/
		assertFalse(question2.removeCorrectAnswer("Enrique")); /*Must fail because question2 is a question of an exercise that has been already solved and Enrique isn't a possible answer*/
		assertFalse(question2.removeCorrectAnswer("Paco")); /*Must fail because question2 is a question of an exercise that has been already solved although Paco is a correct answer*/
	}
	
	@Test
	public void testRemovePossibleAnswer(){
		assertFalse(question1.removePossibleAnswer("Emilio")); /*It's not a possible answer of the question*/
		assertTrue(question1.removePossibleAnswer("Marta"));
		assertFalse(question1.getPossibleAnswers().contains("Marta")); /*Marta has been removed as a possible answer*/
		assertTrue(question1.removePossibleAnswer("Paco"));
		assertFalse(question1.getCorrectAnswers().contains("Paco")); /*We have removed Paco as a correct answer of the question*/
		assertFalse(question2.removePossibleAnswer("Emilio")); /*Must fail because question2 is a question of an exercise that has been already solved and Enrique isn't a possible answer*/
		assertFalse(question2.removePossibleAnswer("Paco")); /*Must fail because question2 is a question of an exercise that has been already solved although Paco is a possible answer*/
	}
	
	@Test
	public void testSolveQuestion(){
		assertEquals(question1.solveQuestion(student, answersRandom), null); /*One of the answers isn't a possible answer of the question*/
		assertEquals(question1.solveQuestion(student, answersCorrect), question1.getAnswers().get(0)); /*The answer was stored in the list of QuestionAnswers of the exercise*/
		assertTrue(question1.getAnswers().get(0).isCorrect()); /*Must be correct*/
		assertEquals(question1.solveQuestion(student2, answersMixed), question1.getAnswers().get(1)); /*The answer was stored in the list of QuestionAnswers of the exercise*/
		assertFalse(question1.getAnswers().get(1).isCorrect()); /*Must be incorrect*/
		assertEquals(question1.solveQuestion(student3, answersNotEnough), question1.getAnswers().get(2)); /*The answer was stored in the list of QuestionAnswers of the exercise*/
		assertFalse(question1.getAnswers().get(2).isCorrect()); /*Must be incorrect*/
		assertEquals(question1.solveQuestion(student4, answersSoMany), question1.getAnswers().get(3)); /*The answer was stored in the list of QuestionAnswers of the exercise*/
		assertFalse(question1.getAnswers().get(3).isCorrect()); /*Must be incorrect*/
		assertEquals(question1.solveQuestion(student5, answersCorrect), null); /*Student5 isn't in the course, so he can't answer*/
	}
	
	

}
