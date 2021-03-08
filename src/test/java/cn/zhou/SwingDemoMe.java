package cn.zhou;

import com.common.utils.FileImageCreator;
import com.common.utils.RunAbs;
import com.common.utils.SimpleDrawer;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Test;
import tool.utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.util.function.Consumer;

public class SwingDemoMe {


    @Test
    public void testRun2() {

        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String title) {
                Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel(title, null, null, null, null);
            }
        };

        RunAbs.invokeLater(() -> {
            consumer.accept("测试窗体");
        }, true);

    }

    /**
     * 阻塞窗体测试 对话框
     */
    @Test
    public void testRun3() {

        Consumer<JFrame> consumer = new Consumer<JFrame>() {
            @Override
            public void accept(JFrame jFrame) {
                //第一个参数 父窗体对象,第二个参数对话框标题,是否阻塞父窗体
                JDialog jDialog = new JDialog(jFrame, "对话框标题", true);
                jDialog.setVisible(true);
                jDialog.setBounds(100, 100, 100, 100);
                Container contentPane = jDialog.getContentPane();
                contentPane.add(new JLabel("这是一个对话框"));
            }
        };

        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("阻塞窗体测试", null, null, null, null);
            Container container = jFrameModel.getValue();

            JButton jButton = new JButton("弹出对话框");
            container.add(jButton);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    consumer.accept(jFrameModel.getKey());
                }
            });
        }, 5000 * 25);

    }

    /**
     * 阻塞窗体测试 优化
     */
    @Test
    public void testRun4() {

        Consumer<JFrame> consumer = new Consumer<JFrame>() {
            @Override
            public void accept(JFrame jFrame) {
                Map.Entry<JDialog, Container> entry = RunAbs.getJDialogModel("对话框标题", 150, 150, 100, 100, jFrame);
                Container contentPane = entry.getValue();
                contentPane.add(new JLabel("这是一个对话框"));
            }
        };

        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("阻塞窗体测试", 300, 300, 50, 50);
            Container container = jFrameModel.getValue();

            JButton jButton = new JButton("弹出对话框");
            container.add(jButton);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    consumer.accept(jFrameModel.getKey());
                }
            });
        }, 5000 * 25);
    }

    /**
     * label 使用
     */
    @Test
    public void testRun5() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体", 300, 300, 100, 150);
            Container container = jFrameModel.getValue();

            JLabel jLabel = new JLabel("这是一个标签!");

            //获取系统中可用的字体的名字
            GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font[] eAllFonts = e.getAllFonts();
            if (eAllFonts.length != 0) {
                Font eAllFont = eAllFonts[0];

                jLabel.setFont(eAllFont);
            }
            jLabel.setFont(new Font("微软雅黑", 2, 14));
            container.add(jLabel);

        }, 5000 * 25);
    }

    /**
     * 图标 使用
     */
    @Test
    public void testRun6() throws IOException {
        String path = getPath();
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体", 300, 300, 100, 150);
            Container container = jFrameModel.getValue();

            JLabel jLabel = new JLabel("标签!");
//            Icon icon = new ImageIcon(path) ;
            //或者这样
            Icon icon = new ImageIcon("src/test/java/cn/zhou/img.jpeg");
            jLabel.setIcon(icon);
            jLabel.setFont(new Font("微软雅黑", 2, 14));
            container.add(jLabel);

        }, 5000 * 25);
        System.out.println(path);
    }

    /**
     * 绝对布局 使用绝对布局的窗口通常都是固定大小的，组件的位置和形状  不会随着窗体 的改变而发生变化
     */
    @Test
    public void testRun7() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体", 300, 300, 100, 150);
            Container container = jFrameModel.getValue();
            container.setLayout(null); // 绝对布局
            JButton b1 = new JButton("按钮1");
            JButton b2 = new JButton("按钮1");
            b1.setBounds(30, 30, 40, 40);
            b2.setBounds(40, 40, 40, 40);
            container.add(b1);
            container.add(b2);
        }, true);
    }

    /**
     * 流式布局 像流水一样流动
     */
    @Test
    public void testRun8() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体", 400, 300, 300, 200);
            Container container = jFrameModel.getValue();
            container.setLayout(new FlowLayout()); // 流式布局
            container.setLayout(new FlowLayout(FlowLayout.CENTER)); // 流式布局 默认和上面一样
            container.setLayout(new FlowLayout(FlowLayout.LEFT)); // 流式布局 左对齐
            container.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5)); // 流式布局 右对齐,间距
            for (int i = 0; i < 10; i++) {
                JButton jButton = new JButton(String.valueOf(i + "-" + System.currentTimeMillis()));
                container.add(jButton);
            }
        }, true);
    }

    /***
     * 边界布局
     */
    @Test
    public void testRun9() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体", 400, 300, 300, 200);
            Container container = jFrameModel.getValue();
            container.setLayout(new BorderLayout(2, 5));
            for (int i = 0; i < 5; i++) {
                JButton jButton = new JButton(String.valueOf(i + "-" + System.currentTimeMillis()));
                switch (i) {
                    case 0:
                        jButton.setText("中心");
                        container.add(jButton, BorderLayout.CENTER);
                        break;
                    case 1:
                        jButton.setText("东部");
                        container.add(jButton, BorderLayout.EAST);
                        break;
                    case 2:
                        jButton.setText("南部");
                        container.add(jButton, BorderLayout.SOUTH);//南部
                        break;
                    case 3:
                        jButton.setText("北部");
                        container.add(jButton, BorderLayout.NORTH);//北部
                        break;
                    case 4:
                        jButton.setText("西部");
                        container.add(jButton, BorderLayout.WEST);
                        break;
                    default:
                        break;
                }
            }
        }, true);
    }

    /**
     * 网格布局
     */
    @Test
    public void testRun10() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体", 400, 300, 300, 200);
            Container container = jFrameModel.getValue();
            //7行3列 水平间距5像素,垂直间距5像素
            container.setLayout(new GridLayout(7, 3,5,5));
            for (int i = 0; i < 20; i++) {
                JLabel jLabel = new JLabel(RandomStringUtils.randomAscii(10)) ;
                jLabel.setForeground(RunAbs.getHSBColorRandom());
                container.add(jLabel) ;
            }
        }, true);
    }

    private String getPath() throws IOException {
        String path = FileUtils.getTestDataDir(getClass(), "") + "img.jpeg";
        StringBuffer sb = new StringBuffer();
        sb.append("这是一个图片\n");
        sb.append(RandomStringUtils.random(22));

        FileImageCreator creator = new FileImageCreator(new SimpleDrawer(), path);
        creator.setWidth(150); //图片宽度
        creator.setHeight(100); //图片高度
        creator.setLineNum(20); //干扰线条数
        creator.setFontSize(18); //字体大小
        creator.setFontName("黑体");

        //文字旋转
        creator.setRadian(30.0); //旋转弧度
        creator.setRotateX(creator.getWidth() / 5);
        creator.setRotateY(creator.getHeight() * 5 / 10);

        creator.generateImage(sb.toString());

        return path;
    }

}
