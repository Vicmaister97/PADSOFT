package controllers;
import gui.*;
import users.Student;
import users.User;
import coorse.Coorse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class LoginController implements ActionListener{
	private JTextField username;
	private JPasswordField password;
	public LoginController(JTextField username, JPasswordField password){
		this.username = username;
		this.password = password;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		User u = Coorse.coorse.login(username.getText(), new String(password.getPassword()));
    	if (u==null){
    		JOptionPane.showMessageDialog(null, "login incorrecto");
    	}
    	else if(u.getUsername().equals("admin")){
    		GeneralFrame.GFrame.changePanel(new MainScreenTeacher(), false);

    	}
    	else{
    		Student s = (Student) u;
    		GeneralFrame.GFrame.setStudent(s);
    		MainScreenStudent m = new MainScreenStudent();
    		GeneralFrame.GFrame.changePanel(m, false);
    	}
    	
	}
	
}
