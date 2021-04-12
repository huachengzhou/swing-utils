package com.general.panel;

import com.data.UniversityGetExcelUtils;
import com.entity.School;
import com.general.enums.PanelEnum;
import com.general.handle.DocumentHandle;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import tool.utils.CrawlerUtils;
import tool.utils.MyEntry;
import tool.utils.pdf.PDFUtil;
import tool.utils.word.aspose.AsposeUtils;
import tool.utils.word.spire.SpireCommonWordUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.List;
import java.util.function.Function;

public class PanelTool {
    public final static String fileName = "images/label/card_106.png";

    public static Map<JPanel, String> createCards(JPanel jPanel) {
        Map<JPanel, String> jPanelStringMap = new HashMap<>();

        MyEntry<JPanel, String> documentA = createWORD_CONVERT_PDF(jPanel);
        MyEntry<JPanel, String> documentB = createONVERT_WORD(jPanel);
        MyEntry<JPanel, String> documentC = createWebCrawler(jPanel);
        MyEntry<JPanel, String> documentD = createChinese_University(jPanel);
        MyEntry<JPanel, String> documentE = createChinese_University_adult(jPanel);
        MyEntry<JPanel, String> documentF = createGet_current_ip(jPanel);
        MyEntry<JPanel, String> documentG = createCHECK_CONNECTED_NET(jPanel);
        MyEntry<JPanel, String> documentTool = SoftwareEngineerPanelTool.getSoftware_Engineer_ToolPanel(jPanel);
        MyEntry<JPanel, String> software_Engineer_A = SoftwareEngineerPanelTool.getSoftware_Engineer_APanel(jPanel);
        MyEntry<JPanel, String> software_Engineer_sugo = SoftwareEngineerPanelTool.createSuGoPanel(jPanel);
        jPanelStringMap.put(documentA.getKey(), documentA.getValue());
        jPanelStringMap.put(documentB.getKey(), documentB.getValue());
        jPanelStringMap.put(documentC.getKey(), documentC.getValue());
        jPanelStringMap.put(documentD.getKey(), documentD.getValue());
        jPanelStringMap.put(documentE.getKey(), documentE.getValue());
        jPanelStringMap.put(documentF.getKey(), documentF.getValue());
        jPanelStringMap.put(documentG.getKey(), documentG.getValue());
        jPanelStringMap.put(documentTool.getKey(), documentTool.getValue());
        jPanelStringMap.put(software_Engineer_A.getKey(), software_Engineer_A.getValue());
        jPanelStringMap.put(software_Engineer_sugo.getKey(), software_Engineer_sugo.getValue());
        return jPanelStringMap;
    }


