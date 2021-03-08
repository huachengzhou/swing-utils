package com.zhou.p0103;

import com.common.utils.RunAbs;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SwingDemo {

    @Test
    public void testRun1() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
        try {
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听器初步使用
     */
    @Test
    public void testRun2() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体");

            Container container = jFrameModel.getValue();
            JLabel jLabel = new JLabel(DateFormatUtils.format(new Date(), DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()));
            JButton jButton = new JButton("时间显示");
            //添加一个监听器
            jButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jLabel.setText(DateFormatUtils.format(new Date(), DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()));
                }
            });
            container.add(jButton);
            container.add(jLabel);

        });
        RunAbs.end();
    }

    /**
     * 标签学习
     */
    @Test
    public void createJLabel() {
        /*
        *
        * void setText(Stxing text)	定义 JLabel 将要显示的单行文本
        void setIcon(Icon image)	定义 JLabel 将要显示的图标
        void setIconTextGap(int iconTextGap)	如果 JLabel 同时显示图标和文本，则此属性定义它们之间的间隔
        void setHorizontalTextPosition(int textPosition)	设置 JLabel 的文本相对其图像的水平位置
        void setHorizontalAlignment(int alignment)	设置标签内容沿 X 轴的对齐方式
        int getText()	返回 JLabel 所显示的文本字符串
        Icon getIcon()	返回 JLabel 显示的图形图像
        Component getLabelFor()	获得将 JLabel 添加到的组件
        int getIconTextGap()	返回此标签中显示的文本和图标之间的间隔量
        int getHorizontalTextPosition()	返回 JLabel 的文本相对其图像的水平位置
        int getHorizontalAlignment()	返回 JLabel 沿 X 轴的对齐方式
        * */
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体");

            Container container = jFrameModel.getValue();
            JLabel jLabel = new JLabel();
            container.add(jLabel);
            jLabel.setText("好好学习");
            jLabel.setForeground(RunAbs.getHSBColorRandom());
            jLabel.setToolTipText("http://c.biancheng.net/swing/");//鼠标放上去提示
            jLabel.setFont(new Font("微软雅黑", 0, 15));


        }, true);
    }

    @Test
    public void createJTextArea() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体");

            Container container = jFrameModel.getValue();
            JTextArea jTextArea = new JTextArea();
            jTextArea.setRows(10);
            jTextArea.setColumns(50);
            jTextArea.setLineWrap(true);    //设置文本域中的文本为自动换行

            JScrollPane jsp = new JScrollPane(jTextArea);    //将文本域放入滚动窗口
            Dimension size = jTextArea.getPreferredSize();    //获得文本域的首选大小
            jsp.setBounds(110, 90, size.width, size.height);
            container.add(jsp);    //将JScrollPane添加到JPanel容器中


            JButton jButton = new JButton("获取文本域的数据");
            jButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //消息提示对话框
//                    JOptionPane.showMessageDialog(container,jTextArea.getText());
                    int dialog = JOptionPane.showConfirmDialog(container, jTextArea.getText());
                    if (dialog == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(container, "你选择了是");
                    }
                    if (dialog == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(container, "你选择了否");
                    }
                    if (dialog == JOptionPane.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(container, "你选择了取消");
                    }
                    if (dialog == JOptionPane.CLOSED_OPTION) {
                        JOptionPane.showMessageDialog(container, "你直接将窗体关闭了，没有选择");
                    }
                    //注意这里面有非常多的提示框
                    //javax.swing.JOptionPane.showConfirmDialog(java.awt.Component, java.lang.Object) 带确认的提升对话框
                }
            });
            container.add(jButton);
