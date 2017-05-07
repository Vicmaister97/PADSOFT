package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gui.GeneralFrame;

public class BackArrowController implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(!GeneralFrame.GFrame.backtracking.isEmpty()){
			GeneralFrame.GFrame.remove(GeneralFrame.GFrame.getContentPane());
			GeneralFrame.GFrame.setContentPane(GeneralFrame.GFrame.backtracking.pop());
			GeneralFrame.GFrame.validate();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
