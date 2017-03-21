
public class Application {
	private Student student;
	private Course course;
	
	public Application (Student student, Course course){
		this.setStudent(student);
		this.setCourse(course);
	}
	
	void admitStudent(){
		this.getCourse().addStudent(this.getStudent());
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
}
