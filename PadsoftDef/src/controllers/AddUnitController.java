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

import coorse.Coorse;
import courseElements.Course;
import courseElements.Note;
import courseElements.Unit;
import courseElements.VisibleElement;
import gui.CourseScreenTeacher;
import gui.GeneralFrame;
import gui.MainScreenTeacher;
import gui.UnitScreenTeacher;

public class AddUnitController implements ActionListener {
	private VisibleElement c;
	public AddUnitController(VisibleElement c){
		this.c=c;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JLabel ul = new JLabel("Unit name:");
		JCheckBox box = new JCheckBox("Visible");
		JTextField uname = new JTextField(10);
		SpringLayout layout = new SpringLayout();
		JPanel panel = new JPanel()
		{
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(320, 100);
            }
        };
		panel.setLayout(layout);
		panel.add(uname);
		panel.add(ul);
		panel.add(box);
		layout.putConstraint(SpringLayout.NORTH, ul, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, ul, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, uname, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, uname, 10, SpringLayout.EAST, ul);
		layout.putConstraint(SpringLayout.NORTH, box, 10, SpringLayout.SOUTH, ul);
		layout.putConstraint(SpringLayout.WEST, box, 10, SpringLayout.WEST, panel);
		int result = JOptionPane.showConfirmDialog(null, panel, "Create course", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION){
			if(uname.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null, "You need to set a unit name");
			}
			else{
				if(c instanceof Course){
					Course c1 = (Course) this.c;
					c1.addUnit(uname.getText(), box.isSelected());
					GeneralFrame.GFrame.changePanel(new CourseScreenTeacher(c1), false);
				}
				else if (c instanceof Unit){
					Unit u = (Unit) this.c;
					u.addSubunit(uname.getText(), box.isSelected());
					GeneralFrame.GFrame.changePanel(new UnitScreenTeacher(u), false);
				}
			}
		}
	}
}
