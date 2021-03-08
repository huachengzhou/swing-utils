package com.zhou.p0103.woodpecker;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class TestJTabbedPane {

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(600, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        //创建一个面板
        final JTabbedPane pane = new JTabbedPane();

        //创建1
        pane.addTab("个人信息", createPanel("阮老师"));

        //创建2
        pane.addTab("教育经历", createPanel("离清华1000公里的大学"));
        //插入指定位置
        //pane.insertTab("教育经历2", new ImageIcon("image/title.png"), createPanel("离华为2000公里的公司"), "OKK", 0);
        //移除
        //pane.remove(0);

        //创建3 标题加图标
        //pane.addTab("工作经历",new ImageIcon("image/title.png"), createPanel("离华为2000公里的公司"));
        pane.addTab("工作经历1", createPanel("离华为2000公里的公司"));
        pane.addTab("工作经历2", createPanel("离华为2000公里的公司"));
        pane.addTab("工作经历3", createPanel("离华为2000公里的公司"));
        pane.addTab("工作经历4", createPanel("离华为2000公里的公司"));
        pane.addTab("工作经历5", createPanel("离华为2000公里的公司"));
        pane.addTab("工作经历6", createPanel("离华为2000公里的公司"));
        pane.addTab("工作经历7", createPanel("离华为2000公里的公司"));

        //添加监听
        pane.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("当前选中了..." + pane.getSelectedIndex());
            }
        });
        //pane.setTabPlacement(JTabbedPane.LEFT);//tabbed标题位置
        pane.setTabPlacement(JTabbedPane.BOTTOM);
        //pane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);//滚动条
        pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);//换行包起来

        //第四个选项卡显示
        pane.setSelectedIndex(3);

        jf.setContentPane(pane);
        jf.setVisible(true);
    }


    public static JPanel createPanel(String text) {
        //创建面板 一行一列 网络布局
        JPanel panel = new JPanel(new GridLayout(1, 1));
        //
        JButton btn = new JButton(text);
        btn.setFont(new Font("宋体", Font.PLAIN, 18));
        btn.setHorizontalAlignment(SwingConstants.CENTER);//内容居中
        panel.add(btn);
        return panel;
    }

}
