package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import controller.Controller;

public class MainFrame extends JFrame {

	private ToolBar toolBar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	private PrefsDialog prefsDialog;
	private Preferences prefs;
	private JSplitPane splitPane;
	private JTabbedPane tabPane;
	private MessagePanel messagePanel;

	public MainFrame() {

		super("People Database");
		setLayout(new BorderLayout());
		setJMenuBar(createMenuBar());
		toolBar = new ToolBar();
		formPanel = new FormPanel();
		tablePanel = new TablePanel();
		prefsDialog = new PrefsDialog(this);
		fileChooser = new JFileChooser();
		controller = new Controller();
		tabPane = new JTabbedPane();
		messagePanel = new MessagePanel(this);
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel,
				tabPane);
		splitPane.setOneTouchExpandable(true);

		// Layout components
		tabPane.addTab("Person", tablePanel);
		tabPane.addTab("Message", messagePanel);
		add(toolBar, BorderLayout.NORTH);
		add(splitPane, BorderLayout.CENTER);
		setMinimumSize(new Dimension(500, 400));
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);

		// Set filter for file chooser
		fileChooser.setFileFilter(new PersonFileFilter());

		// Load saved preference if any
		prefs = Preferences.userRoot().node("sqlPref");
		String user = prefs.get("user", "");
		String password = prefs.get("password", "");
		Integer port = prefs.getInt("port", 3306);
		prefsDialog.setDefault(user, password, port);

		// Load data in table
		tablePanel.setData(controller.getPeople());

		// Show MianFrame
		setVisible(true);

		// Add Listeners
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				controller.disconnect();
				dispose();
				System.gc();
			}
		});

		tablePanel.setPersonTableListener(new PersonTableListener() {
			public void deleteRow(int row) {
				controller.removeRow(row);
			}
		});

		toolBar.setToolBarListener(new ToolBarListener() {

			public void saveClick() {
				connect();
				save();
			}

			public void refreshClick() {
				connect();
				refresh();
			}
		});

		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				controller.addPerson(e);
				tablePanel.refresh();
			}
		});

		prefsDialog.setPreferencesListener(new PrefsListener() {
			public void SetPreferences(String user, String password,
					Integer port) {
				prefs.put("user", user);
				prefs.put("password", password);
				prefs.putInt("port", port);
			}
		});

	}

	private void connect() {
		try {
			controller.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainFrame.this,
					"Can not connect to Database", "Database Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void save() {
		try {
			controller.save();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(MainFrame.this,
					"Can not save to Database", "Database Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void refresh() {
		try {
			controller.load();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(MainFrame.this,
					"Can not load from Database", "Database Error",
					JOptionPane.ERROR_MESSAGE);
		}
		tablePanel.refresh();
	}

	private JMenuBar createMenuBar() {

		// Create menu Bar, menu items and sub menu-items
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataItem = new JMenuItem("Export Item...");
		JMenuItem importDataItem = new JMenuItem("Import Item...");
		JMenuItem exitItem = new JMenuItem("Exit");
		JMenu windowMenu = new JMenu("Window");
		JMenu inputForm = new JMenu("Input Form");
		JCheckBoxMenuItem showForm = new JCheckBoxMenuItem("Show");
		JMenuItem prefsItem = new JMenuItem("Preferences...");

		// Add menu items to menu bar, add sub-menu items to menu items
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		windowMenu.add(inputForm);
		windowMenu.add(prefsItem);
		inputForm.add(showForm);
		showForm.setSelected(true);

		// Add Mnemonics
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);

		// Add Accelerator keys
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.CTRL_MASK));
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				ActionEvent.CTRL_MASK));

		// Add Action Listeners to menu items or sub-menu items
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rtn = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do you really want to exit", "Conform exit",
						JOptionPane.OK_CANCEL_OPTION);

				if (rtn == JOptionPane.OK_OPTION) {
					WindowListener[] listeners = getWindowListeners();
					for (WindowListener l : listeners) {
						l.windowClosing(new WindowEvent(MainFrame.this, 0));
					}
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

		showForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
				if (menuItem.isSelected()) {
					splitPane.setDividerLocation((int) formPanel
							.getMinimumSize().getWidth());
				}
				formPanel.setVisible(menuItem.isSelected());
			}
		});

		prefsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefsDialog.setVisible(true);
			}
		});

		return menuBar;
	}
}