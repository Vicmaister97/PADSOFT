package controllers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

import coorse.Coorse;
import courseElements.Course;
import gui.GeneralFrame;
import gui.MainScreenTeacher;

public class AddCourseController implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JLabel cl = new JLabel("Course name:");
		JLabel tl = new JLabel("Teacher name:");
		JCheckBox box = new JCheckBox("Visible");
		JTextField cname = new JTextField(10);
		JTextField tname = new JTextField(10);
		SpringLayout layout = new SpringLayout();
		JPanel panel = new JPanel()
		{
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(320, 100);
            }
        };
		panel.setLayout(layout);
		panel.add(tname);
		panel.add(cname);
		panel.add(tl);
		panel.add(cl);
		panel.add(box);
		layout.putConstraint(SpringLayout.NORTH, cl, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, cl, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, tl, 10, SpringLayout.SOUTH, cl);
		layout.putConstraint(SpringLayout.WEST, tl, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, cname, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, cname, 10, SpringLayout.EAST, cl);
		layout.putConstraint(SpringLayout.NORTH, tname, 10, SpringLayout.SOUTH, cname);
		layout.putConstraint(SpringLayout.WEST, tname, 10, SpringLayout.EAST, tl);
		layout.putConstraint(SpringLayout.NORTH, box, 10, SpringLayout.SOUTH, tl);
		layout.putConstraint(SpringLayout.WEST, box, 10, SpringLayout.WEST, panel);
		int result = JOptionPane.showConfirmDialog(null, panel, "Create course", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION){
			if(cname.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null, "You need to set a course name");
			}
			else if(tname.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null, "You need to set a teacher name");
			}
			else{
				Coorse.coorse.addCourse(new Course(cname.getText(), tname.getText(), box.isSelected()));
				GeneralFrame.GFrame.changePanel(new MainScreenTeacher(), false);
			}
		}
	}
}
