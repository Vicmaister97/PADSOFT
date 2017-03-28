
public class Student extends User{
	public String name;
	public int id;
	public String email;
	public Student(String username, String password, String name, int id, String email) {
		super(username, password);
		this.name = name;
		this.id = id;
		this.email = email;
	}
	/**
	 * Sends an application to enter a course
	 * @param course the course to enter
	 */
	public Application sendApplication(Course course){
		Application a = new Application(this, course);
		return a;
	}
	
	public boolean accessCourse(Course course){
		if (course.getStudents().contains(this))
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public int getId(){
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id){
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
	
}
