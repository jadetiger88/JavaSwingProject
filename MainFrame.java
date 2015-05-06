import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class MainFrame extends JFrame {

	private TextPanel textPanel;
	private ToolBar toolBar;
	private FormPanel formPanel;

	public MainFrame() {

		super("Hello World");
		setLayout(new BorderLayout());
		textPanel = new TextPanel();
		formPanel = new FormPanel();
		setJMenuBar(createMenuBar());

		toolBar = new ToolBar();
		toolBar.setStringListener(new StringListener() {
			public void textEmitted(String text) {
				textPanel.append(text);
			}
		});

		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				String name = e.getName();
				String occupation = e.getOccupation();
				int ageCat = e.getAgeCategory();
				String empType = e.getEmployeeType();
				String taxID = e.getTaxID();
				String gender = e.getGender();
				textPanel.append(name + " : " + occupation + " : " + ageCat
						+ " " + empType + " " + taxID + " " + gender + " "
						+ "\n");
			}
		});

		add(formPanel, BorderLayout.WEST);
		add(toolBar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);
		setMinimumSize(new Dimension(500, 400));
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private JMenuBar createMenuBar() {

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDatatem = new JMenuItem("Export Item...");
		JMenuItem importDataItem = new JMenuItem("Import Item...");
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exportDatatem);
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