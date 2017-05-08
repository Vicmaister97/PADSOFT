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
    			TFView tFPane = new TFView((TrueFalse) q, student);
    			this.panels[i].add(tFPane, BorderLayout.CENTER);
    		}
    		else if (q instanceof SimpleChoice){
    			SCView sCPane = new SCView((SimpleChoice) q, student);
    			this.panels[i].add(sCPane, BorderLayout.CENTER);
    		}
    		else if (q instanceof MultipleChoice){
    			MCView mCPane = new MCView((MultipleChoice) q, student);
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
					
					JOptionPane.showMessageDialog(null, "Saving the exercise answer");
				
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
								JOptionPane.showMessageDialog(null, "Problem saving your answer" + ans);
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

			ArrayList<String> posAns = ques.getPossibleAnswers();
			int numq = posAns.size();
			JRadioButton jrbs[] = new JRadioButton[numq];
			
			ArrayList<Integer> nums = new ArrayList<Integer>();
	    	
	    	for(int i=0; i<numq; i++){
	    		nums.add(i);
	    	}
	    	
	    	int randomNum;
	    	String pos;
	    	for (int j=0; j<numq; j++){
	    		if(ques.isRandomOrder() == true){
	    			while(true){
	    				randomNum = ThreadLocalRandom.current().nextInt(0, numq);
	    				if (nums.get(randomNum) != -1){
	    					pos = posAns.get(randomNum);
	    					nums.set(randomNum, -1);
	    					break;
	    					}
	    			}
	    		}
	    		else{
	    			pos = posAns.get(j);
	    		}
	    		
	    		jrbs[j] = new JRadioButton(pos);
			
    		}
			
	    	ButtonGroup bg = new ButtonGroup();
			
			for (int i=0; i<numq; i++) {
				bg.add(jrbs[i]);
				this.add(jrbs[i]);
				if (i==0){
					layout.putConstraint(SpringLayout.WEST, jrbs[i], frames, SpringLayout.WEST, this);
					layout.putConstraint(SpringLayout.NORTH, jrbs[i], frames, SpringLayout.SOUTH, etiquetaWeight);
				}
				else{
					layout.putConstraint(SpringLayout.WEST, jrbs[i], frames, SpringLayout.WEST, this);
					layout.putConstraint(SpringLayout.NORTH, jrbs[i], frames, SpringLayout.SOUTH, jrbs[i-1]);
				}
			}

			JButton create = new JButton("Save Changes");
			
			create.addActionListener(
					e ->{
						String ans = "";
						for (JRadioButton b : jrbs) {
							if (b.isSelected()) {
								ans = b.getText();
							}
						}
						if (ans != ""){
							answer = ques.solveQuestion(student, ans);
							if (answer != null){
								JOptionPane.showMessageDialog(null, "Question saved with answer: " + ans);
							}
							else{
								JOptionPane.showMessageDialog(null, "Problem saving your answer" + ans);
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "There is no selected answer for the question");
						}
					}
					
					);
			
			this.add(etiquetaWeight);
			this.add(etiquetaState);
			this.add(create);

			
			layout.putConstraint(SpringLayout.WEST, etiquetaWeight, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, etiquetaWeight, frames, SpringLayout.SOUTH, etiquetaState);
			layout.putConstraint(SpringLayout.WEST, etiquetaState, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, etiquetaState, frames*3, SpringLayout.NORTH, this);
			
			layout.putConstraint(SpringLayout.WEST, create, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.SOUTH, create, -frames*3, SpringLayout.SOUTH, this);
			
			//this.setSize(500, 800);
			this.setVisible(true);
		}
	}
	
	class SCView extends JPanel{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			int frames = 30;
			int frames2 = 10;
			int framesB = 50;
			public AnswerQuestion answer;

			public SCView(SimpleChoice ques, Student student){
				SpringLayout layout = new SpringLayout();
				this.setLayout(layout);
				//this.setBackground(Color.LIGHT_GRAY);
				JLabel etiquetaWeight = new JLabel("Weight: " + ques.getWeight() + " points");
				etiquetaWeight.setFont(new Font("Serif", Font.PLAIN, 16));
				JLabel etiquetaState = new JLabel("Statement: " + ques.getQuestionText());
				etiquetaState.setFont(new Font("Serif", Font.PLAIN, 16));

				ArrayList<String> posAns = ques.getPossibleAnswers();
				int numq = posAns.size();
				JRadioButton jrbs[] = new JRadioButton[numq];
				
				ArrayList<Integer> nums = new ArrayList<Integer>();
		    	
		    	for(int i=0; i<numq; i++){
		    		nums.add(i);
		    	}
		    	
		    	int randomNum;
		    	String pos;
		    	for (int j=0; j<numq; j++){
		    		if(ques.isRandomOrder() == true){
		    			while(true){
		    				randomNum = ThreadLocalRandom.current().nextInt(0, numq);
		    				if (nums.get(randomNum) != -1){
		    					pos = posAns.get(randomNum);
		    					nums.set(randomNum, -1);
		    					break;
		    					}
		    			}
		    		}
		    		else{
		    			pos = posAns.get(j);
		    		}
		    		
		    		jrbs[j] = new JRadioButton(pos);
				
	    		}
				
		    	ButtonGroup bg = new ButtonGroup();
				
				for (int i=0; i<numq; i++) {
					bg.add(jrbs[i]);
					this.add(jrbs[i]);
					if (i==0){
						layout.putConstraint(SpringLayout.WEST, jrbs[i], frames, SpringLayout.WEST, this);
						layout.putConstraint(SpringLayout.NORTH, jrbs[i], frames, SpringLayout.SOUTH, etiquetaWeight);
					}
					else{
						layout.putConstraint(SpringLayout.WEST, jrbs[i], frames, SpringLayout.WEST, this);
						layout.putConstraint(SpringLayout.NORTH, jrbs[i], frames, SpringLayout.SOUTH, jrbs[i-1]);
					}
				}

				JButton create = new JButton("Save Changes");
				
				create.addActionListener(
						e ->{
							String ans = "";
							for (JRadioButton b : jrbs) {
								if (b.isSelected()) {
									ans = b.getText();
								}
							}							
							if (ans != ""){
								answer = ques.solveQuestion(student, ans);
								if (answer != null){
									JOptionPane.showMessageDialog(null, "Question saved with answer: " + ans);
								}
								else{
									JOptionPane.showMessageDialog(null, "Problem saving your answer" + ans);
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "There is no selected answer for the question");
							}
						}
						
						);
				
				this.add(etiquetaWeight);
				this.add(etiquetaState);
				this.add(create);

				
				layout.putConstraint(SpringLayout.WEST, etiquetaWeight, frames, SpringLayout.WEST, this);
				layout.putConstraint(SpringLayout.NORTH, etiquetaWeight, frames, SpringLayout.SOUTH, etiquetaState);
				layout.putConstraint(SpringLayout.WEST, etiquetaState, frames, SpringLayout.WEST, this);
				layout.putConstraint(SpringLayout.NORTH, etiquetaState, frames*3, SpringLayout.NORTH, this);
				
				layout.putConstraint(SpringLayout.WEST, create, frames, SpringLayout.WEST, this);
				layout.putConstraint(SpringLayout.SOUTH, create, -frames*3, SpringLayout.SOUTH, this);
				
				//this.setSize(500, 800);
				this.setVisible(true);
			}
		}
		
	class MCView extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int frames = 30;
		int frames2 = 10;
		int framesB = 50;
		public AnswerQuestion answer;

		public MCView(MultipleChoice ques, Student student){
			SpringLayout layout = new SpringLayout();
			this.setLayout(layout);
			//this.setBackground(Color.LIGHT_GRAY);
			JLabel etiquetaWeight = new JLabel("Weight: " + ques.getWeight() + " points");
			etiquetaWeight.setFont(new Font("Serif", Font.PLAIN, 16));
			JLabel etiquetaState = new JLabel("Statement: " + ques.getQuestionText());
			etiquetaState.setFont(new Font("Serif", Font.PLAIN, 16));

			ArrayList<String> posAns = ques.getPossibleAnswers();
			int numq = posAns.size();
			JCheckBox jrbs[] = new JCheckBox[numq];
			
			ArrayList<Integer> nums = new ArrayList<Integer>();
	    	
	    	for(int i=0; i<numq; i++){
	    		nums.add(i);
	    	}
	    	
	    	int randomNum;
	    	String pos;
	    	for (int j=0; j<numq; j++){
	    		if(ques.isRandomOrder() == true){
	    			while(true){
	    				randomNum = ThreadLocalRandom.current().nextInt(0, numq);
	    				if (nums.get(randomNum) != -1){
	    					pos = posAns.get(randomNum);
	    					nums.set(randomNum, -1);
	    					break;
	    					}
	    			}
	    		}
	    		else{
	    			pos = posAns.get(j);
	    		}
	    		
	    		jrbs[j] = new JCheckBox(pos);
			
    		}
			
	    	ButtonGroup bg = new ButtonGroup();
	    	
	    	JPanel checkbox = new JPanel(new GridLayout(5,1));
	    	checkbox.add(new JLabel("It's possible that there is more than 1 correct answer"));
	    	for (int t=0; t<numq; t++){
	    		checkbox.add(jrbs[t]);
	    	}

			JButton create = new JButton("Save Changes");
			
			create.addActionListener(
					e ->{
						ArrayList<String> ans = new ArrayList<String>();
						for (JCheckBox b : jrbs) {
							if (b.isSelected()) {
								ans.add(b.getText());
							}
						}							
						if (ans.size() != 0){
							answer = ques.solveQuestion(student, ans);
							if (answer != null){
								JOptionPane.showMessageDialog(null, "Question saved with answers: " + ans.toString());
							}
							else{
								JOptionPane.showMessageDialog(null, "Problem saving your answers" + ans.toString());
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "There is no selected answer for the question");
						}
					}
					
					);
			
			this.add(etiquetaWeight);
			this.add(etiquetaState);
			this.add(create);
			this.add(checkbox);

			
			layout.putConstraint(SpringLayout.WEST, etiquetaWeight, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, etiquetaWeight, frames, SpringLayout.SOUTH, etiquetaState);
			layout.putConstraint(SpringLayout.WEST, etiquetaState, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, etiquetaState, frames*3, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.WEST, checkbox, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.NORTH, checkbox, framesB, SpringLayout.NORTH, etiquetaWeight);
			
			layout.putConstraint(SpringLayout.WEST, create, frames, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.SOUTH, create, -frames*3, SpringLayout.SOUTH, this);
			
			//this.setSize(500, 800);
			this.setVisible(true);
		}
	}
		
	

