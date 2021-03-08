package com.zhou.p0103.woodpecker;


import com.common.utils.RunAbs;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class TestFlowLayout {

    /*
    * FlowLayout（流式布局）
使用FlowLayout布局方式的容器中组件按照加入的先后顺序按照设置的对齐方式（居中、左对齐、右对齐）从左向右排列，一行排满（即组件超过容器宽度后）到下一行开始继续排列。

    FlowLayout()------构造一个新的 FlowLayout，它是默认居中对齐的，默认的水平和垂直间隙是5个像素

    FlowLayout(int align)------五个参数值及含义如下：

    0或FlowLayout.lEFT ，控件左对齐

    1或FlowLayout.CENTER ，居中对齐

    2或FlowLayout.RIGHT ，右对齐

    3或FlowLayout.LEADING，控件与容器方向开始边对应

    4或FlowLayout.TRAILING，控件与容器方向结束边对应

    如果是0、1、2、3、4之外的整数，则为左对齐

    FlowLayout(int align, int hgap, int vgap) --------创建一个新的流布局管理器，它具有指定的对齐方式以及指定的水平和垂直间隙
    * */

    public static void main(String[] args) {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> frameModel = RunAbs.getJFrameModel("流布局");
            Container container = frameModel.getValue();
            FlowLayout layout = new FlowLayout();
//            layout.setAlignment(FlowLayout.CENTER);
            layout.setAlignment(FlowLayout.RIGHT);
            container.setLayout(layout);
            for (int i = 0; i < 10; i++) {
                container.add(new JButton(String.valueOf(i)));
            }
        }, true);
    }
}
