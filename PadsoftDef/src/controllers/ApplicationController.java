package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import application.Application;
import gui.CourseScreenTeacher;
import gui.GeneralFrame;

public class ApplicationController implements MouseListener {
	private Application a;
	private boolean state;
	public ApplicationController(Application a, boolean state){
		this.a=a;
		this.state=state;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(state){
			int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to accept " + a.getStudent().getName()+"'s application?", "Application Confirmer", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION){
				a.admitStudent();
				GeneralFrame.GFrame.changePanel(new CourseScreenTeacher(a.getCourse()), false);
			}
			
		}		
		else{
			int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to decline " + a.getStudent().getName()+"'s application?", "Application Confirmer", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION){
				a.getCourse().removeApplication(a);
				GeneralFrame.GFrame.changePanel(new CourseScreenTeacher(a.getCourse()), false);
			}
			
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
