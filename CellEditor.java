package com.company;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CellEditor {
    int localIndex;
    JTable table;
    TableCellEditor tableCellEditor;
    PropertyDescriptor pd;
    Component component;

    public CellEditor(int localIndex, JTable table, PropertyDescriptor pd, Component component) {
        this.localIndex = localIndex;
        this.table = table;
        this.pd = pd;
        this.component = component;
        init();
    }
    private void init(){
        tableCellEditor = table.getCellEditor(localIndex,1);
        CellEditorListener cellEditorListener = new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                Method[] methods= component.getClass().getMethods();
                for (Method m : methods){
                    if (m.getName().compareTo("setText")==0){
                        try {
                            m.invoke(component,table.getValueAt(2,1));
                        } catch (IllegalAccessException | InvocationTargetException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                component.setBounds(Integer.parseInt((String) table.getValueAt(4,1)),Integer.parseInt((String) table.getValueAt(5,1)),
                        Integer.parseInt((String) table.getValueAt(3,1)),Integer.parseInt((String)table.getValueAt(1,1)));
            }
            @Override
            public void editingCanceled(ChangeEvent e) {
                Method[] methods= component.getClass().getMethods();
                for (Method m : methods){
                    if (m.getName().compareTo("setText")==0){
                        try {
                            m.invoke(component,table.getValueAt(2,1));
                        } catch (IllegalAccessException | InvocationTargetException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                component.setBounds(Integer.parseInt((String) table.getValueAt(4,1)),Integer.parseInt((String) table.getValueAt(5,1)),
                        Integer.parseInt((String) table.getValueAt(3,1)),Integer.parseInt((String)table.getValueAt(1,1)));
            }
        };
        tableCellEditor.addCellEditorListener(cellEditorListener);
    }
}
