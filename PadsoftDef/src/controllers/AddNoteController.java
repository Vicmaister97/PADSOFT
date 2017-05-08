package controllers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import courseElements.Course;
import courseElements.Note;
import courseElements.Unit;
import courseElements.VisibleElement;
import gui.CourseScreenTeacher;
import gui.GeneralFrame;
import gui.UnitScreenTeacher;

public class AddNoteController implements ActionListener {
	private VisibleElement c;
	public AddNoteController(VisibleElement c){
		this.c=c;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JLabel nl = new JLabel("Note content:");
		JCheckBox box = new JCheckBox("Visible");
		JTextField nname = new JTextField(10);
		SpringLayout layout = new SpringLayout();
		JPanel panel = new JPanel()
		{
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(320, 100);
            }
        };
		panel.setLayout(layout);
		panel.add(nname);
		panel.add(nl);
		panel.add(box);
		layout.putConstraint(SpringLayout.NORTH, nl, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, nl, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, nname, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, nname, 10, SpringLayout.EAST, nl);
		layout.putConstraint(SpringLayout.NORTH, box, 10, SpringLayout.SOUTH, nl);
		layout.putConstraint(SpringLayout.WEST, box, 10, SpringLayout.WEST, panel);
		int result = JOptionPane.showConfirmDialog(null, panel, "Create note", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION){
			if(nname.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null, "You need to set the note content");
			}
			else{
				if(c instanceof Course){
					Course c1 = (Course) this.c;
					c1.addElement(new Note(nname.getText(), box.isSelected()));
					GeneralFrame.GFrame.changePanel(new CourseScreenTeacher(c1), false);
				}
				else if (c instanceof Unit){
					Unit u = (Unit) this.c;
					u.addElement(new Note(nname.getText(), box.isSelected()));
					GeneralFrame.GFrame.changePanel(new UnitScreenTeacher(u), false);
				}
			}
		}
	}

}
