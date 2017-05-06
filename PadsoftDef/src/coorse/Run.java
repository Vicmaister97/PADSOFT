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
		try {
			Coorse.coorse.load("app.data");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return;
		}
		Login l = new Login();
		GeneralFrame.GFrame.setContentPane(l);
	}
}
