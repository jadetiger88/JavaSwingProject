import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ToolBar extends JPanel implements ActionListener {
	private JButton helloButton;
	private JButton goodbyeButton;
	private StringListener stringListener;

	public ToolBar() {

		helloButton = new JButton("Hello");
		goodbyeButton = new JButton("Goodbye");
		setBorder(BorderFactory.createEtchedBorder());
		helloButton.addActionListener(this);
		goodbyeButton.addActionListener(this);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(helloButton);
		add(goodbyeButton);
	}

	public void setStringListener(StringListener stringListner) {
		this.stringListener = stringListner;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clicked = (JButton) e.getSource();
		if (clicked == helloButton) {
			if (stringListener != null) {
				stringListener.textEmitted("Hello Button Clicked\n");
			}
		} else {
			if (stringListener != null) {
				stringListener.textEmitted("Goodbye Button Clicked\n");
			}
		}
	}
}
