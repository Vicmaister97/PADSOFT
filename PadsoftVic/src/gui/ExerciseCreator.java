package gui;

import java.awt.Container;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import coorse.Coorse;
import courseElements.Course;
import courseElements.Exercise;

public class ExerciseCreator extends JPanel{ //AÑADIR BOTON SAVE Y NUMPOSANS EN SIMPLE Y MULTIPLE
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int frames = 40;
	private static int frames2 = 10;
	Exercise exe;
	
	public ExerciseCreator(Course course){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		//this.setBackground(Color.LIGHT_GRAY);
		JLabel etiquetaTit = new JLabel("~~ EXERCISE CREATOR ~~");
		etiquetaTit.setFont(new Font("Serif", Font.PLAIN, 16));
		JLabel etiquetaName = new JLabel("Name of the exercise: ");
		final JTextField campoName = new JTextField(10);
		JLabel etiquetaRele = new JLabel("Relevance on the global mark (percentage from 0 to 100): ");
		final JTextField campoRele = new JTextField(4);
		JLabel etiquetaStart = new JLabel("Start day and time (dd-MM-yyyy HH:mm): ");
		final JTextField campoStart = new JTextField(13);
		JLabel etiquetaEnd = new JLabel("Expiration day and time (dd-MM-yyyy HH:mm): ");
		final JTextField campoEnd = new JTextField(13);
		JLabel etiquetaPen = new JLabel("Penalty for answering incorrectly the questions: ");
		final JTextField campoPen = new JTextField(4);
		JCheckBox randQuesOr = new JCheckBox("Random Order of the questions");
		
		JButton Create = new JButton("Create Exercise");
		JButton Finish = new JButton("Finish");
		
		//JButton Cancel = new JButton("Cancel");

		Create.addActionListener(
				e ->{
					if (campoName.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter a name for the exercise");
					}
					else if (campoRele.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter a percentage for the exercise");
					}
					else if (campoStart.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter a start date and time for the exercise");
					}
					else if (campoEnd.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter an end date and time for the exercise");
					}
					else if (campoPen.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter a penalty for the questions");
					}
					else{ /*We create the exercise, always with visibility true when we create it*/
						boolean random = false;
						if (randQuesOr.isSelected()){
							random = true;
						}
						try{
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
							LocalDateTime ini = LocalDateTime.parse(campoStart.getText(), formatter);
							LocalDateTime end = LocalDateTime.parse(campoEnd.getText(), formatter);
							double perc = Double.parseDouble(campoRele.getText().replace(",",".")); /*If they write 30,5 instead of 30.5*/
							double penal = Double.parseDouble(campoPen.getText().replace(",","."));
							exe = new Exercise(course, false, random, ini, end, campoName.getText(), perc, penal);
							JOptionPane.showMessageDialog(null, "Exercise SUCCESFULLY CREATED with name " + exe.getName() + ", relevance: " + exe.getWeightE()
									+ ", ini " + exe.getIniDate() + ", end " + exe.getEndDate() + ", penalty: " + exe.getPenalisation() + " and randomOrder: " + exe.isRandomOrder());
							
							/*We clean the JTextFields*/
							campoName.setText("");
							campoRele.setText("");
							campoStart.setText("");
							campoEnd.setText("");
							campoPen.setText("");
							randQuesOr.setSelected(false);
							
							QuestionCreator quesCreat = new QuestionCreator(exe);
							
							this.add(quesCreat);
							
							layout.putConstraint(SpringLayout.EAST, quesCreat, -frames*2, SpringLayout.EAST, this);
							layout.putConstraint(SpringLayout.NORTH, quesCreat, frames-18, SpringLayout.NORTH, this);
							layout.putConstraint(SpringLayout.SOUTH, quesCreat, -frames, SpringLayout.SOUTH, this);
							layout.putConstraint(SpringLayout.WEST, quesCreat, frames*3, SpringLayout.EAST, campoEnd);
														
							this.validate();
							
						}
						catch (DateTimeParseException timeErr){
							JOptionPane.showMessageDialog(null, "The format of Date and Time is incorrect");
						}
						catch (NullPointerException exeErr){
							JOptionPane.showMessageDialog(null, exeErr.getMessage());
						}
						
					}
				}
					
				
				);
		
		Finish.addActionListener(
				f ->{
					if(exe == null){ /*We have created an exercise*/
						JOptionPane.showMessageDialog(null, "Going back to " + course.getName() + "\nNo exercise created");
					}
					else{
						JOptionPane.showMessageDialog(null, "Going back to " + course.getName() + "\nExercise " + exe.getName() + " saved");
					}
					
					CourseScreenStudent backCourse = new CourseScreenStudent(course);
					GeneralFrame.GFrame.remove(GeneralFrame.GFrame.getContentPane());
		    		GeneralFrame.GFrame.setContentPane(backCourse);
		    		GeneralFrame.GFrame.validate();
				}
				
				);
		
		this.add(etiquetaTit);
		this.add(etiquetaName);
		this.add(campoName);
		this.add(etiquetaRele);
		this.add(campoRele);
		this.add(etiquetaStart);
		this.add(campoStart);
		this.add(etiquetaEnd);
		this.add(campoEnd);
		this.add(etiquetaPen);
		this.add(campoPen);
		this.add(randQuesOr);
		this.add(Create);
		this.add(Finish);
		
		layout.putConstraint(SpringLayout.WEST, etiquetaTit, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaTit, frames*2, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, etiquetaName, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaName, frames, SpringLayout.SOUTH, etiquetaTit);
		layout.putConstraint(SpringLayout.WEST, campoName, frames2, SpringLayout.EAST, etiquetaName);
		layout.putConstraint(SpringLayout.NORTH, campoName, frames, SpringLayout.SOUTH, etiquetaTit);
		layout.putConstraint(SpringLayout.WEST, etiquetaRele, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaRele, frames, SpringLayout.SOUTH, etiquetaName);
		layout.putConstraint(SpringLayout.WEST, campoRele, frames2, SpringLayout.EAST, etiquetaRele);
		layout.putConstraint(SpringLayout.NORTH, campoRele, frames, SpringLayout.SOUTH, etiquetaName);
		layout.putConstraint(SpringLayout.WEST, etiquetaStart, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaStart, frames, SpringLayout.SOUTH, etiquetaRele);
		layout.putConstraint(SpringLayout.WEST, campoStart, frames2, SpringLayout.EAST, etiquetaStart);
		layout.putConstraint(SpringLayout.NORTH, campoStart, frames, SpringLayout.SOUTH, etiquetaRele);
		layout.putConstraint(SpringLayout.WEST, etiquetaEnd, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaEnd, frames, SpringLayout.SOUTH, etiquetaStart);
		layout.putConstraint(SpringLayout.WEST, campoEnd, frames2, SpringLayout.EAST, etiquetaEnd);
		layout.putConstraint(SpringLayout.NORTH, campoEnd, frames, SpringLayout.SOUTH, etiquetaStart);
		layout.putConstraint(SpringLayout.WEST, etiquetaPen, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaPen, frames, SpringLayout.SOUTH, etiquetaEnd);
		layout.putConstraint(SpringLayout.WEST, campoPen, frames2, SpringLayout.EAST, etiquetaPen);
		layout.putConstraint(SpringLayout.NORTH, campoPen, frames, SpringLayout.SOUTH, etiquetaEnd);
		layout.putConstraint(SpringLayout.WEST, randQuesOr, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, randQuesOr, frames, SpringLayout.SOUTH, etiquetaPen);
		layout.putConstraint(SpringLayout.WEST, Create, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, Create, frames, SpringLayout.SOUTH, randQuesOr);
		layout.putConstraint(SpringLayout.WEST, Finish, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, Finish, frames*2, SpringLayout.SOUTH, Create);
		
		
		this.setVisible(true);
	}
}
