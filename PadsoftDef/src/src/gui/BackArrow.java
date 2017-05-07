package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.BackArrowController;

public class BackArrow extends JLabel {
	public BackArrow(){
		super(new ImageIcon("backarrow.png"));
		this.addMouseListener(new BackArrowController());
	}
}
