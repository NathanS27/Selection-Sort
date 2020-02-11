package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BreezySwing.*;

public class gradeUI extends GBFrame {
	
	JMenuItem add = addMenuItem("New","Student");
	JMenuItem populate = addMenuItem("New","Populate");
	JMenuItem sortGrade = addMenuItem("Sort","Grade");
	JMenuItem sortName = addMenuItem("Sort","Name");
	
	sorter sort;
	AllStudents students = new AllStudents();
	
	JPanel dataLayout = addPanel(1,2,1,1);
	JTable dataTable = null;
	DefaultTableModel dataModel = null;
	
	JTextArea stats = addTextArea("",2,2,1,1);
	
	public gradeUI() {
		stats.setEditable(false);
		display();
	}
	
	public void menuItemSelected(JMenuItem menuItem) {
		if (menuItem == add) {
			AddDlg dlg = new AddDlg(this,students);
			dlg.setVisible(true);
		}
		if(menuItem == populate) {
			students.addStudent(new StudentInfo("Matt", 60));
			students.addStudent(new StudentInfo("Mike", 100));
			students.addStudent(new StudentInfo("Nate", 80));
			students.addStudent(new StudentInfo("Noah", 10));
		}
		if(menuItem ==sortGrade) {
			dataModel.setRowCount(0);
			displayStudents(students.sortGrades());
			setStats();
		}
		if(menuItem ==sortName) {
			dataModel.setRowCount(0);
			displayStudents(students.sortNames());
			setStats();
		}
	} 
	
	private void setStats() {
		sort=new sorter();
		try {
			for(StudentInfo s: students.sortGrades()) {
				sort.addNum(String.format("%.2f", s.getFinalAvg()));
			}
			
			String str="";
			str+=String.format("Mean: %.3f", sort.getMean());
			str+=String.format("\nMedian: %s", sort.getMedian());
			str+=String.format("\nMode: %s", sort.getMode());
			str+=String.format("\nStandard Deviation: %s", sort.getSD());
			stats.setText(str);
		}
		catch(ImproperFormatException e) {
			
		}
	}
	
	private void display() {
		String[] columnNames = {"Name", "Test Avg", "Quiz Avg", "HW Avg", "Final Avg"};
		String[][] StudentData = {{"","","","",""}};

		// Set the layout mode of the data panel
		dataLayout.setLayout(new BorderLayout());
		
		// Create the placeholder table and put it in a scroll pane
		dataTable = new JTable (StudentData,columnNames);
		dataModel = new DefaultTableModel();
		dataModel.setColumnIdentifiers(columnNames);
        dataTable.setModel(dataModel);
        
        //sets the alignment
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        dataTable.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        dataTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        dataTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        dataTable.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
     

		// Add the scrollPane to the panel and put it in the center so it uses the full panel
		JScrollPane scrollPane = new JScrollPane(dataTable);
		dataLayout.add(scrollPane,BorderLayout.CENTER);
		dataTable.disable();
	}
	
	private void displayStudents(ArrayList<StudentInfo> list) {
		for(StudentInfo s:list) {
			displayStudent(s);
		}
	}
	
	private void displayStudent(StudentInfo stu) {
		String[] dataRow = new String[6];
		dataRow[0] = stu.getName();
		//formats all the dollar signs and outputs 2 decimal places
		dataRow[1] = String.format("%.2f", stu.getTestAvg());
		dataRow[2] = String.format("%.2f", stu.getQuizAvg());
		dataRow[3] = String.format("%.2f", stu.getHwAvg());
		dataRow[4] = String.format("%.2f", stu.getFinalAvg());
		
		dataModel.addRow(dataRow);
	}
	
	public static void main(String[] args) {
		JFrame frm = new gradeUI();
		frm.setSize(500,300);
		frm.setTitle("iPass v2");
		frm.getContentPane().setBackground(new Color(179,153,212));
		frm.setResizable(true);
		frm.setVisible(true);
		frm.setLocationRelativeTo(null);
	}
}
