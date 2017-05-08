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
		if(!c.getExpelled().contains(GeneralFrame.GFrame.getStudent())){
			CourseScreenStudent m = new CourseScreenStudent(c);
			GeneralFrame.GFrame.changePanel(m, true);
		}
		else{
			JOptionPane.showMessageDialog(null, "You have been expelled from this course");
		}
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
