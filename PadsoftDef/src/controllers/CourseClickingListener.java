package controllers;
import gui.*;
import users.Student;
import users.User;
import coorse.Coorse;
import courseElements.*;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
public class CourseClickingListener implements MouseListener{
	private JLabel course;
	public CourseClickingListener(JLabel course){
		this.course=course;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Course c  = Coorse.coorse.searchCourseByName(course.getText());
		JOptionPane.showMessageDialog(null, "Has entrado al curso" + course.getText());
		/*CourseScreenStudent m = new CourseScreenStudent(c);
		GeneralFrame.GFrame.remove(GeneralFrame.GFrame.getContentPane());
		GeneralFrame.GFrame.setContentPane(m);
		GeneralFrame.GFrame.validate();*/
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}


}
