package gui;

import java.awt.Font;

import javax.swing.JLabel;

import controllers.HomeClickingController;

public class HomeButton extends JLabel{
	public HomeButton(){
		super("Home");
		this.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		this.addMouseListener(new HomeClickingController());
	}
}
