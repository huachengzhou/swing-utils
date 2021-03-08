package com.general.menu;

import com.general.enums.PanelEnum;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

public class JMenuBarTool {


    public static JMenuBar createJMenuBar(JPanel jPanel, CardLayout cardLayout) {
        JMenuBar jMenuBar = new JMenuBar();

        jMenuBar.add(createWebCrawler(jPanel, cardLayout));

        jMenuBar.add(createFileHandleMenu(jPanel, cardLayout));

        jMenuBar.add(createHRHandleMenu(jPanel, cardLayout));

        jMenuBar.add(createInternetHandleMenu(jPanel, cardLayout));

        jMenuBar.add(createDocumentHandleMenu(jPanel, cardLayout));

        jMenuBar.add(createSoftwareEngineer(jPanel, cardLayout));

        return jMenuBar;
    }

    /**
     * 网络爬虫
     *
     * @return
     */
    public static JMenu createWebCrawler(JPanel jPanel, CardLayout cardLayout) {
        JMenu menu = new JMenu(PanelEnum.WEB_CRAWLER.getName());
        List<PanelEnum> panelEnumList = Arrays.asList(PanelEnum.WEB_CRAWLER);
        JMenuItem item = null;
        for (PanelEnum panelEnum : panelEnumList) {
            item = new JMenuItem(panelEnum.getName());
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(jPanel, panelEnum.getName());
                }
            });
            menu.add(item);
        }
        return menu;
    }

    /**
     * 文件操作
     *
     * @return
     */
    public static JMenu createFileHandleMenu(JPanel jPanel, CardLayout cardLayout) {
        JMenu menu = new JMenu("文件操作");
        JMenuItem item = new JMenuItem("文件删除");

        menu.add(item);


        //将新分隔符追加到菜单的末尾
        menu.addSeparator();

        item = new JMenuItem("文件大小计算");
        menu.add(item);

        return menu;
    }


    /**
     * 人力资源
     *
     * @return
     */
    public static JMenu createHRHandleMenu(JPanel jPanel, CardLayout cardLayout) {
        JMenu menu = new JMenu("HR");

        List<PanelEnum> panelEnumList = Arrays.asList(PanelEnum.Chinese_University, PanelEnum.Chinese_University_adult);

        JMenuItem item = null;
        for (PanelEnum panelEnum : panelEnumList) {
            item = new JMenuItem(panelEnum.getName());
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(jPanel, panelEnum.getName());
                }
            });
            menu.add(item);
        }

        return menu;
    }

    /**
     * 文档操作
     *
     * @return
     */
    public static JMenu createDocumentHandleMenu(JPanel jPanel, CardLayout cardLayout) {
        JMenu menu = new JMenu("文档操作");
        List<PanelEnum> panelEnumList = Arrays.asList(PanelEnum.WORD_CONVERT_PDF, PanelEnum.PDF_CONVERT_WORD);
        JMenuItem item = null;
        for (PanelEnum panelEnum : panelEnumList) {
            item = new JMenuItem(panelEnum.getName());
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(jPanel, panelEnum.getName());
                }
            });
            menu.add(item);
        }
        return menu;
    }

    /**
     * 网络
     *
     * @return
     */
    public static JMenu createInternetHandleMenu(JPanel jPanel, CardLayout cardLayout) {
        JMenu menu = new JMenu("网络");
        List<PanelEnum> panelEnumList = Arrays.asList(PanelEnum.Get_current_ip, PanelEnum.CHECK_CONNECTED_NET);
        JMenuItem item = null;
        for (PanelEnum panelEnum : panelEnumList) {
            item = new JMenuItem(panelEnum.getName());
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(jPanel, panelEnum.getName());
                }
            });
            menu.add(item);
        }


        return menu;
    }

    public static JMenu createSoftwareEngineer(JPanel jPanel, CardLayout cardLayout) {
        JMenu menu = new JMenu("软件工程师");
        List<PanelEnum> panelEnumList = Arrays.asList(PanelEnum.Software_Engineer_Tool,PanelEnum.Software_Engineer_A, PanelEnum.Software_Engineer_B, PanelEnum.Software_Engineer_hugo,PanelEnum.Software_Engineer_C, PanelEnum.Software_Engineer_D, PanelEnum.Software_Engineer_E, PanelEnum.Software_Engineer_F);
        JMenuItem item = null;
        for (PanelEnum panelEnum : panelEnumList) {
            item = new JMenuItem(panelEnum.getName());
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(jPanel, panelEnum.getName());
                }
            });
            menu.add(item);
        }
        return menu;
    }


}
