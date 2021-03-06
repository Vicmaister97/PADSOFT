package tests;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import courseElements.Course;
import courseElements.Exercise;
import exercises.SimpleChoice;
import users.Student;

public class ExerciseTest {

	private Course course;
	private Student student;
	private Student student2;
	private Exercise exerciseNotDone;
	private Exercise exerciseDone;
	private SimpleChoice question1;
	private SimpleChoice question2;
	private SimpleChoice question3;
	
	@Before
	public void setUp() throws Exception{
		course = new Course ("Historia del Arte", "Paco", true);
		student = new Student ("Platanito", "nerd68", "Carlos", "Isasa", "1", "platanito@gmail.com");
		student2 = new Student ("Victor", "nerd69", "Victor", "Garcia", "2", "boss@gmail.com");
		course.addStudent(student);
		course.addStudent(student2);
		exerciseNotDone = new Exercise (course, true, false, LocalDateTime.now(), LocalDateTime.now().plusDays(10), "Exercise1", 60, 0);
		question1 = new SimpleChoice (exerciseNotDone, 4, "Who painted Mona Lisa?", false, "Leonardo da Vinci");
		question1.addPossibleAnswer("Leonardo da Vinci");
		question1.addPossibleAnswer("Goya");
		question1.addPossibleAnswer("Velazquez");
		exerciseDone = new Exercise (course, true, false, LocalDateTime.now(), LocalDateTime.now().plusDays(10), "Exercise2", 40, 0);
		question2 = new SimpleChoice (exerciseNotDone, 6, "Who painted Guernica?", false, "Picasso");
		question2.addPossibleAnswer("Picasso");
		question2.addPossibleAnswer("Goya");
		question2.addPossibleAnswer("Velazquez");
		question3 = new SimpleChoice (exerciseDone, 6, "Who painted Guernica?", false, "Picasso");
		exerciseDone.solveExercise(student);
		
	}
	@Test
	public void testSetRandomOrder() {
		assertTrue(exerciseNotDone.setRandomOrder(true));
		assertEquals(exerciseNotDone.isRandomOrder(), true); /*The order must have changed*/
		assertFalse(exerciseDone.setRandomOrder(true)); /*Must fail because the exercise has been already solved*/
	}
	
	@Test
	public void testSetIniDate() {
		assertTrue(exerciseNotDone.setIniDate(LocalDateTime.now().plusDays(4)));
		assertEquals(exerciseNotDone.getIniDate(), LocalDateTime.now().plusDays(4)); /*The initial date must have changed*/
		assertFalse(exerciseDone.setIniDate(LocalDateTime.now().plusDays(4))); /*Must fail because the exercise has been already solved*/
	}
	
	@Test
	public void testSetEndDate() {
		assertTrue(exerciseNotDone.setEndDate(LocalDateTime.now().plusDays(4)));
		assertEquals(exerciseNotDone.getEndDate(), LocalDateTime.now().plusDays(4)); /*The end date must have changed*/
		assertFalse(exerciseNotDone.setEndDate(LocalDateTime.now().minusDays(4)));
		assertTrue(exerciseDone.setEndDate(LocalDateTime.now().plusDays(4)));
		assertFalse(exerciseDone.setEndDate(LocalDateTime.now().minusDays(4)));

	}
	
	@Test
	public void testSetWeightE(){
		assertTrue(exerciseNotDone.setWeightE(30));
		assertTrue(exerciseNotDone.getWeightE() == 30); /*The weight must have changed*/
		assertFalse(exerciseDone.setWeightE(30)); /*Must fail because the exercise has been already solved*/
	}
	
	@Test
	public void testAddQuestion(){
		assertTrue(exerciseNotDone.getQuestions().contains(question1));
		assertTrue(exerciseNotDone.getQuestions().contains(question2));
		/*They must be true cause, when we created both exercises, we call the function addQuestion in order to add them on the specified course*/
	}
	
	@Test
	public void testRemoveQuestion(){
		assertTrue(exerciseNotDone.removeQuestion(question1));
		assertFalse(exerciseNotDone.getQuestions().contains(question1)); /*The question mustn't be in the exercise*/
		assertFalse(exerciseNotDone.removeQuestion(question3)); /*Question 3 isn't a question of exerciseNotDone*/
	}
	
	@Test
	public void testSetPenalisation(){
		assertTrue(exerciseNotDone.setPenalisation(2));
		assertTrue(exerciseNotDone.getPenalisation() == 2); /*The penalisation must have changed*/
		assertFalse(exerciseDone.setPenalisation(2)); /*Must fail because the exercise has been already solved*/
	}
	
	@Test
	public void testAddAnswer(){
		assertEquals(exerciseNotDone.solveExercise(student), exerciseNotDone.getAnswers().get(0)); /*If the addAnswer method works correctly, when the exercise is solved, the addAnswer method will add that AnswerExercise to the exercise*/
	}
	
	@Test
	public void testSetName(){
		assertTrue(exerciseNotDone.setName("Paco"));
		assertEquals(exerciseNotDone.getName(), "Paco"); /*The name of the exercise must have changed*/
		assertFalse(exerciseDone.setName("Paco")); /*Must fail because the exercise has been already solved*/
	}
	
	@Test
	public void testSetVisible(){
		exerciseNotDone.setVisible(false);
		assertTrue(exerciseNotDone.isVisible() == false); /*The visibility of the exercise must have changed*/
		exerciseDone.setVisible(false); 
		assertTrue(exerciseDone.isVisible() == false);
	}
	
	@Test
	public void testSolveExercise(){
		assertTrue(exerciseNotDone.isDone() == false);
		assertEquals(exerciseNotDone.solveExercise(student), exerciseNotDone.getAnswers().get(0)); /*If the solveExercise method works correctly, when the exercise is solved, the addAnswer method will add that AnswerExercise to the exercise*/
		assertTrue(exerciseNotDone.isDone() == true);
		assertEquals(exerciseNotDone.solveExercise(student), null); /*If the method addAnswer works correctly, when we try to add the Answer of that student, as it exists another one of that student, it wouldn't work*/
	}
	

}
