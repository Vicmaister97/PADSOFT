package gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import exercises.*;

public class SimpleEditor extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int frames = 30;
	private static int frames2 = 10;

	public SimpleEditor(SimpleChoice ques){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		//this.setBackground(Color.LIGHT_GRAY);
		JLabel etiquetaWeight = new JLabel(ques.getWeight() + " points");
		//etiquetaWeight.setFont(new Font("Calibri", Font.BOLD, 14));
		JLabel etiquetaState = new JLabel(ques.getQuestionText());
		//etiquetaState.setFont(new Font("Calibri", Font.ITALIC, 14));
		JLabel etiquetaAns = new JLabel("Answer: " + ques.getCorrectAnswer());
		//etiquetaAns.setFont(new Font("Calibri", Font.PLAIN, 11));
		JLabel etiquetaRand = new JLabel("Random order: " + ques.isRandomOrder());

		JLabel etiquetaNWeight = new JLabel("New question weight: ");
		JTextField campoNWeight = new JTextField(4);
		JLabel etiquetaNState = new JLabel("New question statement: ");
		JTextField campoNState = new JTextField(30);
		JLabel etiquetaNAns = new JLabel("New question answer: ");
		JTextField campoNAns = new JTextField(20);
		JCheckBox randAnsOr = new JCheckBox("New Random Order of the possible answers");
		JLabel etiquetaNumAns = new JLabel("Number of possible answers: "); /*max 10*/
		final JTextField campoNumAns = new JTextField(2);
		JLabel etiquetaCorrect = new JLabel("Correct number of the answer(the first one is number 1): ");
		final JTextField campoCorrect = new JTextField(2);
		JButton confirm = new JButton("Create possible answers");
		JButton create = new JButton("Save Changes");
		
		
		create.addActionListener(
				e ->{
					if (campoNState.getText().trim().isEmpty() == false){
						if ((ques.setQuestionText(campoNState.getText())) == false){
							JOptionPane.showMessageDialog(null, "No accepted new statement");
						}
						
					}
					else if (campoNAns.getText().trim().isEmpty() == false){
						if ((ques.setAnswer(campoNAns.getText())) == false){
							JOptionPane.showMessageDialog(null, "No accepted new answer");
						}
					
					}
					else if (campoNWeight.getText().trim().isEmpty()){
						try{
							double weight = Double.parseDouble(campoNWeight.getText().replace(",",".")); /*If they write 30,5 instead of 30.5*/
							
							if ((ques.setWeight(weight)) == false){
								JOptionPane.showMessageDialog(null, "No accepted new weight");
							}
							
							/*We clean the JTextFields*/
							campoNState.setText("");
							campoNAns.setText("");
							campoNWeight.setText("");
							
						}
						catch (NumberFormatException errNum){
							JOptionPane.showMessageDialog(null, errNum);
				 		}
											
					}
					
				}
				
				);
		
		this.add(etiquetaWeight);
		this.add(etiquetaState);
		this.add(etiquetaAns);
		this.add(etiquetaRand);
		this.add(etiquetaNWeight);
		this.add(campoNWeight);
		this.add(etiquetaNState);
		this.add(campoNState);
		this.add(etiquetaNAns);
		this.add(campoNAns);
		this.add(create);

		
		layout.putConstraint(SpringLayout.WEST, etiquetaWeight, frames*6, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaWeight, frames*4, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, etiquetaState, frames*6, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaState, frames, SpringLayout.SOUTH, etiquetaWeight);
		layout.putConstraint(SpringLayout.WEST, etiquetaAns, frames*6, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaAns, frames, SpringLayout.SOUTH, etiquetaState);
		layout.putConstraint(SpringLayout.WEST, etiquetaRand, frames*6, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaRand, frames, SpringLayout.SOUTH, etiquetaAns);
		
		layout.putConstraint(SpringLayout.WEST, etiquetaNState, frames*6, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNState, frames*2, SpringLayout.SOUTH, etiquetaRand);
		layout.putConstraint(SpringLayout.WEST, campoNState, frames2, SpringLayout.EAST, etiquetaNState);
		layout.putConstraint(SpringLayout.NORTH, campoNState, frames, SpringLayout.NORTH, etiquetaRand);
		layout.putConstraint(SpringLayout.WEST, etiquetaNAns, frames*6, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNAns, frames, SpringLayout.SOUTH, etiquetaNState);
		layout.putConstraint(SpringLayout.WEST, campoNAns, frames2, SpringLayout.EAST, etiquetaNAns);
		layout.putConstraint(SpringLayout.NORTH, campoNAns, frames, SpringLayout.SOUTH, etiquetaNState);
		layout.putConstraint(SpringLayout.WEST, etiquetaNWeight, frames*6, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNWeight, frames, SpringLayout.SOUTH, etiquetaNAns);
		layout.putConstraint(SpringLayout.WEST, campoNWeight, frames2, SpringLayout.EAST, etiquetaNWeight);
		layout.putConstraint(SpringLayout.NORTH, campoNWeight, frames, SpringLayout.SOUTH, etiquetaNAns);
		layout.putConstraint(SpringLayout.WEST, create, frames*6, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, create, frames*2, SpringLayout.SOUTH, etiquetaNWeight);
		
		//SET SIZE??
		this.setVisible(true);
		
	}
}