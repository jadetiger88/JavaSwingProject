package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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

		tablePanel.setData(controller.getPeople());

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
					System.out.println(fileChooser.getSelectedFile());
				}
			}

		});

		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile());
				}
			}

		});

		JMenu windowMenu = new JMenu("Window");
		JMenu show = new JMenu("Show");
		JCheckBoxMenuItem showForm = new JCheckBoxMenuItem("Show Form");
		show.add(showForm);
		windowMenu.add(show);
		showForm.setSelected(true);
		showForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
				formPanel.setVisible(menuItem.isSelected());
			}

		});

		menuBar.add(fileMenu);
		menuBar.add(windowMenu);

		return menuBar;
	}
}