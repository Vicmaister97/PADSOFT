package tests;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import courseElements.Course;
import courseElements.Exercise;
import exercises.AnswerQuestion;
import exercises.QuestionOption;
import exercises.SimpleChoice;
import users.Student;

public class QuestionTest {

	private Course course;
	private Student student;
	private Student student2;
	private Exercise exerciseNotDone;
	private Exercise exerciseDone;
	private SimpleChoice question1;
	private SimpleChoice question2;
	private QuestionOption opt1;
	private QuestionOption opt2;
	private AnswerQuestion ans1;
	private AnswerQuestion ans2;
	
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
		opt1 = new QuestionOption("Leonardo da Vinci");
		opt2 = new QuestionOption("Goya");
		ans1 = new AnswerQuestion(question1, student, opt1, 4);
		ans2 = new AnswerQuestion(question1, student, opt2, 6);
		exerciseDone.solveExercise(student);
		
	}
	@Test
	public void testSetWeight() {
		assertTrue(question1.setWeight(5));
		assertTrue(question1.getWeight() == 5); /*The weight of the question must have changed*/
		assertFalse(question2.setWeight(5)); /*Must fail because question2 is a question of an exercise that has been already solved*/
	}
	
	@Test
	public void testSetQuestionText(){
		assertTrue(question1.setQuestionText("Changed correctly"));
		assertEquals(question1.getQuestionText(), "Changed correctly"); /*The question text of the question must have changed*/
		assertFalse(question2.setQuestionText("Doesn't change")); /*Must fail because question2 is a question of an exercise that has been already solved*/
	}
	
	@Test
	public void testAddAnswer(){
		assertTrue(question1.addAnswer(ans1)); /*The answer was stored in the list of QuestionAnswers of the exercise*/
		assertEquals(question1.getAnswers().get(0), ans1); /*Must be there*/
		assertFalse(question1.addAnswer(ans2)); /*The same student can't have 2 answers*/
	}

}
