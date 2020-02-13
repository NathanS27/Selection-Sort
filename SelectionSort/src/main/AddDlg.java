package main;

import BreezySwing.*;

import java.awt.Color;

import javax.swing.*;

public class AddDlg extends GBDialog {
	
	AllStudents students;
	
	JFrame main;
	
	String testGradesStr="";
	int numTest=0;
	String quizGradesStr="";
	int numQuiz=0;
	
	JLabel nameLbl = addLabel("Name: ",1,1,1,1);
	JTextField name = addTextField("",1,2,1,1);
	
	JLabel testLbl = addLabel("Test Grade: (Add up to 5) ",2,1,1,1);
	DoubleField testField = addDoubleField(0,2,2,1,1);
	JLabel testGrades = addLabel(testGradesStr,3,1,1,1);
	JButton testButton = addButton("Add Test Grade",3,2,1,1);
	
	JLabel quizLbl = addLabel("Quiz Grade: (Add up to 8) ",4,1,1,1);
	DoubleField quizField = addDoubleField(0,4,2,1,1);
	JLabel quizGrades = addLabel(quizGradesStr,5,1,1,1);
	JButton quizButton = addButton("Add Quiz Grade",5,2,1,1);
	
	JLabel hwLbl = addLabel("Homework Average:",6,1,1,1);
	DoubleField hwField = addDoubleField(0,6,2,1,1);
	
	JButton add = addButton("Add Student",8,1,1,1);
	JButton close = addButton("Close",8,2,1,1);
	
	public void buttonClicked(JButton b) {
		if(b==testButton) {
			if(numTest<5) {
				//parsing to error check
				try {
					double test=Double.parseDouble(testField.getText());
					if(test < 0 || test > 100) {
						throw new ImproperFormatException("Grade must be between 0 and 100");
					}
					testGradesStr+=String.format("%s ", testField.getText());
					numTest++;
				}
				catch(NumberFormatException e) {
					errorMsg("Must Input a Double");
				}
				catch(ImproperFormatException c) {
					errorMsg(c.getMessage());
				}
				testGrades.setText(testGradesStr);
				testField.setText("");
				testField.grabFocus();
				if(numTest==5) {
					testButton.setVisible(false);
					testField.disable();;
					testField.setText("");
					quizField.grabFocus();
				}
			}
			else {
				errorMsg("Only 5 Tests can be entered");
			}
		}
		if(b==quizButton) {
			if(numQuiz<8) {
				//parsing to error check
				try {
					double quiz=Double.parseDouble(quizField.getText());
					if(quiz < 0 || quiz > 100) {
						throw new ImproperFormatException("Grade must be between 0 and 100");
					}
					quizGradesStr+=String.format("%s ",quizField.getText().trim());
					numQuiz++;
				}
				catch(NumberFormatException e) {
					errorMsg("Must Input a Double");
				}
				catch(ImproperFormatException c) {
					errorMsg(c.getMessage());
				}
				quizGrades.setText(quizGradesStr);
				quizField.setText("");
				quizField.grabFocus();
				if(numQuiz==8) {
					quizButton.setVisible(false);
					quizField.disable();;
					quizField.setText("");
					hwField.grabFocus();
				}
			}
			else {
				errorMsg("Only 8 Quizzes can be entered");
			}
		}
		if(b==add) {
			if((name.getText()).isEmpty()) {
				errorMsg("Empty name");
				name.grabFocus();
				return;
			}
			double hwAverage=0;
			try {
				hwAverage = Double.parseDouble(hwField.getText());
			}
			catch(NumberFormatException e) {
				errorMsg("Homework average must be a double");
				hwField.setText("");
				hwField.grabFocus();
				return;
			}
			if(hwAverage < 0 || hwAverage > 100) {
				errorMsg("Homework average must be within 0 and 100");
				hwField.setText("");
				hwField.grabFocus();
				return;
			}
			
			int response = JOptionPane.showConfirmDialog(null,"Are you sure you want to add: \n" + getStudentInfo(), "Add " + name.getText() + "?", JOptionPane.YES_NO_OPTION);
			if(response == JOptionPane.YES_OPTION) {
				try {
					students.addStudent(new StudentInfo(name.getText(),testGradesStr,quizGradesStr,hwAverage));
				}
				catch(ImproperFormatException e) {
					errorMsg(e.getMessage());
				}
				this.dispose();
			}
		}
		if(b==close) {
			dispose();
		}
	}
	
	private String getStudentInfo() {
		String info = "";
		info += "Name: " + name.getText() + '\n'+
				"Test Grades: " + testGradesStr + '\n' +
				"Quiz Grades: " + quizGradesStr + '\n' +
				"Homework Average: " + hwField.getNumber();
		return info;
	}
	
	private void errorMsg(String str) {
		ErrorDlg display = new ErrorDlg(main,str);
		display.setVisible(true);
	}
	
	public AddDlg(JFrame parent,AllStudents a) {
		super(parent);
		main=parent;
		students=a;
		testField.setText("");
		quizField.setText("");
		hwField.setText("");
		close.setBackground(Color.red);
		close.setBorder(BorderFactory.createBevelBorder(0));
		add.setBorder(BorderFactory.createBevelBorder(0));
		getContentPane().setBackground(new Color(179,153,212));
		setTitle("Add Student");
		setDlgCloseIndicator("Cancel");
		setSize(400, 500);
		setLocationRelativeTo(null);
	}
}
