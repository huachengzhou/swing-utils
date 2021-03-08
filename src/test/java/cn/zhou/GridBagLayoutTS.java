package cn.zhou;

import javax.swing.*;
import java.awt.*;

public class GridBagLayoutTS extends JFrame {
    public GridBagLayoutTS() {
        JPanel p = new JPanel();
        JPanel user = new JPanel();
        user.add(new JLabel("用户名:"));
        user.add(new JTextField(20));
        JPanel pwd = new JPanel();
        pwd.add(new JLabel("密    码:"));
        pwd.add(new JTextField(20));
        JPanel btn = new JPanel();
        btn.add(new JButton("登录"));
        btn.add(new JButton("退出"));

        p.setLayout(new GridLayout(3, 1));
        p.add(user);
        p.add(pwd);
        p.add(btn);

        setLayout(new GridBagLayout());
        add(new JPanel(), new GridBagConstraints(0, 0, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0),
                0, 0));
        add(new JPanel(), new GridBagConstraints(0, 1, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0),
                0, 0));
        add(p, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0),
                0, 0));
        add(new JPanel(), new GridBagConstraints(2, 1, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0),
                0, 0));
        add(new JPanel(), new GridBagConstraints(0, 2, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0),
                0, 0));
        setLocationRelativeTo(null);
        setSize(new Dimension(800, 500));
        setVisible(true);
    }

    public static void main(String[] args) {
        new GridBagLayoutTS();
    }
}
