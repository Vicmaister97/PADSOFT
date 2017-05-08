package courseElements;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.uam.eps.padsof.emailconnection.EmailSystem;
import es.uam.eps.padsof.emailconnection.FailedInternetConnectionException;
import es.uam.eps.padsof.emailconnection.InvalidEmailAddressException;

public class Unit extends CourseElement{

	private static final long serialVersionUID = 1797786891165616055L;
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
	
	public void addSubunit(String uname, boolean visibility){
		Unit u = new Unit(uname, visibility, this.course);
		this.elements.add(u);
	}
	
	public void addElement(CourseElement e){
		this.elements.add(e);
	}
	
	public boolean deleteNote(Note n){
		return this.elements.remove(n);
	}
	
	public boolean deleteExercise(Exercise e){
		if (this.elements.contains(e)&&e.isDone()==false){
			this.elements.remove(e);
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
		return Collections.unmodifiableList (elements);
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
