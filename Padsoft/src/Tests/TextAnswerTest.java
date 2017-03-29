package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import CourseElements.Course;
import CourseElements.Exercise;
import Exercises.TextAnswer;
import Users.Student;

import java.time.LocalDate;

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
		exerciseNotDone = new Exercise (course, true, false, LocalDate.now(), LocalDate.now().plusDays(10), "Exercise1", 100, 0);
		question1 = new TextAnswer (exerciseNotDone, 4, "Who painted Mona Lisa?", "Leonardo da Vinci");
		exerciseDone = new Exercise (course, true, false, LocalDate.now(), LocalDate.now().plusDays(10), "Exercise1", 100, 0);
		question2 = new TextAnswer (exerciseDone, 4, "Who painted Mona Lisa?", "Leonardo da Vinci");
		exerciseDone.solveExercise(student);
		
	}
	@Test
	public void testSetAnswer(){
		assertTrue(question1.setAnswer("Goya"));
		assertEquals(question1.getAnswer(), "Goya"); /*The correct answer has been changed*/
		assertFalse(question2.setAnswer("Goya")); /*Must fail because question2 is a question of an exercise that has been already solved*/
	}

}