//            container.add(jTextArea);
        }, true);
    }

    @Test
    public void createJCheckBox() {
        //复选框
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体");

            Container container = jFrameModel.getValue();
            JTextArea jTextArea = new JTextArea(2, 15);
            jTextArea.setLineWrap(true);    //设置文本域中的文本为自动换行
            jTextArea.setEnabled(false);
            JCheckBox jCheckBox = new JCheckBox("我想订阅邮件");
            container.add(jCheckBox);
            container.add(jTextArea);
            jCheckBox.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object[] selectedObjects = jCheckBox.getSelectedObjects();
                    if (selectedObjects.length != 0) {
                        jTextArea.setEnabled(true);
                    }
                }
            });
        }, true);
    }

    @Test
    public void createJComboBox() {
        class ItemString {
            private String colorName;
            private Color color;

            public String getColorName() {
                return colorName;
            }

            public void setColorName(String colorName) {
                this.colorName = colorName;
            }

            public Color getColor() {
                return color;
            }

            public void setColor(Color color) {
                this.color = color;
            }

            public ItemString(String colorName, Color color) {
                this.colorName = colorName;
                this.color = color;
            }

            @Override
            public String toString() {
                return colorName;
            }
        }
        //下拉列表
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体");
            Container container = jFrameModel.getValue();
            container.setLayout(new FlowLayout());
            JComboBox<ItemString> jComboBox = new JComboBox<>();
            jComboBox.addItem(new ItemString("红色", Color.RED));
            jComboBox.addItem(new ItemString("黑色", Color.black));
            jComboBox.addItem(new ItemString("蓝色", Color.BLUE));
            jComboBox.setSelectedIndex(0);//设置默认选中第一个
//            jComboBox.setSize(new Dimension(50,20));
            jComboBox.setMinimumSize(new Dimension(50, 20));
            JLabel jLabel = new JLabel("红色");
            jLabel.setBackground(Color.RED);
            jComboBox.addActionListener((event) -> {
                Object[] selectedObjects = jComboBox.getSelectedObjects();
                if (selectedObjects.length != 0) {
                    for (int i = 0; i < selectedObjects.length; i++) {
                        Object o = selectedObjects[i];
                        ItemString itemString = (ItemString) o;
                        jLabel.setText(itemString.getColorName());
                        jLabel.setBackground(itemString.getColor());
                    }
                }
            });
            container.add(jComboBox);
            container.add(jLabel);
        }, true);
    }

    @Test
    public void createFullLabel() {
        //彩色标签
        BiFunction<Color, String, JLabel> biFunction = (((color, s) -> {
            JLabel jLabel = new JLabel(StringUtils.isBlank(s) ? "" : s);
//            jLabel.setForeground(color);
            jLabel.setOpaque(true);
            jLabel.setBackground(color);
            jLabel.setPreferredSize(new Dimension(60, 30));
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            return jLabel;
        }));
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体");
            Container container = jFrameModel.getValue();
            container.setLayout(new FlowLayout());
            container.add(biFunction.apply(RunAbs.getHSBColorRandom(), "1"));
            container.add(biFunction.apply(RunAbs.getHSBColorRandom(), "2"));
            container.add(biFunction.apply(RunAbs.getHSBColorRandom(), "3"));
            container.add(biFunction.apply(RunAbs.getHSBColorRandom(), "4"));
        }, true);
    }

    @Test
    public void testFlowLayout() {
        BiFunction<Color, String, JLabel> biFunction = (((color, s) -> {
            JLabel jLabel = new JLabel(StringUtils.isBlank(s) ? "" : s);
            jLabel.setOpaque(true);
            jLabel.setBackground(color);
            jLabel.setPreferredSize(new Dimension(60, 30));
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            return jLabel;
        }));
        /*
        * Void setAlignment(int align) 设置此布局的对齐方式
        void setHgap(int hgap) 设置组件之间以及组件与 Container 的边之间的水平间隙。
        void setVgap(int vgap)  设置组件之间以及组件与 Container 的边之间的垂直间隙。
        * */
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体");
            Container container = jFrameModel.getValue();
//            container.setLayout(new FlowLayout(FlowLayout.LEFT));//左对齐
//            container.setLayout(new FlowLayout(FlowLayout.RIGHT));//右对齐
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlignment(FlowLayout.CENTER);
            flowLayout.setHgap(22);
            flowLayout.setVgap(12);
            container.setLayout(flowLayout);
            for (int i = 1; i <= 5; i++) {
                container.add(biFunction.apply(RunAbs.getHSBColorRandom(), String.valueOf(i)));
            }
        }, true);
    }

    @Test
    public void testBorderLayout() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体");
            Container container = jFrameModel.getValue();
            BorderLayout borderLayout = new BorderLayout();//为Frame窗口设置布局为BorderLayout
            borderLayout.setHgap(12);
            borderLayout.setVgap(15);
            container.setLayout(borderLayout);
            JButton button1 = new JButton("上");
            JButton button2 = new JButton("左");
            JButton button3 = new JButton("中");
            JButton button4 = new JButton("右");
            JButton button5 = new JButton("下");
            container.add(button1, BorderLayout.NORTH);
            container.add(button2, BorderLayout.WEST);
            container.add(button3, BorderLayout.CENTER);
            container.add(button4, BorderLayout.EAST);
            container.add(button5, BorderLayout.SOUTH);
        }, true);
    }

    //上北（NORTH）、下南（SOUTH）、左西（WEST）、右东（EAST），中（CENTER）
    @Test
    public void testBorderLayout2() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体");
            Container container = jFrameModel.getValue();
            BorderLayout borderLayout = new BorderLayout();//为Frame窗口设置布局为BorderLayout
            borderLayout.setHgap(12);
            borderLayout.setVgap(15);
            container.setLayout(borderLayout);
            container.add(new JButton("first"), BorderLayout.BEFORE_FIRST_LINE);
            container.add(new JButton("上"), BorderLayout.NORTH);
            container.add(new JButton("左"), BorderLayout.WEST);
            container.add(new JButton("中"), BorderLayout.CENTER);
            container.add(new JButton("右"), BorderLayout.EAST);
            container.add(new JButton("下"), BorderLayout.SOUTH);
