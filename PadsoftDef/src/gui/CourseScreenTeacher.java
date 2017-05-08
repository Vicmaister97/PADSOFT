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
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

import application.Application;
import controllers.AddNoteController;
import controllers.AddUnitController;
import controllers.ApplicationController;
import controllers.EditNoteController;
import controllers.ExerciseClickingController;
import controllers.ExpellStudentController;
import controllers.ReadmitStudentController;
import controllers.SetVisibleController;
import controllers.UnitClickingController;
import controllers.UnitClickingControllerT;
import courseElements.Course;
import courseElements.CourseElement;
import courseElements.Exercise;
import courseElements.Note;
import courseElements.Unit;
import courseElements.VisibleElement;
import users.Student;

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
		ApplicationsPanel ap = new ApplicationsPanel(c);
		StudentsPanel sp = new StudentsPanel(c);
		this.add(sp);
		EStudentsPanel ep = new EStudentsPanel(c);
		this.add(ep);
		ap.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		this.add(ap);
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
		layout.putConstraint(SpringLayout.NORTH, sp, 200, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, sp, -200, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, ep, 75, SpringLayout.SOUTH, sp);
		layout.putConstraint(SpringLayout.EAST, ep, -200, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, ap, 75, SpringLayout.SOUTH, ep);
		layout.putConstraint(SpringLayout.EAST, ap, -200, SpringLayout.EAST, this);
		this.setPreferredSize(new Dimension((int) GeneralFrame.GFrame.getWidth(), (int)e.getPreferredSize().getHeight()+400));
	}
}
class StudentsPanel extends JScrollPane{
	public StudentsPanel(Course c){
		super(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		JLabel title = new JLabel("Students");
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		title.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(title);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		if(!c.getStudents().isEmpty()){
			for (Student s: c.getStudents()){
				StudentLabel label = new StudentLabel(s, c);
				label.setAlignmentX(LEFT_ALIGNMENT);
				panel.add(label);
				panel.add(Box.createRigidArea(new Dimension(0, 10)));
			}
		}
		else{
			JLabel label = new JLabel("There are no enrolled students");
			label.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			label.setAlignmentX(LEFT_ALIGNMENT);
			panel.add(label);		
		}
		panel.setSize(new Dimension((int)panel.getPreferredSize().getWidth(), 200));
		this.setViewportView(panel);
	}
}
class StudentLabel extends JPanel{
	public StudentLabel(Student s, Course c){
		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(layout);
		JLabel l = new JLabel(s.getName());
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		l.setAlignmentY(CENTER_ALIGNMENT);
		this.add(l);
		this.add(Box.createRigidArea(new Dimension(10, 0)));
		JLabel decline = new JLabel(new ImageIcon("decline.png"));
		decline.addMouseListener(new ExpellStudentController(s, c));
		decline.setAlignmentY(CENTER_ALIGNMENT);
		this.add(decline);
	}
}

class EStudentsPanel extends JScrollPane{
	public EStudentsPanel(Course c){
		super(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		JLabel title = new JLabel("Expelled Students");
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		title.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(title);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		if(!c.getExpelled().isEmpty()){
			for (Student s: c.getExpelled()){
				EStudentLabel label = new EStudentLabel(s, c);
				label.setAlignmentX(LEFT_ALIGNMENT);
				panel.add(label);
				panel.add(Box.createRigidArea(new Dimension(0, 10)));
			}
			
		}
		else{
			JLabel label = new JLabel("There are no expelled students");
			label.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			label.setAlignmentX(LEFT_ALIGNMENT);
			panel.add(label);		
		}
		panel.setSize(new Dimension((int)panel.getPreferredSize().getWidth(), 200));
		this.setViewportView(panel);
	}
}
class EStudentLabel extends JPanel{
	public EStudentLabel(Student s, Course c){
		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(layout);
		JLabel l = new JLabel(s.getName());
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		l.setAlignmentY(CENTER_ALIGNMENT);
		this.add(l);
		this.add(Box.createRigidArea(new Dimension(10, 0)));
		JLabel accept = new JLabel(new ImageIcon("accept.png"));
		accept.addMouseListener(new ReadmitStudentController(s, c));
		accept.setAlignmentY(CENTER_ALIGNMENT);
		this.add(accept);
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
		this.add(Box.createRigidArea(new Dimension(10, 0)));
		JLabel editIcon = new JLabel(new ImageIcon("editIcon.png"));
		editIcon.setAlignmentY(CENTER_ALIGNMENT);
		editIcon.addMouseListener(new EditNoteController(c, n));
		this.add(editIcon);
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

class ApplicationsPanel extends JScrollPane{
	public ApplicationsPanel(Course c){
		super(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		JLabel title = new JLabel("Applications Pending");
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		title.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(title);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		if(!c.getPending().isEmpty()){
			for (Application a: c.getPending()){
				ApplicationLabel label = new ApplicationLabel(a);
				label.setAlignmentX(LEFT_ALIGNMENT);
				panel.add(label);
				panel.add(Box.createRigidArea(new Dimension(0, 10)));
			}
			
		}
		else{
			JLabel label = new JLabel("There are no applications left");
			label.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			label.setAlignmentX(LEFT_ALIGNMENT);
			panel.add(label);
		}
		panel.setPreferredSize(new Dimension((int)panel.getPreferredSize().getWidth(), 200));
		this.setViewportView(panel);
	}
}

class ApplicationLabel extends JPanel{
	public ApplicationLabel(Application a){
		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(layout);
		JLabel l = new JLabel(a.getStudent().getName());
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		l.setAlignmentY(CENTER_ALIGNMENT);
		this.add(l);
		this.add(Box.createRigidArea(new Dimension(10, 0)));
		JLabel accept = new JLabel(new ImageIcon("accept.png"));
		accept.addMouseListener(new ApplicationController(a, true));
		accept.setAlignmentY(CENTER_ALIGNMENT);
		this.add(accept);
		this.add(Box.createRigidArea(new Dimension(10, 0)));
		JLabel decline = new JLabel(new ImageIcon("decline.png"));
		decline.addMouseListener(new ApplicationController(a, false));
		decline.setAlignmentY(CENTER_ALIGNMENT);
		this.add(decline);
	}
}