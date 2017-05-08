package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import courseElements.Exercise;
import exercises.AnswerExercise;
import exercises.AnswerQuestion;
import exercises.MultipleChoice;
import exercises.Question;
import exercises.SimpleChoice;
import exercises.TextAnswer;
import exercises.TrueFalse;
import users.Student;

public class ExerciseCorrected extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numq = 0;
    private JComboBox<String> combo;
    private CardLayout panelLayout = new CardLayout();
    private JPanel cards;
    private JPanel[] panels;
    				   
    				   
    private void buildPanels(AnswerExercise exe) {
    	
    	this.panels = new JPanel[numq+1];
		this.panels[0] = new JPanel(new BorderLayout());
		ExerciseShow exePrincipal = new ExerciseShow(exe);
		this.panels[0].add(exePrincipal, BorderLayout.CENTER);
		JLabel title = new JLabel ("Exercise solution", JLabel.CENTER);
		title.setFont(new Font("Serif", Font.ITALIC, 18));
		this.panels[0].add(title, BorderLayout.NORTH);

    	for (int i = 1; i<(numq+1); i++) {
    		this.panels[i] = new JPanel(new BorderLayout());

    		Question q = exe.getExercise().getQuestions().get(i-1);
    		
    		if (q instanceof TextAnswer){
    			TextShow textPane = new TextShow(exe, (TextAnswer)q);
    			this.panels[i].add(textPane, BorderLayout.CENTER);
    		}
    		else if (q instanceof TrueFalse){
    			TrueShow tFPane = new TrueShow(exe, (TrueFalse)q);
    			this.panels[i].add(tFPane, BorderLayout.CENTER);
    		}
    		else if (q instanceof SimpleChoice){
    			SimpleShow sCPane = new SimpleShow(exe, (SimpleChoice)q);
    			this.panels[i].add(sCPane, BorderLayout.CENTER);
    		}
    		else if (q instanceof MultipleChoice){
    			MultiShow mCPane = new MultiShow(exe, (MultipleChoice)q);
    			this.panels[i].add(mCPane, BorderLayout.CENTER);
    		}
    		
    		JLabel quest = new JLabel ("Question "+i, JLabel.CENTER);
    		quest.setFont(new Font("Serif", Font.ITALIC, 18));
    		this.panels[i].add(quest, BorderLayout.NORTH);
    	}
    }    
	
    public ExerciseCorrected(AnswerExercise exe) { //PRECISA STUDENT
    	
    	numq = exe.getExercise().getQuestions().size();
    	cards = new JPanel(panelLayout);
    	this.setLayout(new BorderLayout());
        
        this.buildPanels(exe);
        
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
        
        finish.addActionListener(
				f ->{
					
					CourseScreenStudent backCourse = new CourseScreenStudent(exe.getExercise().getCourse());
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

class ExerciseShow extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int frames = 50;
	Exercise exe;

	public ExerciseShow(AnswerExercise exe) {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);

		JLabel etiquetaName = new JLabel("Name of the exercise: " + exe.getExercise().getName());
		JLabel etiquetaRele = new JLabel("Relevance on the global mark (percentage from 0 to 100): " + exe.getExercise().getWeightE());
		JLabel etiquetaStart = new JLabel("Start day and time (dd-MM-yyyy HH:mm): " + exe.getExercise().getIniDate());
		JLabel etiquetaEnd = new JLabel("Expiration day and time (dd-MM-yyyy HH:mm): " + exe.getExercise().getEndDate());
		JLabel etiquetaPen = new JLabel("Penalty for answering incorrectly the questions: " + exe.getExercise().getPenalisation());
		JLabel randQuesOr = new JLabel("Random Order of the questions: " + exe.getExercise().isRandomOrder());
		JLabel mark = new JLabel("Your mark: " + exe.getMark());
		
		this.add(etiquetaName);
		this.add(etiquetaRele);
		this.add(etiquetaStart);
		this.add(etiquetaEnd);
		this.add(etiquetaPen);
		this.add(randQuesOr);
		this.add(mark);
		
		layout.putConstraint(SpringLayout.WEST, etiquetaName, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaName, frames*2, SpringLayout.NORTH, this);
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
		layout.putConstraint(SpringLayout.WEST, mark, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, mark, frames*2, SpringLayout.SOUTH, randQuesOr);
		
		this.setVisible(true);
		}
	}

class TextShow extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int frames = 30;

	public TextShow(AnswerExercise exe, TextAnswer ques){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JLabel etiquetaWeight = new JLabel("Weight: " + ques.getWeight() + " points");
		etiquetaWeight.setFont(new Font("Calibri", Font.PLAIN, 16));
		JLabel etiquetaState = new JLabel("Statement: " + ques.getQuestionText());
		etiquetaState.setFont(new Font("Calibri", Font.PLAIN, 16));
		JLabel etiquetaAns = new JLabel("Answer: " + ques.getAnswer());
		etiquetaAns.setFont(new Font("Calibri", Font.BOLD, 16));
		String ans = null;
		for (AnswerQuestion anss: exe.getAnswers()){
			if (anss.getQuestion().equals(ques)){
				ans = anss.getAnswer().getChoice();
			}
		}
		JLabel answer = new JLabel("Your answer: " + ans);
		answer.setFont(new Font("Calibri", Font.BOLD, 16));
		
		
		this.add(etiquetaWeight);
		this.add(etiquetaState);
		this.add(etiquetaAns);
		this.add(answer);

		
		layout.putConstraint(SpringLayout.WEST, etiquetaWeight, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaWeight, frames, SpringLayout.SOUTH, etiquetaAns);
		layout.putConstraint(SpringLayout.WEST, etiquetaState, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaState, frames*4, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, etiquetaAns, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaAns, frames, SpringLayout.SOUTH, etiquetaState);
		layout.putConstraint(SpringLayout.WEST, answer, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, answer, frames*2, SpringLayout.SOUTH, etiquetaWeight);
		
		this.setVisible(true);
	}
}

