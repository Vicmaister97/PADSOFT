package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gui.GeneralFrame;
import gui.MainScreenStudent;

public class HomeClickingController implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		MainScreenStudent m = new MainScreenStudent();
		GeneralFrame.GFrame.changePanel(m);
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
