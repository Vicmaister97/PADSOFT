package gui;

import javax.swing.*;
import coorse.*;
import courseElements.Course;
import courseElements.Exercise;
import users.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class QuestionCreator extends JPanel{
	// Cards panel declaration
	private JPanel cards; 
	final static String TEXTPANEL = "Text Answer"; 
	final static String SIMPLEPANEL   = "Simple Choice";
	//final static String TFPANEL = "True/False"; 
	//final static String MULTPANEL   = "Multiple Choice";
	// Create and initialize each card
	private JPanel card1 = new JPanel(); 
	private JPanel card2 = new JPanel();
	//private JPanel card3 = new JPanel();
	//private JPanel card4 = new JPanel();
	
	private static int frames = 40;
	private static int frames2 = 10;
		
	public void buildPanel1() {
		SpringLayout layout = new SpringLayout();
		this.card1.setLayout(layout);
		JLabel etiquetaState = new JLabel("Question statement: ");
		final JTextField campoState = new JTextField(40);
		JLabel etiquetaAnswer = new JLabel("Answer: ");
		final JTextField campoAnswer = new JTextField(20);
		JLabel etiquetaWeight = new JLabel("Weight of the question: ");
		final JTextField campoWeight = new JTextField(5);
		
		this.card1.add(etiquetaState);
		this.card1.add(campoState);
		this.card1.add(etiquetaAnswer);
		this.card1.add(campoAnswer);
		this.card1.add(etiquetaWeight);
		this.card1.add(campoWeight);
		
		layout.putConstraint(SpringLayout.WEST, etiquetaState, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaState, frames, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, campoState, frames2, SpringLayout.EAST, etiquetaState);
		layout.putConstraint(SpringLayout.NORTH, campoState, frames, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, etiquetaAnswer, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaAnswer, frames, SpringLayout.SOUTH, etiquetaState);
		layout.putConstraint(SpringLayout.WEST, campoAnswer, frames2, SpringLayout.EAST, etiquetaAnswer);
		layout.putConstraint(SpringLayout.NORTH, campoAnswer, frames, SpringLayout.SOUTH, etiquetaState);
		layout.putConstraint(SpringLayout.WEST, etiquetaWeight, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaWeight, frames, SpringLayout.SOUTH, etiquetaAnswer);
		layout.putConstraint(SpringLayout.WEST, campoWeight, frames2, SpringLayout.EAST, etiquetaWeight);
		layout.putConstraint(SpringLayout.NORTH, campoWeight, frames, SpringLayout.SOUTH, etiquetaAnswer);
		
	}
		
	public void buildPanel2() {
		SpringLayout layout = new SpringLayout();
		this.card2.setLayout(layout);
		JLabel etiquetaState = new JLabel("Question statement: ");
		final JTextField campoState = new JTextField(20);
		JLabel etiquetaWeight = new JLabel("Weight of the question: ");
		final JTextField campoWeight = new JTextField(4);
		JCheckBox randAnsOr = new JCheckBox("Random Order of the possible answers");
		JLabel etiquetaNumAns = new JLabel("Number of possible answers: "); /*maximum???*/
		final JTextField campoNumAns = new JTextField(2);
		JButton confirm = new JButton("Create possible answers");
		
		this.card2.add(etiquetaState);
		this.card2.add(campoState);
		this.card2.add(etiquetaWeight);
		this.card2.add(campoWeight);
		this.card2.add(randAnsOr);
		this.card2.add(etiquetaNumAns);
		this.card2.add(campoNumAns);
		this.card2.add(confirm);
		
		confirm.addActionListener( //Añadir que si ya le habías dado antes, te borre las que había
				e ->{
					if (campoNumAns.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter a number for the possible answers");
					}
					else{
						try{
							int numAns = Integer.parseInt(campoNumAns.getText());
							if (numAns < 0 || numAns > 10){ /*We choose arbitrarily the top of 10 possible answers*/
								throw new NumberFormatException("It's necessary to enter a valid number for the possible answers (>0 and <10)");
							}
							JTextField[] posAns = new JTextField[numAns];
							int i = 0;
							posAns[i] = new JTextField(15); /*First JTextField, at least this one*/
							this.card2.add(posAns[i]);
							layout.putConstraint(SpringLayout.WEST, posAns[i], frames, SpringLayout.WEST, this);
							layout.putConstraint(SpringLayout.NORTH, posAns[i], frames, SpringLayout.SOUTH, confirm);
							for(i=1; i<numAns; i++){
								posAns[i] = new JTextField(15);
								this.card2.add(posAns[i]);
								layout.putConstraint(SpringLayout.WEST, posAns[i], frames, SpringLayout.WEST, this);
								layout.putConstraint(SpringLayout.NORTH, posAns[i], frames, SpringLayout.SOUTH, posAns[i-1]);
							}
							
							JLabel etiquetaCorrect = new JLabel("Correct answer (from 1 to numPossibleAnswers): ");
							final JTextField campoCorrect = new JTextField(2);
							
							this.card2.add(etiquetaCorrect);
							this.card2.add(campoCorrect);
							
							layout.putConstraint(SpringLayout.WEST, etiquetaCorrect, frames, SpringLayout.WEST, this);
							layout.putConstraint(SpringLayout.NORTH, etiquetaCorrect, frames, SpringLayout.SOUTH, posAns[i-1]);
							layout.putConstraint(SpringLayout.WEST, campoCorrect, frames2, SpringLayout.EAST, etiquetaCorrect);
							layout.putConstraint(SpringLayout.NORTH, campoCorrect, frames, SpringLayout.SOUTH, posAns[i-1]);
						}
						catch (NumberFormatException errNum){
							JOptionPane.showMessageDialog(null, errNum);
						}
					}
					
				}
				
				
				);
		
		layout.putConstraint(SpringLayout.WEST, etiquetaState, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaState, frames, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, campoState, frames2, SpringLayout.EAST, etiquetaState);
		layout.putConstraint(SpringLayout.NORTH, campoState, frames, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, etiquetaWeight, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaWeight, frames, SpringLayout.SOUTH, etiquetaState);
		layout.putConstraint(SpringLayout.WEST, campoWeight, frames2, SpringLayout.EAST, etiquetaWeight);
		layout.putConstraint(SpringLayout.NORTH, campoWeight, frames, SpringLayout.SOUTH, etiquetaState);
		layout.putConstraint(SpringLayout.WEST, randAnsOr, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, randAnsOr, frames, SpringLayout.SOUTH, etiquetaWeight);
		layout.putConstraint(SpringLayout.WEST, etiquetaNumAns, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNumAns, frames, SpringLayout.SOUTH, randAnsOr);
		layout.putConstraint(SpringLayout.WEST, campoNumAns, frames2, SpringLayout.EAST, etiquetaNumAns);
		layout.putConstraint(SpringLayout.NORTH, campoNumAns, frames, SpringLayout.SOUTH, randAnsOr);
		layout.putConstraint(SpringLayout.WEST, confirm, frames2, SpringLayout.EAST, campoNumAns);
		layout.putConstraint(SpringLayout.NORTH, confirm, frames, SpringLayout.SOUTH, randAnsOr);
		
	}
		
	public QuestionCreator() { //AQUI METEMOS COMO PARAM DE ENTRADA EXE
		
		// Create the panel that contains the cards
		cards = new JPanel(new CardLayout()); 
		cards.add(card1, TEXTPANEL); 
		cards.add(card2, SIMPLEPANEL);
			
		this.buildPanel1(); //AQUI METEMOS COMO PARAM DE ENTRADA EXE
		this.buildPanel2(); //AQUI METEMOS COMO PARAM DE ENTRADA EXE
			
		JPanel comboBoxPane = new JPanel(); //FlowLayout is used by default
		String comboBoxItems[] = { TEXTPANEL, SIMPLEPANEL }; 

		JComboBox<String> cb = new JComboBox<String>(comboBoxItems);   // Controls which card is shown

		cb.setEditable(false); 		       // Combo box used just for selection
		cb.addItemListener(
				e ->{
					CardLayout cl = (CardLayout)(cards.getLayout());  // Get cards layout
					 cl.show(cards, (String)e.getItem());   	// Show the card that corresponds to the
										     						// id chosen in the combo box.
				}
				
				);
		
		comboBoxPane.add(cb); 		       // Add combo box to father panel
		JPanel pane = new JPanel();
		pane.setLayout(new BorderLayout());
		pane.add(comboBoxPane, BorderLayout.NORTH);    // Show the combo box
		pane.add(cards, BorderLayout.CENTER); 	       // Show cards panel
		
		//this.setSize(350,240);
		this.add(pane);
		this.setVisible(true);
	}

}
