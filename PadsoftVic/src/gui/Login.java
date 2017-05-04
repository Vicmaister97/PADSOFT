package gui;

import javax.swing.*;
import coorse.*;
import users.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends Container{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int frames = 40;
	private static int frames2 = 10;
	public Login(Coorse c){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		this.setBackground(Color.CYAN);
		JLabel etiqueta = new JLabel("Username");
		final JTextField campo = new JTextField(30);
		JLabel etiqueta2 = new JLabel ("Password");
		final JPasswordField campo2 = new JPasswordField(10);
		JButton boton   = new JButton("Login");
		boton.addActionListener(
				  new ActionListener() {
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
				);
		this.add(etiqueta);
		this.add(etiqueta2);
		this.add(campo);
		this.add(campo2);
		this.add(boton);
		layout.putConstraint(SpringLayout.EAST, etiqueta, -frames, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, etiqueta, frames+10, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.WEST, campo, frames2, SpringLayout.EAST, etiqueta);
		layout.putConstraint(SpringLayout.NORTH, campo, frames+10, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.EAST, etiqueta2, -frames, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, etiqueta2, frames2*2, SpringLayout.VERTICAL_CENTER, etiqueta);
		layout.putConstraint(SpringLayout.WEST, campo2, frames2, SpringLayout.EAST, etiqueta2);
		layout.putConstraint(SpringLayout.NORTH, campo2, frames2, SpringLayout.SOUTH, campo);
		layout.putConstraint(SpringLayout.WEST, boton, -frames, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, boton, frames, SpringLayout.SOUTH, campo2);
		this.setVisible(true);
	}
}

