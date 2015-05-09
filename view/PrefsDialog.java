package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class PrefsDialog extends JDialog {

	private JButton okBtn;
	private JButton cancelBtn;
	private JSpinner spinner;
	private SpinnerNumberModel spinnerNumberModel;
	private JTextField userNameField;
	private JPasswordField pwdField;
	private PrefsListener prefsListener;

	public PrefsDialog(JFrame parent) {
		super(parent, "Preferences", false);
		setSize(400, 300);
		setLocationRelativeTo(parent);

		userNameField = new JTextField(20);
		pwdField = new JPasswordField(20);
		okBtn = new JButton("ok");
		cancelBtn = new JButton("cancel");
		spinnerNumberModel = new SpinnerNumberModel(3306, 0, 9999, 1);
		spinner = new JSpinner(spinnerNumberModel);
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer port = (Integer) spinner.getValue();
				String user = userNameField.getText();
				String pwd = new String(pwdField.getPassword());
				if (prefsListener != null) {
					prefsListener.SetPreferences(user, pwd, port);
				}
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
		gc.gridy = -1;
		gc.fill = GridBagConstraints.NONE;

		// First row
		gc.gridy++;
		gc.gridx = 0;
		add(new JLabel("Use Name:"), gc);

		gc.gridx = 1;
		add(userNameField, gc);

		// 2nd row
		gc.gridy++;
		gc.gridx = 0;
		add(new JLabel("Password: "), gc);

		gc.gridx = 1;
		add(pwdField, gc);

		// 3rd
		gc.gridy++;
		gc.gridx = 0;
		add(new JLabel("port: "), gc);

		gc.gridx = 1;
		add(spinner, gc);

		// 4th
		gc.gridy++;
		gc.gridx = 0;
		add(okBtn, gc);

		gc.gridx = 1;
		add(cancelBtn, gc);

	}

	public void setPreferencesListener(PrefsListener prefsListener) {
		this.prefsListener = prefsListener;

	}

	public void setDefault(String user, String password, Integer port) {
		userNameField.setText(user);
		pwdField.setText(password);
		spinner.setValue(port);
	}
}
