package coorse;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.UIManager;

import application.Application;
import courseElements.*;
import exercises.*;
import gui.*;
import users.*;
public class Run {
	public static void main(String[] args) {
		/*try {
			Coorse.coorse.load("app.data");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return;
		}*/
		UIManager.put("OptionPane.yesButtonText", "Yes");
		UIManager.put("OptionPane.noButtonText", "No");
		//Login l = new Login();
		Course course = new Course("PADSOF", "Juan de Lara", true);
		ExerciseCreator l = new ExerciseCreator(course);
		GeneralFrame.GFrame.setContentPane(l);
	}
}
