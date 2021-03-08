package cn.zhou;

import com.common.utils.RunAbs;
import org.testng.annotations.Test;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.util.Map;

/**
 * 网格包布局管理器 或者网格包布局管理器
 * <p>
 * <p>
 * GridBagLayout
 * （网格包布局管理器）是在网格基础上提供复杂的布局，是最灵活、 最复杂的布局管理器。
 * GridBagLayout 不需要组件的尺寸一致，允许组件扩展到多行多列。
 * 每个 GridBagLayout 对象都维护了一组动态的矩形网格单元，每个组件占一个或多个单元，所占有的网格单元称为组件的显示区域。
 * <p>
 * GridBagLayout 所管理的每个组件都与一个 GridBagConstraints 约束类的对象相关。
 * 这个约束类对象指定了组件的显示区域在网格中的位置，以及在其显示区域中应该如何摆放组件。
 * 除了组件的约束对象，GridBagLayout 还要考虑每个组件的最小和首选尺寸，以确定组件的大小。
 */
public class GridBagLayoutDemo {

    //jdk注释

    /**
     *
     */


    /*
     * gridx,gridy 组件所在位置
     * gridwidth,gridheight 组件所占用的行数和列数
     * anchor 组件所在的方位
     * fill 组件的填充方式
     * insets 组件与单元格边缘的最小距离
     * ipadx,ipady 组件的首先大小 刚开始的大小
     * weightx,weighty 一个单元格的最大宽高
     *
     * */
    @Test
    public void testStart2() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体", 800, 600, 300, 200);
            Container container = jFrameModel.getValue();
            container.setLayout(new GridBagLayout());//布局方式
            GridBagConstraints gs = new GridBagConstraints();
            container.add(new JButton("组1"), gs);//在中心
        }, true);
    }

    /**
     * gridx and gridy
     */
    @Test
    public void testStart3() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体", 800, 600, 300, 200);
            Container container = jFrameModel.getValue();
            container.setLayout(new GridBagLayout());//布局方式

            GridBagConstraints gs1 = new GridBagConstraints();
            container.add(new JButton("组1"), gs1);

            GridBagConstraints gs2 = new GridBagConstraints();
            container.add(new JButton("组2"), gs2);


            GridBagConstraints gs3 = new GridBagConstraints();
            container.add(new JButton("组3"), gs3);

            //直接是在一排了
        }, true);
    }


    /**
     * gridx and gridy
     */
    @Test
    public void testStart4() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体", 800, 600, 300, 200);
            Container container = jFrameModel.getValue();
            container.setLayout(new GridBagLayout());//布局方式

            GridBagConstraints gs1 = new GridBagConstraints();
            gs1.gridx = 0;
            gs1.gridy = 0;
            container.add(new JButton("组1"), gs1);

            GridBagConstraints gs2 = new GridBagConstraints();
            gs2.gridx = 1;
            gs2.gridy = 1;
            container.add(new JButton("组2"), gs2);


            GridBagConstraints gs3 = new GridBagConstraints();
            gs3.gridx = 2;
            gs3.gridy = 2;
            container.add(new JButton("组3"), gs3);

            //直接是在一排了
        }, true);
    }

    @Test
    public void testStart4_() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体", 800, 600, 300, 200);
            Container container = jFrameModel.getValue();
            container.setLayout(new GridBagLayout());//布局方式

            GridBagConstraints gs1 = new GridBagConstraints();
            gs1.gridx = 0;
            gs1.gridy = 0;
            container.add(new JButton("组1"), gs1);

            GridBagConstraints gs2 = new GridBagConstraints();
            gs2.gridx = 1;
            gs2.gridy = 0;
            container.add(new JButton("组2"), gs2);


            GridBagConstraints gs3 = new GridBagConstraints();
            gs3.gridx = 1;
            gs3.gridy = 2;
