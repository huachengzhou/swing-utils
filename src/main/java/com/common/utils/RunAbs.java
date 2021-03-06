package com.common.utils;


import org.apache.commons.lang3.StringUtils;
import tool.utils.FileUtils;
import tool.utils.MyEntry;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Map;
import java.util.function.Function;

public final class RunAbs implements Serializable {

    //-----------------------------------------------------------------||-------------------------------------------------------------
    public static Map.Entry<JFrame, Container> getJFrameModel(String title, Integer width, Integer height, Integer x, Integer y) {
        return getJFrameModel(null, title, width, height, x, y);
    }

    public static Map.Entry<JFrame, Container> getJFrameModel(LayoutManager layoutManager, String title, Integer width, Integer height) {
        return getJFrameModel(layoutManager, title, width, height, null, null);
    }

    public static Map.Entry<JFrame, Container> getJFrameModel(String title, Integer width, Integer height) {
        return getJFrameModel(null, title, width, height, null, null);
    }

    public static Map.Entry<JFrame, Container> getJFrameModel(Integer width, Integer height) {
        return getJFrameModel(null, null, width, height, null, null);
    }

    public static Map.Entry<JFrame, Container> getJFrameModel(LayoutManager layoutManager, String title) {
        return getJFrameModel(layoutManager, title, null, null, null, null);
    }

    public static Map.Entry<JFrame, Container> getJFrameModel(LayoutManager layoutManager) {
        return getJFrameModel(layoutManager, null, null, null, null, null);
    }

    public static Map.Entry<JFrame, Container> getJFrameModel(String title) {
        return getJFrameModel(null, title, null, null, null, null);
    }

    public static Map.Entry<JFrame, Container> getJFrameModelLocation(LayoutManager layoutManager, String title, Integer x, Integer y) {
        return getJFrameModel(layoutManager, title, null, null, x, y);
    }

    public static Map.Entry<JFrame, Container> getJFrameModelLocation(String title, Integer x, Integer y) {
        return getJFrameModelLocation(null, title, x, y);
    }

    public static Map.Entry<JFrame, Container> getJFrameModelLocation(Integer x, Integer y) {
        return getJFrameModelLocation(null, null, x, y);
    }

    public static Map.Entry<JFrame, Container> getJFrameModel(LayoutManager layoutManager, String title, Integer width, Integer height, Integer x, Integer y) {
        JFrame jFrame = new JFrame();
        if (StringUtils.isNotBlank(title)) {
            jFrame.setTitle(title);
        }
        Container container = jFrame.getContentPane();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //????????????
        jFrame.setVisible(true);
        //???????????????
        jFrame.setResizable(true);
        //??????
        if (width != null && height != null) {
            jFrame.setSize(width, height);
        } else {
            jFrame.setSize(1200, 900);
        }


        //??????
        if (x != null && y != null) {
            jFrame.setLocation(x, y);
        } else {
            jFrame.setLocation(60, 60);
        }

        jFrame.setLocationRelativeTo(null);//????????????
//        jFrame.pack();

        if (layoutManager != null) {
            container.setLayout(layoutManager);
        } else {
            //????????????
            container.setLayout(new FlowLayout());
        }

        //???????????????????????????
        centerWindow(container);

        //??????JFrame???java??????
        try {
            removeJavaIconImageSwing(jFrame);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new MyEntry(jFrame, container);
    }

    /**
     * ???????????????????????????
     *
     * @param component
     */
    public static void centerWindow(Component component) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension scmSize = toolkit.getScreenSize();
        Dimension size = component.getPreferredSize();
        int width = component.getWidth(),
                height = component.getHeight();

        component.setLocation(scmSize.width / 2 - (width / 2),
                scmSize.height / 2 - (height / 2));
    }

