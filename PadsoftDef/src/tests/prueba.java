package tests;
import coorse.*;
import users.*;
import java.io.*;
import courseElements.*;
public class prueba {
	public static void main(String[] args){
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream("app.data"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		Object obj;
		try {
			obj = ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ois.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}
		Coorse c = (Coorse) obj;
		for (User userex: c.getUsers()){
			System.out.println(userex);
		}
		for (Course courseex: c.getCourses()){
			System.out.println(courseex);
		}
		try {
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}
