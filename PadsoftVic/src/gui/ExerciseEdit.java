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

public class ExerciseEdit extends JPanel { // AÑADIR BOTON SAVE Y NUMPOSANS EN
											// SIMPLE Y MULTIPLE
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int frames = 30;
	private static int frames2 = 10;
	Exercise exe;

	public ExerciseEdit(Exercise exe) {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);

		JLabel etiquetaName = new JLabel("Name of the exercise: " + exe.getName());
		JLabel etiquetaRele = new JLabel("Relevance on the global mark (percentage from 0 to 100): " + exe.getWeightE());
		JLabel etiquetaStart = new JLabel("Start day and time (dd-MM-yyyy HH:mm): " + exe.getIniDate());
		JLabel etiquetaEnd = new JLabel("Expiration day and time (dd-MM-yyyy HH:mm): " + exe.getEndDate());
		JLabel etiquetaPen = new JLabel("Penalty for answering incorrectly the questions: " + exe.getPenalisation());
		JLabel randQuesOr = new JLabel("Random Order of the questions: " + exe.isRandomOrder());

		JLabel etiquetaNName = new JLabel("New name of the exercise: ");
		final JTextField campoName = new JTextField(10);
		JLabel etiquetaNRele = new JLabel("New relevance on the global mark (percentage from 0 to 100): ");
		final JTextField campoRele = new JTextField(4);
		JLabel etiquetaNStart = new JLabel("New start day and time (dd-MM-yyyy HH:mm): ");
		final JTextField campoStart = new JTextField(13);
		JLabel etiquetaNEnd = new JLabel("New expiration day and time (dd-MM-yyyy HH:mm): ");
		final JTextField campoEnd = new JTextField(13);
		JLabel etiquetaNPen = new JLabel("New penalty for answering incorrectly the questions: ");
		final JTextField campoPen = new JTextField(4);
		JCheckBox randNQuesOr = new JCheckBox("New Random Order of the questions");

		JButton create = new JButton("Save Changes");

		// JButton Cancel = new JButton("Cancel");

		create.addActionListener(
				e ->{
					int cont = 0;
					if (campoName.getText().trim().isEmpty() == false){
						if ((exe.setName(campoName.getText())) == false){
							JOptionPane.showMessageDialog(null, "No accepted new name");
						}
						else{
							cont++;
							JOptionPane.showMessageDialog(null, "ACCEPTED new name: " + exe.getName());
							campoName.setText("");
							etiquetaName.setText("Name of the exercise: " + exe.getName());
						}
						
					}
					
					if (campoRele.getText().trim().isEmpty() == false){
						try{
							double weight = Double.parseDouble(campoRele.getText().replace(",",".")); /*If they write 30,5 instead of 30.5*/
							
							if ((exe.setWeightE(weight)) == false){
								JOptionPane.showMessageDialog(null, "No accepted new weight");
							}
							else{
								cont++;
								JOptionPane.showMessageDialog(null, "ACCEPTED new weight: " + exe.getWeightE());
								campoRele.setText("");
								etiquetaRele.setText("Relevance on the global mark (percentage from 0 to 100): " + exe.getWeightE());
							}
														
						}
						catch (NumberFormatException errNum){
							JOptionPane.showMessageDialog(null, errNum);
				 		}
											
					}
					
					if (campoStart.getText().trim().isEmpty() == false){
						
						try{
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
							LocalDateTime ini = LocalDateTime.parse(campoStart.getText(), formatter);
							
							if (exe.setIniDate(ini) == false){
								JOptionPane.showMessageDialog(null, "No accepted new start date");
							}
							else{
								cont++;
								JOptionPane.showMessageDialog(null, "ACCEPTED new start date: " + exe.getIniDate());
								campoStart.setText("");
								etiquetaStart.setText("Start day and time (dd-MM-yyyy HH:mm): " + exe.getIniDate());
							}
						}
						catch (DateTimeParseException timeErr){
							JOptionPane.showMessageDialog(null, "The format of Date and Time is incorrect");
						}
						
					}
					
					if (campoEnd.getText().trim().isEmpty() == false){
						
						try{
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
							LocalDateTime end = LocalDateTime.parse(campoEnd.getText(), formatter);
							
							if (exe.setEndDate(end) == false){
								JOptionPane.showMessageDialog(null, "No accepted new end date");
							}
							else{
								cont++;
								JOptionPane.showMessageDialog(null, "ACCEPTED new end date: " + exe.getEndDate());
								campoEnd.setText("");
								etiquetaEnd.setText("Expiration day and time (dd-MM-yyyy HH:mm): " + exe.getEndDate());
							}
						}
						catch (DateTimeParseException timeErr){
							JOptionPane.showMessageDialog(null, "The format of Date and Time is incorrect");
						}
						
					}
					
					if (campoPen.getText().trim().isEmpty() == false){
						try{
							double weight = Double.parseDouble(campoPen.getText().replace(",",".")); /*If they write 30,5 instead of 30.5*/
							
							if ((exe.setPenalisation(weight)) == false){
								JOptionPane.showMessageDialog(null, "No accepted new penalty");
							}
							else{
								cont++;
								JOptionPane.showMessageDialog(null, "ACCEPTED new penalty: " + exe.getPenalisation());
								campoPen.setText("");
								etiquetaPen.setText("Penalty for answering incorrectly the questions: " + exe.getPenalisation());
							}
														
						}
						catch (NumberFormatException errNum){
							JOptionPane.showMessageDialog(null, errNum);
				 		}
											
					}
										
					boolean rand = false;
					if(randNQuesOr.isSelected()){
						rand = true;
					}
					if (exe.isRandomOrder() != rand){ /*We are changing the random order value*/
						cont++;
						if (exe.setRandomOrder(rand) == false){
							JOptionPane.showMessageDialog(null, "No accepted new random order value of the questions");
						}
						else{
							JOptionPane.showMessageDialog(null, "ACCEPTED new random order of the questions: " + exe.isRandomOrder());
							randNQuesOr.setSelected(false);
							randQuesOr.setText("Random Order of the questions: " + exe.isRandomOrder());
						}
						

					}
					
					
					JOptionPane.showMessageDialog(null, "Exercise saved with "+cont+" changes");
					this.validate();
				}
				
				);
		

