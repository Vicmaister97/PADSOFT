

public abstract class VisibleElement {
	private boolean visible;
	
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
