package com.general.panel;

import com.general.enums.PanelEnum;
import com.google.common.base.CaseFormat;
import com.swing.GridBagLayoutTool;
import com.swing.JTextField;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.nodes.Element;
import tool.utils.CrawlerUtils;
import tool.utils.HandleGoHuGoFileAppend;
import tool.utils.MyEntry;
import tool.utils.entity.Coordinates;
import tool.utils.excel.ConvertHtml2Excel;
import tool.utils.excel.JsoupConvertExcel;
import tool.utils.word.aspose.AsposeUtils;
import tool.utils.word.spire.SpireCommonWordUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Lenovo on 2020/5/17.
 * 复杂情况数据抓取  软件工程师操作界面
 */
public class SoftwareEngineerPanelTool {

    public static MyEntry<JPanel, String> createSuGoPanel(JPanel contentPane) {
        SpringLayout layout = new SpringLayout();
        JPanel jPanel = new JPanel(layout);
        Border border = BorderFactory.createBevelBorder(0, Color.white, Color.white);
        jPanel.setBorder(border);
        Color foreColor = Color.darkGray;
        Font font = new Font("微软雅黑", Font.BOLD, 16);

        JLabel jLabel = new JLabel("hugo 文档处理");
        jLabel.setForeground(foreColor);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setFont(font);

        javax.swing.JTextField field = new javax.swing.JTextField("oldPath");
        field.setColumns(21);
        field.setFont(font);


        javax.swing.JTextField jTextField = new javax.swing.JTextField("hugoPath");
        jTextField.setColumns(21);
        jTextField.setFont(font);

        JButton button = new JButton("生成");
        button.setForeground(foreColor);
        button.setFont(font);

        jPanel.add(jLabel);
        jPanel.add(field);
        jPanel.add(jTextField);
        jPanel.add(button);
        Dimension dimension = new Dimension(400, 70);
        jPanel.setPreferredSize(dimension);
        jPanel.setSize(dimension);

        final int northPad = 425;


        layout.putConstraint(SpringLayout.WEST, jLabel, 30,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, jLabel, northPad,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, field, northPad,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, field, 60,
                SpringLayout.EAST, jLabel);

        layout.putConstraint(SpringLayout.NORTH, jTextField, northPad,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, jTextField, 80,
                SpringLayout.EAST, field);


        layout.putConstraint(SpringLayout.NORTH, button, northPad,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, button, 40,
                SpringLayout.EAST, jTextField);


        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String oldPath = field.getText();
                    String hugoPath = jTextField.getText();
                    HandleGoHuGoFileAppend.run(oldPath, hugoPath);
                    JOptionPane.showMessageDialog(jPanel, " 转换成功!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(jPanel, "转换失败!" + ex.getMessage());
                }
            }
        });
        MyEntry<JPanel, String> myEntry = new MyEntry<>(jPanel, PanelEnum.Software_Engineer_hugo.getName());
        return myEntry;
    }

    /**
     * 中华人民共和国  国家统计局抓取数据
     *
     * @param contentPane
     * @return
     */
    public static MyEntry<JPanel, String> chineseNationalBureauStatistics(JPanel contentPane) {
        PanelEnum panelEnum = PanelEnum.Software_Engineer_F;
        SpringLayout layout = new SpringLayout();
        JPanel jPanel = new JPanel(layout);

        MyEntry<JPanel, String> myEntry = new MyEntry<>(jPanel, panelEnum.getName());
        return myEntry;
    }


    public static MyEntry<JPanel, String> getSoftware_Engineer_APanel(JPanel contentPane) {
        PanelEnum panelEnum = PanelEnum.Software_Engineer_A;
        Function<InputStream, ImageIcon> function = ((inputStream -> {
            Image image = null;
            try {
                image = ImageIO.read(inputStream);
            } catch (Exception e) {
            }
            ImageIcon imageIcon = new ImageIcon(image);
            return imageIcon;
        }));
        JPanel jPanel = new JPanel(new GridLayout(1, 1));
        MyEntry<JPanel, String> myEntry = new MyEntry<>(jPanel, panelEnum.getName());
        ImageIcon icon = function.apply(Thread.currentThread().getContextClassLoader().getResourceAsStream(PanelTool.fileName));
        JTabbedPane tabbedPane = new JTabbedPane();
        JComponent panel1 = makeTextPanelA(jPanel);
        tabbedPane.addTab(panelEnum.getName(), icon, panel1, "");
        jPanel.add(tabbedPane);
        return myEntry;
    }

    private static JComponent makeTextPanelA(JPanel contentPane) {
        GridBagLayout layout = new GridBagLayout();
        JPanel jPanel = new JPanel(layout);
        Border border = BorderFactory.createBevelBorder(2, Color.white, Color.white);
        jPanel.setBorder(border);
        Color foreColor = Color.darkGray;
        Font font = new Font("微软雅黑", Font.BOLD, 16);
        int row = 3, column = 50;
//        JPanel head = new JPanel(new GridLayout(1, 2, 0, 0));
        JPanel head = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));

        JTextArea input = new JTextArea(row, column);
        input.setFont(font);
        head.add(new JLabel("网络地址"));
        head.add(input);

        JPanel body = new JPanel(new GridLayout(2, 4, 0, 0));
        JButton buttonA = new JButton("run");
        body.add(new JLabel("保存地址"));
        JTextField jTextField = new JTextField(15);
        body.add(jTextField);
        body.add(new JLabel("选择器"));
        JTextField jTextField2 = new JTextField(15);
        body.add(jTextField2);

        body.add(new JLabel("名称"));
        JTextField jTextField3 = new JTextField(15);

        String t1 = "默认使用页面标题";

        JRadioButton pdf = new JRadioButton("pdf");
        JRadioButton excel = new JRadioButton("xls");
        JRadioButton docx = new JRadioButton("docx", true);
        JPanel groupPanel = new JPanel(new GridLayout(1, 3, 0, 0));
        ButtonGroup group = new ButtonGroup();
        group.add(pdf);
        group.add(excel);
        group.add(docx);
        groupPanel.add(pdf);
        groupPanel.add(excel);
        groupPanel.add(docx);

