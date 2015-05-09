package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {

	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameTextField;
	private JTextField occupationTextField;
	private JButton addBtn;
	private FormListener formListener;
	private JList ageList;
	private JComboBox employeeType;
	private JCheckBox citizenCheck;
	private JLabel taxLabel;
	private JTextField taxTextField;
	private JRadioButton maleButton;
	private JRadioButton femaleButton;
	private ButtonGroup genderGroup;

	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);

		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		nameTextField = new JTextField(10);
		occupationTextField = new JTextField(10);
		addBtn = new JButton("Add");
		ageList = new JList();
		employeeType = new JComboBox();
		citizenCheck = new JCheckBox();
		taxLabel = new JLabel("Tax ID: ");
		taxTextField = new JTextField(10);
		maleButton = new JRadioButton("male");
		femaleButton = new JRadioButton("female");
		genderGroup = new ButtonGroup();

		nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLabel.setLabelFor(nameTextField);

		genderGroup.add(maleButton);
		genderGroup.add(femaleButton);
		maleButton.setSelected(true);
		maleButton.setActionCommand("male");
		femaleButton.setActionCommand("female");

		taxLabel.setEnabled(false);
		taxTextField.setEnabled(false);
		citizenCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isTicked = citizenCheck.isSelected();
				taxLabel.setEnabled(isTicked);
				taxTextField.setEnabled(isTicked);

			}

		});

		DefaultListModel ageListModel = new DefaultListModel();
		ageListModel.addElement(new AgeCategory(0, "Under 18"));
		ageListModel.addElement(new AgeCategory(1, "18 to 65"));
		ageListModel.addElement(new AgeCategory(2, "Over 65"));
		ageList.setModel(ageListModel);
		ageList.setPreferredSize(new Dimension(110, 66));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1);

		DefaultComboBoxModel employeeTypeModel = new DefaultComboBoxModel();
		employeeTypeModel.addElement("employed");
		employeeTypeModel.addElement("unemployed");
		employeeTypeModel.addElement("self-employed");
		employeeType.setModel(employeeTypeModel);

		addBtn.setMnemonic(KeyEvent.VK_A);
		addBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String name = nameTextField.getText();
				String occupation = occupationTextField.getText();
				AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue();
				String empType = (String) employeeType.getSelectedItem();
				boolean isCitizen = citizenCheck.isSelected();
				String taxID = taxTextField.getText();
				String gender = genderGroup.getSelection().getActionCommand();
				FormEvent ev = new FormEvent(this, name, occupation, ageCat
						.getId(), empType, isCitizen, taxID, gender);
				if (formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}
		});

		Border iBorder = BorderFactory.createTitledBorder("Add Person");
		Border oBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(oBorder, iBorder));

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.NONE;

		// First row
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridy = 0;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(nameLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameTextField, gc);

		// Second row
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(occupationLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(occupationTextField, gc);

		// Third row
		gc.weightx = 1;
		gc.weighty = .2;
		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Age Category"), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(ageList, gc);

		// Fourth row
		gc.weightx = 1;
		gc.weighty = .2;
		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Employment;"), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(employeeType, gc);

		// Fifth row
		gc.weightx = 1;
		gc.weighty = .2;
		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("US Citizen"), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(citizenCheck, gc);

		// Sixth row
		gc.weightx = 1;
		gc.weighty = .2;
		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(taxLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(taxTextField, gc);

		// 7th row
		gc.weightx = 1;
		gc.weighty = .1;
		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("gender:"), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(maleButton, gc);

		// 8th row
		gc.weightx = 1;
		gc.weighty = .1;
		gc.gridy++;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(femaleButton, gc);

		// Last row
		gc.weightx = 1;
		gc.weighty = 2.0;
		gc.gridy++;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(addBtn, gc);

	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}

	public class AgeCategory {
		private int id;
		private String str;

		public AgeCategory(int id, String str) {
			this.id = id;
			this.str = str;
		}

		public String toString() {
			return str;
		}

		public int getId() {
			return id;
		}

	}
}
