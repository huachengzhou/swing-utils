package com.common.utils;

import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageUtils {

    public static void createImgOne(String path, String text) throws Exception {
        int imageWidth = 128;//图片的宽度
        int imageHeight = 64;//图片的高度
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        Font font = new Font("微软雅黑", Font.PLAIN, 12);
        graphics.setFont(font);
        graphics.fillRect(0, 0, imageWidth, imageHeight);
        graphics.setColor(new Color(0, 0, 0));//设置黑色字体,同样可以graphics.setColor(Color.black);
        graphics.drawString(text, 0, 10);
        ImageIO.write(image, FilenameUtils.getExtension(path), new File(path));//生成图片方法一
        //ImageIO,可以生成不同格式的图片，比如JPG,PNG,GIF.....
        //生成图片方法二开始,只知道生成jpg格式的图片,这个方法其他格式的还是不知道怎么弄。
        //生成图片方法二结束
        graphics.dispose();//释放资源
    }

    // 根据str,font的样式以及输出文件目录
    public static void createImage( String  path, String str,Integer width, Integer height) throws Exception {
        File outFile = new File(path) ;

        Font font = new Font("宋体", Font.BOLD, 30);
        // 创建图片
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setClip(0, 0, width, height);
//        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景
        g.setColor(Color.red);// 在换成黑色
        g.setFont(font);// 设置画笔字体
        /** 用于获得垂直居中y */
        Rectangle clip = g.getClipBounds();
        FontMetrics fm = g.getFontMetrics(font);
        int ascent = fm.getAscent();
        int descent = fm.getDescent();
        int y = (clip.height - (ascent + descent)) / 2 + ascent;
        for (int i = 0; i < 6; i++) {// 256 340 0 680
            g.drawString(str, i * 680, y);// 画出字符串
        }
        g.dispose();
        ImageIO.write(image, FilenameUtils.getExtension(path), outFile);// 输出png图片
    }

    /**
     * 其中是生成推广的图片的
     */
    public static void draw(String imagePath,String path,String content){
        //读取图片文件，得到BufferedImage对象
        BufferedImage bimg;
        try {
            bimg = ImageIO.read(new FileInputStream(imagePath));

            //得到Graphics2D 对象
            Graphics2D g2d=(Graphics2D)bimg.getGraphics();
            //设置颜色和画笔粗细
            g2d.setColor(Color.BLUE);
            g2d.setStroke(new BasicStroke(3));
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 80));

            //绘制图案或文字
            g2d.drawString(content, 150, 468);
            //保存新图片
            ImageIO.write(bimg,  FilenameUtils.getExtension(path),new FileOutputStream(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        createImgOne("D:\\IdeaProjects\\swing-utils\\sdhd.png", "1");
        draw("D:\\IdeaProjects\\swing-utils\\sdhd.png", "D:\\IdeaProjects\\swing-utils\\sdhd1.png","2");
        createImage("D:\\IdeaProjects\\swing-utils\\sdhd2.png", "sdhsdhdsh",300,300);
    }

}
