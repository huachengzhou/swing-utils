package cn.form;

import javax.swing.*;

public class GuiFormDemoA {
    private JButton button1;
    private JPanel panel1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GuiFormDemoA");
        frame.setContentPane(new GuiFormDemoA().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
