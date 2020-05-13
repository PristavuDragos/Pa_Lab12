package com.company;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    ControlPanel controlPanel;
    DesignPanel designPanel;

    public MainFrame() {
        super("App");
        init();
    }

    private void init(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.controlPanel = new ControlPanel(this);
        this.designPanel = new DesignPanel(this);
        add(controlPanel,BorderLayout.NORTH);
        add(designPanel,BorderLayout.CENTER);
        pack();
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}
