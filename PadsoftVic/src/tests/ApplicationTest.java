package tests;
import application.Application;
import users.Student;
import courseElements.Course;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;



public class ApplicationTest {
	private Application a;
	private Student s;
	private Course c;
	
	@Before
	public void setUp(){
		s = new Student("uname", "pass", "fname", "lname", "1", "sample.email@example.test");
		c = new Course("cname", "tname", true);
		a = new Application(s, c);
	}
	
	@Test
	public void testAdmit(){
		a.admitStudent();
		assertTrue(c.getStudents().contains(s));
	}
}
