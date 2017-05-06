package gui;

import javax.swing.JPanel;

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

public class CourseScreenStudent extends JPanel{
	public CourseScreenStudent(Course c){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JLabel home = new JLabel("Home");
		JLabel professorname = new JLabel(c.getTeachername());

	}
	
}

class ElementsPanel extends JPanel{
	public ElementsPanel(Course c){
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		JLabel title = new JLabel(c.getName());
		title.setFont(new Font("Serif", Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		for 
	}
}
