package view;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;

public class ServerTreeCellEditor extends AbstractCellEditor implements
		TreeCellEditor {

	private ServerTreeCellRenderer renderer;
	private JCheckBox checkBox;
	private ServerInfo info;

	public ServerTreeCellEditor() {
		renderer = new ServerTreeCellRenderer();
	}

	@Override
	public Object getCellEditorValue() {

		info.setChecked(checkBox.isSelected());

		return info;
	}

	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value,
			boolean isSelected, boolean expanded, boolean leaf, int row) {

		Component component = renderer.getTreeCellRendererComponent(tree,
				value, isSelected, expanded, leaf, row, true);

		if (leaf) {
			DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;
			info = (ServerInfo) treeNode.getUserObject();
			checkBox = (JCheckBox) component;
			checkBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					fireEditingStopped();
					checkBox.removeItemListener(this);
				}

			});
		}

		return component;
	}

	@Override
	public boolean isCellEditable(EventObject e) {

		if (!(e instanceof MouseEvent))
			return false;
		MouseEvent me = (MouseEvent) e;
		JTree tree = (JTree) me.getSource();
		TreePath path = tree.getPathForLocation(me.getX(), me.getY());
		if (path == null)
			return false;
		Object lastComponent = path.getLastPathComponent();
		if (lastComponent == null)
			return false;
		return (((DefaultMutableTreeNode) lastComponent).isLeaf());
	}

}
