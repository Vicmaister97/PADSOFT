package gui;

import java.io.IOException;

import application.Application;
import coorse.Coorse;
import users.*;
import courseElements.*;

public class MeterCosasEnCosas {
	public static void main(String[] args) {
		try {
			Coorse.coorse.load("app.data");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return;
		}
		Course c1 = new Course("cname2", "tname", true);
		Course c2 = new Course("cname3", "tname", true);
		Coorse.coorse.addCourse(c1);
		Coorse.coorse.addCourse(c2);
		Student s = (Student) Coorse.coorse.login("a@a.es", "a");
		Application a1 = s.sendApplication(c1);
		a1.admitStudent();
		Application a2 = s.sendApplication(c2);
		a2.admitStudent();
		Coorse.coorse.save("app.data");
	}
}
