package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		HomeButton home = new HomeButton();
		BackArrow b = new BackArrow();
		this.add(b);
		this.add(home);
		JLabel pname = new JLabel("Professor " + c.getTeachername());
		pname.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		this.add(pname);
		ElementsPanel e = new ElementsPanel(c);
		this.add(e);
		layout.putConstraint(SpringLayout.NORTH, b, 17, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, b, 15, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, home, 15, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, home, 15, SpringLayout.EAST, b);
		layout.putConstraint(SpringLayout.NORTH, pname, 100, SpringLayout.NORTH, home);
		layout.putConstraint(SpringLayout.WEST, pname, 200, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, e, 75, SpringLayout.NORTH, pname);
		layout.putConstraint(SpringLayout.WEST, e, 200, SpringLayout.WEST, this);
	}
	
}

class ElementsPanel extends JPanel{
	public ElementsPanel(Course c){
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		JLabel title = new JLabel(c.getName());
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		title.setAlignmentX(LEFT_ALIGNMENT);
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		for (CourseElement e: c.getElements()){
			if(e.isVisible()){
				if (e instanceof Note){
					this.addNote((Note)e);
					this.add(Box.createRigidArea(new Dimension(0, 10)));
				}
				else if (e instanceof Unit){
					this.addUnit((Unit)e);
					this.add(Box.createRigidArea(new Dimension(0, 10)));
				}
				else if (e instanceof Exercise){
					this.addExercise((Exercise)e);
					this.add(Box.createRigidArea(new Dimension(0, 10)));
				}
			}
		}
	}
	
	private void addUnit(Unit u){
		ImageIcon uicon = new ImageIcon("try1.png");
		JLabel l = new JLabel(u.getName(), uicon, JLabel.RIGHT);
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		l.setForeground(Color.BLUE);
		l.setAlignmentX(LEFT_ALIGNMENT);
		l.addMouseListener(new UnitClickingController(u));
		this.add(l);
	}
	
	private void addExercise(Exercise e){
		ImageIcon uicon = new ImageIcon("try2.png");
		JLabel l = new JLabel(e.getName(), uicon, JLabel.RIGHT);
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		l.setForeground(Color.BLUE);
		l.setAlignmentX(LEFT_ALIGNMENT);
		l.addMouseListener(new ExerciseClickingController(e));
		this.add(l);
	}
	
	private void addNote(Note n){
		JLabel l = new JLabel("<html><p style=\"width:300px\">"+n.getText()+"</p></html>");
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		l.setAlignmentX(LEFT_ALIGNMENT);
		this.add(l);
	}
	
}

