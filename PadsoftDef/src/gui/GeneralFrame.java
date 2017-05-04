package gui;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFrame;

import coorse.Coorse;
import users.User;
public class GeneralFrame extends JFrame{
	public static final GeneralFrame GFrame = new GeneralFrame();
	public GeneralFrame(){
		super("Coorse");
		this.setBackground(Color.CYAN);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setUndecorated(true);
		this.setVisible(true);

	}
}
