package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import controllers.AddNoteController;
import controllers.AddUnitController;
import controllers.ExerciseClickingController;
import controllers.SetVisibleController;
import controllers.UnitClickingController;
import controllers.UnitClickingControllerT;
import courseElements.Course;
import courseElements.CourseElement;
import courseElements.Exercise;
import courseElements.Note;
import courseElements.Unit;
import courseElements.VisibleElement;

public class CourseScreenTeacher extends JPanel {
	public CourseScreenTeacher(Course c){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		HomeButtonT home = new HomeButtonT();
		BackArrow b = new BackArrow();
		this.setBackground(new Color(153, 255, 255));
		this.add(b);
		this.add(home);
		TeacherButtons tb = new TeacherButtons(c);
		this.add(tb);
		JLabel pname = new JLabel("Professor " + c.getTeachername());
		pname.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		this.add(pname);
		ElementsPanelT e = new ElementsPanelT(c);
		this.add(e);
		layout.putConstraint(SpringLayout.NORTH, b, 17, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, b, 15, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, home, 15, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, home, 15, SpringLayout.EAST, b);
		layout.putConstraint(SpringLayout.NORTH, pname, 75, SpringLayout.SOUTH, home);
		layout.putConstraint(SpringLayout.WEST, pname, 200, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, e, 50, SpringLayout.SOUTH, pname);
		layout.putConstraint(SpringLayout.WEST, e, 200, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, tb, 50, SpringLayout.SOUTH, e);
		layout.putConstraint(SpringLayout.WEST, tb, 200, SpringLayout.WEST, this);
		this.setPreferredSize(new Dimension((int) GeneralFrame.GFrame.getWidth(), (int)e.getPreferredSize().getHeight()+400));
	}
}

class ElementsPanelT extends JPanel{
	public ElementsPanelT(Course c){
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		JLabel title = new JLabel(c.getName());
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		title.setAlignmentX(LEFT_ALIGNMENT);
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		for (CourseElement e: c.getElements()){
			if (e instanceof Note){
				NoteLabel label = new NoteLabel((Note)e, c);
				label.setAlignmentX(LEFT_ALIGNMENT);
				this.add(label);
				this.add(Box.createRigidArea(new Dimension(0, 10)));
			}
			else if (e instanceof Unit){
				UnitLabel label = new UnitLabel((Unit)e, c);
				label.setAlignmentX(LEFT_ALIGNMENT);
				this.add(label);
				this.add(Box.createRigidArea(new Dimension(0, 10)));
			}
			else if (e instanceof Exercise){
				ExerciseLabel label = new ExerciseLabel((Exercise)e, c);
				label.setAlignmentX(LEFT_ALIGNMENT);
				this.add(label);
				this.add(Box.createRigidArea(new Dimension(0, 10)));
			}
		}
	}
	
	
}


class UnitLabel extends JPanel{
	public UnitLabel(Unit u, VisibleElement c){
		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		ImageIcon uicon = new ImageIcon("try1.png");
		this.setLayout(layout);
		JLabel l = new JLabel(u.getName(), uicon, JLabel.RIGHT);
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		l.setAlignmentY(CENTER_ALIGNMENT);
		l.setForeground(Color.BLUE);
		this.add(l);
		this.add(Box.createRigidArea(new Dimension(10, 0)));
		l.addMouseListener(new UnitClickingControllerT(u));
		if(u.isVisible()){
			JLabel visibleIcon = new JLabel(new ImageIcon("setInvisible.png"));
			visibleIcon.addMouseListener(new SetVisibleController(u, c));
			visibleIcon.setAlignmentY(CENTER_ALIGNMENT);
			this.add(visibleIcon);
		}
		else{
			JLabel visibleIcon = new JLabel(new ImageIcon("setVisible.png"));
			visibleIcon.addMouseListener(new SetVisibleController(u, c));
			visibleIcon.setAlignmentY(CENTER_ALIGNMENT);
			this.add(visibleIcon);
		}
	}
}

class ExerciseLabel extends JPanel{
	public ExerciseLabel(Exercise e, VisibleElement c){
		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		ImageIcon eicon = new ImageIcon("try2.png");
		this.setLayout(layout);
		JLabel l = new JLabel(e.getName(), eicon, JLabel.RIGHT);
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		l.setAlignmentY(CENTER_ALIGNMENT);
		l.setForeground(Color.BLUE);
		this.add(l);
		this.add(Box.createRigidArea(new Dimension(10, 0)));
		//l.addMouseListener(new UnitClickingController(u));
		if(!e.isDone()){
			if(e.isVisible()){
				JLabel visibleIcon = new JLabel(new ImageIcon("setInvisible.png"));
				visibleIcon.addMouseListener(new SetVisibleController(e, c));
				visibleIcon.setAlignmentY(CENTER_ALIGNMENT);
				this.add(visibleIcon);
			}
			else{
				JLabel visibleIcon = new JLabel(new ImageIcon("setVisible.png"));
				visibleIcon.addMouseListener(new SetVisibleController(e, c));
				visibleIcon.setAlignmentY(CENTER_ALIGNMENT);
				this.add(visibleIcon);
			}
			this.add(Box.createRigidArea(new Dimension(10, 0)));
			JLabel editIcon = new JLabel(new ImageIcon("editIcon.png"));
			editIcon.setAlignmentY(CENTER_ALIGNMENT);
			this.add(editIcon);
		}
	}
}

class NoteLabel extends JPanel{
	public NoteLabel(Note n, VisibleElement c){
		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(layout);
		JLabel l = new JLabel("<html><p style=\"width:300px\">"+n.getText()+"</p></html>");
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		l.setAlignmentY(CENTER_ALIGNMENT);
		this.add(l);
		this.add(Box.createRigidArea(new Dimension(10, 0)));
		if(n.isVisible()){
			JLabel visibleIcon = new JLabel(new ImageIcon("setInvisible.png"));
			visibleIcon.addMouseListener(new SetVisibleController(n, c));
			visibleIcon.setAlignmentY(CENTER_ALIGNMENT);
			this.add(visibleIcon);
		}
		else{
			JLabel visibleIcon = new JLabel(new ImageIcon("setVisible.png"));
			visibleIcon.addMouseListener(new SetVisibleController(n, c));
			visibleIcon.setAlignmentY(CENTER_ALIGNMENT);
			this.add(visibleIcon);
		}
	}
}

class TeacherButtons extends JPanel{
	public TeacherButtons(VisibleElement c){
		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(layout);
		JButton note = new JButton("Create Note");
		JButton exercise = new JButton("Create Exercise");
		JButton unit = new JButton("Create Unit");
		unit.addActionListener(new AddUnitController(c));
		note.addActionListener(new AddNoteController(c));
		this.add(note);
		this.add(Box.createRigidArea(new Dimension(10, 0)));
		this.add(unit);
		this.add(Box.createRigidArea(new Dimension(10, 0)));
		this.add(exercise);
	}
}