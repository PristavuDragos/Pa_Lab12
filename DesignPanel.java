package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DesignPanel extends JPanel {
    final MainFrame mainFrame;
    final static int W = 800, H = 600;
    Insets insets;

    public DesignPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        init();
    }


    private void init(){
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        insets=getInsets();
        setLayout(null);
    }
}
