package com.general;

import com.alee.laf.WebLookAndFeel;
import com.common.utils.RunAbs;
import com.general.menu.JMenuBarTool;
import com.general.panel.PanelTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SwingBootstrap {

    private final static Logger logger = LoggerFactory.getLogger(SwingBootstrap.class);

    public static void execute() {
        SwingUtilities.invokeLater(() -> {
            try {
                run();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e);
                logger.error(e.getMessage(), e);
            }
        });
    }

    private static void run() throws Exception {
        WebLookAndFeel.install();
//        UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
//        UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Microsoft Yahei", Font.PLAIN, 13));
        //创建窗口
        JFrame jf = new JFrame("财务工具");
        RunAbs.removeJavaIconImageSwing(jf);
        Dimension dimension = new Dimension(1200, 1100);
        jf.setSize(dimension);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel body = new JPanel();
        body.setLayout(new BorderLayout());

        JPanel jPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        jPanel.setLayout(cardLayout);
        Set<Map.Entry<JPanel, String>> entrySet = PanelTool.createCards(jPanel).entrySet();
        Iterator<Map.Entry<JPanel, String>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<JPanel, String> panelStringEntry = iterator.next();
            JPanel entryKey = panelStringEntry.getKey();
            String value = panelStringEntry.getValue();
            jPanel.add(entryKey, value);
        }

        body.add(jPanel, BorderLayout.CENTER);


        jf.getContentPane().add(body);
        jf.setJMenuBar(JMenuBarTool.createJMenuBar(jPanel, cardLayout));
        jf.setVisible(true);

    }


    public static void main(String[] args) {
        execute();
    }


}
