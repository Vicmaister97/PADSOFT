package gui;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import coorse.Coorse;
import courseElements.*;

import controllers.*;

public class CourseScreenStudent extends JPanel{
	public CourseScreenStudent(Course c){
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		JLabel home = new JLabel("Home");
		this.add(home);
		JLabel professorname = new JLabel(c.getTeachername());
		this.add(professorname);
		ElementsPanel e = new ElementsPanel(c);
		this.add(e);
	}
	
}

class ElementsPanel extends JPanel{
	public ElementsPanel(Course c){
		int i=1;
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		JLabel title = new JLabel(c.getName());
		title.setFont(new Font("Serif", Font.PLAIN, 25));
		title.setAlignmentX(LEFT_ALIGNMENT);
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		for (CourseElement e: c.getElements()){
			if(e.isVisible()){
				if (e instanceof Note){
					Note n = (Note) e;
					this.add(new JLabel(n.getText()));
				}
				else if (e instanceof Unit){
					JOptionPane.showMessageDialog(null, "cosas");
					this.addUnit((Unit)e);
				}
				else if (e instanceof Exercise){
					this.addExercise((Exercise)e);
				}
			}
		}
	}
	
	private void addUnit(Unit u){
		ImageIcon uicon = new ImageIcon("try1.png");
		JLabel l = new JLabel(u.getName(), uicon, JLabel.RIGHT);
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		l.setForeground(Color.BLUE);
		l.setAlignmentX(LEFT_ALIGNMENT);
		l.addMouseListener(new UnitClickingController(u));
		this.add(l);
	}
	
	private void addExercise(Exercise e){
		ImageIcon uicon = new ImageIcon("try2.png");
		JLabel l = new JLabel(e.getName(), uicon, JLabel.RIGHT);
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		l.setForeground(Color.BLUE);
		l.setAlignmentX(LEFT_ALIGNMENT);
		l.addMouseListener(new ExerciseClickingController(e));
		this.add(l);
	}
	
}


