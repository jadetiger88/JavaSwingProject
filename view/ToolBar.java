package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar implements ActionListener {
	private JButton saveButton;
	private JButton refreshButton;
	private ToolBarListener toolBarListener;

	public ToolBar() {

		saveButton = new JButton();
		saveButton.setIcon(Utile.createIcon("/images/Save16.gif"));
		saveButton.setToolTipText("save");
		refreshButton = new JButton();
		refreshButton.setIcon(Utile.createIcon("/images/Refresh16.gif"));
		refreshButton.setToolTipText("refresh");
		setBorder(BorderFactory.createEtchedBorder());
		saveButton.addActionListener(this);
		refreshButton.addActionListener(this);
		add(saveButton);
		addSeparator();
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

}
