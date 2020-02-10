package main;


import javax.swing.JFrame;
import BreezySwing.*;

public class AddDlg extends GBDialog {

	public AddDlg(JFrame parent) {
		super(parent);
		setTitle("Add Student");
		setDlgCloseIndicator("Cancel");
		setSize(400, 200);
		setLocationRelativeTo(null);
	}
}
