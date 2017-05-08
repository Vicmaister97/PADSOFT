package gui;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Stack;

import controllers.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import coorse.Coorse;
import users.Student;
import users.User;
public class GeneralFrame extends JFrame{
	public Stack<Container> backtracking;
	public static final GeneralFrame GFrame = new GeneralFrame();
	public int flagLogin;
	private Student actualstudent;
	public GeneralFrame(){
		super("Coorse");
		flagLogin = 0;
		this.backtracking = new Stack <Container>();
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new ClosingController(this));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setUndecorated(true);
		this.setVisible(true);

	}
	
	public void changePanel(JPanel jp, boolean add){
		if(add){
			this.backtracking.push(this.getContentPane());
		}
		else{
			JScrollPane js = new JScrollPane(jp, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			if(this.backtracking.contains(js)){
				this.backtracking.remove(js);
			}
		}
		this.remove(this.getContentPane());
		JScrollPane frame = new JScrollPane(jp, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER){
			public boolean equals(JScrollPane js){
				return this.getComponents().equals(js.getComponents());
			}
		};
		frame.getVerticalScrollBar().setUnitIncrement(10);
		this.setContentPane(frame);
		this.validate();
		
	}
	
	
	
	public Student getStudent(){
		return actualstudent;
	}
	
	public void setStudent(Student s){
		this.actualstudent=s;
	}
}
