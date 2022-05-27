package tool.utils.word.spire;


//https://www.e-iceblue.cn/spiredocforjavaconversion/java-convert-word-to-pdf.html

//https://www.e-iceblue.com/Tutorials/Java/Spire.Doc-for-Java/Program-Guide/Document-Operation/Create-Word-Document-in-Java.html

//https://github.com/eiceblue/Spire.Doc-for-Java

//import com.spire.doc.Document;
//import com.spire.doc.DocumentBase;
//import com.spire.doc.FileFormat;
//import com.spire.doc.Section;
//import com.spire.doc.documents.DocumentObjectType;
//import com.spire.doc.documents.ImageType;
//import com.spire.doc.documents.TextSelection;
//import com.spire.doc.documents.XHTMLValidationType;
//import com.spire.doc.fields.DocPicture;
//import com.spire.doc.fields.TextRange;
//import com.spire.doc.interfaces.ICompositeObject;
//import com.spire.doc.interfaces.IDocument;
//import com.spire.doc.interfaces.IDocumentObject;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;

public class SpireCommonWordUtils {


//    /**
//     * 获取word中的图片
//     *
//     * @param path
//     * @return
//     * @throws Exception
//     */
//    public static List<String> getImages(String path) throws Exception {
//        List<String> stringList = new ArrayList<>();
//        //load word document
//        Document document = new Document();
//        document.loadFromFile(path);
//
//        //create a Queue object
//        Queue nodes = new LinkedList();
//        nodes.add(document);
//
//        //create a List object
//        List<BufferedImage> images = new ArrayList<>();
//
//        //loop through the child objects of the document
//        while (nodes.size() > 0) {
//            ICompositeObject node = (ICompositeObject) nodes.poll();
//            for (int i = 0; i < node.getChildObjects().getCount(); i++) {
//                IDocumentObject child = node.getChildObjects().get(i);
//                if (child instanceof ICompositeObject) {
//                    nodes.add(child);
//                    //get each image and add it to the list
//                    if (child.getDocumentObjectType() == DocumentObjectType.Picture) {
//                        DocPicture picture = (DocPicture) child;
//                        images.add(picture.getImage());
//                    }
//                }
//            }
//        }
//
//        //save images as .png files
//        for (int i = 0; i < images.size(); i++) {
//            String localPath = String.join(File.separator, FileUtils.getTempDirectoryPath(), String.format("ExtractedImage-%d.png", i));
//            File file = new File(localPath);
//            ImageIO.write(images.get(i), FilenameUtils.getExtension(localPath), file);
//            stringList.add(localPath);
//        }
//        return stringList;
//    }
//
//
//    /**
//     * 查找文本并且是查找到的文本变为强调
//     *
//     * @param stringList
//     * @param path
//     * @throws Exception
//     */
//    public static void findAndHightText(List<String> stringList, String path) throws Exception {
//        if (CollectionUtils.isEmpty(stringList)) {
//            return;
//        }
//        //Load a sample Word file
//        Document document = new Document();
//        document.loadFromFile(path);
//        Iterator<String> iterator = stringList.iterator();
//        while (iterator.hasNext()) {
//            //Find all “Spire.Doc for Java” text
//            String findText = iterator.next();
//            TextSelection[] textSelections = document.findAllString(findText, false, true);
//            //Set highlight color
//            for (TextSelection selection : textSelections) {
//                selection.getAsOneRange().getCharacterFormat().setHighlightColor(Color.YELLOW);
//            }
//        }
//        //Save the document
//        document.saveToFile(path);
//    }
//
//    /**
//     * 文本替换
//     *
//     * @param stringMap
//     * @param path
//     * @throws Exception
//     */
//    public static void replaceWithText(Map<String, String> stringMap, String path) throws Exception {
//        if (stringMap == null) {
//            return;
//        }
//        if (stringMap.isEmpty()) {
//            return;
//        }
//        //Load a sample Word file
//        Document document = new Document();
//        document.loadFromFile(path);
//        Iterator<Map.Entry<String, String>> iterator = stringMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, String> stringEntry = iterator.next();
//            //replace the text
//            document.replace(stringEntry.getKey(), stringEntry.getValue(), false, true);
//        }
//        //Save the document
//        document.saveToFile(path);
//    }
//
//    /**
//     * 文本替换文档
//     *
//     * @param stringMap
//     * @param path
//     * @throws Exception
//     */
//    public static void replaceWithWord(Map<String, String> stringMap, String path) throws Exception {
//        if (stringMap == null) {
//            return;
//        }
//        if (stringMap.isEmpty()) {
//            return;
//        }
//        //Load a sample Word file
//        Document document = new Document();
//        document.loadFromFile(path);
//        Iterator<Map.Entry<String, String>> iterator = stringMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, String> stringEntry = iterator.next();
//            //加载用于替换文本的文档
//            IDocument replaceDocument = new Document(stringEntry.getValue());
//            //使用该文档替换模板中的指定文本
//            document.replace(stringEntry.getKey(), replaceDocument, false, true);
//        }
//        //Save the document
//        document.saveToFile(path);
//    }
//
//
//    /**
//     * 用Word中的文本替换图像
//     *
//     * @param stringMap
//     * @param path
//     * @throws Exception
//     */
//    public static void replaceTextWithImage(Map<String, String> stringMap, String path) throws Exception {
//        if (stringMap == null) {
//            return;
//        }
//        if (stringMap.isEmpty()) {
//            return;
//        }
//        //Load a sample Word file
//        Document document = new Document();
//        document.loadFromFile(path);
//        Iterator<Map.Entry<String, String>> iterator = stringMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, String> stringEntry = iterator.next();
//            //Find the string text in the document
//            TextSelection[] selections = document.findAllString(stringEntry.getKey(), true, true);
//            //Replace the string with an image
//            int index = 0;
//            TextRange range = null;
//            for (Object obj : selections) {
//                TextSelection textSelection = (TextSelection) obj;
//                DocPicture pic = new DocPicture(document);
//                pic.loadImage(stringEntry.getValue());
//                range = textSelection.getAsOneRange();
//                index = range.getOwnerParagraph().getChildObjects().indexOf(range);
//                range.getOwnerParagraph().getChildObjects().insert(index, pic);
//                range.getOwnerParagraph().getChildObjects().remove(range);
//            }
//        }
//        //Save the document
//        document.saveToFile(path);
//    }
//
//    /**
//     * html 转 word
//     *
//     * @param htmlPath
//     * @param wordPath
//     * @throws Exception
//     */
//    public static void htmlFileToWord(String htmlPath, String wordPath) throws Exception {
//        //open an html file.
//        Document document = new Document();
//        document.loadFromFile(htmlPath, FileFormat.Html, XHTMLValidationType.None);
//        //save to a Word document.
//        document.saveToFile(wordPath);
//    }
//
//    /**
//     * html 文本转为html
//     * @param htmlText
//     * @param wordPath
//     * @throws Exception
//     */
//    public static void appendHtmlFileToWord(String htmlText, String wordPath) throws Exception {
//        //open an html file.
//        Document document = new Document();
//
//        //add a section.
//        Section sec = document.addSection();
//        //add a paragraph and append html string.
//        sec.addParagraph().appendHTML(htmlText);
//        //save to a Word document.
//        document.saveToFile(wordPath);
//    }
//
//    /**
//     * text 转word
//     *
//     * @param text
//     * @param wordPath
//     * @throws Exception
//     */
//    public static void textFileToWord(String text, String wordPath) throws Exception {
//        Document document = new Document();
//        //add a section.
//        Section sec = document.addSection();
//        //add a paragraph and append html string.
//        sec.addParagraph().appendText(text);
//        //save to a Word file.
//        document.saveToFile(wordPath);
//    }
//
//
//    /**
//     * word转pdf
//     *
//     * @param targetPath
//     * @param wordPath
//     * @throws Exception
//     */
//    public static void wordFileToPDF(String targetPath, String wordPath) throws Exception {
//        //加载word示例文档
//        Document document = new Document();
//        document.loadFromFile(wordPath);
//        //保存结果文件
//        document.saveToFile(targetPath, FileFormat.PDF);
//    }
//
//    /**
//     * word 转 xps
//     *
//     * @param targetPath
//     * @param wordPath
//     * @throws Exception
//     */
//    public static void wordFileToXPS(String targetPath, String wordPath) throws Exception {
//        //加载word示例文档
//        Document document = new Document();
//        document.loadFromFile(wordPath);
//        //将Word保存为XPS格式
//        document.saveToFile(targetPath, FileFormat.XPS);
//    }
//
//    /**
//     * word 转 Rtf
//     *
//     * @param targetPath
//     * @param wordPath
//     * @throws Exception
//     */
//    public static void wordFileToRtf(String targetPath, String wordPath) throws Exception {
//        //加载word示例文档
//        Document document = new Document();
//        document.loadFromFile(wordPath);
//        //将Word保存为Rtf格式
//        document.saveToFile(targetPath, FileFormat.Rtf);
//    }
//
//    /**
//     * word 转 png
//     *
//     * @param targetPath
//     * @param wordPath
//     * @throws Exception
//     */
//    public static void wordFileToPng(String targetPath, String wordPath) throws Exception {
//        //加载word示例文档
//        Document document = new Document();
//        document.loadFromFile(wordPath);
//        //将指定页保存为BufferedImage
//        BufferedImage image = document.saveToImages(0, ImageType.Bitmap);
//        //将图片数据保存为PNG格式文档
//        File file = new File(targetPath);
//        ImageIO.write(image, FilenameUtils.getExtension(targetPath), file);
//    }
//
//    /**
//     * 文档合并
//     * @param targetPath
//     * @param stringList
//     * @throws Exception
//     */
//    public static void mergeDocument(String targetPath, List<String> stringList) throws Exception {
//        if (CollectionUtils.isEmpty(stringList)) {
//            return;
//        }
//        Document document = new Document();
//        Iterator<String> iterator = stringList.iterator();
//        while (iterator.hasNext()) {
//            String next = iterator.next();
//            //使用insertTextFromFile方法将第二个文档的内容插入到第一个文档
//            String extension = FilenameUtils.getExtension(next);
//            if ("doc".equalsIgnoreCase(extension)) {
//                document.insertTextFromFile(next, FileFormat.Doc);
//            }
//            if ("docx".equalsIgnoreCase(extension)) {
//                document.insertTextFromFile(next, FileFormat.Docx_2010);
//            }
//            if ("dot".equalsIgnoreCase(extension)) {
//                document.insertTextFromFile(next, FileFormat.Dot);
//            }
//            if ("dotm".equalsIgnoreCase(extension)) {
//                document.insertTextFromFile(next, FileFormat.Dotm);
//            }
//        }
//        document.saveToFile(targetPath);
//    }




}