class SimpleShow extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int frames = 30;

	public SimpleShow(AnswerExercise exe, SimpleChoice ques){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JLabel etiquetaWeight = new JLabel("Weight: " + ques.getWeight() + " points");
		etiquetaWeight.setFont(new Font("Calibri", Font.PLAIN, 16));
		JLabel etiquetaState = new JLabel("Statement: " + ques.getQuestionText());
		etiquetaState.setFont(new Font("Calibri", Font.PLAIN, 16));
		JLabel etiquetaAns = new JLabel("Answer: " + ques.getCorrectAnswer());
		etiquetaAns.setFont(new Font("Calibri", Font.BOLD, 16));
		JLabel etiquetaRandOr = new JLabel("Random Order of the possible answers: " + ques.isRandomOrder());
		etiquetaRandOr.setFont(new Font("Calibri", Font.PLAIN, 16));
		JLabel etiquetaPosAns = new JLabel("Possible answers of the question: " + ques.getPossibleAnswers().toString());
		String ans = null;
		for (AnswerQuestion anss: exe.getAnswers()){
			if (anss.getQuestion().equals(ques)){
				ans = anss.getAnswer().getChoice();
			}
		}
		JLabel answer = new JLabel("Your answer: " + ans);
		answer.setFont(new Font("Calibri", Font.BOLD, 16));
		
		this.add(etiquetaWeight);
		this.add(etiquetaState);
		this.add(etiquetaAns);
		this.add(etiquetaRandOr);
		this.add(etiquetaPosAns);
		this.add(answer);

		
		layout.putConstraint(SpringLayout.WEST, etiquetaWeight, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaWeight, frames, SpringLayout.SOUTH, etiquetaAns);
		layout.putConstraint(SpringLayout.WEST, etiquetaState, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaState, frames*4, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, etiquetaAns, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaAns, frames, SpringLayout.SOUTH, etiquetaState);
		layout.putConstraint(SpringLayout.WEST, etiquetaRandOr, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaRandOr, frames, SpringLayout.SOUTH, etiquetaWeight);
		layout.putConstraint(SpringLayout.WEST, etiquetaPosAns, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaPosAns, frames, SpringLayout.SOUTH, etiquetaRandOr);
		layout.putConstraint(SpringLayout.WEST, answer, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, answer, frames*2, SpringLayout.SOUTH, etiquetaPosAns);
		
		this.setVisible(true);
	}
}

