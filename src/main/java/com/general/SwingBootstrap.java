package com.general;

import com.alee.laf.WebLookAndFeel;
import com.common.utils.RunAbs;
import com.formdev.flatlaf.FlatLightLaf;
import com.general.menu.JMenuBarTool;
import com.general.panel.PanelTool;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SwingBootstrap {
    //初始化的时候 路径设置为项目路径 E:/IdeaProjects/swing-utils
    public static String BASE_DIR = "";
    //初始化的时候 路径设置为类路径 E:/IdeaProjects/swing-utils/target/classes
    public static String BASE_CLASS_DIR = "";
    private final static Logger logger = LoggerFactory.getLogger(SwingBootstrap.class);
    /**
     * 设置路径
     */
    private static void settingPath() {
        final String separatorChars = File.separator;
        String path = Thread.currentThread().getContextClassLoader().getResource("images/cloud_74.147368421053px_1258173_easyicon.net.png").getPath();
        //path前面有可能多出一个/所以用file 中转一下
        File file = new File(path);
        System.out.println(path);
        path = StringUtils.remove(file.getPath(), "file:\\");
        BASE_DIR = StringUtils.substringBeforeLast(path, "swing-utils");
        System.out.println(BASE_DIR);
        BASE_CLASS_DIR = BASE_DIR + separatorChars + "classes";
        File dirFile = new File(BASE_CLASS_DIR);
        //假如没有目录存在 则创建
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

    }

    public static void execute() {
        settingPath() ;
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
        try {
//            FlatLightLaf.setup();//idea theme
//            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
//        UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
//        UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Microsoft Yahei", Font.PLAIN, 13));
        //创建窗口
        JFrame jf = new JFrame("财务工具");
        RunAbs.removeJavaIconImageSwing(jf);
        Dimension dimension = new Dimension(1200, 1100);
        jf.setSize(dimension);
        jf.setResizable(true);
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
