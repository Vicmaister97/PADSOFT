package tests;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import courseElements.Course;
import courseElements.Exercise;
import courseElements.Note;
import courseElements.Unit;
import users.Student;

public class UnitTest {
	private Unit u;
	private Course c;
	private Note n;
	private Exercise e;
	private Student s;
	@Before
	public void setUp() throws Exception {
		c = new Course("cname", "tname", true);
		u = new Unit("uname", true, c);
		s = new Student("uname", "pass", "fname", "lname", "1", "sample.email@example.com");
		n = new Note("test", false);
		e = new Exercise (c, true, true, LocalDateTime.MIN, LocalDateTime.MAX, "name", 5.5, 1.0);
		u.addElement(n);
		c.addStudent(s);
		u.addElement(e);
	}

	@Test
	public void testVisibilityEmail() {
		assertTrue(u.setSmthVisible(n));
	}
	
	@Test
	public void testNoteDeletion(){
		assertTrue(u.deleteNote(n));
	}
	
	@Test
	public void testCorrectExerciseDeletion(){
		assertTrue(u.deleteExercise(e));
	}
	
	@Test
	public void testIncorrectExerciseDeletion(){
		e.setDone(true);
		assertTrue(u.deleteExercise(e)==false);
	}

}
