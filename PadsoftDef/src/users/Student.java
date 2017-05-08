package users;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.Application;
import coorse.Coorse;
import courseElements.Course;
public class Student extends User{

	private static final long serialVersionUID = 5956689220406391382L;
	public String firstname;
	public String lastname;
	public String id;
	public String email;
	private List<Course> courses;
	public Student(String username, String password, String firstname, String lastname, String id, String email) {
		super(username, password);
		this.courses = new ArrayList<Course>();
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
		this.email = email;
	}
	/**
	 * Sends an application to enter a course
	 * @param course the course to enter
	 */
	public Application sendApplication(Course course){
		Application a = new Application(this, course);
		if(!course.getPending().contains(a)){
			course.addApplication(a);
		}
		return a;
	}
	
	public boolean accessCourse(Course course){
		return course.getStudents().contains(this);
	}
	/**
	 * @return firstname
	 */
	public String getName() {
		return firstname+" "+lastname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setName(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the id
	 */
	public String getId(){
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return Collections.unmodifiableList(courses);
	}
	
	public void addCourse(Course c){
		this.courses.add(c);
	}

	
	
}
