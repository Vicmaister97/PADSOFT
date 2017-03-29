package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CourseElements.Course;
import Users.Student;
import Application.Application;

public class StudentTest {
	private Student s;
	private Course c;
	
	@Before
	public void setUp() throws Exception {
		s = new Student("uname", "pass", "fname", "lname", "1", "sample.email@example.test");
		c = new Course("cname", "tname", true);
		c.addStudent(s);
	}
	

	@Test
	public void testSendApplication() {
		Application a = s.sendApplication(c);
		assertEquals(a.getStudent(), s);
		assertEquals(a.getCourse(), c);
	}
	
	@Test
	public void testAccessCourse(){
		assertTrue(s.accessCourse(c));
	}
}
