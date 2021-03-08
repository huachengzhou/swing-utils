package com.zhou.p0103.layout;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 测试内部窗口
 *
 * @author wenber
 */
public class TestJInternalFrame {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        //设置样式 为当前系统的
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        //创建窗口
        JFrame jf = new JFrame("内部窗口");
        jf.setSize(600, 400);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //创建桌面面板
        final JDesktopPane desk = new JDesktopPane();

        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("基本资料管理");
        JMenuItem hrItem = new JMenuItem("人事管理");
        hrItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //开始创建内部窗口
                System.out.println("==");
                JInternalFrame frame = createInternalFrame();
                //将内部窗口添加到虚拟面板中
                desk.add(frame);
                //设置当前这个为选中状态
                try {
                    frame.setSelected(true);
                } catch (PropertyVetoException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //组合
        menu.add(hrItem);
        bar.add(menu);
        //将菜单栏装载到窗口中
        jf.setJMenuBar(bar);

        //将桌面面板添加到窗口中
        jf.setContentPane(desk);
        jf.setVisible(true);
    }

    /**
     * 测试用于创建内部窗口
     */
    public static JInternalFrame createInternalFrame() {
        //创建内部窗口
        JInternalFrame frame = new JInternalFrame(
                "人事管理",
                true,//是否最大化
                true,//是否可关闭
                true,//最大
                true //是否显示图标
        );
        //设置大小
        frame.setSize(300, 150);
        //位置
        frame.setLocation(50, 50);
        //创建面板
        JPanel pane = new JPanel();
        pane.add(new JLabel("人事"));
        pane.add(new JButton("确定"));
        //将面面板添加到内部窗口中
        frame.setContentPane(pane);
        //可见
        frame.setVisible(true);
        return frame;
    }

}
