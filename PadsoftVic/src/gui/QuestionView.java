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

public class QuestionView extends JPanel{
	private int nump = 0;
    private JComboBox<String> combo;
    private CardLayout panelLayout = new CardLayout();
    private JPanel cardPanel;
    private JPanel[] panels;
    				   
    				   
    private void buildPanels() {
    	this.panels = new JPanel[nump];
    	for (int i = 0; i<nump; i++) {
    		this.panels[i] = new JPanel();
    		this.panels[i].add(new JLabel ("Component "+i, JLabel.CENTER));
    	}
    }
    
    public QuestionView(Exercise exe) {
    	
    	nump = exe.getQuestions().size();
    	cardPanel = new JPanel(panelLayout);
        
        this.buildPanels();
        
        for (int i = 0; i<nump; i++){
        	cardPanel.add(String.valueOf(i), panels[i]);

        }
        
        this.add (cardPanel, BorderLayout.CENTER);
        
        JButton first 	 = new JButton("First");
        JButton last 	 = new JButton("Last");
        JButton previous = new JButton("Previous");
        JButton next     = new JButton("Next");
        
        combo = new JComboBox<String>();
        
        for (int i = 0; i<nump; i++)
        	combo.addItem(String.valueOf(i));
        
        JPanel panel = new JPanel();
        panel.add(first);
        panel.add(last);
        panel.add(previous);
        panel.add(next);
        panel.add(combo);
        
        p.add(panel, BorderLayout.SOUTH);
        
        first.addActionListener(this);
        last.addActionListener(this);
        next.addActionListener(this);
        previous.addActionListener(this);
        combo.addItemListener(this);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(380, 300);

}
