import java.util.ArrayList;
import java.util.List;

public class Unit extends CourseElement{
	private String name;
	private List<CourseElement> elements;
	
	public Unit(String name, boolean visibility){
		super(visibility);
		this.setName(name);
		this.elements = new ArrayList<CourseElement>();
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
		if (this.getElements().contains(e)){
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
}
