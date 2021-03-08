package cn.zhou;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AAAA extends JFrame {
    JButton b0 = new JButton("0");
    JButton b1 = new JButton("1");
    JButton b2 = new JButton("2");
    JButton b3 = new JButton("3");

    AAAA() {
        setSize(400, 400);
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.weightx = 60;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(b0, gbc);
        GridBagConstraints gbc_1 = new GridBagConstraints();
        gbc_1.gridx = 0;
        gbc_1.gridy = 1;
        gbc_1.gridheight = 4;
        gbc_1.weighty = 40;
        gbc_1.fill = GridBagConstraints.VERTICAL;
        this.add(b1, gbc_1);
        GridBagConstraints gbc_2 = new GridBagConstraints();
        gbc_2.gridx = 1;
        gbc_2.gridy = 1;
        gbc_2.gridwidth = 5;
        gbc_2.gridheight = 4;
        gbc_2.weightx = 50;
        gbc_2.weighty = 40;
        gbc_2.fill = GridBagConstraints.BOTH;
        this.add(b2, gbc_2);
        GridBagConstraints gbc_3 = new GridBagConstraints();
        gbc_3.gridx = 0;
        gbc_3.gridy = 5;
        gbc_3.gridwidth = 6;
        gbc_3.weightx = 60;
        gbc_3.fill = GridBagConstraints.HORIZONTAL;
        this.add(b3, gbc_3);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new AAAA();
    }
}