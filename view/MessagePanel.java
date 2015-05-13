package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import model.Message;
import controller.MessageServer;

public class MessagePanel extends JPanel implements ProgressDialogListener {

	private JTree serverTree;
	private ServerTreeCellRenderer treeCellRenderer;
	private ServerTreeCellEditor treeCellEditor;
	private Set<Integer> selectedServers;
	private MessageServer messageServer;
	private ProgressDialog progressDialog;
	private SwingWorker<List<Message>, Integer> worker;
	private TextPanel textPanel;
	private JList messageList;
	private JScrollPane leftPane;
	private JSplitPane rightPane;
	private JSplitPane pane;

	public MessagePanel(JFrame parent) {
		progressDialog = new ProgressDialog(parent, "Dowloading message(s)...");
		textPanel = new TextPanel();
		messageList = new JList();
		messageServer = new MessageServer();
		selectedServers = new TreeSet<Integer>();
		selectedServers.add(0);
		selectedServers.add(3);
		selectedServers.add(5);
		selectedServers.add(6);
		selectedServers.add(8);

		serverTree = new JTree(createTreeNode());
		treeCellRenderer = new ServerTreeCellRenderer();
		treeCellEditor = new ServerTreeCellEditor();

		serverTree.setCellRenderer(treeCellRenderer);
		serverTree.setCellEditor(treeCellEditor);
		serverTree.setEditable(true);
		serverTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		progressDialog.setListener(this);
		treeCellEditor.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingCanceled(ChangeEvent arg0) {
			}

			@Override
			public void editingStopped(ChangeEvent e) {
				ServerInfo info = (ServerInfo) treeCellEditor
						.getCellEditorValue();
				System.out.println(info + " is checked : " + info.isChecked());
				int id = info.getId();
				if (info.isChecked()) {
					selectedServers.add(id);
				} else {
					selectedServers.remove(id);
				}

				messageServer.setSelectedServer(selectedServers);

				retrieveMessage();
			}

		});

		// Set component position
		rightPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, messageList,
				textPanel);
		leftPane = new JScrollPane(serverTree);
		pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);

		// Set component size
		messageList.setMinimumSize(new Dimension(0, 100));
		textPanel.setMinimumSize(new Dimension(0, 150));
		leftPane.setMinimumSize(new Dimension(100, 0));
		pane.setResizeWeight(0.1);

		// Layout the component
		setLayout(new BorderLayout());
		add(pane, BorderLayout.CENTER);

	}

	private void retrieveMessage() {

		progressDialog.setMaximum(messageServer.getMessageCount());
		progressDialog.setValue(0);

		progressDialog.setVisible(true);

		worker = new SwingWorker<List<Message>, Integer>() {

			protected void done() {

				progressDialog.setVisible(false);
				if (isCancelled() == true)
					return;

				try {
					List<Message> retrievedMessage = get();
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			protected void process(List<Integer> countList) {

				int numMsg = countList.get(countList.size() - 1);
				progressDialog.setValue(numMsg);
			}

			protected List<Message> doInBackground() throws Exception {

				Integer count = 0;
				List<Message> retrievedMessage = new ArrayList<Message>();
				for (Message message : messageServer) {
					if (isCancelled() == true)
						break;
					retrievedMessage.add(message);
					count++;
					publish(count);
				}
				return retrievedMessage;
			}
		};

		worker.execute();
	}

	private DefaultMutableTreeNode createTreeNode() {

		int id = 0;

		DefaultMutableTreeNode servers = new DefaultMutableTreeNode("Servers");

		DefaultMutableTreeNode china = new DefaultMutableTreeNode("China");
		DefaultMutableTreeNode shanghai = new DefaultMutableTreeNode(
				new ServerInfo("Shanghai", id++,
						selectedServers.contains(id - 1)));
		DefaultMutableTreeNode beijing = new DefaultMutableTreeNode(
				new ServerInfo("Beijing", id++,
						selectedServers.contains(id - 1)));
		DefaultMutableTreeNode nanjing = new DefaultMutableTreeNode(
				new ServerInfo("Nanjing", id++,
						selectedServers.contains(id - 1)));
		DefaultMutableTreeNode shzhou = new DefaultMutableTreeNode(
				new ServerInfo("Shzhou", id++, selectedServers.contains(id - 1)));
		DefaultMutableTreeNode Wuhan = new DefaultMutableTreeNode(
				new ServerInfo("Wuhan", id++, selectedServers.contains(id - 1)));

		DefaultMutableTreeNode uk = new DefaultMutableTreeNode("UK");
		DefaultMutableTreeNode london = new DefaultMutableTreeNode(
				new ServerInfo("London", id++, selectedServers.contains(id - 1)));
		DefaultMutableTreeNode edinburg = new DefaultMutableTreeNode(
				new ServerInfo("Edinburg", id++,
						selectedServers.contains(id - 1)));

		DefaultMutableTreeNode usa = new DefaultMutableTreeNode("USA");
		DefaultMutableTreeNode newYork = new DefaultMutableTreeNode(
				new ServerInfo("New York", id++,
						selectedServers.contains(id - 1)));
		DefaultMutableTreeNode boston = new DefaultMutableTreeNode(
				new ServerInfo("Boston", id++, selectedServers.contains(id - 1)));
		DefaultMutableTreeNode losAngele = new DefaultMutableTreeNode(
				new ServerInfo("Los Angeles", id++,
						selectedServers.contains(id - 1)));

		servers.add(china);
		servers.add(uk);
		servers.add(usa);
		china.add(shanghai);
		china.add(beijing);
		china.add(nanjing);
		china.add(shzhou);
		china.add(Wuhan);
		uk.add(london);
		uk.add(edinburg);
		usa.add(newYork);
		usa.add(boston);
		usa.add(losAngele);

		return servers;
	}

	@Override
	public void progressDialogCancelled() {
		if (worker != null) {
			worker.cancel(true);
		}
	}
}
