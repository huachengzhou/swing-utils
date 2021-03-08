package com.zhou.p0103;

import javax.swing.*;
import java.awt.*;

public class SwingDemo2 {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame jFrame = new JFrame("窗体");
                jFrame.setVisible(true);
                jFrame.setSize(new Dimension(400, 400));

                jFrame.setBackground(Color.BLACK);

                Container contentPane = jFrame.getContentPane();
                contentPane.add(new JButton("按钮A")) ;
            }
        });
    }
}