//            gs3.gridy = 8;//感觉gridx和gridy 不能超过GridBagConstraints的数量
            container.add(new JButton("组3"), gs3);

            //直接是在一排了
        }, true);
    }


    /**
     * gridx and gridy
     */
    @Test
    public void testStart5() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体", 800, 600, 300, 200);
            Container container = jFrameModel.getValue();
            container.setLayout(new GridBagLayout());//布局方式

            for (int i = 0; i < 9; i++) {
                GridBagConstraints gs1 = new GridBagConstraints();
                gs1.gridx = i;
                gs1.gridy = 0;
                container.add(new JButton("组y " + i), gs1);

                GridBagConstraints gs2 = new GridBagConstraints();
                gs2.gridx = 0;
                gs2.gridy = i;
                container.add(new JButton("组x " + i), gs2);
            }
            //直接是在一排了
        }, true);
    }

    /**
     * gridwidth and gridheight
     */
    @Test
    public void testStart6() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体", 1000, 800, 300, 500);
            Container container = jFrameModel.getValue();
            container.setLayout(new GridBagLayout());//布局方式
            int len = 9;
            for (int i = 0; i < len; i++) {
                GridBagConstraints gs1 = new GridBagConstraints();
                gs1.gridx = i;
                gs1.gridy = 0;
                container.add(new JButton("组y " + i), gs1);

                GridBagConstraints gs2 = new GridBagConstraints();
                gs2.gridx = 0;
                gs2.gridy = i;
                container.add(new JButton("组x " + i), gs2);
            }

            GridBagConstraints gs1 = new GridBagConstraints();
            gs1.gridx = 1;
            gs1.gridy = 1;
            container.add(new JButton("组1"), gs1);


            GridBagConstraints gs3 = new GridBagConstraints();
            gs3.gridx = 3;
            gs3.gridy = 3;
            gs3.gridwidth = 3;
            gs3.gridheight = 3;
            container.add(new JButton("组3"), gs3);

            GridBagConstraints gs4 = new GridBagConstraints();
            gs4.gridx = 4;
            gs4.gridy = 4;
            gs4.gridwidth = 4;
            gs4.gridheight = 4;
            container.add(new JButton("组4"), gs4);
            //直接是在一排了
        }, true);
    }

    @Test
    public void testStart7() {
        RunAbs.invokeLater(() -> {
            JFrame jFrame = new JFrame();
            jFrame.setBounds(200, 200, 1000, 800);
            jFrame.setTitle("窗体");
            try {
                RunAbs.removeJavaIconImageSwing(jFrame);
            } catch (Exception e) {
            }
            jFrame.setVisible(true);
            Container container = jFrame.getContentPane();
            container.setLayout(new GridBagLayout());//布局方式

            int len = 9;
            for (int i = 0; i < len; i++) {
                GridBagConstraints gs1 = new GridBagConstraints();
                gs1.gridx = i;
                gs1.gridy = 0;
                container.add(new JButton("组y " + i), gs1);

                GridBagConstraints gs2 = new GridBagConstraints();
                gs2.gridx = 0;
                gs2.gridy = i;
                container.add(new JButton("组x " + i), gs2);
            }


            GridBagConstraints gs1 = new GridBagConstraints();
            gs1.gridx = 1;
            gs1.gridy = 1;
            gs1.gridwidth = 2;
            gs1.gridheight = 2;
            gs1.fill = GridBagConstraints.HORIZONTAL;
            container.add(new JButton("组1"), gs1);


            GridBagConstraints gs3 = new GridBagConstraints();
            gs3.gridx = 2;
            gs3.gridy = 2;
            gs3.gridwidth = 3;
            gs3.gridheight = 3;
            gs3.fill = GridBagConstraints.VERTICAL;
            container.add(new JButton("组3"), gs3);

            GridBagConstraints gs4 = new GridBagConstraints();
            gs4.gridx = 7;
            gs4.gridy = 1;
            gs4.gridwidth = 2;
            gs4.gridheight = 2;
            gs4.fill = GridBagConstraints.BOTH;
            container.add(new JButton("组4"), gs4);

        }, true);
    }

    @Test
    public void testStart8() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("", 1000, 800, 300, 500);
            Container container = jFrameModel.getValue();
            container.setLayout(new GridBagLayout());//布局方式

            JPanel jPanelA = new JPanel(new BorderLayout()) ;
            jPanelA.setBackground(RunAbs.getHSBColorRandom());
            JButton jButtonA = new JButton("获取验证码") ;
            JTextField jTextFieldA = new JTextField("输入验证码") ;
            jPanelA.add(jTextFieldA,BorderLayout.SOUTH);
            jPanelA.add(jButtonA,BorderLayout.EAST);

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            constraints.gridheight = GridBagConstraints.REMAINDER;
            constraints.fill = GridBagConstraints.BOTH;
            constraints.weightx = 50;
            constraints.weighty = 50;
            container.add(jPanelA,constraints) ;
        }, true);
    }


    @Test
    public void testStartE() {
        RunAbs.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            JButton button1 = new JButton("Next");
            frame.add(button1, constraints);
            JTextField text1 = new JTextField();
            text1.setColumns(10);
            constraints.gridx = 1;
            frame.add(text1, constraints);
            JLabel label1 = new JLabel("First Name:");
            constraints.gridx = 2;
            frame.add(label1, constraints);
            TextArea textArea1 = new TextArea("This is some text in a text area");
            textArea1.setRows(2);
            textArea1.setColumns(10);
            constraints.gridx = 0;
            constraints.gridy = 1;
            frame.add(textArea1, constraints);
            List list1 = new List();
            list1.add("FirstItem");
            list1.add("SecondItem");
            list1.add("ThirdItem");
            list1.add("FourthItem");
            list1.add("FifthItem");
            constraints.gridx = 1;
            frame.add(list1, constraints);
            frame.pack();
            frame.setVisible(true);
            list1 = new List();
            list1.add("FirstItem");
            list1.add("SecondItem");
            list1.add("ThirdItem");
            list1.add("FourthItem");
            list1.add("FifthItem");
            constraints.gridx = 1;
            frame.add(list1, constraints);
            frame.pack();
            frame.setVisible(true);
        }, true);


    }

    @Test
    public void testStart1() {

        RunAbs.invokeLater(() -> {
            JFrame f = new JFrame("GridBag Layout Example");
            GridBagEx1 ex1 = new GridBagEx1();

            ex1.init();

            f.add("Center", ex1);
            f.pack();
            f.setSize(f.getPreferredSize());
            f.setVisible(true);
        }, true);


    }


    class GridBagEx1 extends Applet {
        protected void makebutton(String name,
                                  GridBagLayout gridbag,
                                  GridBagConstraints c) {
            JButton button = new JButton(name);
            gridbag.setConstraints(button, c);
            add(button);
        }

        public void init() {
            GridBagLayout gridbag = new GridBagLayout();
            GridBagConstraints c = new GridBagConstraints();

            setFont(new Font("SansSerif", Font.PLAIN, 14));
            setLayout(gridbag);

            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1.0;
            makebutton("Button1", gridbag, c);
            makebutton("Button2", gridbag, c);
            makebutton("Button3", gridbag, c);

            c.gridwidth = GridBagConstraints.REMAINDER; //end row
            makebutton("Button4", gridbag, c);

            c.weightx = 0.0;                  //reset to the default
            makebutton("Button5", gridbag, c); //another row

            c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last in row
            makebutton("Button6", gridbag, c);

            c.gridwidth = GridBagConstraints.REMAINDER; //end row
            makebutton("Button7", gridbag, c);

            c.gridwidth = 1;                //reset to the default
            c.gridheight = 2;
            c.weighty = 1.0;
            makebutton("Button8", gridbag, c);

            c.weighty = 0.0;                  //reset to the default
            c.gridwidth = GridBagConstraints.REMAINDER; //end row
            c.gridheight = 1;               //reset to the default
            makebutton("Button9", gridbag, c);
            makebutton("Button10", gridbag, c);

            setSize(300, 100);
        }


    }

}
