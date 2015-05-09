package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class PrefsDialog extends JDialog {

	private JButton okBtn;
	private JButton cancelBtn;
	private JSpinner spinner;
	private SpinnerNumberModel spinnerNumberModel;
	private JTextField userNameField;
	private JPasswordField pwdField;
	private PrefsListener prefsListener;
	private JPanel controlPanel;
	private JPanel buttonPanel;

	public PrefsDialog(JFrame parent) {
		super(parent, "Preferences", false);
		setPrefsDialogBoxDisplayParameters(parent);
		createComponents();
		layoutComponents();
		populateButtonActionListeners();
	}

	private void createComponents() {
		userNameField = new JTextField(20);
		pwdField = new JPasswordField(20);
		okBtn = new JButton("ok");
		cancelBtn = new JButton("cancel");
		spinnerNumberModel = new SpinnerNumberModel(3306, 0, 9999, 1);
		spinner = new JSpinner(spinnerNumberModel);
	}

	private void setPrefsDialogBoxDisplayParameters(JFrame parent) {
		setSize(500, 180);
		setLocationRelativeTo(parent);
	}

	private void populateButtonActionListeners() {
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
	}

	private void layoutComponents() {

		controlPanel = new JPanel();
		buttonPanel = new JPanel();

		// Set layout
		setLayout(new BorderLayout());
		controlPanel.setLayout(new GridBagLayout());
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// Set border
		int spacer = 15; 
		Border spaceBorder = BorderFactory.createEmptyBorder(spacer, spacer, spacer, spacer);
		Border tileBorder = BorderFactory.createTitledBorder("SQL Database Setting");
		controlPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, tileBorder));

		// Add Panels and components
		addPanelsToPrefsDialog();
		addComponentsToControlPanel();
		addComponentsToButtonPanel();
	}

	private void addComponentsToButtonPanel() {
		// Button Panel
		Dimension btnSize = cancelBtn.getPreferredSize();
		okBtn.setPreferredSize(btnSize);
		buttonPanel.add(okBtn);
		buttonPanel.add(cancelBtn);
	}

	private void addComponentsToControlPanel() {
		// Add components to controlPanel
		Insets rightPadding = new Insets(0, 0, 0, 15);
		Insets noPadding = new Insets(0, 0, 0, 0);

		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridy = -1;
		gc.fill = GridBagConstraints.NONE;

		// First row
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controlPanel.add(new JLabel("Use Name:"), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlPanel.add(userNameField, gc);

		// 2nd row
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controlPanel.add(new JLabel("Password: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlPanel.add(pwdField, gc);

		// 3rd
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controlPanel.add(new JLabel("port: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlPanel.add(spinner, gc);
	}

	private void addPanelsToPrefsDialog() {
		add(controlPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
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
