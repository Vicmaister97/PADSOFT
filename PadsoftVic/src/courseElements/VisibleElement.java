package courseElements;

public abstract class VisibleElement implements java.io.Serializable{

	private static final long serialVersionUID = 8556069367141726930L;
	public boolean visible;
	
	public VisibleElement(boolean visibility){
		this.setVisible(visibility);
	}
	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
