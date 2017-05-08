package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import courseElements.Course;
import courseElements.Unit;
import courseElements.VisibleElement;
import gui.CourseScreenTeacher;
import gui.GeneralFrame;
import gui.UnitScreenTeacher;

public class SetVisibleController implements MouseListener {
	private VisibleElement e;
	private VisibleElement c;
	
	public SetVisibleController(VisibleElement e, VisibleElement c){
		this.e=e;
		this.c=c;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.e.setVisible(!this.e.isVisible());
		if(c instanceof Course){
			GeneralFrame.GFrame.changePanel(new CourseScreenTeacher((Course)this.c), false);
		}
		if (c instanceof Unit){
			GeneralFrame.GFrame.changePanel(new UnitScreenTeacher((Unit)this.c), false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
