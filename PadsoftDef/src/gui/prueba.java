package gui;

import javax.swing.*;
import coorse.*;
import users.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
class MyFrame extends JFrame{
	private static int frames = 10;
	public MyFrame(Coorse c){
		super("login");
		Container contenedor = this.getContentPane();
		SpringLayout layout = new SpringLayout();
		contenedor.setLayout(layout);
		JLabel etiqueta = new JLabel("Username");
		final JTextField campo = new JTextField(30);
		JLabel etiqueta2 = new JLabel ("Password");
		final JTextField campo2 = new JTextField(10);
		JButton boton   = new JButton("Login");
		boton.addActionListener(
				  new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	User u = c.login(campo.getText(), campo2.getText());
				    	if (u==null){
				    		JOptionPane.showMessageDialog(null, "login incorrecto");
				    	}
				    	else if(u.getUsername().equals("admin")){
				    		JOptionPane.showMessageDialog(null, "Loggeado como profesor");
				    	}
				    	else{
				    		Student s = (Student) u;
				    		JOptionPane.showMessageDialog(null, "Loggeado como " + s.getFirstName());
				    	}
				    }
				  }
				);
		contenedor.add(etiqueta);
		contenedor.add(etiqueta2);
		contenedor.add(campo);
		contenedor.add(campo2);
		contenedor.add(boton);
		layout.putConstraint(SpringLayout.WEST, etiqueta, frames, SpringLayout.WEST, contenedor);
		layout.putConstraint(SpringLayout.NORTH, etiqueta, frames, SpringLayout.NORTH, contenedor);
		layout.putConstraint(SpringLayout.WEST, campo, frames, SpringLayout.EAST, etiqueta);
		layout.putConstraint(SpringLayout.NORTH, campo, frames, SpringLayout.NORTH, contenedor);
		layout.putConstraint(SpringLayout.WEST, etiqueta2, frames, SpringLayout.WEST, contenedor);
		layout.putConstraint(SpringLayout.NORTH, etiqueta2, frames, SpringLayout.SOUTH, etiqueta);
		layout.putConstraint(SpringLayout.WEST, campo2, frames, SpringLayout.EAST, etiqueta2);
		layout.putConstraint(SpringLayout.NORTH, campo2, frames, SpringLayout.SOUTH, campo);
		layout.putConstraint(SpringLayout.WEST, boton, 20, SpringLayout.WEST, contenedor);
		layout.putConstraint(SpringLayout.NORTH, boton, frames, SpringLayout.SOUTH, campo2);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 150);
		this.setVisible(true);
	}
}

public class prueba {
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
			new MyFrame(c);
			}
		};
		EventQueue.invokeLater(runner);
		}

}
