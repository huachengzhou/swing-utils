package com.swing;


public class JLabel extends javax.swing.JLabel {

    public JLabel(int width, int height) {
        super();
        this.setSize(width, height);
    }

    public JLabel(String text, int width, int height) {
        super();
        this.setSize(width, height);
        this.setText(text);
    }

    public JLabel() {
    }

    public JLabel(String text) {
        this.setText(text);
    }


}
