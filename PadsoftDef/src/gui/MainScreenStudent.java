package gui;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import coorse.Coorse;
import courseElements.Course;
import users.Student;


public class MainScreenStudent extends JPanel{
	public MainScreenStudent(Coorse c, Student s){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		CourseList cl = new CourseList(s);
		this.add(cl);
		layout.putConstraint(SpringLayout.EAST, cl, (int) this.getSize().getWidth()/4, SpringLayout.HORIZONTAL_CENTER, this);
	}
}

class CourseList extends JPanel{
	public CourseList(Student s){
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		for (Course c: s.getCourses()){
			this.add(new JLabel(c.getName()));
		}
	}
}