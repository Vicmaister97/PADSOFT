package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import coorse.Coorse;
import users.*;


public class CoorseTest {
	private Coorse c;
	private Student s;
	
	@Before
	public void setUp() throws Exception {
		c = new Coorse();
		s = new Student("uname", "pass", "fname", "lname", "1", "sample.email@example.test");
		c.addUser(s);
	}

	@Test
	public void testLogin() {
		User u = c.login(s.getUsername(), s.getPassword());
		assertEquals(u, s);
	}
	
	@Test
	public void testReadFromFile(){
		c.readFromFile("C:\\Users\\Carlos\\workspace\\Padsoft\\StudentData.txt");
		assertTrue(c.getUsers().size()==19); /*17 in the file plus professor plus s*/
	}

}
