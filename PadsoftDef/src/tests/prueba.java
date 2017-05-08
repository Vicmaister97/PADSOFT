package tests;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import gui.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import application.Application;
import controllers.CourseApplierController;
import controllers.CourseClickingListener;
import coorse.Coorse;
import courseElements.Course;
import users.Student;
import users.User;
public class prueba {
	public static void main(String[] args){
		Student s = new Student("A", "a", "a", "a", "a", "a");
		Course c = new Course("cname", "tname", true);
		Application a1 = new Application (s, c);
		Application a2 = new Application (s, c);
		List<Application> pending= new ArrayList<Application>();
		pending.add(a1);
		a1.equals(a2);
		System.out.println(a1.equals(a2));
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
		if(c.getStudents().contains(GeneralFrame.GFrame.getStudent())){
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
