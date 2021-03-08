package com.zhou.p0103.woodpecker;

import com.common.utils.RunAbs;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class TestBoxLayout {

    public static void main(String[] args) {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> frameModel = RunAbs.getJFrameModel("盒子布局");

            Container container = frameModel.getValue();

            JPanel jPanel = new JPanel();

            //创建一个水平盒子
            Box hBox1 = Box.createHorizontalBox();
            for (int i = 0; i < 3; i++) {
                hBox1.add(new JButton(String.valueOf(i)));
            }

            Box hBox2 = Box.createHorizontalBox();
            for (int i = 0; i < 3; i++) {
                if (i == 1){
                    Component glue = Box.createHorizontalGlue();//添加一个水平方向胶状不可见的组件
                    hBox2.add(glue) ;
                    continue;
                }
                hBox2.add(new JButton(String.valueOf(i)));
            }

            Box vBox = Box.createVerticalBox();
            vBox.add(hBox1);
            vBox.add(hBox2);

            jPanel.add(vBox);

            container.add(jPanel);
//            frameModel.getKey().pack();
        }, true);
    }

}
