package com.swing;

public class JButton extends javax.swing.JButton {

    public JButton(int width, int height) {
        super();
        this.setSize(width, height);
    }

    public JButton(String text, int width, int height) {
        super();
        this.setSize(width, height);
        this.setText(text);
    }

    public JButton() {
        super();
    }

    public JButton(String text) {
        super();
        this.setText(text);
    }


}
