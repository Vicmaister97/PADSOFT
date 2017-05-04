package tests;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import application.Application;
import coorse.Coorse;
import courseElements.Course;
import courseElements.Exercise;
import courseElements.Note;
import courseElements.Unit;
import exercises.MultipleChoice;
import exercises.SimpleChoice;
import exercises.TextAnswer;
import users.Professor;
import users.Student;
import users.User;


public class Demonstrator {
	public static void main(String[] args){
		Coorse c = new Coorse();
		/*First of all, we load the database*/
		c.readFromFile("StudentData.txt");
		for (User userex: c.getUsers()){
			System.out.println(userex);
		}
		Course course = new Course("cname", "tname", true);
		
		/*PROFESSOR REQUIREMENTS*/
		
		/*1.Login as professor*/
		Professor p = (Professor) c.login("admin", "12345");
		if(p == null){
			System.out.println("Login error");
		}
		
		/*2. Creating Units, Notes, Exercises*/
		Unit u = new Unit("uname", true, course);
		course.addElement(u);
		Exercise e1 = new Exercise(course, true, true, LocalDateTime.MIN, LocalDateTime.MAX, "ename1", 50.0, 0.0);
		Note n1 = new Note("example", true);
		course.addElement(n1);
		
		/*3. Creating Subunits, Notes, Exercises in Units*/
		u.addSubunit("uname");
		Exercise e2 = new Exercise(course, true, true, LocalDateTime.MIN, LocalDateTime.MAX, "ename2", 50.0, 0.0);
		u.addElement(e2);
		Note n2 =  new Note("example", true);
		u.addElement(n2);
		
		/*4. Use the exercise editor to edit exercises*/
		SimpleChoice q1 = new SimpleChoice(e1, 5.0, "Which is the highest mountain in Spain?", true, "Teide");
		q1.addPossibleAnswer("Teide");
		q1.addPossibleAnswer("Aneto");
		q1.addPossibleAnswer("Mulhacen");
		q1.addPossibleAnswer("Moncano");
		TextAnswer q2 = new TextAnswer (e1, 5.0, "1+1=", "2");
		List<String> solutions = new ArrayList<String>();
		solutions.add("Goya");
		solutions.add("Velazquez");
		MultipleChoice q3 = new MultipleChoice(e2, 5.0, "Which ones of these were spanish painters?", true, solutions);
		q3.addPossibleAnswer("Goya");
		q3.addPossibleAnswer("Hopper");
		q3.addPossibleAnswer("Turner");
		q3.addPossibleAnswer("Velazquez");
		TextAnswer q4 = new TextAnswer (e2, 5.0, "5+3=", "8");
		
		/*5. Modify exercises*/
		q4.setQuestionText("5+4=");
		q4.setAnswer("9");
		
		/*6. See statistics(We'll get to that after some students have solved a few exercises)*/
		
		/*STUDENT REQUIREMENTS*/
		
		/*1. Login with credentials*/
		Student s1 = (Student) c.login("Luis.Gallego@coor.es", "s.ll");
		if (s1==null){
			System.out.println("Login Error");
		}
		Student s2 = (Student) c.login("Jorge.Alcazar@coor.es", "JoA");
		if (s1==null){
			System.out.println("Login Error");
		}
		
		/*2. Applying to course*/
		c.addCourse(course);
		Application a1 = s1.sendApplication(course); /*let's see the trace*/
		Application a2= s2.sendApplication(course);
		a1.admitStudent();
		a2.admitStudent();
		if(course.getStudents().contains(s1)==false || course.getStudents().contains(s2)==false){
			System.out.println("Application error");
		}
		
		/*3. Solving exercises*/
		q1.solveQuestion(s1, "Teide");/*correct*/
		q2.solveQuestion(s1, "2");/*correct*/
		System.out.println(e1.solveExercise(s1).getMark());/*10/10*/
		q1.solveQuestion(s2, "Teide");/*correct*/
		q2.solveQuestion(s2, "4");/*wrong*/
		System.out.println(e1.solveExercise(s2).getMark());/*5/10*/
		q3.solveQuestion(s1, solutions); /*correct*/
		q4.solveQuestion(s1, "9"); /*correct*/
		System.out.println(e2.solveExercise(s1).getMark());/*10/10*/
		List<String> bad = new ArrayList<String>();
		bad.add("Hopper");
		q3.solveQuestion(s2, bad);/*wrong*/
		q4.solveQuestion(s2, "8");/*wrong*/
		System.out.println(e2.solveExercise(s2).getMark());/*0/10*/
		
		/*4. Get notifications*/
		Note n3 = new Note("example", false);
		course.addElement(n3);
		course.setSmthVisible(n3); /*now we'll see the trace*/
		
		/*5. See statistics*/
		System.out.println(course.getGlobalMark(s1));
		System.out.println(course.getGlobalMark(s2));
		
		/*Teacher Statistics(he also has access to all the student answers)*/
		System.out.println(q1.getNumCorrect());/*number of correct responses to q1 (2/2)*/
		System.out.println(q2.getNumIncorrect());/*number of incorrect responses to q2 (1/2)*/
		
		c.save("app.data");
		
		
	}
}
