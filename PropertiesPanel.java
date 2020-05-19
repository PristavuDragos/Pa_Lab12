package com.company;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.beans.*;
import java.lang.reflect.InvocationTargetException;

public class PropertiesPanel extends JPanel{
    final MainFrame mainFrame;
    JTable table = new JTable();
    JScrollPane sp=new JScrollPane(table);
    int index;
    public PropertiesPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        table.putClientProperty("terminateEditOnFocusLost", true);
        table.setBounds(10,10,100,800);
        this.add(sp);
    }

    public void display(Component component) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(component.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            DefaultTableModel model = new DefaultTableModel();
            table = new JTable(model);
            model.addColumn("Property");
            model.addColumn("Value");
            index=0;
            String[][] data = new String[6][2];
            data[index][0] = "Class";
            data[index][1] = component.getClass().toString();
            model.addRow(data[0]);
            for (PropertyDescriptor pd : propertyDescriptors){
                switch (pd.getName()) {
                    case "text":
                        try {
                            index++;
                            data[index][1] = (String) pd.getReadMethod().invoke(component);
                            data[index][0] = "Text";
                            model.addRow(data[index]);
                            CellEditor cellEditor = new CellEditor(index,table,pd,component);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "x":
                        try {
                            index++;
                            data[index][1] = pd.getReadMethod().invoke(component).toString();
                            data[index][0] = "X";
                            model.addRow(data[index]);
                            CellEditor cellEditor = new CellEditor(index,table,pd,component);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "y":
                        try {
                            index++;
                            data[index][1] = pd.getReadMethod().invoke(component).toString();
                            data[index][0] = "Y";
                            model.addRow(data[index]);
                            CellEditor cellEditor = new CellEditor(index,table,pd,component);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "width":
                        try {
                            index++;
                            data[index][1] = pd.getReadMethod().invoke(component).toString();
                            data[index][0] = "Width";
                            model.addRow(data[index]);
                            CellEditor cellEditor = new CellEditor(index,table,pd,component);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "height":
                        try {
                            index++;
                            data[index][1] = pd.getReadMethod().invoke(component).toString();
                            data[index][0] = "Height";
                            model.addRow(data[index]);
                            CellEditor cellEditor = new CellEditor(index,table,pd,component);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
            remove(sp);
            sp = new JScrollPane(table);
            add(sp);
            updateUI();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }
}
