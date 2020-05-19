package com.company;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    ControlPanel controlPanel;
    DesignPanel designPanel;
    PropertiesPanel propertiesPanel;

    public MainFrame() {
        super("App");
        init();
    }

    private void init(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.controlPanel = new ControlPanel(this);
        this.designPanel = new DesignPanel(this);
        this.propertiesPanel = new PropertiesPanel(this);
        add(controlPanel,BorderLayout.NORTH);
        add(designPanel,BorderLayout.CENTER);
        add(propertiesPanel,BorderLayout.EAST);
        pack();
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}
