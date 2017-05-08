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

public class QuestionEditor extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numq = 0;
    private JComboBox<String> combo;
    private CardLayout panelLayout = new CardLayout();
    private JPanel cards;
    private JPanel[] panels;
    				   
    				   
    private void buildPanels(Exercise exe, int numq) {
    	
    	ArrayList<Integer> nums = new ArrayList<Integer>();
    	
    	/*for(int i=0; i<numq; i++){
    		nums.add(i);
    	}*/
    	this.panels = new JPanel[numq+1];
		this.panels[0] = new JPanel(new BorderLayout());
		ExerciseEdit editPrincipal = new ExerciseEdit(exe);
		this.panels[0].add(editPrincipal, BorderLayout.CENTER);

    	//int randomNum;
    	for (int i = 1; i<(numq+1); i++) {
    		this.panels[i] = new JPanel(new BorderLayout());
    		
    		/*if(exe.isRandomOrder() == true){
    			while(true){
    				randomNum = ThreadLocalRandom.current().nextInt(0, numq);
    				if (nums.get(randomNum) != -1){
    					q = exe.getQuestions().get(randomNum);
    					nums.set(randomNum, -1);
    					break;
    					}
    			}
    		}*/
    		
    		Question q = exe.getQuestions().get(i-1);
    		
    		if (q instanceof TextAnswer){
    			TextEditor textPane = new TextEditor((TextAnswer) q);
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
    		
    		this.panels[i].add(new JLabel ("Question "+i, JLabel.CENTER), BorderLayout.NORTH);
    	}
    }    
    
    
    public QuestionEditor(Exercise exe) {
    	
    	numq = exe.getQuestions().size();
    	cards = new JPanel(panelLayout);
    	this.setLayout(new BorderLayout());
        
        this.buildPanels(exe, numq);
        
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
