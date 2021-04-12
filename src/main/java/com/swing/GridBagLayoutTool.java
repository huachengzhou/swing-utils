package com.swing;


import java.awt.*;

public class GridBagLayoutTool {
    //   https://blog.csdn.net/wstz_5461/article/details/78067176

    //https://blog.csdn.net/xietansheng/article/details/72814492
    /**
     * @param x       x轴坐标
     * @param y       y轴纵坐标
     * @param column  列数
     * @param row     行数
     * @param weightx x轴权重
     * @param weighty y轴权重
     * @param insets  外边距
     * @param fill    填充方式
     * @param anchor
     * @return
     */
    public static GridBagConstraints createGridBagConstraints(int x, int y, int column, int row, double weightx, double weighty, Insets insets, int fill, int anchor) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();//实例化这个对象用来对组件进行管理
        gridBagConstraints.fill = fill;

        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = row;//占1行
        gridBagConstraints.gridheight = column;//占15列
        gridBagConstraints.weightx = weightx;//填充权重 , 默认为0
        gridBagConstraints.weighty = weighty;//填充权重 , 默认为 0
        if (insets != null) {
            gridBagConstraints.insets = insets;//相当于html中margin top,left,bottom,right
        }
        gridBagConstraints.anchor = anchor;

        return gridBagConstraints;
    }

    public static GridBagConstraints createGridBagConstraints(int x, int y, int column, int row, double weightx, double weighty, Insets insets, int fill) {
        return createGridBagConstraints(x, y, column, row, weightx, weighty, insets, fill, GridBagConstraints.CENTER);
    }

    public static GridBagConstraints createGridBagConstraints(int x, int y, int column, int row, double weightx, double weighty, Insets insets) {
        //BOTH 是为了设置如果组件所在的区域比组件本身要大时的显示情况
        return createGridBagConstraints(x, y, column, row, weightx, weighty, insets, GridBagConstraints.BOTH);
    }

    public static GridBagConstraints createGridBagConstraints(int x, int y, int column, int row, double weightx, double weighty) {
        //BOTH 是为了设置如果组件所在的区域比组件本身要大时的显示情况
        return createGridBagConstraints(x, y, column, row, weightx, weighty, null);
    }

    public static GridBagConstraints createGridBagConstraints(int x, int y, int column, int row) {
        //BOTH 是为了设置如果组件所在的区域比组件本身要大时的显示情况
        return createGridBagConstraints(x, y, column, row, 0, 0);
    }

    public static GridBagConstraints createGridBagConstraints(int x, int y, int column, int row,Insets insets) {
        //BOTH 是为了设置如果组件所在的区域比组件本身要大时的显示情况
        return createGridBagConstraints(x, y, column, row, 0, 0, insets, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
    }


}