    /**
     * ??????JFrame???java??????
     *
     * @param f
     */
    public static void removeJavaIconImageSwing(Frame f) throws Exception {
        Class<RunAbs> c = RunAbs.class;
        final String fileName = "images/cloud_74.147368421053px_1258173_easyicon.net.png";
        InputStream asStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        if (asStream == null) {

            String dir = System.getProperty("java.io.tmpdir") + fileName;
            StringBuffer buffer = new StringBuffer();

            FileImageCreator creator = new FileImageCreator(new SimpleDrawer(), dir);
            creator.setWidth(150); //????????????
            creator.setHeight(100); //????????????
            creator.setLineNum(20); //???????????????
            creator.setFontSize(18); //????????????
            creator.setFontName("??????");

            //????????????
            creator.setRadian(30.0); //????????????
            creator.setRotateX(creator.getWidth() / 5);
            creator.setRotateY(creator.getHeight() * 5 / 10);

            creator.generateImage(buffer.toString());
            asStream = new FileInputStream(dir);

        }
        if (asStream != null) {
            try {
                Image image = ImageIO.read(asStream);
                f.setIconImage(image);
            } catch (Exception e) {
                String dir = System.getProperty("java.io.tmpdir") + fileName;
                if (asStream != null) {
                    byte[] bytes = FileUtils.inputStreamByteArray(asStream, true);
                    org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(dir), bytes);
                }
            }
        }

    }

    //-----------------------------------------------------------------||-------------------------------------------------------------
    public static Map.Entry<JDialog, Container> getJDialogModel(String title, Integer width, Integer height, Integer x, Integer y) {
        return getJDialogModel(null, title, width, height, x, y);
    }

    public static Map.Entry<JDialog, Container> getJDialogModel(String title, Integer width, Integer height, Integer x, Integer y, Frame owner) {
        return getJDialogModel(null, title, width, height, x, y, owner);
    }

    public static Map.Entry<JDialog, Container> getJDialogModel(LayoutManager layoutManager, String title, Integer width, Integer height) {
        return getJDialogModel(layoutManager, title, width, height, null, null);
    }

    public static Map.Entry<JDialog, Container> getJDialogModel(LayoutManager layoutManager, String title, Integer width, Integer height, Frame owner) {
        return getJDialogModel(layoutManager, title, width, height, null, null, owner);
    }

    public static Map.Entry<JDialog, Container> getJDialogModel(String title, Integer width, Integer height) {
        return getJDialogModel(null, title, width, height, null, null);
    }

    public static Map.Entry<JDialog, Container> getJDialogModel(Integer width, Integer height) {
        return getJDialogModel(null, null, width, height, null, null, null);
    }

    public static Map.Entry<JDialog, Container> getJDialogModel(LayoutManager layoutManager, String title) {
        return getJDialogModel(layoutManager, title, null, null, null, null);
    }

    public static Map.Entry<JDialog, Container> getJDialogModel(LayoutManager layoutManager, String title, Frame owner) {
        return getJDialogModel(layoutManager, title, null, null, null, null, owner);
    }

    public static Map.Entry<JDialog, Container> getJDialogModel(LayoutManager layoutManager) {
        return getJDialogModel(layoutManager, null, null, null, null, null);
    }

    public static Map.Entry<JDialog, Container> getJDialogModel(String title) {
        return getJDialogModel(null, title, null, null, null, null);
    }

    public static Map.Entry<JDialog, Container> getJDialogModelLocation(LayoutManager layoutManager, String title, Integer x, Integer y) {
        return getJDialogModel(layoutManager, title, null, null, x, y);
    }

    public static Map.Entry<JDialog, Container> getJDialogModelLocation(String title, Integer x, Integer y) {
        return getJDialogModelLocation(null, title, x, y);
    }

    public static Map.Entry<JDialog, Container> getJDialogModelLocation(Integer x, Integer y) {
        return getJDialogModelLocation(null, null, x, y);
    }

    public static Map.Entry<JDialog, Container> getJDialogModel(LayoutManager layoutManager, String title, Integer width, Integer height, Integer x, Integer y) {
        return getJDialogModel(layoutManager, title, width, height, x, y, null);
    }

    public static Map.Entry<JDialog, Container> getJDialogModel(LayoutManager layoutManager, String title, Integer width, Integer height, Integer x, Integer y, Frame owner) {
        return getJDialogModel(layoutManager, title, width, height, x, y, owner, true);
    }

    public static Map.Entry<JDialog, Container> getJDialogModel(LayoutManager layoutManager, String title, Integer width, Integer height, Integer x, Integer y, Frame owner, boolean modal) {
        JDialog jDialog = new JDialog();
        if (owner != null) {
            jDialog = new JDialog(owner, null, modal);
        }
        if (StringUtils.isNotBlank(title)) {
            jDialog.setTitle(title);
        }
        Container container = jDialog.getContentPane();
//        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //????????????
        jDialog.setVisible(true);
        //???????????????
        jDialog.setResizable(false);
        //??????
        if (width != null && height != null) {
            jDialog.setSize(width, height);
        } else {
            jDialog.setSize(1200, 900);
        }
        //??????
        if (x != null && y != null) {
            jDialog.setLocation(x, y);
        } else {
            jDialog.setLocation(60, 60);
        }

        if (layoutManager != null) {
            container.setLayout(layoutManager);
        } else {
            //????????????
            container.setLayout(new FlowLayout());
        }
        return new MyEntry(jDialog, container);
    }

    //-----------------------------------------------------------------||-------------------------------------------------------------

    public static void invokeLater(Runnable runnable, boolean sleep, Long millis) {
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
            UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Microsoft Yahei", Font.PLAIN, 13));
        } catch (Exception e) {

        }

        //?????????????????????
//        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
//        UIManager.setLookAndFeel(lookAndFeel);

        SwingUtilities.invokeLater(runnable);
        if (sleep) {
            endStatic(millis);
        }
    }

    public static void invokeLater(Runnable runnable, boolean sleep) {
        invokeLater(runnable, sleep, null);
    }

    public static void invokeLater(Runnable runnable) {
        invokeLater(runnable, false);
    }

    public static void invokeLater(Runnable runnable, Long millis) {
        invokeLater(runnable, true, millis);
    }

    public static void invokeLater(Runnable runnable, Integer millis) {
        invokeLater(runnable, true, (long) millis);
    }


    public static void end() {
        long millis = 15 * 1000;
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void endStatic() {
        endStatic(null);
    }

    public static void endStatic(Long millis) {
        if (millis == null) {
            millis = (long) 15 * 20 * 1000;
        }
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0); //????????????
//        System.exit(-1); //???????????????
    }

    /**
     * ????????????
     * HSB??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     * ????????????????????????????????????0%-100%??????????????????????????????0??-360??????????? [1]  ??? HSB??????????????????????????????????????????????????????????????????????????????????????? [2]  ???
     *
     * @return Color
     * @author zch
     */
    public static Color getHSBColorRandom() {
        float s = 100f - 3f;
        float b = 100f - 2f;
        float h = 360f - 1f;
        Function<Float, Float> function = new Function<Float, Float>() {
            @Override
            public Float apply(Float max) {
                float min = 0.000000000000000000000000000000000000000000001f;
                return org.apache.commons.lang3.RandomUtils.nextFloat(min, max);
            }
        };
        return Color.getHSBColor(function.apply(s), function.apply(b), function.apply(h));
    }

}
