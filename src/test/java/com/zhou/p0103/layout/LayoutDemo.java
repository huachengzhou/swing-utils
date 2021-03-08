package com.zhou.p0103.layout;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LayoutDemo {
    /**
     * @author liuyan
     */
    private static class MyFrame {

        JFrame jframe = new JFrame("搜索");
        JTextField textField = new JTextField(20);
        JButton jButton = new JButton("转到");
        JButton jButton2 = new JButton("转到2");
        JButton jButton3 = new JButton("转到3");
        JButton jButton4 = new JButton("转到4");
        JTextArea textArea = new JTextArea();
        JPanel jPanel = new JPanel(new BorderLayout(5, 20));
        JPanel jPanel2 = new JPanel(new BorderLayout());
        JPanel jPanel3 = new JPanel(new BorderLayout());
        JPanel jPanel4 = new JPanel(new BorderLayout());


        /**
         *
         */
        public MyFrame() {

            init();
        }

        /**
         *
         */
        private void init() {
            jframe.setLayout(new BorderLayout());
            jframe.add(jPanel2, BorderLayout.SOUTH);
            jframe.add(jPanel3, BorderLayout.EAST);
            jframe.add(jPanel4, BorderLayout.WEST);
            jframe.add(jPanel, BorderLayout.NORTH);

            jPanel.add(jButton, BorderLayout.EAST);
            jPanel.add(textField, BorderLayout.CENTER);

            jPanel2.add(jButton2);
            jPanel3.add(jButton3);
            jPanel4.add(jButton4);

            jPanel.setPreferredSize(new Dimension(0, 150));
            jPanel2.setPreferredSize(new Dimension(0, 150));
            jPanel3.setPreferredSize(new Dimension(150, 0));
            jPanel4.setPreferredSize(new Dimension(150, 0));

            jframe.add(textArea, BorderLayout.CENTER);

            jframe.setVisible(true);
            FrameUtil2.initFram(jframe, 500, 700);
        }
    }

    private static class FrameUtil2 {
        public static void initFram(JFrame f, int width, int height) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            f.setVisible(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Dimension d = toolkit.getScreenSize();
            int x = (int) d.getWidth();
            int y = (int) d.getHeight();
            f.setBounds((x - width) / 2, (y - height) / 2, width, height);
        }
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}

