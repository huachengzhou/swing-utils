package com.zhou.p0103.woodpecker;

import com.common.utils.RunAbs;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class TestGridLayout {
    /*
    *
      *网格布局特点：
       使容器中的各组件呈M行×N列的网格状分布。

       网格每列宽度相同，等于容器的宽度除以网格的列数。

       网格每行高度相同，等于容器的高度除以网格的行数。

       各组件的排列方式为：从上到下，从左到右。

       组件放入容器的次序决定了它在容器中的位置。

       容器大小改变时，组件的相对位置不变，大小会改变。

       设置网格布局行数和列数时，行数或者列数可以有一个为零。若rows为0，cols为3，则列数固定为3，行数不限，每行只能放3个控件或容器。若cols为0，rows为3，则行数固定为3，列数不限，且每行必定有控件，若组件个数不能整除行数，则除去最后一行外的所有行组件个数为：Math.ceil(组件个数/rows)。

        Math.ceil(double x)：传回不小于x的最小整数值。比如行数为3，组件数为13个，则Math.ceil(13/3)=5，即第一行，第二行组件数各为5个，剩下的组件放在最后一行。

       若组件数超过网格设定的个数，则布局管理器会自动增加网格个数，原则是保持行数不变。



        构造方法摘要

        GridLayout()： 创建具有默认值的网格布局，即每个组件占据一行一列。

        GridLayout(int rows, int cols) :创建具有指定行数和列数的网格布局。Rows为行数，cols为列数。

        GridLayout(int rows, int cols, int hgap, int vgap) :创建具有指定行数、列数以及组件水平、纵向一定间距的网格布局。


        方法摘要

        getColumns() ：获取此布局中的列数。

        getHgap()：获取组件之间的水平间距。

        getRows() ：获取此布局中的行数。

        getVgap() ：获取组件之间的垂直间距。

        removeLayoutComponent(Component comp) ：从布局移除指定组件。

        setColumns(int cols) ：将此布局中的列数设置为指定值。

        setHgap(int hgap)：将组件之间的水平间距设置为指定值。

        setRows(int rows)：将此布局中的行数设置为指定值。

        setVgap(int vgap) ：将组件之间的垂直间距设置为指定值。

        toString()：返回此网格布局的值的字符串表示形式。
    * */
    public static void main(String[] args) {
        RunAbs.invokeLater(() -> {
            Map.Entry<JFrame, Container> frameModel = RunAbs.getJFrameModel("流布局");
            Container container = frameModel.getValue();
            GridLayout layout = new GridLayout(3, 3, 0, 0);
            JPanel jPanel = new JPanel(layout);
            for (int i = 0; i < 9; i++) {
                jPanel.add(new JButton(String.valueOf(i)));
            }
            container.add(jPanel);
        }, true);
    }


}
