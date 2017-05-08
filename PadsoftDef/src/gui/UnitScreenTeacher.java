package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import courseElements.Course;
import courseElements.CourseElement;
import courseElements.Exercise;
import courseElements.Note;
import courseElements.Unit;

public class UnitScreenTeacher extends JPanel {
	public UnitScreenTeacher(Unit u){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		HomeButtonT home = new HomeButtonT();
		BackArrow b = new BackArrow();
		this.add(b);
		TeacherButtons tb = new TeacherButtons(u);
		this.add(tb);
		JLabel cname = new JLabel(u.getCourse().getName());
		cname.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		this.add(cname);
		this.setBackground(new Color(153, 255, 255));
		this.add(home);
		ElementsPanelUT e = new ElementsPanelUT(u);
		this.add(e);
		layout.putConstraint(SpringLayout.NORTH, b, 17, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, b, 15, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, home, 15, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, home, 15, SpringLayout.EAST, b);
		layout.putConstraint(SpringLayout.NORTH, cname, 100, SpringLayout.NORTH, home);
		layout.putConstraint(SpringLayout.WEST, cname, 200, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, e, 75, SpringLayout.NORTH, cname);
		layout.putConstraint(SpringLayout.WEST, e, 200, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, tb, 50, SpringLayout.SOUTH, e);
		layout.putConstraint(SpringLayout.WEST, tb, 200, SpringLayout.WEST, this);
		this.setPreferredSize(new Dimension((int) GeneralFrame.GFrame.getWidth(), (int)e.getPreferredSize().getHeight()+400));
	}
}

class ElementsPanelUT extends JPanel{
	public ElementsPanelUT(Unit u){
		this.setBackground(new Color(153, 255, 255));
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		JLabel title = new JLabel(u.getName());
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		title.setAlignmentX(LEFT_ALIGNMENT);
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		for (CourseElement e: u.getElements()){
			if (e instanceof Note){
				NoteLabel label = new NoteLabel((Note)e, u);
				label.setAlignmentX(LEFT_ALIGNMENT);
				this.add(label);
				this.add(Box.createRigidArea(new Dimension(0, 10)));
			}
			else if (e instanceof Unit){
				UnitLabel label = new UnitLabel((Unit)e, u);
				label.setAlignmentX(LEFT_ALIGNMENT);
				this.add(label);
				this.add(Box.createRigidArea(new Dimension(0, 10)));
			}
			else if (e instanceof Exercise){
				ExerciseLabel label = new ExerciseLabel((Exercise)e, u);
				label.setAlignmentX(LEFT_ALIGNMENT);
				this.add(label);
				this.add(Box.createRigidArea(new Dimension(0, 10)));
			}
		}
	}
	
	
}