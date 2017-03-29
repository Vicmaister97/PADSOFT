package CourseElements;
import java.util.ArrayList;
import java.util.List;

import es.uam.eps.padsof.emailconnection.EmailSystem;
import es.uam.eps.padsof.emailconnection.FailedInternetConnectionException;
import es.uam.eps.padsof.emailconnection.InvalidEmailAddressException;

public class Unit extends CourseElement{
	private String name;
	private List<CourseElement> elements;
	private Course course;
	
	public Unit(String name, boolean visibility, Course course){
		super(visibility);
		this.setName(name);
		this.elements = new ArrayList<CourseElement>();
		this.course = course;
	}
	
	public boolean setSmthVisible(VisibleElement e){
		if (this.getElements().contains(e)==false){
			return false;
		}
		e.setVisible(true);
		for (int i=0; i<this.getCourse().getStudents().size(); i++){
			try{
				EmailSystem.send(this.getCourse().getStudents().get(i).getEmail(), "New element in course " + this.getName(), "You have unchecked elements", true);
			}
			catch(FailedInternetConnectionException|InvalidEmailAddressException  ex){
				return false;
			}
			finally{}
		}
		return true;
	}
	
	public void addSubunit(Unit u){
		this.getElements().add(u);
	}
	
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
		if (this.getElements().contains(e)&&e.isDone()==false){
			this.getElements().remove(e);
			return true;
		}
		else {
			return false;
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
