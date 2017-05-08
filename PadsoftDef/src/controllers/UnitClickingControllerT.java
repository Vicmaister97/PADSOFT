package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import courseElements.Unit;
import gui.GeneralFrame;
import gui.UnitScreenStudent;
import gui.UnitScreenTeacher;

public class UnitClickingControllerT implements MouseListener {
	private Unit u;
	public UnitClickingControllerT(Unit u){
		this.u=u;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		UnitScreenTeacher m = new UnitScreenTeacher(u);
		GeneralFrame.GFrame.changePanel(m, true);
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
