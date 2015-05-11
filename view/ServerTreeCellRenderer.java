package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class ServerTreeCellRenderer implements TreeCellRenderer {

	private JCheckBox leafRenderer;
	private DefaultTreeCellRenderer noneLeafRenderer;

	private Color textForeground;
	private Color textBackground;
	private Color selectionForeground;
	private Color selectionBackground;

	ServerTreeCellRenderer() {
		leafRenderer = new JCheckBox();

		noneLeafRenderer = new DefaultTreeCellRenderer();
		noneLeafRenderer.setLeafIcon(Utile.createIcon("/images/Server16.gif"));
		noneLeafRenderer.setOpenIcon(Utile
				.createIcon("/images/WebComponent16.gif"));
		noneLeafRenderer.setClosedIcon(Utile
				.createIcon("/images/WebComponentAdd16.gif"));

		textForeground = UIManager.getColor("Tree.textForeground");
		textBackground = UIManager.getColor("Tree.textBackground");
		selectionForeground = UIManager.getColor("Tree.selectionForeground");
		selectionBackground = UIManager.getColor("Tree.selectionBackground");
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		if (leaf) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			ServerInfo obj = (ServerInfo) node.getUserObject();
			leafRenderer.setText(obj.toString());
			leafRenderer.setSelected(obj.isChecked());
			if (selected) {
				leafRenderer.setBackground(selectionBackground);
				leafRenderer.setForeground(selectionForeground);
			} else {
				leafRenderer.setBackground(textBackground);
				leafRenderer.setForeground(textForeground);
			}

			return leafRenderer;
		} else {
			return noneLeafRenderer.getTreeCellRendererComponent(tree, value,
					selected, expanded, leaf, row, hasFocus);
		}
	}
}
