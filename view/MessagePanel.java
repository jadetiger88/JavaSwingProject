package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;


public class MessagePanel extends JPanel {

	private JTree serverTree;
	private ServerTreeCellRenderer treeCellRenderer;
	private ServerTreeCellEditor treeCellEditor;

	public MessagePanel() {
		serverTree = new JTree(createTreeNode());
		treeCellRenderer = new ServerTreeCellRenderer();
		treeCellEditor = new ServerTreeCellEditor();

		serverTree.setCellRenderer(treeCellRenderer);
		serverTree.setCellEditor(treeCellEditor);
		serverTree.setEditable(true);
		serverTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

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

		setLayout(new BorderLayout());
		add(new JScrollPane(serverTree), BorderLayout.CENTER);
	}

	private DefaultMutableTreeNode createTreeNode() {

		int id = 0;

		DefaultMutableTreeNode servers = new DefaultMutableTreeNode("Servers");

		DefaultMutableTreeNode china = new DefaultMutableTreeNode("China");
		DefaultMutableTreeNode shanghai = new DefaultMutableTreeNode(
				new ServerInfo("Shanghai", id++, true));
		DefaultMutableTreeNode beijing = new DefaultMutableTreeNode(
				new ServerInfo("Beijing", id++));
		DefaultMutableTreeNode nanjing = new DefaultMutableTreeNode(
				new ServerInfo("Nanjing", id++));
		DefaultMutableTreeNode shzhou = new DefaultMutableTreeNode(
				new ServerInfo("Shzhou", id++));
		DefaultMutableTreeNode Wuhan = new DefaultMutableTreeNode(
				new ServerInfo("Wuhan", id++));

		DefaultMutableTreeNode uk = new DefaultMutableTreeNode("UK");
		DefaultMutableTreeNode london = new DefaultMutableTreeNode(
				new ServerInfo("London", id++));
		DefaultMutableTreeNode edinburg = new DefaultMutableTreeNode(
				new ServerInfo("Edinburg", id++));

		DefaultMutableTreeNode usa = new DefaultMutableTreeNode("USA");
		DefaultMutableTreeNode newYork = new DefaultMutableTreeNode(
				new ServerInfo("New York", id++));
		DefaultMutableTreeNode boston = new DefaultMutableTreeNode(
				new ServerInfo("Boston", id++));
		DefaultMutableTreeNode losAngele = new DefaultMutableTreeNode(
				new ServerInfo("Los Angeles", id++));

		servers.add(china);
		servers.add(uk);
		servers.add(usa);
		usa.add(newYork);
		usa.add(boston);
		usa.add(losAngele);
		uk.add(london);
		uk.add(edinburg);
		china.add(shanghai);
		china.add(beijing);
		china.add(Wuhan);
		china.add(nanjing);
		china.add(shzhou);

		return servers;
	}
}
