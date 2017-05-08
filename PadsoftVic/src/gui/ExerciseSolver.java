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
import java.util.concurrent.ThreadLocalRandom;

public class ExerciseSolver extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numq = 0;
    private JComboBox<String> combo;
    private CardLayout panelLayout = new CardLayout();
    private JPanel cards;
    private JPanel[] panels;
    				   
    				   
    private void buildPanels(Exercise exe, int numq, Student student) {
    	
    	ArrayList<Integer> nums = new ArrayList<Integer>();
    	
    	for(int i=0; i<numq; i++){
    		nums.add(i);
    	}
    	
    	this.panels = new JPanel[numq+1];
		this.panels[0] = new JPanel(new BorderLayout());
		ExerciseView exePrincipal = new ExerciseView(exe);
		this.panels[0].add(exePrincipal, BorderLayout.CENTER);
		JLabel title = new JLabel ("Exercise solver", JLabel.CENTER);
		title.setFont(new Font("Serif", Font.ITALIC, 18));
		this.panels[0].add(title, BorderLayout.NORTH);


    	int randomNum;
    	for (int i = 1; i<(numq+1); i++) {
    		this.panels[i] = new JPanel(new BorderLayout());
    		Question q;
    		
    		if(exe.isRandomOrder() == true){
    			while(true){
    				randomNum = ThreadLocalRandom.current().nextInt(0, numq);
    				if (nums.get(randomNum) != -1){
    					q = exe.getQuestions().get(randomNum);
    					nums.set(randomNum, -1);
    					break;
    					}
    			}
    		}
    		else{
    			q = exe.getQuestions().get(i-1);
    		}
    		
    		
    		if (q instanceof TextAnswer){
    			TextView textPane = new TextView((TextAnswer) q, student);
    			this.panels[i].add(textPane, BorderLayout.CENTER);
    		}
    		else if (q instanceof TrueFalse){
    			TFEditor tFPane = new TFEditor((TrueFalse) q);
    			this.panels[i].add(tFPane, BorderLayout.CENTER);
    		}
    		else if (q instanceof SimpleChoice){
    			SimpleEditor sCPane = new SimpleEditor((SimpleChoice) q);
    			this.panels[i].add(sCPane, BorderLayout.CENTER);
    		}
    		else if (q instanceof MultipleChoice){
    			MultiEditor mCPane = new MultiEditor((MultipleChoice) q);
    			this.panels[i].add(mCPane, BorderLayout.CENTER);
    		}
    		
    		JLabel quest = new JLabel ("Question "+i, JLabel.CENTER);
    		quest.setFont(new Font("Serif", Font.ITALIC, 18));
    		this.panels[i].add(quest, BorderLayout.NORTH);
    	}
    }    
    
    
    public ExerciseSolver(Exercise exe, Student student) { //PRECISA STUDENT
    	
    	numq = exe.getQuestions().size();
    	cards = new JPanel(panelLayout);
    	this.setLayout(new BorderLayout());
        
        this.buildPanels(exe, numq, student);
        
        for (int i = 0; i<numq+1; i++){
        	cards.add(panels[i], String.valueOf(i+1));
        }
        
        this.add(cards, BorderLayout.CENTER);
        
        JButton first 	 = new JButton("First");
        JButton last 	 = new JButton("Last");
        JButton previous = new JButton("Previous");
        JButton next     = new JButton("Next");
        JButton finish = new JButton("Finish");
        //AÑADIR SAVE CHANGES Y EXIT
        
        combo = new JComboBox<String>();
        
        for (int i = 0; i<numq+1; i++)
        	combo.addItem(String.valueOf(i));
        
        JPanel panel = new JPanel();
        panel.add(first);
        panel.add(last);
        panel.add(previous);
        panel.add(next);
        panel.add(combo);
        panel.add(finish);
        
        this.add(panel, BorderLayout.SOUTH);
        
        first.addActionListener(
        		a ->{
                    panelLayout.first (cards);
        		}
        		);
        last.addActionListener(
        		b ->{
                    panelLayout.last (cards);
        		}
        		);
        next.addActionListener(
        		c ->{
                    panelLayout.next (cards);
        		}
        		);
        previous.addActionListener(
        		d ->{
                    panelLayout.previous (cards);
        		}
        		);
        
        finish.addActionListener( //CAMBIAAAAAAAAAAAAAAAAAAAAAAAAR
				f ->{
					
					JOptionPane.showMessageDialog(null, "Going back to " + exe.getCourse().getName());
				
					CourseScreenStudent backCourse = new CourseScreenStudent(exe.getCourse());
					GeneralFrame.GFrame.remove(GeneralFrame.GFrame.getContentPane());
		    		GeneralFrame.GFrame.setContentPane(backCourse);
		    		GeneralFrame.GFrame.validate();
				}
				); 
        
        combo.addItemListener(
        		e ->{
        			String item = (String) e.getItem ();
        		        
        		    panelLayout.show (cards, item);        		
        		    }
        		);
        
        this.setVisible(true);

    }
    
}

