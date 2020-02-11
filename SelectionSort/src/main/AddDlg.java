package main;

import javax.swing.JFrame;
import BreezySwing.*;
import javax.swing.*;

public class AddDlg extends GBDialog {
	
	AllStudents students;
	
	JLabel nameLbl = addLabel("Name: ",1,1,1,1);
	JTextField name = addTextField("",1,2,1,1);
	
	JLabel testLbl = addLabel("Test Grade: (Add up to 5) ",2,1,1,1);
	DoubleField testField = addDoubleField(0,2,2,1,1);
	JLabel testGrades = addLabel("",3,1,1,1);
	JButton testButton = addButton("Add Test Grade",3,2,1,1);
	
	JLabel quizLbl = addLabel("Quiz Grade: (Add up to 8) ",2,1,1,1);
	DoubleField quizField = addDoubleField(0,2,2,1,1);
	JLabel quizGrades = addLabel("",3,1,1,1);
	JButton quizButton = addButton("Add Quiz Grade",3,2,1,1);
	
	
	
	public AddDlg(JFrame parent,AllStudents a) {
		super(parent);
		students=a;
		setTitle("Add Student");
		setDlgCloseIndicator("Cancel");
		setSize(400, 200);
		setLocationRelativeTo(null);
	}
}
