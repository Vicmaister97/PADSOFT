package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import courseElements.Course;
import gui.CourseScreenTeacher;
import gui.GeneralFrame;
import users.Student;

public class ExpellStudentController implements MouseListener {
	private Student s;
	private Course c;
	public ExpellStudentController(Student s, Course c){
		this.s=s;
		this.c=c;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		c.expellStudent(s);
		GeneralFrame.GFrame.changePanel(new CourseScreenTeacher(c), false);
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
