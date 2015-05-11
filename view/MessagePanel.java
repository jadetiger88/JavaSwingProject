package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

class ServerInfo {
	int id;
	String name;

	ServerInfo(String name, int id) {

		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return name;
	}

}

public class MessagePanel extends JPanel {

	private JTree serverTree;

	public MessagePanel() {
		serverTree = new JTree(createTreeNode());

		serverTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		setLayout(new BorderLayout());
		add(new JScrollPane(serverTree), BorderLayout.CENTER);

		serverTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) serverTree
						.getLastSelectedPathComponent();
				Object server = (Object) node.getUserObject();
				Integer id = server instanceof ServerInfo ? ((ServerInfo) server)
						.getId() : null;
				System.out.println(server + ";		ID: " + id);
			}

		});

	}

	private DefaultMutableTreeNode createTreeNode() {

		int id = 0;

		DefaultMutableTreeNode servers = new DefaultMutableTreeNode("Servers");

		DefaultMutableTreeNode usa = new DefaultMutableTreeNode("USA");
		DefaultMutableTreeNode newYork = new DefaultMutableTreeNode(
				new ServerInfo("New York", id++));
		DefaultMutableTreeNode boston = new DefaultMutableTreeNode(
				new ServerInfo("Boston", id++));
		DefaultMutableTreeNode losAngele = new DefaultMutableTreeNode(
				new ServerInfo("Los Angeles", id++));

		DefaultMutableTreeNode uk = new DefaultMutableTreeNode("UK");
		DefaultMutableTreeNode london = new DefaultMutableTreeNode(
				new ServerInfo("London", id++));
		DefaultMutableTreeNode edinburg = new DefaultMutableTreeNode(
				new ServerInfo("Edinburg", id++));

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
