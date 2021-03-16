package com.swing;

import javax.swing.*;
import java.awt.*;

public class NewJFrame extends JFrame {
    public NewJFrame(String title, Dimension dimension ) throws HeadlessException {
        super(title);
        this.setSize(dimension);

        //自动化页面大小--全屏
//        int fraWidth = this.getWidth();//frame的宽
//        int fraHeight = this.getHeight();//frame的高
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int screenWidth = screenSize.width;
//        int screenHeight = screenSize.height;
//        this.setSize(screenWidth, screenHeight);
//        this.setLocation(0, 0);
//        float proportionW = screenWidth/fraWidth;
//        float proportionH = screenHeight/fraHeight;

//        NewJFrame.modifyComponentSize(this, proportionW,proportionH);
//        this.toFront();
    }

    /**
     * frame中的控件自适应frame大小：改变大小位置和字体
     * @param frame 要控制的窗体
     * @param proportion 当前和原始的比例
     */
    public static void modifyComponentSize(JFrame frame,float proportionW,float proportionH){

        try
        {
            Component[] components = frame.getRootPane().getContentPane().getComponents();
            for(Component co:components)
            {
//				String a = co.getClass().getName();//获取类型名称
//				if(a.equals("javax.swing.JLabel"))
//				{
//				}
                float locX = co.getX() * proportionW;
                float locY = co.getY() * proportionH;
                float width = co.getWidth() * proportionW;
                float height = co.getHeight() * proportionH;
                co.setLocation((int)locX, (int)locY);
                co.setSize((int)width, (int)height);
                int size = (int)(co.getFont().getSize() * proportionH);
                Font font = new Font(co.getFont().getFontName(), co.getFont().getStyle(), size);
                co.setFont(font);
            }
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
    }
}
