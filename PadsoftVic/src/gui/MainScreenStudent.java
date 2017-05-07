package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import coorse.Coorse;
import courseElements.Course;
import controllers.*;


public class MainScreenStudent extends JPanel{
	public MainScreenStudent(){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		CourseList cl = new CourseList();
		AllCoursesList c2 = new AllCoursesList();
		this.add(cl);
		this.add(c2);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, cl, -GeneralFrame.GFrame.getWidth()/4, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, cl, 200, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, c2, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, c2, 200, SpringLayout.NORTH, this);
		

	}
}

class CourseList extends JPanel{
	public CourseList(){
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		JLabel title = new JLabel("Your Courses");
		title.setFont(new Font("Serif", Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		for (Course c: GeneralFrame.GFrame.getStudent().getCourses()){
				this.addCourse(c.getName());
		}
	}
	
	private void addCourse(String cname){
		JLabel l = new JLabel(cname);
		l.addMouseListener(new CourseClickingListener(l));
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		l.setForeground(Color.BLUE);
		l.setAlignmentX(CENTER_ALIGNMENT);
		this.add(l);
	}
}

class AllCoursesList extends JPanel{
	public AllCoursesList(){
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		JLabel title = new JLabel("All Courses");
		title.setFont(new Font("Serif", Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		for (Course c: Coorse.coorse.getCourses()){
			if(c.isVisible()){
				this.addCourse(c);
			}
		}
	}
	
	private void addCourse(Course c){
		JLabel l = new JLabel(c.getName());
		if(c.getStudents().contains(GeneralFrame.GFrame.getStudent())){
			l.addMouseListener(new CourseClickingListener(l));
			l.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			l.setForeground(Color.BLUE);
			l.setAlignmentX(CENTER_ALIGNMENT);
			this.add(l);
			return;
		}
		else{
			l.addMouseListener(new CourseApplierController(c));
			l.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			l.setAlignmentX(CENTER_ALIGNMENT);
			this.add(l);
			return;
		}
	}
}