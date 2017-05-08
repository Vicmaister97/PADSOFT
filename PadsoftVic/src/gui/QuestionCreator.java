package gui;

import javax.swing.*;
import coorse.*;
import courseElements.Course;
import courseElements.Exercise;
import exercises.*;
import users.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class QuestionCreator extends JPanel{
	// Cards panel declaration
	private JPanel cards; 
	final static String TEXTPANEL = "Text Answer";
	final static String SIMPLEPANEL   = "Simple Choice";
	final static String TFPANEL = "True/False"; 
	final static String MULTPANEL   = "Multiple Choice";
	
	// Create and initialize each card
	final private JPanel card1 = new JPanel(); 
	final private JPanel card2 = new JPanel();
	final private JPanel card3 = new JPanel();
	final private JPanel card4 = new JPanel();
	
	private static int frames = 30;
	private static int frames2 = 10;
		
	public void buildPanel1(Exercise exe) {
		SpringLayout layout = new SpringLayout();
		this.card1.setLayout(layout);
		JLabel etiquetaState = new JLabel("Question statement: ");
		final JTextField campoState = new JTextField(30);
		JLabel etiquetaAnswer = new JLabel("Answer: ");
		final JTextField campoAnswer = new JTextField(20);
		JLabel etiquetaWeight = new JLabel("Weight (points) of the question: ");
		final JTextField campoWeight = new JTextField(4);
		JButton create = new JButton("Create Text Answer");
		
		create.addActionListener(
				e ->{
					if (campoState.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter a statement for the question");
					}
					else if (campoAnswer.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter an answer for the question");
					}
					else if (campoWeight.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter the points(weight) of the question");
					}
					
					else{
						try{
							double weight = Double.parseDouble(campoWeight.getText().replace(",",".")); /*If they write 30,5 instead of 30.5*/
							TextAnswer ques = new TextAnswer(exe, weight, campoState.getText(), campoAnswer.getText());
							JOptionPane.showMessageDialog(null, "Question SUCCESFULLY created and stored in " + exe.getName() + "\nQuestion statement: "
									+ ques.getQuestionText() + " Question answer: " + ques.getAnswer());
							
							/*We clean the JTextFields*/
							campoState.setText("");
							campoAnswer.setText("");
							campoWeight.setText("");
							
						}
						catch (NumberFormatException errNum){
							JOptionPane.showMessageDialog(null, errNum);
				 		}
						
					}
				}
				
				);
		
		this.card1.add(etiquetaState);
		this.card1.add(campoState);
		this.card1.add(etiquetaAnswer);
		this.card1.add(campoAnswer);
		this.card1.add(etiquetaWeight);
		this.card1.add(campoWeight);
		this.card1.add(create);
		
		layout.putConstraint(SpringLayout.WEST, etiquetaState, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaState, frames*4, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, campoState, frames2, SpringLayout.EAST, etiquetaState);
		layout.putConstraint(SpringLayout.NORTH, campoState, frames*4, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, etiquetaAnswer, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaAnswer, frames, SpringLayout.SOUTH, etiquetaState);
		layout.putConstraint(SpringLayout.WEST, campoAnswer, frames2, SpringLayout.EAST, etiquetaAnswer);
		layout.putConstraint(SpringLayout.NORTH, campoAnswer, frames, SpringLayout.SOUTH, etiquetaState);
		layout.putConstraint(SpringLayout.WEST, etiquetaWeight, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaWeight, frames, SpringLayout.SOUTH, etiquetaAnswer);
		layout.putConstraint(SpringLayout.WEST, campoWeight, frames2, SpringLayout.EAST, etiquetaWeight);
		layout.putConstraint(SpringLayout.NORTH, campoWeight, frames, SpringLayout.SOUTH, etiquetaAnswer);
		layout.putConstraint(SpringLayout.WEST, create, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, create, frames*2, SpringLayout.SOUTH, etiquetaWeight);
		
	}
		
	public void buildPanel2(Exercise exe) {
		SpringLayout layout = new SpringLayout();
		this.card2.setLayout(layout);
		JLabel etiquetaState = new JLabel("Question statement: ");
		final JTextField campoState = new JTextField(30);
		JLabel etiquetaWeight = new JLabel("Weight (points) of the question: ");
		final JTextField campoWeight = new JTextField(5);
		JCheckBox randAnsOr = new JCheckBox("Random Order of the possible answers");
		JLabel etiquetaNumAns = new JLabel("Number of possible answers: "); /*max 10*/
		final JTextField campoNumAns = new JTextField(2);
		JLabel etiquetaCorrect = new JLabel("Correct number of the answer(the first one is number 1): ");
		final JTextField campoCorrect = new JTextField(2);
		JButton confirm = new JButton("Create possible answers");
		JButton create = new JButton("Create Simple Choice");
		
		JTextField[] posAns = new JTextField[10];
		
		/*Autoscrolling through the panel*/
		/*MouseMotionListener doScrollRectToVisible = new MouseMotionAdapter() {
		     public void mouseDragged(MouseEvent e) {
		        Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
		        ((JPanel)e.getSource()).scrollRectToVisible(r);
		    }
		 };
		
		this.card2.addMouseMotionListener(doScrollRectToVisible);
		this.card2.setAutoscrolls(true);*/
		
		confirm.addActionListener(
				e ->{
					if (campoNumAns.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter a number for the possible answers");
					}
					else{
						try{
							/*If the button is clicked a second time, we have to remove the existing TextFields*/
							for (int k=0; k<10; k++){
								if(posAns[k] != null){
									this.card2.remove(posAns[k]);
								}
							}
							
							int numAns = Integer.parseInt(campoNumAns.getText());
							if (numAns < 1 || numAns > 10){ /*We choose arbitrarily the top of 10 possible answers*/
				 					throw new NumberFormatException("It's necessary to enter a valid number for the possible answers (>0 and <10)");
				 					}
				 			int i = 0;
				 			posAns[i] = new JTextField(15); /*First JTextField, at least this one*/
				 			this.card2.add(posAns[i]);
				 			layout.putConstraint(SpringLayout.WEST, posAns[i], frames, SpringLayout.WEST, this);
				 			layout.putConstraint(SpringLayout.NORTH, posAns[i], frames-10, SpringLayout.SOUTH, confirm);
				 			for(i=1; i<numAns; i++){
				 					posAns[i] = new JTextField(15);
				 					this.card2.add(posAns[i]);
				 					layout.putConstraint(SpringLayout.WEST, posAns[i], frames, SpringLayout.WEST, this);
				 					layout.putConstraint(SpringLayout.NORTH, posAns[i], frames2, SpringLayout.SOUTH, posAns[i-1]);
				 			}
				 						
				 			this.card2.add(etiquetaCorrect);
				 			this.card2.add(campoCorrect);
				 			this.card2.add(create);
				 						
				 			layout.putConstraint(SpringLayout.WEST, etiquetaCorrect, frames, SpringLayout.WEST, this);
				 			layout.putConstraint(SpringLayout.NORTH, etiquetaCorrect, frames, SpringLayout.SOUTH, posAns[i-1]);
				 			layout.putConstraint(SpringLayout.WEST, campoCorrect, frames2, SpringLayout.EAST, etiquetaCorrect);
				 			layout.putConstraint(SpringLayout.NORTH, campoCorrect, frames, SpringLayout.SOUTH, posAns[i-1]);
				 			layout.putConstraint(SpringLayout.WEST, create, frames, SpringLayout.WEST, this);
				 			layout.putConstraint(SpringLayout.NORTH, create, frames, SpringLayout.SOUTH, etiquetaCorrect);
				 			
				 			this.validate();
						}
						catch (NumberFormatException errNum){
							JOptionPane.showMessageDialog(null, errNum);
				 		}
						
				 	}
				 			
				 }
				 				
				 				
				 );	
		
		
		create.addActionListener(
				e ->{
					if (campoState.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter a statement for the question");
					}
					else if (campoWeight.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter the points(weight) of the question");
					}
					else if (campoCorrect.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter an answer for the question");
					}
					else{
						boolean random = false;
						if (randAnsOr.isSelected()){
							random = true;
						}
						
						try{
							boolean noEmpty = true;
							double weight = Double.parseDouble(campoWeight.getText().replace(",",".")); /*If they write 30,5 instead of 30.5*/
							Integer correct = Integer.parseInt(campoCorrect.getText());
							correct -= 1;
							String statement = campoState.getText();
							String answer = posAns[correct].getText();
							
							ArrayList<String> posAnswers = new ArrayList<String>();
							int t=0;
							int numAns = Integer.parseInt(campoNumAns.getText());
							while (t<numAns){
								String ans = posAns[t].getText();
								if (ans.equals("")){
									noEmpty = false;
									break;
								}
								else{
									posAnswers.add(ans);
								}
								t++;
							}
							
							if ((answer.equals("") == false) && (noEmpty == true)){
								SimpleChoice ques = new SimpleChoice(exe, weight, statement, random, answer);
								boolean nice = true;
								for(String possible: posAnswers){
									if(ques.addPossibleAnswer(possible) == false){
										nice = false;
										break;
									}
								}
								if(nice == true){
									JOptionPane.showMessageDialog(null, "Question SUCCESFULLY created and stored in " + exe.getName() + "\nQuestion statement: "
											+ ques.getQuestionText() + " Question answer: " + ques.getCorrectAnswer() + "\nQuestion possible answers: "
											+ ques.getPossibleAnswers().toString());
									
									/*We clean the JTextFields*/
									campoState.setText("");
									campoWeight.setText("");
									randAnsOr.setSelected(false);
									campoCorrect.setText("");
									for (int j=0; j<10; j++){
										if (posAns[j] != null){
											posAns[j].setText("");
										}
									}
								}
								else{
									exe.removeQuestion(ques);
									JOptionPane.showMessageDialog(null, "Don't repeat possible answers");
								}
							}
							else{
								if ((answer.equals("") == true)){
									JOptionPane.showMessageDialog(null, "The correct answer selected does not exists");
								}
								else{
									JOptionPane.showMessageDialog(null, "Don't leave empty spaces in the possible answers text fields");
								}
							}
							
						}
						catch (NumberFormatException err){
							JOptionPane.showMessageDialog(null, err);
						}
						catch (NullPointerException errAns){
							errAns.printStackTrace();
							JOptionPane.showMessageDialog(null, "There was a problem with the correct number answer");
						}
						catch (ArrayIndexOutOfBoundsException errAns){
							JOptionPane.showMessageDialog(null, errAns);
						}
					
					}
				}
				
				);
		
		
		this.card2.add(etiquetaState);
		this.card2.add(campoState);
		this.card2.add(etiquetaWeight);
		this.card2.add(campoWeight);
		this.card2.add(randAnsOr);
		this.card2.add(etiquetaNumAns);
		this.card2.add(campoNumAns);
		this.card2.add(confirm);
		
		
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
		layout.putConstraint(SpringLayout.WEST, confirm, frames, SpringLayout.EAST, campoNumAns);
		layout.putConstraint(SpringLayout.NORTH, confirm, frames-2, SpringLayout.SOUTH, randAnsOr);

	}
	
	public void buildPanel3(Exercise exe) {
		SpringLayout layout = new SpringLayout();
		this.card3.setLayout(layout);
		JLabel etiquetaState = new JLabel("Question statement: ");
		final JTextField campoState = new JTextField(30);
		JLabel etiquetaWeight = new JLabel("Weight (points) of the question: ");
		final JTextField campoWeight = new JTextField(4);
		JCheckBox randAnsOr = new JCheckBox("Random Order of the possible answers");
		JLabel etiquetaCorrect = new JLabel("Correct answer (True or False): ");
		final JTextField campoCorrect = new JTextField(3);
		JButton create = new JButton("Create True/False");
		
		create.addActionListener(
				e ->{
					if (campoState.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter a statement for the question");
					}
					else if (campoWeight.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter the points(weight) of the question");
					}
					else if (campoCorrect.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter an answer for the question");
					}
					else if ((campoCorrect.getText().equalsIgnoreCase("true")) || (campoCorrect.getText().equalsIgnoreCase("false"))){ /*The answer must be true or false*/
						
						try{
							boolean random = false;
							if (randAnsOr.isSelected()){
								random = true;
							}
							
							double weight = Double.parseDouble(campoWeight.getText().replace(",",".")); /*If they write 30,5 instead of 30.5*/
							TrueFalse ques = new TrueFalse(exe, weight, campoState.getText(), random, campoCorrect.getText());
							JOptionPane.showMessageDialog(null, "Question SUCCESFULLY created and stored in " + exe.getName() + "\nQuestion statement: "
									+ ques.getQuestionText() + " Question answer: " + ques.getCorrectAnswer());
							
							/*We clean the JTextFields*/
							campoState.setText("");
							campoWeight.setText("");
							randAnsOr.setSelected(false);
							campoCorrect.setText("");
							
						}
						catch (NumberFormatException err){
							JOptionPane.showMessageDialog(null, err);
						}
						
					}
					else{
						JOptionPane.showMessageDialog(null, "The correct answer must be wether true or false");
					}
				}
				
				);
		
		
		this.card3.add(etiquetaState);
		this.card3.add(campoState);
		this.card3.add(etiquetaWeight);
		this.card3.add(campoWeight);
		this.card3.add(randAnsOr);
		this.card3.add(etiquetaCorrect);
		this.card3.add(campoCorrect);
		this.card3.add(create);
		
		
		layout.putConstraint(SpringLayout.WEST, etiquetaState, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaState, frames*4, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, campoState, frames2, SpringLayout.EAST, etiquetaState);
		layout.putConstraint(SpringLayout.NORTH, campoState, frames*4, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, etiquetaWeight, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaWeight, frames, SpringLayout.SOUTH, etiquetaState);
		layout.putConstraint(SpringLayout.WEST, campoWeight, frames2, SpringLayout.EAST, etiquetaWeight);
		layout.putConstraint(SpringLayout.NORTH, campoWeight, frames, SpringLayout.SOUTH, etiquetaState);
		layout.putConstraint(SpringLayout.WEST, randAnsOr, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, randAnsOr, frames, SpringLayout.SOUTH, etiquetaWeight);
		layout.putConstraint(SpringLayout.WEST, etiquetaCorrect, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaCorrect, frames, SpringLayout.SOUTH, randAnsOr);
		layout.putConstraint(SpringLayout.WEST, campoCorrect, frames2, SpringLayout.EAST, etiquetaCorrect);
		layout.putConstraint(SpringLayout.NORTH, campoCorrect, frames, SpringLayout.SOUTH, randAnsOr);
		layout.putConstraint(SpringLayout.WEST, create, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, create, frames*2, SpringLayout.SOUTH, campoCorrect);
		
	}
	
	public void buildPanel4(Exercise exe) {
		SpringLayout layout = new SpringLayout();
		this.card4.setLayout(layout);
		JLabel etiquetaState = new JLabel("Question statement: ");
		final JTextField campoState = new JTextField(30);
		JLabel etiquetaWeight = new JLabel("Weight (points) of the question: ");
		final JTextField campoWeight = new JTextField(5);
		JCheckBox randAnsOr = new JCheckBox("Random Order of the possible answers");
		JLabel etiquetaNumAns = new JLabel("Number of possible answers: "); /*max 10*/
		final JTextField campoNumAns = new JTextField(2);
		JLabel etiquetaCorrect = new JLabel("Correct number of the answers(the first one is number 1): ");
		final JTextField campoCorrect = new JTextField(4);
		JLabel etiquetaExample = new JLabel("Example: 4 possible answers, correct ones are written on 2,3 and 7, write 2,3,7");
		etiquetaExample.setFont(new Font("Serif", Font.PLAIN, 12));
		JButton confirm = new JButton("Create possible answers");
		JButton create = new JButton("Create Multiple Choice");
		
		JTextField[] posAns = new JTextField[10];
		
		/*Autoscrolling through the panel*/
		/*MouseMotionListener doScrollRectToVisible = new MouseMotionAdapter() {
		     public void mouseDragged(MouseEvent e) {
		        Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
		        ((JPanel)e.getSource()).scrollRectToVisible(r);
		    }
		 };
		
		this.card2.addMouseMotionListener(doScrollRectToVisible);
		this.card2.setAutoscrolls(true);*/
		
		confirm.addActionListener( //Añadir que si ya le habías dado antes, te borre las que había
				e ->{
					if (campoNumAns.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter a number for the possible answers");
					}
					else{
						try{
							/*If the button is clicked a second time, we have to remove the existing TextFields*/
							for (int k=0; k<10; k++){
								if(posAns[k] != null){
									this.card4.remove(posAns[k]);
								}
							}
							
							int numAns = Integer.parseInt(campoNumAns.getText());
							if (numAns < 1 || numAns > 10){ /*We choose arbitrarily the top of 10 possible answers*/
				 					throw new NumberFormatException("It's necessary to enter a valid number for the possible answers (>0 and <10)");
				 					}
				 			int i = 0;
				 			posAns[i] = new JTextField(15); /*First JTextField, at least this one*/
				 			this.card4.add(posAns[i]);
				 			layout.putConstraint(SpringLayout.WEST, posAns[i], frames, SpringLayout.WEST, this);
				 			layout.putConstraint(SpringLayout.NORTH, posAns[i], frames-10, SpringLayout.SOUTH, confirm);
				 			for(i=1; i<numAns; i++){
				 					posAns[i] = new JTextField(15);
				 					this.card4.add(posAns[i]);
				 					layout.putConstraint(SpringLayout.WEST, posAns[i], frames, SpringLayout.WEST, this);
				 					layout.putConstraint(SpringLayout.NORTH, posAns[i], frames2, SpringLayout.SOUTH, posAns[i-1]);
				 			}
				 						
				 			this.card4.add(etiquetaCorrect);
				 			this.card4.add(campoCorrect);
				 			this.card4.add(etiquetaExample);
				 			this.card4.add(create);
				 						
				 			layout.putConstraint(SpringLayout.WEST, etiquetaCorrect, frames, SpringLayout.WEST, this);
				 			layout.putConstraint(SpringLayout.NORTH, etiquetaCorrect, frames, SpringLayout.SOUTH, posAns[i-1]);
				 			layout.putConstraint(SpringLayout.WEST, campoCorrect, frames2, SpringLayout.EAST, etiquetaCorrect);
				 			layout.putConstraint(SpringLayout.NORTH, campoCorrect, frames, SpringLayout.SOUTH, posAns[i-1]);
				 			layout.putConstraint(SpringLayout.WEST, etiquetaExample, frames, SpringLayout.WEST, this);
				 			layout.putConstraint(SpringLayout.NORTH, etiquetaExample, frames2, SpringLayout.SOUTH, etiquetaCorrect);
				 			layout.putConstraint(SpringLayout.WEST, create, frames, SpringLayout.WEST, this);
				 			layout.putConstraint(SpringLayout.NORTH, create, frames, SpringLayout.SOUTH, etiquetaExample);
				 			
				 			this.validate();
						}
						catch (NumberFormatException errNum){
							JOptionPane.showMessageDialog(null, errNum);
				 		}
						
				 	}
				 			
				 }
				 				
				 				
				 );	
		
		create.addActionListener(
				e ->{
					if (campoState.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter a statement for the question");
					}
					else if (campoWeight.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter the points(weight) of the question");
					}
					else if (campoCorrect.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter an answer/answers for the question");
					}
					else{
						try{
							boolean flag = true;
							String ans = campoCorrect.getText();
							StringTokenizer multiTokenizer = new StringTokenizer(ans, ", ");
							ArrayList<String> answers = new ArrayList<String>();
							while (multiTokenizer.hasMoreTokens()){
								int pos = Integer.parseInt(multiTokenizer.nextToken());
							    String answer = posAns[pos-1].getText();
							    answers.add(answer);
							    if (answer.equals("")){
							    	flag = false;
							    }
							}
							if (flag == false){
						    	JOptionPane.showMessageDialog(null, "One of the correct answers selected does not exists");
							}

							else{
								boolean noEmpty = true;
								int max = Integer.parseInt(campoNumAns.getText());
								if (max == answers.size()){
							    	int allCorrect = JOptionPane.showConfirmDialog(null, "Do you really want a question with all the possible answers correct?", "Confirmation", JOptionPane.YES_NO_OPTION);
							    	if (allCorrect == JOptionPane.NO_OPTION){
										throw new MyException("ALL CORRECT");
									}
								}
								
								boolean random = false;
								if (randAnsOr.isSelected()){
									random = true;
								}
								double weight = Double.parseDouble(campoWeight.getText().replace(",",".")); /*If they write 30,5 instead of 30.5*/
								
								ArrayList<String> posAnswers = new ArrayList<String>();
								int t=0;
								int numAns = max;
								while (t<numAns){
									String answer = posAns[t].getText();
									if (answer.equals("")){
										noEmpty = false;
										break;
									}
									else{
										posAnswers.add(answer);
									}
									t++;
								}
								
								if (noEmpty == true){
									MultipleChoice ques = new MultipleChoice(exe, weight, campoState.getText(), random, answers);
									
									boolean nice = true;
									for(String possible: posAnswers){
										if(ques.addPossibleAnswer(possible) == false){
											nice = false;
											break;
										}
										else{
											
										}
									}
									if(nice == true){
										JOptionPane.showMessageDialog(null, "Question SUCCESFULLY created and stored in " + exe.getName() + "\nQuestion statement: "
												+ ques.getQuestionText() + " Question answers: " + ques.getCorrectAnswers() +"\nQuestion possible answers: "
														+ ques.getPossibleAnswers().toString());
										
										/*We clean the JTextFields*/
										campoState.setText("");
										campoWeight.setText("");
										randAnsOr.setSelected(false);
										campoCorrect.setText("");
										for (int j=0; j<10; j++){
											if(posAns[j] != null){
												posAns[j].setText("");
											}
										}
									}
									else{
										exe.removeQuestion(ques);
										JOptionPane.showMessageDialog(null, "Don't repeat possible answers");
									}
									
								}
								else{
									JOptionPane.showMessageDialog(null, "Don't leave empty spaces in the possible answers text fields");
								}
								
							}
							
							
						}
						catch (NumberFormatException err){
							JOptionPane.showMessageDialog(null, err);
						}
						catch (NullPointerException errAns){
							errAns.printStackTrace();
							JOptionPane.showMessageDialog(null, "There was a problem with the correct number answers");
						}
						catch (ArrayIndexOutOfBoundsException errAns){
							JOptionPane.showMessageDialog(null, errAns);
						}
						catch (MyException allCorrect){
							
						}
						
					
					}
				}
				
				);
		
		
		this.card4.add(etiquetaState);
		this.card4.add(campoState);
		this.card4.add(etiquetaWeight);
		this.card4.add(campoWeight);
		this.card4.add(randAnsOr);
		this.card4.add(etiquetaNumAns);
		this.card4.add(campoNumAns);
		this.card4.add(confirm);
		
		
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
		layout.putConstraint(SpringLayout.WEST, confirm, frames, SpringLayout.EAST, campoNumAns);
		layout.putConstraint(SpringLayout.NORTH, confirm, frames-2, SpringLayout.SOUTH, randAnsOr);
		
	}
	
	
		
	public QuestionCreator(Exercise exe) { //AQUI METEMOS COMO PARAM DE ENTRADA EXE
		
		// Create the panel that contains the cards		
		this.buildPanel1(exe);
		this.buildPanel2(exe);
		this.buildPanel3(exe);
		this.buildPanel4(exe);
		
		/*JScrollPane scroll = new JScrollPane(this.card4);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(30, 30, 350, 650);
		this.card4.setPreferredSize(new Dimension(350, 650));*/
		
		
		cards = new JPanel(new CardLayout());
		cards.add(card1, TEXTPANEL); 
		cards.add(card2, SIMPLEPANEL);
		cards.add(card3, TFPANEL);
		cards.add(card4, MULTPANEL);
			
		JPanel comboBoxPane = new JPanel(); //FlowLayout is used by default
		String comboBoxItems[] = { TEXTPANEL, TFPANEL, SIMPLEPANEL, MULTPANEL }; 

		JComboBox<String> cb = new JComboBox<String>(comboBoxItems);   // Controls which card is shown

		cb.setEditable(false); 		       // Combo box used just for selection
		cb.addItemListener(
				e ->{
					CardLayout cl = (CardLayout)(cards.getLayout());  // Get cards layout
					 cl.show(cards, (String)e.getItem());   	// Show the card that corresponds to the
					 //cards.revalidate();     						// id chosen in the combo box.
				}
				
				);
		
		comboBoxPane.add(cb); 		       // Add combo box to father panel
		setLayout(new BorderLayout());
		add(comboBoxPane, BorderLayout.NORTH);    // Show the combo box
		add(cards, BorderLayout.CENTER); 	       // Show cards panel
		
		//this.add(pane);
		//this.validate();
		this.setVisible(true);
	}

}
