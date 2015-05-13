package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class ProgressDialog extends JDialog {

	private JButton cancelButton;
	private JProgressBar progressBar;

	public ProgressDialog(Window parent) {
		super(parent, "Download in Progress...", ModalityType.APPLICATION_MODAL);

		progressBar = new JProgressBar();
		cancelButton = new JButton("Cancel");
		progressBar.setStringPainted(true);
		progressBar.setString("retrieving messages...");

		setLayout(new FlowLayout());
		Dimension size = cancelButton.getPreferredSize();
		size.width = 400;
		progressBar.setPreferredSize(size);

		add(progressBar);
		add(cancelButton);

		pack();
		setLocationRelativeTo(parent);
	}

	@Override
	public void setVisible(final boolean b) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (b == false) {
					// Add delay so the final progress is shown
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				ProgressDialog.super.setVisible(b);
			}
		});
	}

	public void setMaximum(int value) {
		progressBar.setMaximum(value);
	}

	public void setValue(int value) {

		int max = progressBar.getMaximum();
		if (max > 0) {
			int percentCompleted = (value * 100) / max;
			progressBar.setString(percentCompleted + "% completed" );
		}
		progressBar.setValue(value);
	}
}
