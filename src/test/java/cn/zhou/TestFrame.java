package cn.zhou;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TestFrame extends JFrame {
    public ArrayList list = new ArrayList();
    public JPanel analysePanel = new JPanel();

    public void showME() {
        this.setSize(300, 200);
        this.setTitle("测试");
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        list.add("h");
        JLabel label;
        analysePanel.setLayout(new GridBagLayout());
        for (int i = 0; i < list.size(); i++) {
            GridBagConstraints gbc = new GridBagConstraints();
            String name = list.get(i).toString();
            label = new JLabel(name);
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.insets = new Insets(2, 10, 2, 2);
            gbc.fill = GridBagConstraints.VERTICAL;
            analysePanel.add(label, gbc);
        }
        this.add(analysePanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        TestFrame tf = new TestFrame();
        tf.showME();
    }
}