package tool.utils.pdf;


import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import tool.utils.word.spire.SpireCommonPDFUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;

/**
 * Created by zch on 2019-10-24.
 */
public class PDFUtil {
    public static String PDF = "pdf";
    public static String PNG = "png";

    /**
     * 将pdf文件中的图片存入指定目录,并且可以指定图片后缀
     *
     * @param path
     * @param targetFolder
     * @param suffix
     * @throws Exception
     */
    public static void extractImages(String path, String targetFolder, String suffix) throws Exception {
        extractImages(new File(path), null, targetFolder, suffix);
    }

    /**
     * 将pdf文件中的图片存入指定目录
     *
     * @param path
     * @param targetFolder
     * @throws Exception
     */
    public static void extractImages(String path, String targetFolder) throws Exception {
        extractImages(new File(path), null, targetFolder, null);
    }

    /**
     * 将pdf文件中的图片存入指定目录,并且收集存入图片路径
     *
     * @param path
     * @param targetFolder
     * @param linkedList
     * @throws Exception
     */
    public static void extractImages(String path, String targetFolder, LinkedList<String> linkedList) throws Exception {
        extractImages(new File(path), linkedList, targetFolder, null);
    }


    /**
     * pdf 转换为word
     *
     * @param path
     * @param targetPath
     * @throws Exception
     */
    public static void pdfToWordConversion(String path, String targetPath) throws Exception {
        String folder = System.getProperty("java.io.tmpdir");
        File file = new File(path);
        String fileExtension = FilenameUtils.getExtension(file.getName());
        if (!fileExtension.equals(PDF)) {
            throw new Exception("not if pdf");
        }
        com.aspose.words.Document asposeDoc = new com.aspose.words.Document();
        com.aspose.words.DocumentBuilder documentBuilder = new com.aspose.words.DocumentBuilder(asposeDoc);
        documentBuilder.getFont().setName("微软雅黑");
        documentBuilder.getFont().setColor(Color.red);
        documentBuilder.getFont().setSize(14);
        PDDocument document = PDDocument.load(file);
        int pageSize = document.getNumberOfPages();
        // 一页一页读取
        for (int i = 0; i < pageSize; i++) {
            PDPage page = document.getPage(i);
            if (page == null) {
                continue;
            }
            // 文本内容
            PDFTextStripper stripper = new PDFTextStripper();
            // 设置按顺序输出
            stripper.setSortByPosition(true);
            stripper.setStartPage(i + 1);
            stripper.setEndPage(i + 1);
            String text = stripper.getText(document);
            documentBuilder.insertParagraph();//插入一个段落
            documentBuilder.write(text);
            PDResources resources = page.getResources();
            Iterable<COSName> cosNames = resources.getXObjectNames();
            if (cosNames != null) {
                Iterator<COSName> cosNamesIter = cosNames.iterator();
                while (cosNamesIter.hasNext()) {
                    COSName cosName = cosNamesIter.next();
                    if (resources.isImageXObject(cosName)) {
                        PDImageXObject Ipdmage = (PDImageXObject) resources.getXObject(cosName);
                        BufferedImage image = Ipdmage.getImage();
                        String imagePath = String.join("", folder, File.separator, UUID.randomUUID().toString(), ".", PNG);
                        FileOutputStream out = new FileOutputStream(imagePath);
                        ImageIO.write(image, PNG, out);
                        documentBuilder.insertImage(new FileInputStream(imagePath), image.getWidth(), image.getHeight());
                    }
                }
            }
        }
        asposeDoc.save(targetPath, com.aspose.words.SaveFormat.DOC);
    }

    public static String pdfToWordConversion(String pdfFile) throws Exception {
        PDDocument doc = PDDocument.load(new File(pdfFile));
        int pagenumber = doc.getNumberOfPages();
        String fileName = FilenameUtils.getFullPath(pdfFile) + FilenameUtils.getBaseName(pdfFile) + ".doc";
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(fileName);
        Writer writer = new OutputStreamWriter(fos, "UTF-8");
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setSortByPosition(true);// 排序
        stripper.setStartPage(1);// 设置转换的开始页
        stripper.setEndPage(pagenumber);// 设置转换的结束页
        stripper.writeText(doc, writer);
        writer.close();
        doc.close();
        return fileName;
    }

    public static String pdfToWordConversion2(String pdfFile) throws Exception {
        return SpireCommonPDFUtils.pdfToWordConversion(pdfFile) ;
    }

    /**
     * 获取pdf文本内容
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static String getPDFText(String path) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        File file = new File(path);
        String fileExtension = FilenameUtils.getExtension(file.getName());
        if (!fileExtension.equals(PDF)) {
            throw new Exception("not if pdf");
        }
        PDDocument document = PDDocument.load(file);
        int pageSize = document.getNumberOfPages();
        // 一页一页读取
        for (int i = 0; i < pageSize; i++) {
            // 文本内容
            PDFTextStripper stripper = new PDFTextStripper();
            // 设置按顺序输出
            stripper.setSortByPosition(true);
            stripper.setStartPage(i + 1);
            stripper.setEndPage(i + 1);
            String text = stripper.getText(document);
            stringBuilder.append(text);
        }
        return stringBuilder.toString();
    }

    /**
     * 获取图片
     *
     * @param file
     * @param linkedList
     * @param targetFolder
     * @param suffix
     * @throws Exception
     */
    public static void extractImages(File file, LinkedList<String> linkedList, String targetFolder, String suffix) throws Exception {
        if (StringUtils.isEmpty(suffix)) {
            suffix = PNG;
        }
        String fileExtension = FilenameUtils.getExtension(file.getName());
        if (!fileExtension.equals(PDF)) {
            throw new Exception("not if pdf");
        }
        PDDocument document = PDDocument.load(file);
        int pageSize = document.getNumberOfPages();
        // 一页一页读取
        for (int i = 0; i < pageSize; i++) {
            // 图片内容
            PDPage page = document.getPage(i);
            PDResources resources = page.getResources();
            Iterable<COSName> cosNames = resources.getXObjectNames();
            if (cosNames != null) {
                Iterator<COSName> cosNamesIter = cosNames.iterator();
                while (cosNamesIter.hasNext()) {
                    COSName cosName = cosNamesIter.next();
                    if (resources.isImageXObject(cosName)) {
                        PDImageXObject Ipdmage = (PDImageXObject) resources.getXObject(cosName);
                        BufferedImage image = Ipdmage.getImage();
                        File fileFolder = new File(targetFolder);
                        if (!fileFolder.isDirectory()) {
                            fileFolder.mkdirs();
                        }
                        String imagePath = String.join("", targetFolder, File.separator, UUID.randomUUID().toString(), ".", suffix);
                        if (linkedList != null) {
                            linkedList.add(imagePath);
                        }
                        FileOutputStream out = new FileOutputStream(imagePath);
                        ImageIO.write(image, suffix, out);
                    }
                }
            }
        }
    }
}
