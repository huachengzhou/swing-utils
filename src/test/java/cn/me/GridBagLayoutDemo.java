package cn.me;

import com.common.utils.RunAbs;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GridBagLayoutDemo {

    @Test
    public void testStart2() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体", 800, 600, 300, 200);
            Container container = jFrameModel.getValue();
            container.setLayout(new GridBagLayout());//布局方式
            JPanel a = new JPanel();
            a.setBackground(Color.BLACK);
            JPanel b = new JPanel();
            b.setBackground(Color.BLUE);
            JPanel c = new JPanel();
            c.setBackground(Color.RED);

            GridBagConstraints aG = new GridBagConstraints();
            aG.gridx = 2;
            aG.gridy = 2;
            aG.weighty = 4;
            aG.weightx = 4;
            aG.fill = GridBagConstraints.BOTH;

            GridBagConstraints bG = new GridBagConstraints();
            bG.gridx = 4;
            bG.gridy = 2;
            bG.weighty = 4;
            bG.weightx = 4;
            bG.fill = GridBagConstraints.BOTH;

            GridBagConstraints cG = new GridBagConstraints();
            cG.gridx = 8;
            cG.gridy = 2;
            cG.weighty = 4;
            cG.weightx = 4;
            cG.fill = GridBagConstraints.BOTH;

            container.add(a, aG);
            container.add(b, bG);
            container.add(c, cG);
        }, true);
    }

    @Test
    public void testStart3() {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> jFrameModel = RunAbs.getJFrameModel("窗体", 800, 600, 300, 200);
            Container container = jFrameModel.getValue();

            GridBagLayout gridBagLayout = new GridBagLayout();
            GridBagConstraints cons = null;


            JPanel a = new JPanel();
            a.setBackground(Color.BLACK);
            JPanel b = new JPanel();
            b.setBackground(Color.BLUE);
            JPanel c = new JPanel();
            c.setBackground(Color.RED);
            JPanel d = new JPanel();
            c.setBackground(Color.PINK);

            cons = new GridBagConstraints();
            cons.weightx = 2;
            cons.weighty = 2;
            gridBagLayout.addLayoutComponent(a, cons);

            cons = new GridBagConstraints();
            gridBagLayout.addLayoutComponent(b, cons);

            cons = new GridBagConstraints();
            gridBagLayout.addLayoutComponent(c, cons);

            cons = new GridBagConstraints();
            cons.gridwidth = GridBagConstraints.REMAINDER ;
            cons.fill = GridBagConstraints.BOTH ;
            gridBagLayout.addLayoutComponent(d, cons);

            //创建中间容器
            JPanel panel = new JPanel(gridBagLayout);
            panel.add(a) ;
            panel.add(b) ;
            panel.add(c) ;
            panel.add(d) ;
            jFrameModel.getKey().setContentPane(panel);

        }, true);
    }

}
