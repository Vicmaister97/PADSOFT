package courseElements;
public class Note extends CourseElement{

	private static final long serialVersionUID = 3530399622120294862L;
	private String text;

	public Note (String text, boolean visibility){
		super(visibility);
		this.setText(text);
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
}
