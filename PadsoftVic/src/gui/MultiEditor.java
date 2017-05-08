package gui;

import java.awt.Font;
import java.awt.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import exercises.*;

public class MultiEditor extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int frames = 30;
	private static int frames2 = 10;

	public MultiEditor(MultipleChoice ques){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		//this.setBackground(Color.LIGHT_GRAY);
		JLabel etiquetaWeight = new JLabel("Weight: " + ques.getWeight() + " points");
		etiquetaWeight.setFont(new Font("Calibri", Font.PLAIN, 16));
		JLabel etiquetaState = new JLabel("Statement: " + ques.getQuestionText());
		etiquetaState.setFont(new Font("Calibri", Font.PLAIN, 16));
		JLabel etiquetaAns = new JLabel("Answers: " + ques.getCorrectAnswers().toString());
		etiquetaAns.setFont(new Font("Calibri", Font.BOLD, 16));
		JLabel etiquetaRandOr = new JLabel("Random Order of the possible answers: " + ques.isRandomOrder());
		etiquetaRandOr.setFont(new Font("Calibri", Font.PLAIN, 16));
		JLabel etiquetaPosAns = new JLabel("Possible answers of the question: " + ques.getPossibleAnswers().toString());

		
		JLabel etiquetaNWeight = new JLabel("New question weight: ");
		JTextField campoNWeight = new JTextField(4);
		JLabel etiquetaNState = new JLabel("New question statement: ");
		JTextField campoNState = new JTextField(30);
		JCheckBox randAnsOr = new JCheckBox("New random order of the possible answers");
		JLabel etiquetaNumAns = new JLabel("Number of new possible answers: "); /*max 10*/
		final JTextField campoNumAns = new JTextField(2);
		JLabel etiquetaCorrect = new JLabel("Correct number of the new answers(the first one is number 1): ");
		final JTextField campoCorrect = new JTextField(2);
		JLabel etiquetaExample = new JLabel("Example: 4 possible answers, correct ones are written on 2,3 and 7, write 2,3,7");
		etiquetaExample.setFont(new Font("Serif", Font.PLAIN, 12));

		JButton confirm = new JButton("Create new possible answers");
		JButton create = new JButton("Save Changes");
		
		JTextField[] posAns = new JTextField[10];
		
		confirm.addActionListener(
				e ->{
					if (campoNumAns.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "It's necessary to enter a number for the new possible answers");
					}
					else{
						try{
							/*If the button is clicked a second time, we have to remove the existing TextFields*/
							for (int k=0; k<10; k++){
								if(posAns[k] != null){
									this.remove(posAns[k]);
								}
							}
							
							int numAns = Integer.parseInt(campoNumAns.getText());
							if (numAns < 1 || numAns > 10){ /*We choose arbitrarily the top of 10 possible answers*/
				 					throw new NumberFormatException("It's necessary to enter a valid number for the new possible answers (>0 and <10)");
				 					}
				 			int i = 0;
				 			posAns[i] = new JTextField(15); /*First JTextField, at least this one*/
				 			this.add(posAns[i]);
				 			layout.putConstraint(SpringLayout.EAST, posAns[i], -frames*5, SpringLayout.EAST, this);
				 			layout.putConstraint(SpringLayout.NORTH, posAns[i], frames, SpringLayout.SOUTH, confirm);
				 			for(i=1; i<numAns; i++){
				 					posAns[i] = new JTextField(15);
				 					this.add(posAns[i]);
				 					layout.putConstraint(SpringLayout.EAST, posAns[i], -frames*5, SpringLayout.EAST, this);
				 					layout.putConstraint(SpringLayout.NORTH, posAns[i], frames2, SpringLayout.SOUTH, posAns[i-1]);
				 			}
				 						
				 			this.add(etiquetaCorrect);
				 			this.add(campoCorrect);
				 			this.add(etiquetaExample);
				 						
				 			layout.putConstraint(SpringLayout.EAST, etiquetaCorrect, -frames2, SpringLayout.WEST, campoCorrect);
				 			layout.putConstraint(SpringLayout.NORTH, etiquetaCorrect, frames, SpringLayout.SOUTH, posAns[i-1]);
				 			layout.putConstraint(SpringLayout.EAST, campoCorrect, -frames*5, SpringLayout.EAST, this);
				 			layout.putConstraint(SpringLayout.NORTH, campoCorrect, frames, SpringLayout.SOUTH, posAns[i-1]);
				 			layout.putConstraint(SpringLayout.EAST, etiquetaExample, -frames*5, SpringLayout.EAST, this);
				 			layout.putConstraint(SpringLayout.NORTH, etiquetaExample, frames, SpringLayout.SOUTH, campoCorrect);
		
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
					int cont = 0;
					if (campoNState.getText().trim().isEmpty() == false){
						if ((ques.setQuestionText(campoNState.getText())) == false){
							JOptionPane.showMessageDialog(null, "No accepted new statement");
						}
						else{
							cont++;
							JOptionPane.showMessageDialog(null, "ACCEPTED new statement: " + ques.getQuestionText());
							campoNState.setText("");
							etiquetaState.setText("Statement: " + ques.getQuestionText());
						}
						
					}
					
					if (campoNWeight.getText().trim().isEmpty() == false){
						try{
							double weight = Double.parseDouble(campoNWeight.getText().replace(",",".")); /*If they write 30,5 instead of 30.5*/
							
							if ((ques.setWeight(weight)) == false){
								JOptionPane.showMessageDialog(null, "No accepted new weight");
							}
							else{
								cont++;
								JOptionPane.showMessageDialog(null, "ACCEPTED new weight: " + ques.getWeight());
								campoNWeight.setText("");
								etiquetaWeight.setText("Weight: " + ques.getWeight() + " points");
							}
														
						}
						catch (NumberFormatException errNum){
							JOptionPane.showMessageDialog(null, errNum);
				 		}
											
					}
					boolean rand = false;
					if(randAnsOr.isSelected()){
						rand = true;
					}
					if (ques.isRandomOrder() != rand){ /*We are changing the random order value*/
						cont++;
						JOptionPane.showMessageDialog(null, "New possible answers order: "+rand);
						randAnsOr.setSelected(false);
						ques.changeOrder(rand);
						etiquetaRandOr.setText("Random Order of the possible answers: " + ques.isRandomOrder());

					}
					
					if(campoNumAns.getText().equals("") == false){
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
								
								ArrayList<String> posAnswers = new ArrayList<String>();
								int t=0;
								int numAns = Integer.parseInt(campoNumAns.getText());
								while (t<numAns){
									String anss = posAns[t].getText();
									if (anss.equals("")){
										noEmpty = false;
										break;
									}
									else{
										posAnswers.add(anss);
									}
									t++;
								}
								
								if (noEmpty == true){
									ArrayList<String> oldCorrect = ques.getCorrectAnswers();
									int p=0;
									while(p<oldCorrect.size()){
										ques.removeCorrectAnswer(oldCorrect.get(p));
									}
									
									//ques.setCorrectAnswer(""); /*In order to remove the already existing possible answers*/
									
									ArrayList<String>oldAns = ques.getPossibleAnswers();
									int i=0;
									while(i<ques.getPossibleAnswers().size()){
										ques.removePossibleAnswer(oldAns.get(i));
									}
									
									boolean nice = true;
									for(String possible: posAnswers){
										if(ques.addPossibleAnswer(possible) == false){
											cont++;
											nice = false;
											break;
										}
									}
									if(nice == true){
										//ques.setCorrectAnswer(answer);
										for (String newCorrect : answers){
											cont++;
											ques.addCorrectAnswer(newCorrect);
										}
										etiquetaAns.setText("Answers: " + ques.getCorrectAnswers().toString());
										etiquetaPosAns.setText("Possible answers of the question: " + ques.getPossibleAnswers().toString());
										JOptionPane.showMessageDialog(null, "ACCEPTED new possible answers and correct answer");
										
										campoNumAns.setText("");
										campoCorrect.setText("");
										for (int j=0; j<10; j++){
											if (posAns[j] != null){
												posAns[j].setText("");
											}
										}
									}
									else{									
										ArrayList<String>badAns = ques.getPossibleAnswers();
										int j=0;
										while(j<ques.getPossibleAnswers().size()){
											ques.removePossibleAnswer(badAns.remove(j));
										}
										for(String possible: oldAns){
											ques.addPossibleAnswer(possible);
										}
										for(String correct : oldCorrect){
											ques.addCorrectAnswer(correct);
										}
										
										JOptionPane.showMessageDialog(null, "Don't repeat possible answers");
									}
								}
								else{
									JOptionPane.showMessageDialog(null, "Don't leave empty spaces in the new possible answers text fields");
								}
							}
							
						}
						catch (NumberFormatException err){
							JOptionPane.showMessageDialog(null, err);
						}
						catch (NullPointerException errAns){
							errAns.printStackTrace();
							JOptionPane.showMessageDialog(null, "There was a problem with the new correct number answer");
						}
						catch (ArrayIndexOutOfBoundsException errAns){
							JOptionPane.showMessageDialog(null, errAns);
						}
					}
					
					
					JOptionPane.showMessageDialog(null, "Question saved with "+cont+" changes");
					this.validate();
				}
				
				);
		
		this.add(etiquetaWeight);
		this.add(etiquetaState);
		this.add(etiquetaAns);
		this.add(etiquetaRandOr);
		this.add(etiquetaNWeight);
		this.add(campoNWeight);
		this.add(etiquetaNState);
		this.add(campoNState);
		this.add(randAnsOr);
		this.add(etiquetaNumAns);
		this.add(campoNumAns);
		this.add(etiquetaPosAns);
		this.add(confirm);
		this.add(create);

		
		layout.putConstraint(SpringLayout.WEST, etiquetaWeight, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaWeight, frames, SpringLayout.SOUTH, etiquetaAns);
		layout.putConstraint(SpringLayout.WEST, etiquetaState, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaState, frames*3, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, etiquetaAns, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaAns, frames, SpringLayout.SOUTH, etiquetaState);
		layout.putConstraint(SpringLayout.WEST, etiquetaRandOr, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaRandOr, frames, SpringLayout.SOUTH, etiquetaWeight);
		layout.putConstraint(SpringLayout.WEST, etiquetaPosAns, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaPosAns, frames, SpringLayout.SOUTH, etiquetaRandOr);
		
		layout.putConstraint(SpringLayout.EAST, etiquetaNState, -frames2, SpringLayout.WEST, campoNState);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNState, (frames*3)+2, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, campoNState, -frames*4, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, campoNState, frames*3, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.EAST, etiquetaNWeight, -frames2, SpringLayout.WEST, campoNWeight);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNWeight, frames, SpringLayout.SOUTH, etiquetaNState);
		layout.putConstraint(SpringLayout.EAST, campoNWeight, -frames*2, SpringLayout.WEST, randAnsOr);
		layout.putConstraint(SpringLayout.NORTH, campoNWeight, frames, SpringLayout.SOUTH, etiquetaNState);
		layout.putConstraint(SpringLayout.EAST, randAnsOr, -frames*4, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, randAnsOr, frames, SpringLayout.SOUTH, etiquetaNState);
		
		layout.putConstraint(SpringLayout.EAST, etiquetaNumAns, -frames2, SpringLayout.WEST, campoNumAns);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNumAns, frames, SpringLayout.SOUTH, randAnsOr);
		layout.putConstraint(SpringLayout.EAST, campoNumAns, -frames2, SpringLayout.WEST, confirm);
		layout.putConstraint(SpringLayout.NORTH, campoNumAns, frames, SpringLayout.SOUTH, randAnsOr);
		layout.putConstraint(SpringLayout.EAST, confirm, -frames*4, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, confirm, frames, SpringLayout.SOUTH, randAnsOr);
		
		layout.putConstraint(SpringLayout.WEST, create, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, create, frames*2, SpringLayout.SOUTH, etiquetaPosAns);
		
		//this.setSize(500, 800);
		this.setVisible(true);
	}
}