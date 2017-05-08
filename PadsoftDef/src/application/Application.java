package application;
import java.io.Serializable;

import coorse.Coorse;
import courseElements.Course;
import users.Student;
public class Application implements Serializable{
	private Student student;
	private Course course;
	
	public Application (Student student, Course course){
		this.setStudent(student);
		this.setCourse(course);
	}
	/**
	 * Accepts the application and adds the student to the course
	 */
	public void admitStudent(){
		this.getCourse().addStudent(this.getStudent());
		this.getStudent().addCourse(this.getCourse());
		this.course.removeApplication(this);
	}
	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}
	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}
	@Override
	public boolean equals(Object o){
		Application a = (Application) o;
		return a.getCourse() == this.course && a.getStudent()==this.student;
	}
}
