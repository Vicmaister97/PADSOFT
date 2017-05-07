package tests;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import courseElements.Course;
import courseElements.Exercise;
import courseElements.Note;
import users.Student;

public class CourseTest {
	private Course c;
	private Student s;
	private Note n;
	private Exercise e;
	
	@Before
	public void setUp() throws Exception {
		c = new Course("cname", "tname", true);
		s = new Student("uname", "pass", "fname", "lname", "1", "sample.email@example.com");
		n = new Note("test", false);
		e = new Exercise (c, true, true, LocalDateTime.MIN, LocalDateTime.MAX, "name", 5.5, 1.0);
		c.addElement(n);
		c.addStudent(s);
		c.addElement(e);
	}

	@Test
	public void testVisibilityEmail() {
		assertTrue(c.setSmthVisible(n));
	}
	
	@Test
	public void testNoteDeletion(){
		assertTrue(c.deleteNote(n));
	}
	
	@Test
	public void testExpell(){
		c.expellStudent(s);
		assertTrue(c.getStudents().contains(s)==false);
	}
	
	@Test
	public void testReadmit(){
		c.expellStudent(s);
		c.admitStudent(s);
		assertTrue(c.getStudents().contains(s));
	}
	
	@Test
	public void testCorrectExerciseDeletion(){
		assertTrue(c.deleteExercise(e));
	}
	
	@Test
	public void testIncorrectExerciseDeletion(){
		e.setDone(true);
		assertTrue(c.deleteExercise(e)==false);
	}

}
