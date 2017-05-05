package controllers;
import gui.*;
import users.Student;
import users.User;

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
		User u = c.login(campo.getText(), new String(campo2.getPassword()));
    	if (u==null){
    		JOptionPane.showMessageDialog(null, "login incorrecto");
    	}
    	else if(u.getUsername().equals("admin")){
    		JOptionPane.showMessageDialog(null, "Loggeado como profesor");
    	}
    	else{
    		Student s = (Student) u;
    		JOptionPane.showMessageDialog(null, "Loggeado como " + s.getFirstName());
    	}
	}
	
}
