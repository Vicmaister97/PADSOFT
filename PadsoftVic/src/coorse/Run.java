package coorse;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import application.Application;
import courseElements.*;
import exercises.*;
import gui.*;
import users.*;
public class Run {
	public static void main(String[] args) {
		Coorse c = new Coorse();
		try {
			c.load("app.data");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return;
		}
		//Login l = new Login(c);
		//Course course = new Course("PADSOF", "Juan de Lara", true);
		//ExerciseEditor l = new ExerciseEditor(course);
		QuestionCreator l = new QuestionCreator();
		GeneralFrame.GFrame.setContentPane(l);
	}
}
