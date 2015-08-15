package edu.utn.gestion.ui.util;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public abstract class GenericTableModel<T> extends AbstractTableModel {
    private final String[] columnNames;
    protected final List<T> objectList = new ArrayList<T>();

    public GenericTableModel(String[] columnNames) {
        this.columnNames = columnNames;
    }
    
    public void addObject(T object) {
        int row = this.objectList.size();
        this.objectList.add(row, object);
        this.fireTableRowsInserted(row, row);
    }
    
    public void removeObject(int rowIndex) {
        this.objectList.remove(rowIndex);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public T getObject(int rowIndex) {
        return this.objectList.get(rowIndex);
    }
    
    public void setObjectList(List<T> objectList) {
        this.objectList.clear();
        this.objectList.addAll(objectList);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.objectList.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }
}
