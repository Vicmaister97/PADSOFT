package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import coorse.Coorse;
import courseElements.Course;
import gui.*;

public class CurseTeacherController implements MouseListener {
	private JLabel course;
	public CourseTeacherListener(JLabel course){
		this.course=course;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Course c  = Coorse.coorse.searchCourseByName(course.getText());
		CourseScreenTeacher m = new CourseScreenStudent(c);
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
