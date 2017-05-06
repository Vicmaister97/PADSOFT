package gui;

import java.io.IOException;

import application.Application;
import coorse.Coorse;
import users.*;
import courseElements.*;

public class MeterCosasEnCosas {
	public static void main(String[] args) {
		Coorse.coorse.readFromFile("StudentData.txt");
		Course c1 = new Course("cname1", "tname", true);
		Course c2 = new Course("cname2", "tname", true);
		Coorse.coorse.addCourse(c1);
		Coorse.coorse.addCourse(c2);
		Student s = (Student) Coorse.coorse.login("a@a.es", "a");
		Application a1 = s.sendApplication(c1);
		a1.admitStudent();
		Application a2 = s.sendApplication(c2);
		a2.admitStudent();
		Course c3 = new Course("cname3", "tname", true);
		Course c4 = new Course("cname4", "tname", false);
		Coorse.coorse.addCourse(c3);
		Coorse.coorse.addCourse(c4);
		for (User u: Coorse.coorse.getUsers()){
			System.out.println(u);
		}
		for(Course c: Coorse.coorse.getCourses()){
			System.out.println(c);
		}
		Coorse.coorse.save("app.data");
	}
}
