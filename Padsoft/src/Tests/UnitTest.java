package Tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import CourseElements.*;
import Users.Student;

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
		e = new Exercise (c, true, true, LocalDate.MIN, LocalDate.MAX, "name", 5.5, 1.0);
		u.addNote(n);
		c.addStudent(s);
		u.addExercise(e);
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