//            initFram(jFrameModel.getKey(),container.getWidth(),container.getHeight()) ;
        }, true);
    }

    @Test
    public void testBorderLayout3() {
        BiFunction<Integer, Integer, Integer> biFunction = (((integer2, integer) -> {
            return (integer2 - integer) / 2;
        }));
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体");
            Container container = jFrameModel.getValue();
            int width = jFrameModel.getKey().getWidth();
            int height = jFrameModel.getKey().getHeight();

            BorderLayout layout = new BorderLayout();
            container.setLayout(layout);
            JPanel jPanel = new JPanel(new BorderLayout(5, 15));
            jPanel.add(new com.swing.JButton("1", width / 3, height / 3), BorderLayout.WEST);
            jPanel.add(new com.swing.JTextField(22, width / 3, height / 3), BorderLayout.EAST);
            container.add(jPanel, BorderLayout.NORTH);
        }, true);
    }

    @Test
    public void testBorderLayout4() {
        BiFunction<Integer, Integer, Integer> biFunction = (((integer2, integer) -> {
            return (integer2 - integer) / 2;
        }));
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体");
            Container container = jFrameModel.getValue();
            int width = jFrameModel.getKey().getWidth();
            int height = jFrameModel.getKey().getHeight();

            BorderLayout layout = new BorderLayout(1, 2);
            container.setLayout(layout);
            JPanel jPanel = new JPanel(new BorderLayout(5, 15));
            jPanel.add(new com.swing.JButton("1"), BorderLayout.NORTH);
            container.add(jPanel, BorderLayout.NORTH);


            JPanel jPanel2 = new JPanel(new BorderLayout(5, 15));
            jPanel.add(new com.swing.JButton("2"), BorderLayout.BEFORE_LINE_BEGINS);
            container.add(jPanel2, BorderLayout.BEFORE_LINE_BEGINS);
        }, true);
    }

    @Test
    public void testCardLayout() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体");
            Container container = jFrameModel.getValue();
            container.setLayout(new BorderLayout());
            JComboBox<String> jComboBox = new JComboBox<>();
            jComboBox.addItem("1");
            jComboBox.addItem("2");
            CardLayout cardLayout = new CardLayout();
            JPanel jPanel = new JPanel();
            jPanel.setLayout(cardLayout);
            JPanel jPanelA = new JPanel();
            jPanelA.add(new JButton("btn"));
            JPanel jPanelB = new JPanel();
            jPanelB.add(new JTextField("", 22));
            jPanel.add(jPanelA, "1");
            jPanel.add(jPanelB, "2");

            container.add(jComboBox, BorderLayout.PAGE_START);
            container.add(jPanel, BorderLayout.CENTER);
