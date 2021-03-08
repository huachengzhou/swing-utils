package cn.zhou;

import javax.swing.*;
import java.awt.*;

public class TestGridBagLayout {

    public static void main(String[] args) {
        //创建窗口
        JFrame jf = new JFrame();
        jf.setTitle("测试网格袋布局");

        //设置退出并关闭
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //创建布局
        GridBagLayout layout = new GridBagLayout();
        //创建一个空的约束对象
        GridBagConstraints cons = null;

        //创建中间容器
        JPanel panel = new JPanel(layout);

        //创建按钮
        JButton btn1 = new JButton("按钮1");
        JButton btn2 = new JButton("按钮2");
        JButton btn3 = new JButton("按钮3");
        JButton btn4 = new JButton("按钮4");
        JButton btn5 = new JButton("按钮5");
        JButton btn6 = new JButton("按钮6");
        JButton btn7 = new JButton("按钮7");
        JButton btn8 = new JButton("按钮8");
        JButton btn9 = new JButton("按钮9");
        JButton btn10 = new JButton("按钮10");

        //添加组件和约束到布局管理器
        cons = new GridBagConstraints();
        //cons.gridx=20; 设定显示的位置偏移
        //cons.gridy=20;
        //cons.insets=new Insets(10, 10, 10, 10);//表示与其它控件的距离
        layout.addLayoutComponent(btn1, cons);//内部使用的是cons副本

        //添加组件和约束到布局管理器
        cons = new GridBagConstraints();
        layout.addLayoutComponent(btn2, cons);//内部使用的是cons副本

        //添加组件和约束到布局管理器
        cons = new GridBagConstraints();
        layout.addLayoutComponent(btn3, cons);//内部使用的是cons副本

        //添加组件和约束到布局管理器
        cons = new GridBagConstraints();
        cons.gridwidth = GridBagConstraints.REMAINDER;//占满剩下的部分
        cons.fill = GridBagConstraints.BOTH;
        layout.addLayoutComponent(btn4, cons);//内部使用的是cons副本


        //添加组件和约束到布局管理器
        cons = new GridBagConstraints();
        cons.gridwidth = GridBagConstraints.REMAINDER;//占满剩下的部分
        cons.fill = GridBagConstraints.BOTH;
        layout.addLayoutComponent(btn5, cons);//内部使用的是cons副本

        //添加组件和约束到布局管理器
        cons = new GridBagConstraints();
        cons.gridwidth = GridBagConstraints.RELATIVE;//占到所在行/列余下的倒数第二个单元格
        cons.fill = GridBagConstraints.BOTH;
        layout.addLayoutComponent(btn6, cons);//内部使用的是cons副本

        //添加组件和约束到布局管理器
        cons = new GridBagConstraints();
        cons.gridwidth = GridBagConstraints.REMAINDER;//占满剩下的部分 换行
        layout.addLayoutComponent(btn7, cons);//内部使用的是cons副本

        //添加组件和约束到布局管理器
        cons = new GridBagConstraints();
        cons.gridheight = 2;
        cons.fill = GridBagConstraints.BOTH;
        layout.addLayoutComponent(btn8, cons);//内部使用的是cons副本

        //添加组件和约束到布局管理器
        cons = new GridBagConstraints();
        cons.gridwidth = GridBagConstraints.REMAINDER;//占满剩下的部分 换行
        cons.fill = GridBagConstraints.BOTH;
        layout.addLayoutComponent(btn9, cons);//内部使用的是cons副本

        //添加组件和约束到布局管理器
        cons = new GridBagConstraints();
        cons.gridwidth = GridBagConstraints.REMAINDER;//占满剩下的部分 换行
        cons.fill = GridBagConstraints.BOTH;
        layout.addLayoutComponent(btn10, cons);//内部使用的是cons副本


        //将组添加到panel中
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);
        panel.add(btn5);
        panel.add(btn6);
        panel.add(btn7);
        panel.add(btn8);
        panel.add(btn9);
        panel.add(btn10);

        //将中间容器添加到窗口中
        jf.setContentPane(panel);
        //
        jf.pack();
        //显示
        jf.setVisible(true);


    }

}
