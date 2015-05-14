package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import model.EmploymentCategory;

public class EmploymentCategoryEditorr extends AbstractCellEditor implements
		TableCellEditor {

	private JComboBox comboBox;

	public EmploymentCategoryEditorr() {
		comboBox = new JComboBox(EmploymentCategory.values());
	}

	@Override
	public Object getCellEditorValue() {
		return comboBox.getSelectedItem();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		comboBox.setSelectedItem(value);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fireEditingStopped();
			}

		});
		return comboBox;
	}

	@Override
	public boolean isCellEditable(EventObject e) {
		return true;
	}

}
