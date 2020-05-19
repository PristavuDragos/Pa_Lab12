package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ControlPanel extends JPanel {
    final MainFrame mainFrame;
    JButton addButton = new JButton("Add");
    JButton saveButton = new JButton("Save");
    JButton loadButton = new JButton( "Load");
    JLabel label1 = new JLabel("Component name: ");
    JLabel label2 = new JLabel("Width: ");
    JLabel label3 = new JLabel("Height: ");
    JLabel label4 = new JLabel("Default text: ");
    JLabel label5 = new JLabel("PosX");
    JLabel label6 = new JLabel("PosY");
    JTextField inputClassName = new JTextField("class name");
    JTextField inputWidth = new JTextField("width");
    JTextField inputHeight = new JTextField("height");
    JTextField inputText = new JTextField("default text");
    JTextField inputPosX = new JTextField("posX");
    JTextField inputPosY = new JTextField("posY");

    public ControlPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        init();
    }

    private void init(){
        add(label1);
        add(inputClassName);
        add(label5);
        add(inputPosX);
        add(label6);
        add(inputPosY);
        add(label2);
        add(inputWidth);
        add(label3);
        add(inputHeight);
        add(label4);
        add(inputText);
        add(addButton);
        add(saveButton);
        add(loadButton);
        addButton.addActionListener(this::addComponent);
        saveButton.addActionListener(this::save);
        loadButton.addActionListener(this::load);
    }

    private void addComponent(ActionEvent e){
        try {
            Class temp = Class.forName("javax.swing."+inputClassName.getText());
            Component newComponent =(Component) temp.getConstructor().newInstance();
            Insets insets = mainFrame.designPanel.insets;
            newComponent.setBounds(insets.left+Integer.parseInt(inputPosX.getText()),insets.top+Integer.parseInt(inputPosY.getText()),
                    Integer.parseInt(inputWidth.getText()),Integer.parseInt(inputHeight.getText()));
            Method[] methods= temp.getMethods();
            for (Method m : methods){
                if (m.getName().compareTo("setText")==0){
                    m.invoke(newComponent,inputText.getText());
                }
            }
            mainFrame.designPanel.componentList.add(newComponent);
            mainFrame.designPanel.update();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }

    private void save (ActionEvent e){
        String fileName = "save.xml";
        XMLEncoder encoder = null;
        try{
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
        }catch(FileNotFoundException fileNotFound){
            System.out.println("ERROR: While Creating or Opening the File dvd.xml");
        }
        encoder.writeObject(this.mainFrame.designPanel.componentList);
        encoder.close();
    }

    private void load (ActionEvent e){
        String fileName = "save.xml";
        XMLDecoder decoder = null;
        try {
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("ERROR: File dvd.xml not found");
        }
        this.mainFrame.designPanel.componentList= (List<Component>) decoder.readObject();
        this.mainFrame.designPanel.update();
        decoder.close();
    }
}