    private static MyEntry<JPanel, String> createCHECK_CONNECTED_NET(JPanel contentPane) {
        PanelEnum panelEnum = PanelEnum.CHECK_CONNECTED_NET;
        GridBagLayout gridBagLayout = new GridBagLayout();
        JPanel jPanel = new JPanel(gridBagLayout);
        JButton button = new JButton(panelEnum.getName());
        JTextArea jTextArea = new JTextArea(2, 22);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();//实例化这个对象用来对组件进行管理
        gridBagConstraints.fill = GridBagConstraints.BOTH;//该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        //组件1(gridx,gridy)组件的左上角坐标，gridwidth，gridheight：组件占用的网格行数和列数
        //坐标(6,2)
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 1;//占1行
        gridBagConstraints.gridheight = 15;//占15列
        gridBagConstraints.weightx = 0.1;//填充权重
        gridBagConstraints.weighty = 0.1;//填充权重
        gridBagConstraints.insets = new Insets(400, 50, 400, 50);//相当于html中margin top,left,bottom,right
        gridBagConstraints.anchor = GridBagConstraints.CENTER;

        gridBagLayout.setConstraints(jTextArea, gridBagConstraints);

        //组件2
        //坐标(6,2+6)  必须+6 不然在一个位置会有遮挡效应 如果设置了具体的宽度还需要 把组件2的宽度加上组件1的宽度
        gridBagConstraints.gridx = 2 + 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 1;//占1行
        gridBagConstraints.gridheight = 2;//占2列
        gridBagConstraints.weightx = 0;//填充权重
        gridBagConstraints.weighty = 0;//填充权重

        gridBagLayout.setConstraints(button, gridBagConstraints);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isBlank(jTextArea.getText())) {
                    JOptionPane.showMessageDialog(jPanel, "必须ip地址");
                    return;
                }
                try {
                    InetAddress address = InetAddress.getByName(jTextArea.getText());
                    jTextArea.setText("地址可以连接");
                } catch (Exception eX) {
                    jTextArea.setText(String.format("地址不可以连接原因:%s", eX.getMessage()));
                }
            }
        });
        jPanel.add(button);
        jPanel.add(jTextArea);
        MyEntry<JPanel, String> myEntry = new MyEntry<>(jPanel, panelEnum.getName());
        return myEntry;
    }

    private static MyEntry<JPanel, String> createGet_current_ip(JPanel contentPane) {
        PanelEnum panelEnum = PanelEnum.Get_current_ip;
        GridBagLayout gridBagLayout = new GridBagLayout(); //实例化布局对象

        JPanel jPanel = new JPanel(gridBagLayout);

        JButton button = new JButton(panelEnum.getName());
        JTextArea jTextArea = new JTextArea(2, 22);
        jTextArea.setEditable(false);


        GridBagConstraints gridBagConstraints = new GridBagConstraints();//实例化这个对象用来对组件进行管理
        gridBagConstraints.fill = GridBagConstraints.BOTH;//该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        //组件1(gridx,gridy)组件的左上角坐标，gridwidth，gridheight：组件占用的网格行数和列数
        //坐标(6,2)
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 1;//占1行
        gridBagConstraints.gridheight = 15;//占15列
        gridBagConstraints.weightx = 0.1;//填充权重
        gridBagConstraints.weighty = 0.1;//填充权重
        gridBagConstraints.insets = new Insets(400, 50, 400, 50);//相当于html中margin top,left,bottom,right
        gridBagConstraints.anchor = GridBagConstraints.CENTER;

        gridBagLayout.setConstraints(jTextArea, gridBagConstraints);

        //组件2
        //坐标(6,2+6)  必须+6 不然在一个位置会有遮挡效应 如果设置了具体的宽度还需要 把组件2的宽度加上组件1的宽度
        gridBagConstraints.gridx = 2 + 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 1;//占1行
        gridBagConstraints.gridheight = 1;//占2列
        gridBagConstraints.weightx = 0;//填充权重
        gridBagConstraints.weighty = 0;//填充权重

        gridBagLayout.setConstraints(button, gridBagConstraints);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InetAddress address = InetAddress.getLocalHost();
                    jTextArea.setText("IP地址：" + address.getHostAddress() + "，主机名：" + address.getHostName());
                } catch (UnknownHostException e1) {
                }
            }
        });
        jPanel.add(button);
        jPanel.add(jTextArea);
        MyEntry<JPanel, String> myEntry = new MyEntry<>(jPanel, panelEnum.getName());
        return myEntry;
    }


    private static MyEntry<JPanel, String> createChinese_University(JPanel contentPane) {
        SpringLayout layout = new SpringLayout();
        JPanel jPanel = new JPanel(layout);
        Border border = BorderFactory.createBevelBorder(0, Color.white, Color.white);
        jPanel.setBorder(border);
        Font font = new Font("微软雅黑", Font.BOLD, 16);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(PanelEnum.Chinese_University.getName());

        try {
            LinkedHashMap<String, java.util.List<School>> chineseBaseData = UniversityGetExcelUtils.getChineseBaseData();
            if (!chineseBaseData.isEmpty()) {
                Iterator<Map.Entry<String, List<School>>> iterator = chineseBaseData.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, List<School>> listEntry = iterator.next();
                    if (CollectionUtils.isEmpty(listEntry.getValue())) {
                        continue;
                    }
                    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(String.join("", listEntry.getKey(), String.valueOf(listEntry.getValue().size()), "所"));
                    Iterator<School> schoolIterator = listEntry.getValue().iterator();
                    while (schoolIterator.hasNext()) {
                        School next = schoolIterator.next();
                        DefaultMutableTreeNode node = new DefaultMutableTreeNode(String.join("-", next.getName(), next.getLocation(), next.getLevel()));
                        childNode.add(node);
                    }
                    root.add(childNode);
                }
            }
        } catch (Exception e) {
        }
        JTree tree = new JTree(root);
        tree.setPreferredSize(new Dimension(700, 1600));
        tree.setFont(font);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setViewportView(tree);
        layout.putConstraint(SpringLayout.WEST, jScrollPane, 80, SpringLayout.WEST, jPanel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPane, 180, SpringLayout.NORTH, jPanel);
        jPanel.add(jScrollPane);
        MyEntry<JPanel, String> myEntry = new MyEntry<>(jPanel, PanelEnum.Chinese_University.getName());
        return myEntry;
    }

    private static MyEntry<JPanel, String> createChinese_University_adult(JPanel contentPane) {
        SpringLayout layout = new SpringLayout();
        JPanel jPanel = new JPanel(layout);
        Border border = BorderFactory.createBevelBorder(0, Color.white, Color.white);
        jPanel.setBorder(border);
        Font font = new Font("微软雅黑", Font.BOLD, 16);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(PanelEnum.Chinese_University_adult.getName());

        try {
            LinkedHashMap<String, java.util.List<School>> chineseBaseData = UniversityGetExcelUtils.getChineseBaseData();
            if (!chineseBaseData.isEmpty()) {
                Iterator<Map.Entry<String, List<School>>> iterator = chineseBaseData.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, List<School>> listEntry = iterator.next();
                    if (CollectionUtils.isEmpty(listEntry.getValue())) {
                        continue;
                    }
                    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(String.join("", listEntry.getKey(), String.valueOf(listEntry.getValue().size()), "所"));
                    Iterator<School> schoolIterator = listEntry.getValue().iterator();
                    while (schoolIterator.hasNext()) {
                        School next = schoolIterator.next();
                        DefaultMutableTreeNode node = new DefaultMutableTreeNode(String.join("-", next.getName(), next.getLocation(), next.getLevel()));
                        childNode.add(node);
                    }
                    root.add(childNode);
                }
            }
        } catch (Exception e) {
        }
        JTree tree = new JTree(root);
        tree.setPreferredSize(new Dimension(700, 1600));
        tree.setFont(font);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setViewportView(tree);
        layout.putConstraint(SpringLayout.WEST, jScrollPane, 80, SpringLayout.WEST, jPanel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPane, 180, SpringLayout.NORTH, jPanel);
        jPanel.add(jScrollPane);
        MyEntry<JPanel, String> myEntry = new MyEntry<>(jPanel, PanelEnum.Chinese_University_adult.getName());
        return myEntry;
    }

    /**
     * word 转 pdf
     *
     * @return
     */
    private static MyEntry<JPanel, String> createWORD_CONVERT_PDF(JPanel contentPane) {
        SpringLayout layout = new SpringLayout();
        JPanel jPanel = new JPanel(layout);
        Border border = BorderFactory.createBevelBorder(0, Color.white, Color.white);
        jPanel.setBorder(border);
        Color foreColor = Color.darkGray;
        Font font = new Font("微软雅黑", Font.BOLD, 16);

        JLabel jLabel = new JLabel("word 转 pdf");
        jLabel.setForeground(foreColor);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setFont(font);

        JButton openBtn = new JButton("打开");
        openBtn.setForeground(foreColor);
        openBtn.setFont(font);


        JTextField jTextField = new JTextField("");
        jTextField.setColumns(36);
        jTextField.setFont(font);

        JButton saveBtn = new JButton("转换");
        saveBtn.setForeground(foreColor);
        saveBtn.setFont(font);

        jPanel.add(jLabel);
        jPanel.add(openBtn);
        jPanel.add(jTextField);
        jPanel.add(saveBtn);
        Dimension dimension = new Dimension(400, 70);
        jPanel.setPreferredSize(dimension);
        jPanel.setSize(dimension);

        final int northPad = 425;


        layout.putConstraint(SpringLayout.WEST, jLabel, 30,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, jLabel, northPad,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, openBtn, northPad,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, openBtn, 60,
                SpringLayout.EAST, jLabel);

        layout.putConstraint(SpringLayout.NORTH, jTextField, northPad,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, jTextField, 80,
                SpringLayout.EAST, openBtn);


        layout.putConstraint(SpringLayout.NORTH, saveBtn, northPad,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, saveBtn, 40,
                SpringLayout.EAST, jTextField);

        openBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentHandle.showFileOpenDialog(jPanel, jTextField);
            }
        });

        saveBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String extension = FilenameUtils.getExtension(jTextField.getText());
                java.util.List<String> stringList = Arrays.asList("doc", "dot", "dotx", "docm", "docx", "dotm");
                if (StringUtils.isBlank(jTextField.getText())) {
                    JOptionPane.showMessageDialog(jPanel, "必选选择文件!");
                    return;
                }
                try {
                    String targetPath = FilenameUtils.getFullPath(jTextField.getText()) + FilenameUtils.getBaseName(jTextField.getText()) + ".pdf";
                    SpireCommonWordUtils.wordFileToPDF(targetPath, jTextField.getText());
                    JOptionPane.showMessageDialog(jPanel, FilenameUtils.getName(jTextField.getText()) + " 转换成功!");
                    jTextField.setText("文件位置: " + targetPath);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(jPanel, "转换失败!" + ex.getMessage());
                }
            }
        });
        MyEntry<JPanel, String> myEntry = new MyEntry<>(jPanel, PanelEnum.WORD_CONVERT_PDF.getName());
        return myEntry;
    }


    /**
     * pdf 转word
     *
     * @param contentPane
     * @return
     */
    private static MyEntry<JPanel, String> createONVERT_WORD(JPanel contentPane) {
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
        MyEntry<JPanel, String> myEntry = new MyEntry<>(jPanel, PanelEnum.PDF_CONVERT_WORD.getName());
        ImageIcon icon = function.apply(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
        JTabbedPane tabbedPane = new JTabbedPane();
        JComponent panel1 = makeTextPanel(PanelEnum.PDF_CONVERT_WORD_LOW, jPanel);
        tabbedPane.addTab(PanelEnum.PDF_CONVERT_WORD_LOW.getName(), icon, panel1, "");
        JComponent panel2 = makeTextPanel(PanelEnum.PDF_CONVERT_WORD_HIGH, jPanel);
        tabbedPane.addTab(PanelEnum.PDF_CONVERT_WORD_HIGH.getName(), icon, panel2, "");
        jPanel.add(tabbedPane);
        return myEntry;
    }

    private static JComponent makeTextPanel(PanelEnum panelEnum, JPanel contentPane) {
        SpringLayout layout = new SpringLayout();
        JPanel jPanel = new JPanel(layout);
        Border border = BorderFactory.createBevelBorder(0, Color.white, Color.white);
        jPanel.setBorder(border);
        Color foreColor = Color.darkGray;
        Font font = new Font("微软雅黑", Font.BOLD, 16);

        JLabel jLabel = new JLabel(panelEnum.getName());
        jLabel.setForeground(foreColor);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setFont(font);

        JButton openBtn = new JButton("打开");
        openBtn.setForeground(foreColor);
        openBtn.setFont(font);


        JTextField jTextField = new JTextField("");
        jTextField.setColumns(36);
        jTextField.setFont(font);

        JButton saveBtn = new JButton("转换");
        saveBtn.setForeground(foreColor);
        saveBtn.setFont(font);

        jPanel.add(jLabel);
        jPanel.add(openBtn);
        jPanel.add(jTextField);
        jPanel.add(saveBtn);
        Dimension dimension = new Dimension(400, 70);
        jPanel.setPreferredSize(dimension);
        jPanel.setSize(dimension);

        final int northPad = 425;


        layout.putConstraint(SpringLayout.WEST, jLabel, 30,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, jLabel, northPad,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, openBtn, northPad,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, openBtn, 60,
                SpringLayout.EAST, jLabel);

        layout.putConstraint(SpringLayout.NORTH, jTextField, northPad,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, jTextField, 80,
                SpringLayout.EAST, openBtn);


        layout.putConstraint(SpringLayout.NORTH, saveBtn, northPad,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, saveBtn, 40,
                SpringLayout.EAST, jTextField);

        openBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentHandle.showFileOpenDialog(jPanel, jTextField);
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isBlank(jTextField.getText())) {
                    JOptionPane.showMessageDialog(jPanel, "必选选择文件!");
                    return;
                }
                String extension = FilenameUtils.getExtension(jTextField.getText());
                if (extension.equalsIgnoreCase("pdf")) {
                    try {
                        switch (panelEnum) {
                            case PDF_CONVERT_WORD_LOW: {
                                String wordConversion = PDFUtil.pdfToWordConversion(jTextField.getText());
                                JOptionPane.showMessageDialog(jPanel, FilenameUtils.getName(jTextField.getText()) + " 转换成功!");
                                jTextField.setText("文件位置: " + wordConversion);
                            }
                            break;
                            case PDF_CONVERT_WORD_HIGH: {
                                String wordConversion = PDFUtil.pdfToWordConversion2(jTextField.getText());
                                JOptionPane.showMessageDialog(jPanel, FilenameUtils.getName(jTextField.getText()) + " 转换成功!");
                                jTextField.setText("文件位置: " + wordConversion);
                            }
                            break;
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(jPanel, "转换失败!" + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(jPanel, "非pdf文件");
                }
            }
        });
        return jPanel;
    }

    private static MyEntry<JPanel, String> createWebCrawler(JPanel contentPane) {
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
        MyEntry<JPanel, String> myEntry = new MyEntry<>(jPanel, PanelEnum.WEB_CRAWLER.getName());
        ImageIcon icon = function.apply(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
        JTabbedPane tabbedPane = new JTabbedPane();
        JComponent panel1 = makeTextPanelCrawler(PanelEnum.WEB_CRAWLER, jPanel);
        tabbedPane.addTab(PanelEnum.WEB_CRAWLER.getName(), icon, panel1, "");
        JComponent panel2 = makeTextPanelCrawler(PanelEnum.WEB_WEI_XIN_CRAWLER, jPanel);
        tabbedPane.addTab(PanelEnum.WEB_WEI_XIN_CRAWLER.getName(), icon, panel2, "");
        jPanel.add(tabbedPane);
        return myEntry;
    }

    /**
     * 网络爬虫 抓取数据
     *
     * @param panelEnum
     * @param contentPane
     * @return
     */
    private static JComponent makeTextPanelCrawler(PanelEnum panelEnum, JPanel contentPane) {
        SpringLayout layout = new SpringLayout();
        JPanel jPanel = new JPanel(layout);
        Border border = BorderFactory.createBevelBorder(0, Color.white, Color.white);
        jPanel.setBorder(border);
        Color foreColor = Color.darkGray;
        Font font = new Font("微软雅黑", Font.BOLD, 16);

        String textPath = "保存路径";
        JTextArea jta = new JTextArea("", 7, 30);
        jta.setLineWrap(true);    //设置文本域中的文本为自动换行


        //创建一个进度条
        JProgressBar progressBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
        progressBar.setPreferredSize(new Dimension(500, 30));
        progressBar.setStringPainted(true);


        JLabel headLabel = new JLabel(textPath);
        headLabel.setForeground(foreColor);
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(font);

        JLabel jLabel = new JLabel(panelEnum.getName() + "地址");
        jLabel.setForeground(foreColor);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setFont(font);


        JTextArea jTextField = new JTextArea("", 3, 30);
        jTextField.setFont(font);

        JButton saveBtn = new JButton("抓取");
        saveBtn.setForeground(foreColor);
        saveBtn.setFont(font);

        JButton clearBtn = new JButton("清除面板");
        clearBtn.setForeground(foreColor);
        clearBtn.setFont(font);

        jPanel.add(headLabel);
        jPanel.add(jta);
        jPanel.add(jLabel);
        jPanel.add(jTextField);
        jPanel.add(saveBtn);
        jPanel.add(progressBar);
        jPanel.add(clearBtn);
        Dimension dimension = new Dimension(400, 70);
        jPanel.setPreferredSize(dimension);
        jPanel.setSize(dimension);

        final int northPad = 425;


        layout.putConstraint(SpringLayout.EAST, jta, 340, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, jta, 280, SpringLayout.NORTH, contentPane);


        layout.putConstraint(SpringLayout.NORTH, headLabel, 340, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, jta, 60, SpringLayout.EAST, headLabel);
        layout.putConstraint(SpringLayout.WEST, headLabel, 70, SpringLayout.WEST, contentPane);


        //------------------||-------------------------------------------------------------


        layout.putConstraint(SpringLayout.WEST, jLabel, 30, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, jLabel, northPad, SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, jTextField, northPad, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, jTextField, 60, SpringLayout.EAST, jLabel);


        layout.putConstraint(SpringLayout.NORTH, saveBtn, northPad, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, saveBtn, 40, SpringLayout.EAST, jTextField);

        layout.putConstraint(SpringLayout.NORTH, clearBtn, northPad, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 20, SpringLayout.EAST, saveBtn);


        //----------------------------------||----------------------

        layout.putConstraint(SpringLayout.NORTH, progressBar, 560, SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.WEST, progressBar, 200, SpringLayout.WEST, contentPane);


        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progressBar.setValue(0);
                if (StringUtils.isBlank(jTextField.getText())) {
                    JOptionPane.showMessageDialog(jPanel, "必须填写网络地址");
                    return;
                }
                if (StringUtils.isBlank(jta.getText())) {
                    JOptionPane.showMessageDialog(jPanel, String.format("必须填写%s", textPath));
                    return;
                }
                progressBar.setValue(0);
                org.jsoup.nodes.Document document = null;
                try {
                    String dir = jta.getText();
                    String url = jTextField.getText();
                    switch (panelEnum) {
                        case WEB_CRAWLER: {
                            document = CrawlerUtils.getCrawlerDocument(url);
                        }
                        break;
                        case WEB_WEI_XIN_CRAWLER: {
                            document = CrawlerUtils.getCrawlerDocument(url, true);
                        }
                        break;
                    }
                    progressBar.setValue(20);
                    if (document != null) {
                        Element head = document.head();
                        Element body = document.body();
                        String title = document.title();
                        String html = document.html();
                        String text = document.text();
                        if (StringUtils.isBlank(title)) {
                            title = RandomStringUtils.randomAlphabetic(4);
                        }
                        if (body != null) {
                            dir = String.join(File.separator, dir, title + ".docx");
                            try {
                                AsposeUtils.appendHtml(html, dir);
                            } catch (Exception e1) {
                                System.out.println(e1.getMessage());
                                SpireCommonWordUtils.appendHtmlFileToWord(html, dir);
                            }
                            progressBar.setValue(100);
                            JOptionPane.showMessageDialog(jPanel, "成功!");
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(jPanel, "抓取失败!" + ex.getMessage());
                }

            }
        });

        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField.setText("");
                progressBar.setValue(0);
            }
        });
        return jPanel;
    }

}
