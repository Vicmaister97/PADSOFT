package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import courseElements.Course;
import courseElements.Exercise;
import exercises.TextAnswer;
import users.Student;

import java.time.LocalDateTime;

import org.junit.Before;

public class TextAnswerTest {

	private Course course;
	private Student student;
	private Student student2;
	private Student student3;
	private Exercise exerciseNotDone;
	private Exercise exerciseDone;
	private TextAnswer question1;
	private TextAnswer question2;
	
	@Before
	public void setUp() throws Exception{
		course = new Course ("Historia del Arte", "Paco", true);
		student = new Student ("Platanito", "nerd68", "Carlos", "Isasa", "1", "platanito@gmail.com");
		student2 = new Student ("Victor", "nerd69", "Victor", "Garcia", "2", "boss@gmail.com");
		student3 = new Student ("Paco", "nerd70", "Paco", "Martinez", "3", "noboss@gmail.com");
		course.addStudent(student);
		course.addStudent(student2);
		exerciseNotDone = new Exercise (course, true, false, LocalDateTime.now(), LocalDateTime.now().plusDays(10), "Exercise1", 70, 0);
		question1 = new TextAnswer (exerciseNotDone, 4, "Who painted Mona Lisa?", "Leonardo da Vinci");
		exerciseDone = new Exercise (course, true, false, LocalDateTime.now(), LocalDateTime.now().plusDays(10), "Exercise2", 30, 0);
		question2 = new TextAnswer (exerciseDone, 4, "Who painted Mona Lisa?", "Leonardo da Vinci");
		exerciseDone.solveExercise(student);
		
	}
	@Test
	public void testSetAnswer(){
		assertTrue(question1.setAnswer("Goya"));
		assertEquals(question1.getAnswer(), "Goya"); /*The correct answer has been changed*/
		assertFalse(question2.setAnswer("Goya")); /*Must fail because question2 is a question of an exercise that has been already solved*/
	}
	
	@Test
	public void testSolveQuestion(){
		assertEquals(question1.solveQuestion(student, "Leonardo da Vinci"), question1.getAnswers().get(0)); /*The answer was stored in the list of QuestionAnswers of the exercise*/
		assertTrue(question1.getAnswers().get(0).isCorrect()); /*Must be correct*/
		assertEquals(question1.solveQuestion(student2, "Goya"), question1.getAnswers().get(1)); /*The answer was stored in the list of QuestionAnswers of the exercise*/
		assertFalse(question1.getAnswers().get(1).isCorrect()); /*Must be incorrect*/
		assertEquals(question1.solveQuestion(student3, "Leonardo da Vinci"), null); /*Student3 isn't in the course, so he can't answer*/
	}

}
