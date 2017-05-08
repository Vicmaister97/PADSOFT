package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

import coorse.Coorse;
import courseElements.Course;
import controllers.*;


public class MainScreenStudent extends JPanel{
	public MainScreenStudent(){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		BackArrow b = new BackArrow();
		this.add(b);
		CourseList cl = new CourseList();
		this.setBackground(new Color(153, 255, 255));
		AllCoursesList c2 = new AllCoursesList();
		this.add(cl);
		this.add(c2);
		layout.putConstraint(SpringLayout.NORTH, b, 17, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, b, 15, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, cl, -GeneralFrame.GFrame.getWidth()/4, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, cl, 200, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, c2, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, c2, 200, SpringLayout.NORTH, this);
		this.setPreferredSize(new Dimension((int)GeneralFrame.GFrame.getWidth(), (int)c2.getPreferredSize().getHeight()+200));
	}
}

class CourseList extends JPanel{
	public CourseList(){
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		JLabel title = new JLabel("Your Courses");
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
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
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
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
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
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
		if(c.getStudents().contains(GeneralFrame.GFrame.getStudent())||c.getExpelled().contains(GeneralFrame.GFrame.getStudent())){
			l.addMouseListener(new CourseClickingListener(l));
			l.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
			l.setForeground(Color.BLUE);
			l.setAlignmentX(CENTER_ALIGNMENT);
			this.add(l);
			return;
		}
		else{
			l.addMouseListener(new CourseApplierController(c));
			l.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
			l.setAlignmentX(CENTER_ALIGNMENT);
			this.add(l);
			return;
		}
	}
}
