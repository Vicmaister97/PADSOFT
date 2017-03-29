package CourseElements;
import Users.Student;
import java.util.ArrayList;
import java.util.List;
import es.uam.eps.padsof.emailconnection.*;


public class Course extends VisibleElement {
	private String name;
	private String teacherName;
	private List<Student> students;
	private List<Student> expelled;
	private List<CourseElement> elements;
	
	public Course (String name, String teachername, Boolean visibility){
		super(visibility);
		this.setName(name);
		this.setTeachername(teachername);
		this.students = new ArrayList<Student>();
		this.expelled = new ArrayList<Student>();
		this.elements = new ArrayList<CourseElement>();
	}
	
	public boolean setSmthVisible(VisibleElement e){
		if (this.getElements().contains(e)==false){
			return false;
		}
		e.setVisible(true);
		for (int i=0; i<this.getStudents().size(); i++){
			try{
				EmailSystem.send(this.getStudents().get(i).getEmail(), "New element in course " + this.getName(), "You have unchecked elements", true);
			}
			catch(FailedInternetConnectionException|InvalidEmailAddressException  ex){
				return false;
			}
			finally{}
		}
		return true;
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
	public void addUnit(Unit u){
		this.getElements().add(u);
	}
	/**
	 * Creates a note
	 * @param text the note text 
	 * @param visibility the visibility
	 */
	public void addNote(Note n){
		this.getElements().add(n);
	}
	
	public boolean deleteNote(Note n){
		if (this.getElements().contains(n)){
			this.getElements().remove(n);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addExercise(Exercise e){
		this.getElements().add(e);
	}
	
	public boolean deleteExercise(Exercise e){
		if (this.getElements().contains(e) && e.isDone()==false){
			this.getElements().remove(e);
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Expells a student
	 * @param stuedn the student to expell
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
	/**
	 * Readmits a student
	 * @param student the student to readmit
	 * @return true if suceeds false if not (the student wasn't expelled)
	 */
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