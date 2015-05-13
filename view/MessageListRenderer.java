package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import model.Message;

/*
 * *This demonstrate the use of an arbitrary component as list renderer. The JLabel could have
 * been use in this case rather than wrapping label within a JPanel.
 */

public class MessageListRenderer implements ListCellRenderer {

	private JPanel panel;
	private JLabel label;
	private Color selectedColor;
	private Color normalColor;

	public MessageListRenderer() {
		panel = new JPanel();
		label = new JLabel();
		selectedColor = new Color(210, 210, 255);
		normalColor = Color.WHITE;

		label.setIcon(Utile.createIcon("/images/Information24.gif"));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(label);

	}

	// list - The JList we're painting.
	// value - The value returned by list.getModel().getElementAt(index).
	// index - The cells index.
	// isSelected - True if the specified cell was selected.
	// cellHasFocus - True if the specified cell has the focus.
	// Returns:
	// A component whose paint() method will render the specified value
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {

		Message message = (Message) value;
		label.setText(message.getTitle());
		panel.setBackground(cellHasFocus ? selectedColor : normalColor);

		return panel;
	}

}
