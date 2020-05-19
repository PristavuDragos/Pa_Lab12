package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;


public class DesignPanel extends JPanel {
    final MainFrame mainFrame;
    final static int W = 800, H = 600;
    Insets insets;
    FocusListener focusListener = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            mainFrame.propertiesPanel.display(e.getComponent());
        }
        @Override
        public void focusLost(FocusEvent e) {}
    };
    List<Component> componentList;

    public DesignPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        init();
    }

    private void init(){
        this.componentList = new ArrayList<>();
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        insets=getInsets();
        setLayout(null);
    }

    public void update(){
        this.removeAll();
        for (Component c : componentList){
            this.add(c);
            c.addFocusListener(focusListener);
        }
        this.updateUI();
    }
}
