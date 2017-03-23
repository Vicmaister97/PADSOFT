import java.util.ArrayList;
import java.util.List;

public class Course extends VisibleElement {
	private String name;
	private String teacherName;
	private List<Student> students;
	private List<Student> expelled;
	private List<CourseElement> elements;
	
	public Course (String name, String teachername, Boolean visibility){
		this.setName(name);
		this.setTeachername(teachername);
		this.students = new ArrayList<Student>();
		this.expelled = new ArrayList<Student>();
		this.elements = new ArrayList<CourseElement>();
		this.setVisible(visibility);
	}
	/**
	 * Adds a student to the course
	 * @param the student to add to the course
	 */
	public void addStudent(Student student){
		this.getStudents().add(student);
	}
	/**
	 * Creates a Unit
	 * @param the name of the unit and the visibility
	 */
	public void addUnit(String name, boolean visibility){
		Unit u = new Unit(name, visibility);
		this.getElements().add(u);
	}
	/**
	 * Creates a note
	 * @param the note text and the visibility
	 */
	public void addNote(String text, boolean visibility){
		Note e = new Note(name, visibility);
		this.getElements().add(e);
	}
	/**
	 * Expells a student
	 * @param the student to expell
	 * @return true if succeeds false if not (the student isn't in the course)
	 */
	public boolean expellStudent(Student student){
		if (this.getStudents().contains(student)==false){
			return false;
		}
		else {
			this.getStudents().remove(student);
			this.getExpelled().add(student);
			return true;
		}
		
	}
	
	public boolean admitStudent (Student student){
		if (this.getExpelled().contains(student)==false){
			return false;
		}
		else {
			this.getExpelled().remove(student);
			this.getStudents().add(student);
			return true;
		}
	}
	/**
	 * @return the name
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
	 * @return the teachername
	 */
	public String getTeachername() {
		return teacherName;
	}

	/**
	 * @param teachername the teachername to set
	 */
	public void setTeachername(String teachername) {
		this.teacherName = teachername;
	}

	/**
	 * @return the students
	 */
	public List<Student> getStudents() {
		return students;
	}

	/**
	 * @param students the students to set
	 */
	public void setStudents(List<Student> students) {
		this.students = students;
	}

	/**
	 * @return the elements
	 */
	public List<CourseElement> getElements() {
		return elements;
	}

	/**
	 * @param elements the elements to set
	 */
	public void setElements(List<CourseElement> elements) {
		this.elements = elements;
	}

	/**
	 * @return the expelled
	 */
	public List<Student> getExpelled() {
		return expelled;
	}

	/**
	 * @param expelled the expelled to set
	 */
	public void setExpelled(List<Student> expelled) {
		this.expelled = expelled;
	}
}
