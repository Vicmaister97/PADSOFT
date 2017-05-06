package gui;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import coorse.Coorse;
import courseElements.Course;
import users.Student;


public class MainScreenStudent extends JPanel{
	public MainScreenStudent(Student s){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		CourseList cl = new CourseList(s);
		this.add(cl);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, cl, -GeneralFrame.GFrame.getWidth()/4, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, cl, 200, SpringLayout.NORTH, this);


	}
}

class CourseList extends JPanel{
	public CourseList(Student s){
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		JLabel title = new JLabel("Your Courses");
		title.setAlignmentX(CENTER_ALIGNMENT);
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		for (Course c: s.getCourses()){
			this.addCourse(c.getName());
		}
	}
	
	private void addCourse(String cname){
		JLabel l = new JLabel(cname);
		l.setAlignmentX(CENTER_ALIGNMENT);
		this.add(l);
	}
}