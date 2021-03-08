package com.swing;

public class JTextField extends javax.swing.JTextField {

    public JTextField(int width, int height) {
        super();
        this.setSize(width, height);
    }

    public JTextField(String text, int width, int height) {
        super();
        this.setSize(width, height);
        this.setText(text);
    }

    public JTextField(int columns, int width, int height) {
        super();
        this.setSize(width, height);
        this.setColumns(columns);
    }

    public JTextField(int columns) {
        super();
        this.setColumns(columns);
    }

    public JTextField() {
        super();
    }

}
