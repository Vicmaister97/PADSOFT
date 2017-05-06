package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

import courseElements.Course;
import gui.GeneralFrame;

public class CourseApplierController implements MouseListener{
	private Course c;

	public CourseApplierController(Course c){
		this.c=c;

	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		int apply = JOptionPane.showConfirmDialog(null, "You are not enrolled in this course. Do you want to apply?", "Application Dialog",  JOptionPane.YES_NO_OPTION);
		if(apply==JOptionPane.YES_OPTION){
			GeneralFrame.GFrame.getStudent().sendApplication(c);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0){
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
}
