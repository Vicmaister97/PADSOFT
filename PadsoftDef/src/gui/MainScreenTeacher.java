package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

import controllers.AddCourseController;
import controllers.CourseApplierController;
import controllers.CourseClickingListener;
import controllers.CourseTeacherController;
import coorse.Coorse;
import courseElements.Course;
import users.Student;
import users.User;

public class MainScreenTeacher extends JPanel {
		public MainScreenTeacher(){
			SpringLayout layout = new SpringLayout();
			this.setLayout(layout);
			BackArrow b = new BackArrow();
			this.add(b);
			JLabel logo = new JLabel(new ImageIcon("logo.png"));
			this.add(logo);
			this.setBackground(new Color(153, 255, 255));
			AllCoursesListT c1 = new AllCoursesListT();
			this.add(c1);
			layout.putConstraint(SpringLayout.NORTH, b, 17, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.WEST, b, 15, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logo, 0, SpringLayout.HORIZONTAL_CENTER, this);
			layout.putConstraint(SpringLayout.NORTH, logo, 100, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, c1, 0, SpringLayout.HORIZONTAL_CENTER, this);
			layout.putConstraint(SpringLayout.NORTH, c1, 50, SpringLayout.SOUTH, logo);
			this.setPreferredSize(new Dimension((int) GeneralFrame.GFrame.getWidth(), (int)c1.getPreferredSize().getHeight()+400));
		}
}


class AllCoursesListT extends JPanel{
	public AllCoursesListT(){
		this.setBackground(new Color(153, 255, 255));
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		JLabel title = new JLabel("All Courses");
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		title.setAlignmentX(CENTER_ALIGNMENT);
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		for (Course c: Coorse.coorse.getCourses()){
			this.addCourse(c);
		}
		JButton button = new JButton ("Add course");
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(button);
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.addActionListener(new AddCourseController());
	}
	
	private void addCourse(Course c){
		JLabel l = new JLabel(c.getName());
		l.addMouseListener(new CourseTeacherController(l));
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		l.setForeground(Color.BLUE);
		l.setAlignmentX(CENTER_ALIGNMENT);
		this.add(l);
		return;
	}
}