package coorse;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;

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
		UIManager.put("OptionPane.yesButtonText", "Yes");
		UIManager.put("OptionPane.noButtonText", "No");
		UIManager.put("OptionPane.cancelButtonText", "Cancel");
		
		Course course = new Course("PADSOF", "Juan de Lara", true);
		Exercise e1 = new Exercise(course, true, true, LocalDateTime.MIN, LocalDateTime.MAX, "Examen1", 50.0, 0.0);
		//Exercise e2 = new Exercise(course, true, true, LocalDateTime.MIN, LocalDateTime.MAX, "Examen2", 40.0, 0.0);
		Student s1 = (Student) Coorse.coorse.login("Luis.Gallego@coor.es", "s.ll");
		Application a1 = s1.sendApplication(course); /*let's see the trace*/
		a1.admitStudent();
		
		
		SimpleChoice q1 = new SimpleChoice(e1, 5.0, "Which is the highest mountain in Spain?", true, "Teide");
		q1.addPossibleAnswer("Teide");
		q1.addPossibleAnswer("Aneto");
		q1.addPossibleAnswer("Mulhacen");
		q1.addPossibleAnswer("Moncano");
		TextAnswer q2 = new TextAnswer (e1, 5.0, "1+1=", "2");
		ArrayList<String> solutions = new ArrayList<String>();
		solutions.add("Goya");
		solutions.add("Velazquez");
		MultipleChoice q3 = new MultipleChoice(e1, 5.0, "Which ones of these were spanish painters?", true, solutions);
		q3.addPossibleAnswer("Goya");
		q3.addPossibleAnswer("Hopper");
		q3.addPossibleAnswer("Turner");
		q3.addPossibleAnswer("Velazquez");
		TrueFalse q4 = new TrueFalse(e1, 5.0, "Do you love me?", true, "True");
		//e1.setDone(true);
		
		q1.solveQuestion(s1, "Teide");/*correct*/
		q2.solveQuestion(s1, "4");/*wrong*/
		List<String> bad = new ArrayList<String>();
		bad.add("Hopper");
		q3.solveQuestion(s1, bad); /*bad*/
		q4.solveQuestion(s1, "False"); /*wrong*/
		
		AnswerExercise ans = e1.solveExercise(s1);
		
		//Login l = new Login();
		//QuestionEditor l = new QuestionEditor(e1);
		//ExerciseSolver l = new ExerciseSolver(e1,s1);
		//TextEditor l = new TextEditor(q2);
		//ExerciseCreator l = new ExerciseCreator(course);
		//QuestionCreator l = new QuestionCreator(e1);
		ExerciseCorrected l = new ExerciseCorrected(ans);
		GeneralFrame.GFrame.setContentPane(l);
	}
}