//        jTextField3.setText(t1);
        body.add(jTextField3);
        body.add(new JLabel("数据保存格式"));
        body.add(groupPanel);

        String remark = "";
        remark = "Selector选择器组合使用\n" +
                "el#id: 元素+ID，比如： div#logo\n" +
                "el.class: 元素+class，比如： div.masthead\n" +
                "el[attr]: 元素+class，比如： a[href]\n" +
                "任意组合，比如：a[href].highlight\n" +
                "ancestor child: 查找某个元素下子元素，比如：可以用.body p 查找在”body”元素下的所有 p元素\n" +
                "parent > child: 查找某个父元素下的直接子元素，比如：可以用div.content > p 查找 p 元素，也可以用body > * 查找body标签下所有直接子元素\n" +
                "siblingA + siblingB: 查找在A元素之前第一个同级元素B，比如：div.head + div\n" +
                "siblingA ~ siblingX: 查找A元素之前的同级X元素，比如：h1 ~ p\n" +
                "el, el, el:多个选择器组合，查找匹配任一选择器的唯一元素，例如：div.masthead, div.logo\n" +
                "\n" +
                "伪选择器selectors\n" +
                ":lt(n): 查找哪些元素的同级索引值（它的位置在DOM树中是相对于它的父节点）小于n，比如：td:lt(3) 表示小于三列的元素\n" +
                ":gt(n):查找哪些元素的同级索引值大于n，比如： div p:gt(2)表示哪些div中有包含2个以上的p元素\n" +
                ":eq(n): 查找哪些元素的同级索引值与n相等，比如：form input:eq(1)表示包含一个input标签的Form元素\n" +
                ":has(seletor): 查找匹配选择器包含元素的元素，比如：div:has(p)表示哪些div包含了p元素\n" +
                ":not(selector): 查找与选择器不匹配的元素，比如： div:not(.logo) 表示不包含 class=”logo” 元素的所有 div 列表\n" +
                ":contains(text): 查找包含给定文本的元素，搜索不区分大不写，比如： p:contains(jsoup)\n" +
                ":containsOwn(text): 查找直接包含给定文本的元素\n" +
                ":matches(regex): 查找哪些元素的文本匹配指定的正则表达式，比如：div:matches((?i)login)\n" +
                ":matchesOwn(regex): 查找自身包含文本匹配指定正则表达式的元素\n" +
                "注意：上述伪选择器索引是从0开始的，也就是说第一个元素索引值为0，第二个元素index为1等";

        JTextArea label = new JTextArea(40, 80);
        label.setText(remark);
        label.setEditable(false);
        final int y = 2;
        com.google.common.base.Function<List<Coordinates>, Integer> fun = ((list) -> {
            int sum = list.stream().mapToInt(obj -> obj.getRow() + obj.getY()).sum();
            return sum+y;
        });
        List<Coordinates> coordinatesList = new ArrayList<>(4);
        for (int i = 1; i <= 4; i++) {
            Coordinates coordinates = null;
            switch (i) {
                case 1: {
                    coordinates = new Coordinates(1, 1, 3, 3);
                    coordinatesList.add(coordinates);
                    layout.setConstraints(head, GridBagLayoutTool.createGridBagConstraints(
                            coordinates.getX(), coordinates.getY(),
                            coordinates.getColumn(), coordinates.getRow(),
                            0,0.05,
                            new Insets(0, 10, 0, 10)));
                    break;
                }
                case 2: {
                    coordinates = new Coordinates(1, 8, 3, 2);
                    coordinatesList.add(coordinates);
                    layout.setConstraints(body,
                            GridBagLayoutTool.createGridBagConstraints(fun.apply(coordinatesList) - coordinates.getY(),
                                    coordinates,
                                    new Insets(0, 0, 0, 0)));
                    break;
                }
                case 3: {
                    coordinates = new Coordinates(3, 3, 1, 1);
                    coordinatesList.add(coordinates);
                    layout.setConstraints(buttonA,
                            GridBagLayoutTool.createGridBagConstraints(fun.apply(coordinatesList) - coordinates.getY(),
                                    coordinates,
                                    new Insets(10, -100, 10, 0)));
                    break;
                }
                case 4: {
                    coordinates = new Coordinates(1, 2, 8, 5);
                    coordinatesList.add(coordinates);
                    layout.setConstraints(label,
                            GridBagLayoutTool.createGridBagConstraints(fun.apply(coordinatesList) - coordinates.getY(),
                                    coordinates,
                                    new Insets(10, 0, 0, 0)));
                    break;
                }
            }
        }

