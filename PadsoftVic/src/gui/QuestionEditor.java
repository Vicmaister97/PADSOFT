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
    private JPanel cardPanel;
    private JPanel[] panels;
    				   
    				   
    private void buildPanels(Exercise exe) {
    	
    	ArrayList<Integer> nums = new ArrayList<Integer>();
    	
    	for(int i=0; i<numq; i++){
    		nums.add(i);
    	}
    	this.panels = new JPanel[numq];
    	int randomNum;
    	for (int i = 0; i<numq; i++) {
    		Question q;
    		this.panels[i] = new JPanel(new BorderLayout());
    		
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
    		
    		q = exe.getQuestions().get(i);
    		
    		if (q instanceof TextAnswer){
    			TextEditor textPane = new TextEditor((TextAnswer) q);
    			this.panels[i].add(textPane, BorderLayout.CENTER);
    		}
    		else if (q instanceof SimpleChoice){
    			
    		}
    		
    		
    		this.panels[i].add(new JLabel ("Question "+(i+1)), BorderLayout.NORTH);
    	}
    }    
    
    
    public QuestionEditor(Exercise exe) {
    	
    	numq = exe.getQuestions().size();
    	cardPanel = new JPanel(panelLayout);
        
        this.buildPanels(exe);
        
        for (int i = 0; i<numq; i++){
        	cardPanel.add(String.valueOf(i), panels[i]);
        }
        
        this.add (cardPanel, BorderLayout.CENTER);
        
        JButton first 	 = new JButton("First");
        JButton last 	 = new JButton("Last");
        JButton previous = new JButton("Previous");
        JButton next     = new JButton("Next");
        //AÑADIR SAVE CHANGES Y EXIT
        
        combo = new JComboBox<String>();
        
        for (int i = 0; i<numq; i++)
        	combo.addItem(String.valueOf(i));
        
        JPanel panel = new JPanel();
        panel.add(first);
        panel.add(last);
        panel.add(previous);
        panel.add(next);
        panel.add(combo);
        
        this.add(panel, BorderLayout.SOUTH);
        
        first.addActionListener((ActionListener) this);
        last.addActionListener((ActionListener) this);
        next.addActionListener((ActionListener) this);
        previous.addActionListener((ActionListener) this);
        combo.addItemListener((ItemListener) this);
        
        this.setVisible(true);
        this.setSize(380, 300);

}
    
    
    
    public void actionPerformed (ActionEvent e) {
        String command = e.getActionCommand ();
        if (command.equals ("First"))
            panelLayout.first (cardPanel);
        else if (command.equals ("Last"))
            panelLayout.last (cardPanel);
        else if (command.equals ("Previous"))
            panelLayout.previous (cardPanel);
        else if (command.equals ("Next"))
            panelLayout.next (cardPanel);
    }
    
    public void itemStateChanged (ItemEvent e) {
        String item = (String) e.getItem ();
        
        panelLayout.show (cardPanel, item);
    }
}
