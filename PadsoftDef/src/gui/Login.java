package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import coorse.Coorse;
import users.Student;
import users.User;
import controllers.LoginController;
public class Login extends JPanel{
	private static int frames = 60;
	private static int frames2 = 10;
	public Login(){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		this.setBackground(new Color(153, 255, 255));
		JLabel logo = new JLabel(new ImageIcon("logo.png"));
		this.add(logo);
		JLabel userlabel = new JLabel("Username");
		final JTextField usertext = new JTextField(20);
		JLabel passlabel = new JLabel ("Password");
		final JPasswordField passtext = new JPasswordField(10);
		JButton boton   = new JButton("Login");
		boton.addActionListener(new LoginController(usertext, passtext));
		this.add(userlabel);
		this.add(passlabel);
		this.add(usertext);
		this.add(passtext);
		this.add(boton);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logo, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, logo, 200, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, userlabel, -frames, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, userlabel, frames+10, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.WEST, usertext, frames2, SpringLayout.EAST, userlabel);
		layout.putConstraint(SpringLayout.NORTH, usertext, frames+10, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.EAST, passlabel, -frames, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, passlabel, frames2*2, SpringLayout.VERTICAL_CENTER, userlabel);
		layout.putConstraint(SpringLayout.WEST, passtext, frames2, SpringLayout.EAST, passlabel);
		layout.putConstraint(SpringLayout.NORTH, passtext, frames2, SpringLayout.SOUTH, usertext);
		layout.putConstraint(SpringLayout.WEST, boton, -frames, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, boton, frames, SpringLayout.SOUTH, passtext);
		this.setVisible(true);
	}
}