//        layout.setConstraints(head, GridBagLayoutTool.createGridBagConstraints(1, 1, 3, 4, new Insets(10, 0, 10, 0)));//y=1+4 = 5
//        layout.setConstraints(body, GridBagLayoutTool.createGridBagConstraints(1, 5 + 5 + y, 3, 2, new Insets(10, 0, 10, 0)));//y=10+2=12
//        layout.setConstraints(buttonA, GridBagLayoutTool.createGridBagConstraints(3, 12 + 2 + y, 1, 1, new Insets(0, -150, 0, 0)));//y=14+1=15
//        layout.setConstraints(label, GridBagLayoutTool.createGridBagConstraints(1, 15 + 2 + y, 10, 5));


        jPanel.add(head);
        jPanel.add(body);
        jPanel.add(buttonA);
        jPanel.add(label);


        //-------------------------------------------/-----------------------------------------
        buttonA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dir = jTextField.getText();

                String suffix = null;
                if (pdf.isSelected()) {
                    suffix = pdf.getText();
                }
                if (excel.isSelected()) {
                    suffix = excel.getText();
                }
                if (docx.isSelected()) {
                    suffix = docx.getText();
                }

                System.out.println(suffix);

                if (StringUtils.isBlank(input.getText())) {
                    JOptionPane.showMessageDialog(jPanel, "网络链接没有输入");
                    return;
                }
                if (StringUtils.isBlank(jTextField.getText())) {
                    JOptionPane.showMessageDialog(jPanel, "保存地址没有输入");
                    return;
                }
                if (StringUtils.isBlank(jTextField2.getText())) {
                    JOptionPane.showMessageDialog(jPanel, "选择器没有输入");
                    return;
                }
                try {
                    org.jsoup.nodes.Document document = CrawlerUtils.getCrawlerDocument(input.getText(), true);

                    Element head = document.head();
                    Element body = document.body();
                    String title = document.title();
                    if (StringUtils.isNotBlank(jTextField3.getText())) {
                        title = jTextField3.getText();
                    }
                    if (StringUtils.isBlank(title)) {
                        title = RandomStringUtils.randomNumeric(4);
                    }

                    org.jsoup.select.Elements elements = document.select(jTextField2.getText());
                    if (elements.size() == 1) {
                        dir = String.join(File.separator, jTextField.getText(), title + "." + suffix);
                        org.jsoup.nodes.Element element = elements.get(0);
                        writeData(suffix, element, dir);
                        JOptionPane.showMessageDialog(jPanel, "成功!");
                    }
                    if (elements.size() == 0) {
                        JOptionPane.showMessageDialog(jPanel, "没有找到元素!");
                    }
                    if (elements.size() > 1) {
                        Iterator<org.jsoup.nodes.Element> iterator = elements.iterator();
                        int k = 0;
                        while (iterator.hasNext()) {
                            k++;
                            org.jsoup.nodes.Element doc = iterator.next();
                            String localPath = String.join(File.separator, jTextField.getText(), title + String.valueOf(k) + "." + suffix);
                            writeData(suffix, doc, localPath);
                        }
                        JOptionPane.showMessageDialog(jPanel, "成功!");
                    }

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(jPanel, "抓取失败!" + ex.getMessage());
                }
            }
        });
        //------------------------------------------/------------------------------------

        return jPanel;
    }


    private static String writeData(String suffix, org.jsoup.nodes.Element ele, String path) throws Exception {
        String value = ele.html();
        String nodeName = ele.nodeName();
        if ("docx".equals(suffix)) {
            try {
                AsposeUtils.appendHtml(value, path);
            } catch (Exception e) {
//                SpireCommonWordUtils.appendHtmlFileToWord(value, path);
            }
        }
        if ("pdf".equals(suffix)) {
            String dir = StringUtils.remove(path, "pdf");
            String dir2 = dir + "docx";
            try {
                AsposeUtils.appendHtml(value, dir2);
            } catch (Exception e) {
//                SpireCommonWordUtils.appendHtmlFileToWord(value, dir2);
            }
//            SpireCommonWordUtils.wordFileToPDF(path, dir2);
        }
        String toString = ele.toString();
        if ("xls".equals(suffix)) {
            HSSFWorkbook workbook = null;
            try {
                //自己写的  没有合并单元格
                workbook = JsoupConvertExcel.writeExcel(ele);
            } catch (Exception e) {
                //网上找的
                workbook = ConvertHtml2Excel.table2Excel(toString);
            }
            FileOutputStream outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }
        return path;
    }


    public static MyEntry<JPanel, String> getSoftware_Engineer_ToolPanel(JPanel contentPane) {
        PanelEnum panelEnum = PanelEnum.Software_Engineer_Tool;
        Function<InputStream, ImageIcon> function = ((inputStream -> {
            Image image = null;
            try {
                image = ImageIO.read(inputStream);
            } catch (Exception e) {
            }
            ImageIcon imageIcon = new ImageIcon(image);
            return imageIcon;
        }));
        String name1 = "驼峰转下划线,下划线转驼峰";
        JPanel jPanel = new JPanel(new GridLayout(1, 1));
        MyEntry<JPanel, String> myEntry = new MyEntry<>(jPanel, panelEnum.getName());
        ImageIcon icon = function.apply(Thread.currentThread().getContextClassLoader().getResourceAsStream(PanelTool.fileName));

        JTabbedPane tabbedPane = new JTabbedPane();
        JComponent panel1 = makeTextPanel(jPanel);
        tabbedPane.addTab(name1, icon, panel1, "");


        jPanel.add(tabbedPane);
        return myEntry;
    }

    private static JComponent makeTextPanel(JPanel contentPane) {
        GridBagLayout layout = new GridBagLayout();
        JPanel jPanel = new JPanel(layout);
        Border border = BorderFactory.createBevelBorder(2, Color.white, Color.white);
        jPanel.setBorder(border);
        Font font = new Font("微软雅黑", Font.BOLD, 16);
        int row = 10, column = 50;

        JTextArea input = new JTextArea(row, column);
        input.setFont(font);

        JPanel body = new JPanel(new GridLayout(1, 3, 2, 2));
        JButton buttonA = new JButton("下划线转驼峰");
        JButton buttonB = new JButton("驼峰转下划线");
        JButton buttonC = new JButton("清空结果");
        body.add(buttonA);
        body.add(buttonB);
        body.add(buttonC);


        JTextArea output = new JTextArea(row, column);
        output.setFont(font);


        jPanel.add(input);
        jPanel.add(body);
        jPanel.add(output);

        final int y = 10;

        layout.setConstraints(input, GridBagLayoutTool.createGridBagConstraints(1, 5, 10, 4));//y=9

        layout.setConstraints(body, GridBagLayoutTool.createGridBagConstraints(1, 9 + 4 + y, 5, 1, new Insets(1, 70, 1, 70)));//y=15

        layout.setConstraints(output, GridBagLayoutTool.createGridBagConstraints(1, 15 + 4 + y, 10, 4));


        //-------------------------------------------/-----------------------------------------

        buttonA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isBlank(input.getText())) {
                    JOptionPane.showMessageDialog(jPanel, "数据没有输入");
                    return;
                }
                output.setText(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, input.getText()));
            }
        });
        buttonB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isBlank(input.getText())) {
                    JOptionPane.showMessageDialog(jPanel, "数据没有输入");
                    return;
                }
                output.setText(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, input.getText()));
            }
        });
        buttonC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText("");
                input.setText("");
            }
        });

        //------------------------------------------/------------------------------------

        return jPanel;
    }


}
