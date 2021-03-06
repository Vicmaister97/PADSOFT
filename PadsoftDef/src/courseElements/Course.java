package courseElements;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.Application;
import es.uam.eps.padsof.emailconnection.EmailSystem;
import es.uam.eps.padsof.emailconnection.FailedInternetConnectionException;
import es.uam.eps.padsof.emailconnection.InvalidEmailAddressException;
import exercises.AnswerExercise;
import users.Student;


public class Course extends VisibleElement {

	private static final long serialVersionUID = -5809335566513798755L;
	private String name;
	private String teacherName;
	private List<Student> students;
	private List<Student> expelled;
	private List<CourseElement> elements;
	private List<Application> pending;
	
	public Course (String name, String teachername, Boolean visibility){
		super(visibility);
		this.setName(name);
		this.setTeachername(teachername);
		this.students = new ArrayList<Student>();
		this.expelled = new ArrayList<Student>();
		this.elements = new ArrayList<CourseElement>();
		this.pending = new ArrayList<Application>();
		
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
	
	public void addElement(CourseElement e){
		this.elements.add(e);
	}
	
	public boolean deleteNote(Note n){
		return this.elements.remove(n);
	}
	
	public boolean deleteExercise(Exercise e){
		if (this.elements.contains(e) && e.isDone()==false){
			this.elements.remove(e);
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
		return Collections.unmodifiableList (elements);
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
	public void addUnit(String uname, boolean visibility){
		Unit u = new Unit(uname, visibility, this);
		this.addElement(u);
	}
	public double getGlobalMark(Student student){
		double globalMark = 0;
		Exercise exe;
		
		if (this.students.contains(student) == false){ /*The student is not a member of this course*/
			return 0;
		}
		for (CourseElement ele : this.elements){
			if (ele instanceof Exercise){ /*If it's an exercise*/
				exe = (Exercise) ele;
				for (AnswerExercise ansexe : exe.getAnswers()){ /*We look for the answer of the student to that exercise*/
					if (ansexe.getStudent().equals(student)){
						globalMark += (ansexe.getMark() * (exe.getWeightE() / 100));
						break;
					}
				}
			}
		}
		return globalMark;		
	}
	@Override
	public String toString(){
		return this.name;
	}

	/**
	 * @return the pending
	 */
	public List<Application> getPending() {
		return Collections.unmodifiableList(pending);
	}


	public void addApplication(Application a){
		this.pending.add(a);
	}
	
	public void removeApplication(Application a){
		this.pending.remove(a);
	}
}