//            cardLayout.show(jPanel,"1");
            jComboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    int selectedIndex = jComboBox.getSelectedIndex();
                    String itemAt = jComboBox.getItemAt(selectedIndex);
                    cardLayout.show(jPanel, itemAt);
                }
            });
        }, true);
    }

    @Test
    public void createBoxLayout() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体");
            Container container = jFrameModel.getValue();
            JFrame frame = jFrameModel.getKey();
            container.setLayout(new BorderLayout());
            JPanel head = new JPanel();
//            head.setSize(frame.getWidth(),frame.getHeight() / 3);//无效
            head.setPreferredSize(new Dimension(frame.getWidth() + 0, frame.getHeight() / 3));//使用这个才有效
            head.setBackground(Color.BLACK);
            container.add(head, BorderLayout.NORTH);
            JPanel body = new JPanel(new BorderLayout());


            Box box = Box.createHorizontalBox();
            box.setPreferredSize(body.getPreferredSize());

            Box leftBox = Box.createVerticalBox();
            leftBox.setBackground(Color.BLUE);
            for (int i = 0; i < 2; i++) {
                JPanel panel = new JPanel();
                if (i == 0) {
                    panel.add(new JTextField("请输入数据", 13));
                }
                if (i == 1){
                    Box boxLeft = Box.createHorizontalBox();
                    for (int j = 0; j < 3; j++) {
                        // 表头（列名）
                        Object[] columnNames = {"姓名", "语文", "数学", "英语", "总分"};

                        // 表格所有行数据
                        Object[][] rowData = {
                                {"张三", 80, 80, 80, 240},
                                {"John", 70, 80, 90, 240},
                                {"Sue", 70, 70, 70, 210},
                                {"Jane", 80, 70, 60, 210},
                                {"Joe", 80, 70, 60, 210}
                        };
                        JTable jTable = new JTable(rowData, columnNames);
                        jTable.setBackground(RunAbs.getHSBColorRandom());
                        boxLeft.add(jTable) ;
                    }
                    panel.add(boxLeft) ;
                }
                leftBox.add(panel);
            }

            Box rightBox = Box.createVerticalBox();
            rightBox.setBackground(Color.GREEN);
            for (int i = 0; i < 3; i++) {
                if (i == 1) {
                    rightBox.add(Box.createVerticalStrut(20));
                    continue;
                }
                JPanel panel = new JPanel();
                panel.add(new JLabel(RandomStringUtils.randomNumeric(5)));
                panel.setBackground(RunAbs.getHSBColorRandom());
                rightBox.add(panel);
            }

            box.add(leftBox);
            box.add(rightBox);

            body.add(box, BorderLayout.CENTER);
            container.add(body, BorderLayout.CENTER);
        }, true);
    }


    private void createGUI() {
        JFrame frame = new JFrame("Swing Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());

        contentPane.add(new JLabel("Hello,World"));
        contentPane.add(new JButton("测试"));

        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private void initFram(JFrame f, int width, int height) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        f.setVisible(true);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = toolkit.getScreenSize();
        int x = (int) d.getWidth();
        int y = (int) d.getHeight();
        f.setBounds((x - width) / 2, (y - height) / 2, width, height);
    }


}
