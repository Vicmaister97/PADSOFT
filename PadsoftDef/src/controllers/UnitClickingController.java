package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import courseElements.Unit;
import gui.CourseScreenStudent;
import gui.GeneralFrame;
import gui.*;
public class UnitClickingController implements MouseListener {
	private Unit u;
	public UnitClickingController(Unit u){
		this.u=u;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		UnitScreenStudent m = new UnitScreenStudent(u);
		GeneralFrame.GFrame.changePanel(m);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