		this.add(etiquetaName);
		this.add(etiquetaRele);
		this.add(etiquetaStart);
		this.add(etiquetaEnd);
		this.add(etiquetaPen);
		this.add(randQuesOr);
		this.add(etiquetaNName);
		this.add(campoName);
		this.add(etiquetaNRele);
		this.add(campoRele);
		this.add(etiquetaNStart);
		this.add(campoStart);
		this.add(etiquetaNEnd);
		this.add(campoEnd);
		this.add(etiquetaNPen);
		this.add(campoPen);
		this.add(randNQuesOr);
		this.add(create);

		layout.putConstraint(SpringLayout.WEST, etiquetaName, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaName, frames, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, etiquetaRele, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaRele, frames, SpringLayout.SOUTH, etiquetaName);
		layout.putConstraint(SpringLayout.WEST, etiquetaStart, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaStart, frames, SpringLayout.SOUTH, etiquetaRele);
		layout.putConstraint(SpringLayout.WEST, etiquetaEnd, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaEnd, frames, SpringLayout.SOUTH, etiquetaStart);
		layout.putConstraint(SpringLayout.WEST, etiquetaPen, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaPen, frames, SpringLayout.SOUTH, etiquetaEnd);
		layout.putConstraint(SpringLayout.WEST, randQuesOr, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, randQuesOr, frames, SpringLayout.SOUTH, etiquetaPen);
		
		layout.putConstraint(SpringLayout.WEST, etiquetaNName, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNName, frames*2, SpringLayout.SOUTH, randQuesOr);
		layout.putConstraint(SpringLayout.WEST, campoName, frames2, SpringLayout.EAST, etiquetaNName);
		layout.putConstraint(SpringLayout.NORTH, campoName, frames*2, SpringLayout.SOUTH, randQuesOr);
		layout.putConstraint(SpringLayout.WEST, etiquetaNRele, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNRele, frames, SpringLayout.SOUTH, etiquetaNName);
		layout.putConstraint(SpringLayout.WEST, campoRele, frames2, SpringLayout.EAST, etiquetaNRele);
		layout.putConstraint(SpringLayout.NORTH, campoRele, frames, SpringLayout.SOUTH, etiquetaNName);
		layout.putConstraint(SpringLayout.WEST, etiquetaNStart, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNStart, frames, SpringLayout.SOUTH, etiquetaNRele);
		layout.putConstraint(SpringLayout.WEST, campoStart, frames2, SpringLayout.EAST, etiquetaNStart);
		layout.putConstraint(SpringLayout.NORTH, campoStart, frames, SpringLayout.SOUTH, etiquetaNRele);
		layout.putConstraint(SpringLayout.WEST, etiquetaNEnd, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNEnd, frames, SpringLayout.SOUTH, etiquetaNStart);
		layout.putConstraint(SpringLayout.WEST, campoEnd, frames2, SpringLayout.EAST, etiquetaNEnd);
		layout.putConstraint(SpringLayout.NORTH, campoEnd, frames, SpringLayout.SOUTH, etiquetaNStart);
		layout.putConstraint(SpringLayout.WEST, etiquetaNPen, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNPen, frames, SpringLayout.SOUTH, etiquetaNEnd);
		layout.putConstraint(SpringLayout.WEST, campoPen, frames2, SpringLayout.EAST, etiquetaNPen);
		layout.putConstraint(SpringLayout.NORTH, campoPen, frames, SpringLayout.SOUTH, etiquetaNEnd);
		layout.putConstraint(SpringLayout.WEST, randNQuesOr, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, randNQuesOr, frames, SpringLayout.SOUTH, etiquetaNPen);
		
		
		layout.putConstraint(SpringLayout.WEST, create, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, create, frames, SpringLayout.SOUTH, randNQuesOr);

		this.setVisible(true);
	}
}
