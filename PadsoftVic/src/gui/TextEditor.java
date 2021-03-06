package gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import exercises.*;

public class TextEditor extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int frames = 30;
	private static int frames2 = 10;

	public TextEditor(TextAnswer ques){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		//this.setBackground(Color.LIGHT_GRAY);
		JLabel etiquetaWeight = new JLabel("Weight: " + ques.getWeight() + " points");
		etiquetaWeight.setFont(new Font("Calibri", Font.PLAIN, 16));
		JLabel etiquetaState = new JLabel("Statement: " + ques.getQuestionText());
		etiquetaState.setFont(new Font("Calibri", Font.PLAIN, 16));
		JLabel etiquetaAns = new JLabel("Answer: " + ques.getAnswer());
		etiquetaAns.setFont(new Font("Calibri", Font.BOLD, 16));
		JLabel etiquetaNWeight = new JLabel("New question weight: ");
		JTextField campoNWeight = new JTextField(4);
		JLabel etiquetaNState = new JLabel("New question statement: ");
		JTextField campoNState = new JTextField(30);
		JLabel etiquetaNAns = new JLabel("New question answer: ");
		JTextField campoNAns = new JTextField(20);
		JButton create = new JButton("Save Changes");
		
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
					if (campoNAns.getText().trim().isEmpty() == false){
						if ((ques.setAnswer(campoNAns.getText())) == false){
							JOptionPane.showMessageDialog(null, "No accepted new answer");
						}
						else{
							cont++;
							JOptionPane.showMessageDialog(null, "ACCEPTED new answer: " + ques.getAnswer());
							campoNAns.setText("");
							etiquetaAns.setText("Answer: " + ques.getAnswer());
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
					
					JOptionPane.showMessageDialog(null, "Question saved with "+cont+" changes");
					this.validate();
				}
				
				);
		
		
		this.add(etiquetaWeight);
		this.add(etiquetaState);
		this.add(etiquetaAns);
		this.add(etiquetaNWeight);
		this.add(campoNWeight);
		this.add(etiquetaNState);
		this.add(campoNState);
		this.add(etiquetaNAns);
		this.add(campoNAns);
		this.add(create);

		
		layout.putConstraint(SpringLayout.WEST, etiquetaWeight, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaWeight, frames, SpringLayout.SOUTH, etiquetaAns);
		layout.putConstraint(SpringLayout.WEST, etiquetaState, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaState, frames*3, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, etiquetaAns, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaAns, frames, SpringLayout.SOUTH, etiquetaState);
		
		layout.putConstraint(SpringLayout.WEST, etiquetaNState, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNState, frames*2, SpringLayout.SOUTH, etiquetaWeight);
		layout.putConstraint(SpringLayout.WEST, campoNState, frames2, SpringLayout.EAST, etiquetaNState);
		layout.putConstraint(SpringLayout.NORTH, campoNState, frames*2, SpringLayout.SOUTH, etiquetaWeight);
		layout.putConstraint(SpringLayout.WEST, etiquetaNAns, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNAns, frames, SpringLayout.SOUTH, etiquetaNState);
		layout.putConstraint(SpringLayout.WEST, campoNAns, frames2, SpringLayout.EAST, etiquetaNAns);
		layout.putConstraint(SpringLayout.NORTH, campoNAns, frames, SpringLayout.SOUTH, etiquetaNState);
		layout.putConstraint(SpringLayout.WEST, etiquetaNWeight, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNWeight, frames, SpringLayout.SOUTH, etiquetaNAns);
		layout.putConstraint(SpringLayout.WEST, campoNWeight, frames2, SpringLayout.EAST, etiquetaNWeight);
		layout.putConstraint(SpringLayout.NORTH, campoNWeight, frames, SpringLayout.SOUTH, etiquetaNAns);
		layout.putConstraint(SpringLayout.WEST, create, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, create, frames*2, SpringLayout.SOUTH, etiquetaNWeight);
		
		//this.setSize(500, 800);
		this.setVisible(true);
	}
}