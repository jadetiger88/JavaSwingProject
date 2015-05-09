package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.Controller;

public class MainFrame extends JFrame {

	private TextPanel textPanel;
	private ToolBar toolBar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	private PrefsDialog prefsDialog;
	private Preferences prefs;

	public MainFrame() {

		super("Hello World");
		setLayout(new BorderLayout());
		textPanel = new TextPanel();
		formPanel = new FormPanel();
		setJMenuBar(createMenuBar());
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new PersonFileFilter());
		toolBar = new ToolBar();
		controller = new Controller();
		tablePanel = new TablePanel();
		prefsDialog = new PrefsDialog(this);
		prefs = Preferences.userRoot().node("sqlPref");

		prefsDialog.setPreferencesListener(new PrefsListener() {
			public void SetPreferences(String user, String password,
					Integer port) {
				prefs.put("user", user);
				prefs.put("password", password);
				prefs.putInt("port", port);
			}
		});

		String user = prefs.get("user", "");
		String password = prefs.get("password", "");
		Integer port = prefs.getInt("port", 3306);
		prefsDialog.setDefault(user, password, port);
		tablePanel.setData(controller.getPeople());
		tablePanel.setPersonTableListener(new PersonTableListener() {
			public void deleteRow(int row) {
				controller.removeRow(row);
			}
		});

		toolBar.setStringListener(new StringListener() {
			public void textEmitted(String text) {
				textPanel.append(text);
			}
		});

		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				controller.addPerson(e);
				tablePanel.refresh();
			}
		});

		add(formPanel, BorderLayout.WEST);
		add(toolBar, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
		setMinimumSize(new Dimension(500, 400));
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JMenuBar createMenuBar() {

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataItem = new JMenuItem("Export Item...");
		JMenuItem importDataItem = new JMenuItem("Import Item...");
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.CTRL_MASK));
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				ActionEvent.CTRL_MASK));
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rtn = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do you really want to exit", "Conform exit",
						JOptionPane.OK_CANCEL_OPTION);
				if (rtn == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}

		});

		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(MainFrame.this,
								"Could not load file", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});

		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(MainFrame.this,
								"Could not save file", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});

		JMenu windowMenu = new JMenu("Window");
		JMenu show = new JMenu("Show");
		JCheckBoxMenuItem showForm = new JCheckBoxMenuItem("Show Form");
		JMenuItem prefsItem = new JMenuItem("Preferences...");
		show.add(showForm);
		windowMenu.add(show);
		windowMenu.add(prefsItem);
		showForm.setSelected(true);
		showForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
				formPanel.setVisible(menuItem.isSelected());
			}

		});

		prefsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefsDialog.setVisible(true);
			}

		});

		menuBar.add(fileMenu);
		menuBar.add(windowMenu);

		return menuBar;
	}
}