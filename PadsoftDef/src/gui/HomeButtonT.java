package gui;

import java.awt.Font;

import javax.swing.JLabel;

import controllers.HomeClickingController;
import controllers.HomeClickingControllerT;

public class HomeButtonT extends JLabel {
	public HomeButtonT(){
		super("Home");
		this.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		this.addMouseListener(new HomeClickingControllerT());
	}
}
