package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class PrefsDialog extends JDialog {

	private JButton okBtn;
	private JButton cancelBtn;
	private JSpinner spinner;
	private SpinnerNumberModel spinnerNumberModel;

	public PrefsDialog(JFrame parent) {
		super(parent, "Preferences", false);
		setSize(400, 300);
		setLocationRelativeTo(parent);

		okBtn = new JButton("ok");
		cancelBtn = new JButton("cancel");
		spinnerNumberModel = new SpinnerNumberModel(3306, 0, 9999, 1);
		spinner = new JSpinner(spinnerNumberModel);
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer value = (Integer) spinner.getValue();
				System.out.println(value);
				setVisible(false);
			}
		});
		;
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer value = (Integer) spinner.getValue();
				setVisible(false);
			}
		});
		;

		layoutComponent();
	}

	private void layoutComponent() {
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		gc.gridx = 0;
		gc.gridy = 0;
		add(new JLabel("port; "), gc);

		gc.gridx = 1;
		gc.gridy = 0;
		add(spinner, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		add(okBtn, gc);

		gc.gridx = 1;
		gc.gridy = 1;
		add(cancelBtn, gc);

	}

}
