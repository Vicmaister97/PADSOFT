package gui;

import java.io.IOException;
import java.time.LocalDateTime;

import application.Application;
import coorse.Coorse;
import users.*;
import courseElements.*;

public class MeterCosasEnCosas {
	public static void main(String[] args) {
		Coorse.coorse.readFromFile("StudentData.txt");
		Course c1 = new Course("Conjuntos y N�meros", "Alberto PutoAmo", true);
		Course c2 = new Course("Calculo I", "Torrea Esta Muerto Por Dentro", true);
		Coorse.coorse.addCourse(c1);
		Coorse.coorse.addCourse(c2);
		Student s = (Student) Coorse.coorse.login("a@a.es", "a");
		Application a1 = s.sendApplication(c1);
		a1.admitStudent();
		Application a2 = s.sendApplication(c2);
		a2.admitStudent();
		c1.addUnit("Cardinalidad", true);
		c1.addElement(new Note("Entregad el ejercicio 1 para la semana que viene", true));
		c1.addElement(new Note("jaja xd 2", false));
		new Exercise(c1, true, true, LocalDateTime.now(), LocalDateTime.now(), "Ejercicio 1", 2.0, 1.0);
		Course c3 = new Course("Programacion I", "Ivan Cantador", true);
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
