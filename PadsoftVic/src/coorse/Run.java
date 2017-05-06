package coorse;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;

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
		Course course = new Course("PADSOF", "Juan de Lara", true);
		ExerciseEditor l = new ExerciseEditor(course);
		//Exercise e1 = new Exercise(course, true, true, LocalDateTime.MIN, LocalDateTime.MAX, "ename1", 50.0, 0.0);
		//QuestionCreator l = new QuestionCreator(e1);
		GeneralFrame.GFrame.setContentPane(l);
	}
}