class TrueShow extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int frames = 30;

	public TrueShow(AnswerExercise exe, TrueFalse ques){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JLabel etiquetaWeight = new JLabel("Weight: " + ques.getWeight() + " points");
		etiquetaWeight.setFont(new Font("Calibri", Font.PLAIN, 16));
		JLabel etiquetaState = new JLabel("Statement: " + ques.getQuestionText());
		etiquetaState.setFont(new Font("Calibri", Font.PLAIN, 16));
		JLabel etiquetaAns = new JLabel("Answer: " + ques.getCorrectAnswer());
		etiquetaAns.setFont(new Font("Calibri", Font.BOLD, 16));
		JLabel etiquetaRandOr = new JLabel("Random Order of the possible answers: " + ques.isRandomOrder());
		etiquetaRandOr.setFont(new Font("Calibri", Font.PLAIN, 16));
		
		String ans = null;
		for (AnswerQuestion anss: exe.getAnswers()){
			if (anss.getQuestion().equals(ques)){
				ans = anss.getAnswer().getChoice();
			}
		}
		JLabel answer = new JLabel("Your answer: " + ans);
		answer.setFont(new Font("Calibri", Font.BOLD, 16));
		
		this.add(etiquetaWeight);
		this.add(etiquetaState);
		this.add(etiquetaAns);
		this.add(etiquetaRandOr);
		this.add(answer);
		
		layout.putConstraint(SpringLayout.WEST, etiquetaWeight, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaWeight, frames, SpringLayout.SOUTH, etiquetaAns);
		layout.putConstraint(SpringLayout.WEST, etiquetaState, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaState, frames*4, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, etiquetaAns, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaAns, frames, SpringLayout.SOUTH, etiquetaState);
		layout.putConstraint(SpringLayout.WEST, etiquetaRandOr, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaRandOr, frames, SpringLayout.SOUTH, etiquetaWeight);
		layout.putConstraint(SpringLayout.WEST, answer, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, answer, frames*2, SpringLayout.SOUTH, etiquetaRandOr);
		
		this.setVisible(true);
	}
}

class MultiShow extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int frames = 30;

	public MultiShow(AnswerExercise exe, MultipleChoice ques){
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JLabel etiquetaWeight = new JLabel("Weight: " + ques.getWeight() + " points");
		etiquetaWeight.setFont(new Font("Calibri", Font.PLAIN, 16));
		JLabel etiquetaState = new JLabel("Statement: " + ques.getQuestionText());
		etiquetaState.setFont(new Font("Calibri", Font.PLAIN, 16));
		JLabel etiquetaAns = new JLabel("Answers: " + ques.getCorrectAnswers().toString());
		etiquetaAns.setFont(new Font("Calibri", Font.BOLD, 16));
		JLabel etiquetaRandOr = new JLabel("Random Order of the possible answers: " + ques.isRandomOrder());
		etiquetaRandOr.setFont(new Font("Calibri", Font.PLAIN, 16));
		JLabel etiquetaPosAns = new JLabel("Possible answers of the question: " + ques.getPossibleAnswers().toString());
		
		List<String> ans = null;
		for (AnswerQuestion anss: exe.getAnswers()){
			if (anss.getQuestion().equals(ques)){
				ans = anss.getAnswer().getMultChoices();
			}
		}
		JLabel answer = new JLabel("Your answer: " + ans);
		answer.setFont(new Font("Calibri", Font.BOLD, 16));

		this.add(etiquetaWeight);
		this.add(etiquetaState);
		this.add(etiquetaAns);
		this.add(etiquetaRandOr);
		this.add(etiquetaPosAns);
		this.add(answer);
		
		layout.putConstraint(SpringLayout.WEST, etiquetaWeight, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaWeight, frames, SpringLayout.SOUTH, etiquetaAns);
		layout.putConstraint(SpringLayout.WEST, etiquetaState, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaState, frames*4, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, etiquetaAns, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaAns, frames, SpringLayout.SOUTH, etiquetaState);
		layout.putConstraint(SpringLayout.WEST, etiquetaRandOr, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaRandOr, frames, SpringLayout.SOUTH, etiquetaWeight);
		layout.putConstraint(SpringLayout.WEST, etiquetaPosAns, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaPosAns, frames, SpringLayout.SOUTH, etiquetaRandOr);
		
		layout.putConstraint(SpringLayout.WEST, answer, frames, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, answer, frames*2, SpringLayout.SOUTH, etiquetaPosAns);
		
		this.setVisible(true);
	}
}
