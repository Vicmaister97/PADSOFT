package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

import controllers.CourseClickingListener;
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
			StudentList sl = new StudentList();
			this.add(sl);
			AllCoursesList c1 = new AllCoursesList();
			this.add(c1);
			layout.putConstraint(SpringLayout.NORTH, b, 17, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.WEST, b, 15, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, sl, -GeneralFrame.GFrame.getWidth()/4, SpringLayout.HORIZONTAL_CENTER, this);
			layout.putConstraint(SpringLayout.NORTH, sl, 200, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, c1, 0, SpringLayout.HORIZONTAL_CENTER, this);
			layout.putConstraint(SpringLayout.NORTH, c1, 200, SpringLayout.NORTH, this);
			this.setPreferredSize(new Dimension((int) GeneralFrame.GFrame.getWidth(), (int)sl.getPreferredSize().getHeight()+200));
		}
}

class StudentList extends JPanel{
	public StudentList(){
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		JLabel title = new JLabel("Students");
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		title.setAlignmentX(CENTER_ALIGNMENT);
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		for (User u: Coorse.coorse.getUsers()){
			if(!u.getUsername().equals("admin")){
				this.addStudent((Student) u);
			}
		}
	}
	
	private void addStudent(Student s){
		JLabel l = new JLabel(s.getName());
		//l.addMouseListener(new CourseClickingListener(l));
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		l.setForeground(Color.BLUE);
		l.setAlignmentX(CENTER_ALIGNMENT);
		this.add(l);
	}
	
}