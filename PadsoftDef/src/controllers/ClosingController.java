package controllers;

import java.awt.event.WindowEvent;
import gui.*;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import coorse.Coorse;
import sun.awt.WindowClosingListener;

public class ClosingController implements WindowListener {
	JFrame frame;
	public ClosingController(JFrame frame){
		this.frame=frame;
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		int result = JOptionPane.showConfirmDialog(null, "You are exiting the application. Do you want to save?", "Exit Panel", JOptionPane.YES_NO_CANCEL_OPTION);
        if (result == JOptionPane.YES_OPTION){
        	Coorse.coorse.save("app.data");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        else if(result == JOptionPane.NO_OPTION) {
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
