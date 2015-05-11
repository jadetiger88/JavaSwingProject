package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ToolBar extends JPanel implements ActionListener {
	private JButton saveButton;
	private JButton refreshButton;
	private ToolBarListener toolBarListener;

	public ToolBar() {

		saveButton = new JButton("save");
		saveButton.setIcon(createIcon("../images/Save16.gif"));
		refreshButton = new JButton("refresh");
		refreshButton.setIcon(createIcon("../images/Refresh16.gif"));
		setBorder(BorderFactory.createEtchedBorder());
		saveButton.addActionListener(this);
		refreshButton.addActionListener(this);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(saveButton);
		add(refreshButton);
	}

	public void setToolBarListener(ToolBarListener toolBarListener) {
		this.toolBarListener = toolBarListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clicked = (JButton) e.getSource();
		if (clicked == saveButton) {
			if (toolBarListener != null) {
				toolBarListener.saveClick();
			}
		} else if (clicked == refreshButton) {
			if (toolBarListener != null) {
				toolBarListener.refreshClick();
			}
		}
	}

	private ImageIcon createIcon(String path) {
		URL url = getClass().getResource(path);
		if (url == null) {
			System.err.println("Uanble to load icon image");
		}
		ImageIcon icon = new ImageIcon(url);
		return icon;
	}
}
