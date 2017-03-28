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
	
	public void addSubunit(String name, boolean visibility){
		Unit u = new Unit(name, visibility);
		this.getElements().add(u);
	}
	
	public void addNote(String text, boolean visibility){
		Note e = new Note(name, visibility);
		this.getElements().add(e);
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
