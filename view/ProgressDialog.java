package view;

import java.awt.Window;

import javax.swing.JDialog;

public class ProgressDialog extends JDialog {

	public ProgressDialog(Window parent) {
		super(parent, "Download in Progress...", ModalityType.APPLICATION_MODAL);
		setSize(400, 200);
		setLocationRelativeTo(parent);
	}
}
