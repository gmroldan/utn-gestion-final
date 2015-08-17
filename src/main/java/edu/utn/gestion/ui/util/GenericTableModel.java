package edu.utn.gestion.ui.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.table.AbstractTableModel;

public abstract class GenericTableModel<T> extends AbstractTableModel {
    private final String[] columnNames;
    protected final List<T> objectList = new ArrayList<T>();
    protected final Set<T> selectedObjects = new HashSet<T>();

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
    
    public void refreshSelectedObjects(int[] selectedRows) {
        this.selectedObjects.clear();
        
        for (int index : selectedRows) {
            this.selectedObjects.add(this.getObject(index));
        }
    }
    
    public Set<T> getSelectedObjects() {
        return this.selectedObjects;
    }
    
    public void setObjectList(List<T> objectList) {
        this.selectedObjects.clear();
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
