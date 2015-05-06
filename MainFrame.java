import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private TextPanel textPanel;
	private ToolBar toolBar;
	private FormPanel formPanel;

	public MainFrame() {
		
		super("Hello World");
		setLayout(new BorderLayout());
		textPanel = new TextPanel();
		formPanel = new FormPanel();
		
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
				textPanel.append(name + " : " + occupation + " : " + ageCat + empType + taxID + "\n"); 
			}
		});
		
		add(formPanel, BorderLayout.WEST);
		add(toolBar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}