class ExerciseView extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int frames = 50;
	Exercise exe;

	public ExerciseView(Exercise exe) {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);

		JLabel etiquetaName = new JLabel("Name of the exercise: " + exe.getName());
		JLabel etiquetaRele = new JLabel("Relevance on the global mark (percentage from 0 to 100): " + exe.getWeightE());
		JLabel etiquetaStart = new JLabel("Start day and time (dd-MM-yyyy HH:mm): " + exe.getIniDate());
		JLabel etiquetaEnd = new JLabel("Expiration day and time (dd-MM-yyyy HH:mm): " + exe.getEndDate());
		JLabel etiquetaPen = new JLabel("Penalty for answering incorrectly the questions: " + exe.getPenalisation());
		JLabel randQuesOr = new JLabel("Random Order of the questions: " + exe.isRandomOrder());
		
		this.add(etiquetaName);
		this.add(etiquetaRele);
		this.add(etiquetaStart);
		this.add(etiquetaEnd);
		this.add(etiquetaPen);
		this.add(randQuesOr);
		
		layout.putConstraint(SpringLayout.WEST, etiquetaName, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaName, frames*3-5, SpringLayout.NORTH, this);
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
		
		this.setVisible(true);
		}
	}
	
	
	class TextView extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int frames = 30;
		int frames2 = 10;
		int framesB = 50;
		public AnswerQuestion answer;

		public TextView(TextAnswer ques, Student student){
			SpringLayout layout = new SpringLayout();
			this.setLayout(layout);
			//this.setBackground(Color.LIGHT_GRAY);
			JLabel etiquetaWeight = new JLabel("Weight: " + ques.getWeight() + " points");
			etiquetaWeight.setFont(new Font("Calibri", Font.PLAIN, 16));
			JLabel etiquetaState = new JLabel("Statement: " + ques.getQuestionText());
			etiquetaState.setFont(new Font("Calibri", Font.PLAIN, 16));
			/*JLabel etiquetaAns = new JLabel("Answer: " + ques.getAnswer());
			etiquetaAns.setFont(new Font("Calibri", Font.BOLD, 16));*/
			
			JTextArea campoNAns = new JTextArea("",7,30);
			JButton create = new JButton("Save Answer");
			
			create.addActionListener(
					e ->{
						if (campoNAns.getText().trim().isEmpty() == false){
							String ans = campoNAns.getText();
							answer = ques.solveQuestion(student, ans);
							if (answer != null){
								JOptionPane.showMessageDialog(null, "Question saved with answer: " + ans);
								campoNAns.setText("");
							}
							else{
								JOptionPane.showMessageDialog(null, "ijewFCIJUEFJR: " + ans);
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "There is no answer for the question");
						}
					}
					
					);
			
			this.add(etiquetaWeight);
			this.add(etiquetaState);
			this.add(campoNAns);
			this.add(create);

			
			layout.putConstraint(SpringLayout.WEST, etiquetaWeight, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, etiquetaWeight, frames, SpringLayout.SOUTH, etiquetaState);
			layout.putConstraint(SpringLayout.WEST, etiquetaState, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, etiquetaState, frames*3, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.WEST, campoNAns, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, campoNAns, framesB, SpringLayout.SOUTH, etiquetaWeight);
			
			layout.putConstraint(SpringLayout.WEST, create, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, create, frames*2, SpringLayout.SOUTH, campoNAns);
			
			//this.setSize(500, 800);
			this.setVisible(true);
		}
	}
	
	class TFView extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int frames = 30;
		int frames2 = 10;
		int framesB = 50;
		public AnswerQuestion answer;

		public TFView(TrueFalse ques, Student student){
			SpringLayout layout = new SpringLayout();
			this.setLayout(layout);
			//this.setBackground(Color.LIGHT_GRAY);
			JLabel etiquetaWeight = new JLabel("Weight: " + ques.getWeight() + " points");
			etiquetaWeight.setFont(new Font("Serif", Font.PLAIN, 16));
			JLabel etiquetaState = new JLabel("Statement: " + ques.getQuestionText());
			etiquetaState.setFont(new Font("Serif", Font.PLAIN, 16));

			
			JLabel etiquetaNWeight = new JLabel("New question weight: ");
			JTextField campoNWeight = new JTextField(4);
			JLabel etiquetaNState = new JLabel("New question statement: ");
			JTextField campoNState = new JTextField(30);
			JLabel etiquetaNAns = new JLabel("New question answer (True or False): ");
			JTextField campoNAns = new JTextField(20);
			JCheckBox randAnsOr = new JCheckBox("New random order of the possible answers");

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
							if ((ques.setCorrectAnswer(campoNAns.getText())) == false){
								JOptionPane.showMessageDialog(null, "No accepted new answer");
							}
							else{
								cont++;
								JOptionPane.showMessageDialog(null, "ACCEPTED new answer: " + ques.getCorrectAnswer());
								campoNAns.setText("");
								etiquetaAns.setText("Answer: " + ques.getCorrectAnswer());
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
						
						if(ques.isRandomOrder() != rand){
							if (ques.changeOrder(rand) == false){
								JOptionPane.showMessageDialog(null, "No accepted new random order");
							}
							else{
								cont++;
								JOptionPane.showMessageDialog(null, "Random Order of the possible answers: "+rand);
								randAnsOr.setSelected(false);
								etiquetaRandOr.setText("Random Order of the possible answers: " + ques.isRandomOrder());
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
			this.add(etiquetaNAns);
			this.add(campoNAns);
			this.add(randAnsOr);
			this.add(create);

			
			layout.putConstraint(SpringLayout.WEST, etiquetaWeight, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, etiquetaWeight, frames, SpringLayout.SOUTH, etiquetaAns);
			layout.putConstraint(SpringLayout.WEST, etiquetaState, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, etiquetaState, frames*3, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.WEST, etiquetaAns, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, etiquetaAns, frames, SpringLayout.SOUTH, etiquetaState);
			layout.putConstraint(SpringLayout.WEST, etiquetaRandOr, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, etiquetaRandOr, frames, SpringLayout.SOUTH, etiquetaWeight);
			
			layout.putConstraint(SpringLayout.WEST, etiquetaNState, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, etiquetaNState, frames*2, SpringLayout.SOUTH, etiquetaRandOr);
			layout.putConstraint(SpringLayout.WEST, campoNState, frames2, SpringLayout.EAST, etiquetaNState);
			layout.putConstraint(SpringLayout.NORTH, campoNState, frames*2, SpringLayout.SOUTH, etiquetaRandOr);
			layout.putConstraint(SpringLayout.WEST, etiquetaNAns, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, etiquetaNAns, frames, SpringLayout.SOUTH, etiquetaNState);
			layout.putConstraint(SpringLayout.WEST, campoNAns, frames2, SpringLayout.EAST, etiquetaNAns);
			layout.putConstraint(SpringLayout.NORTH, campoNAns, frames, SpringLayout.SOUTH, etiquetaNState);
			layout.putConstraint(SpringLayout.WEST, etiquetaNWeight, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, etiquetaNWeight, frames, SpringLayout.SOUTH, etiquetaNAns);
			layout.putConstraint(SpringLayout.WEST, campoNWeight, frames2, SpringLayout.EAST, etiquetaNWeight);
			layout.putConstraint(SpringLayout.NORTH, campoNWeight, frames, SpringLayout.SOUTH, etiquetaNAns);
			layout.putConstraint(SpringLayout.WEST, randAnsOr, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, randAnsOr, frames, SpringLayout.SOUTH, etiquetaNWeight);
			
			layout.putConstraint(SpringLayout.WEST, create, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, create, frames*2, SpringLayout.SOUTH, randAnsOr);
			
			//this.setSize(500, 800);
			this.setVisible(true);
		}
	}

