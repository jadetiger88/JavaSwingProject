package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class MessagePanel extends JPanel {

	private JTree serverTree;

	public MessagePanel() {
		serverTree = new JTree(createTreeNode());
		setLayout(new BorderLayout());
		add(new JScrollPane(serverTree), BorderLayout.CENTER);

	}

	private DefaultMutableTreeNode createTreeNode() {

		DefaultMutableTreeNode servers = new DefaultMutableTreeNode("Servers");

		DefaultMutableTreeNode usa = new DefaultMutableTreeNode("USA");
		DefaultMutableTreeNode newYork = new DefaultMutableTreeNode("New York");
		DefaultMutableTreeNode boston = new DefaultMutableTreeNode("Boston");
		DefaultMutableTreeNode losAngele = new DefaultMutableTreeNode(
				"Los Angeles");

		DefaultMutableTreeNode uk = new DefaultMutableTreeNode("UK");
		DefaultMutableTreeNode london = new DefaultMutableTreeNode("London");
		DefaultMutableTreeNode edinburg = new DefaultMutableTreeNode("Edinburg");

		servers.add(usa);
		servers.add(uk);
		usa.add(newYork);
		usa.add(boston);
		usa.add(losAngele);
		uk.add(london);
		uk.add(edinburg);

		return servers;
	}
}
