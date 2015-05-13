package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class ProgressDialog extends JDialog {

	private JButton cancelButton;
	private JProgressBar progressBar;
	private ProgressDialogListener listener;

	public ProgressDialog(Window parent, String title) {
		super(parent, title, ModalityType.APPLICATION_MODAL);

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

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (listener != null) {
					listener.progressDialogCancelled();
				}
			}
		});

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (listener != null) {
					listener.progressDialogCancelled();
				}
			}

		});
		pack();
		setLocationRelativeTo(parent);
	}

	public void setListener(ProgressDialogListener listener) {
		this.listener = listener;
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
			progressBar.setString(percentCompleted + "% completed");
		}
		progressBar.setValue(value);
	}
}
