package controllers;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

public class EditNoteController implements MouseListener {
	private VisibleElement c;
	private Note n;
	
	public EditNoteController(VisibleElement c, Note n){
		this.c=c;
		this.n = n;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel nl = new JLabel("Note content:");
		JTextField nname = new JTextField(10);
		SpringLayout layout = new SpringLayout();
		JPanel panel = new JPanel()
		{
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(320, 100);
            }
        };
        nname.setText(this.n.getText());
		panel.setLayout(layout);
		panel.add(nname);
		panel.add(nl);
		layout.putConstraint(SpringLayout.NORTH, nl, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, nl, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, nname, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, nname, 10, SpringLayout.EAST, nl);
		int result = JOptionPane.showConfirmDialog(null, panel, "Create note", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION){
			if(nname.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null, "You need to set the note content");
			}
			
			else{
				this.n.setText(nname.getText());
				if(c instanceof Course){
					Course c1 = (Course) this.c;
					GeneralFrame.GFrame.changePanel(new CourseScreenTeacher(c1), false);
				}
				else if (c instanceof Unit){
					Unit u = (Unit) this.c;
					GeneralFrame.GFrame.changePanel(new UnitScreenTeacher(u), false);
				}
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
