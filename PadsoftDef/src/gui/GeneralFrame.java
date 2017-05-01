package gui;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import coorse.*;
import users.*;
import gui.Login;
public class GeneralFrame extends JFrame{
	public GeneralFrame(Container c){
		super("Coorse");
		this.setContentPane(c);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		Runnable runner = new Runnable() {
		@Override 
		public void run() {
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
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Login l = new Login(c);
			new GeneralFrame(l);
			}
		};
		EventQueue.invokeLater(runner);
		}
}
