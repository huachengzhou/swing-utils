package tool.utils.word;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import tool.utils.word.apache.HwpfUtils;
import tool.utils.word.apache.XwpfUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author: zch
 * @date: 2019/5/31 19:08
 * @description:
 */
public class WordHelp {

    public void replaceText(String path, Map<String, String> map) throws Exception {
        if (isWord2003(path)) {
            HWPFDocument hwpfDocument = new HWPFDocument(new FileInputStream(path));
            org.apache.poi.hwpf.usermodel.Range range = hwpfDocument.getRange();
            int i = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (StringUtils.contains(range.text(), entry.getKey())) {
                    i++;
                    range.replaceText(entry.getKey(), entry.getValue());
                }
            }
            if (i != 0) {
                hwpfDocument.write(new FileOutputStream(path));
            }
        }
        if (isWord2007(path)) {
            XWPFDocument document = new XWPFDocument(new FileInputStream(path));
            /**
             * 替换段落中的指定文字
             */
            Iterator<org.apache.poi.xwpf.usermodel.XWPFParagraph> itPara = document.getParagraphsIterator();
            while (itPara.hasNext()) {
                org.apache.poi.xwpf.usermodel.XWPFParagraph paragraph = itPara.next();
                Set<String> set = map.keySet();
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    List<org.apache.poi.xwpf.usermodel.XWPFRun> run = paragraph.getRuns();
                    for (int i = 0; i < run.size(); i++) {
                        if (run.get(i).getText(run.get(i).getTextPosition()) != null && run.get(i).getText(run.get(i).getTextPosition()).equals(key)) {
                            /**
                             * 参数0表示生成的文字是要从哪一个地方开始放置,设置文字从位置0开始
                             * 就可以把原来的文字全部替换掉了
                             */
                            if (StringUtils.isNotEmpty(map.get(key))) {
                                run.get(i).setText(map.get(key), 0);
                            }
                        }
                    }
                }
            }
            document.write(new FileOutputStream(path));
        }
    }

    /**
     * 获取word中表格内容
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static String getWordTableContent(String path) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        if (isWord2007(path)) {
            List<org.apache.poi.xwpf.usermodel.XWPFTable> xwpfTableList = XwpfUtils.getWordXWPFTable(path);
            if (CollectionUtils.isNotEmpty(xwpfTableList)) {
                xwpfTableList.forEach(table -> {
                    List<org.apache.poi.xwpf.usermodel.XWPFTableRow> xwpfTableRows = XwpfUtils.getWordXWPFRow(table);
                    if (CollectionUtils.isNotEmpty(xwpfTableRows)) {
                        xwpfTableRows.forEach(row -> {
                            List<org.apache.poi.xwpf.usermodel.XWPFTableCell> xwpfTableCellList = XwpfUtils.getWordXWPFCell(row);
                            if (CollectionUtils.isNotEmpty(xwpfTableCellList)) {
                                xwpfTableCellList.forEach(cell -> {
                                    List<org.apache.poi.xwpf.usermodel.XWPFParagraph> xwpfParagraphList = XwpfUtils.getWordXWPFParagraph(cell);
                                    if (CollectionUtils.isNotEmpty(xwpfParagraphList)) {
                                        xwpfParagraphList.forEach(paragraph -> {
                                            if (StringUtils.isNotEmpty(paragraph.getText())) {
                                                stringBuilder.append(paragraph.getText());
                                            } else {
                                                paragraph.getRuns().forEach(xwpfRun -> {
                                                    try {
                                                        stringBuilder.append(xwpfRun.getText(0));
                                                    } catch (Exception e) {
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }
        if (isWord2003(path)) {
            List<org.apache.poi.hwpf.usermodel.Table> tableList = HwpfUtils.getWordHwpfTable(path);
            if (CollectionUtils.isNotEmpty(tableList)) {
                tableList.forEach(table -> {
                    List<org.apache.poi.hwpf.usermodel.TableRow> tableRowList = HwpfUtils.getWordHwpfRow(table);
                    if (CollectionUtils.isNotEmpty(tableRowList)) {
                        tableRowList.forEach(tableRow -> {
                            List<org.apache.poi.hwpf.usermodel.TableCell> tableCellList = HwpfUtils.getWordHwpfCell(tableRow);
                            if (CollectionUtils.isNotEmpty(tableCellList)) {
                                tableCellList.forEach(cell -> {
                                    List<org.apache.poi.hwpf.usermodel.Paragraph> paragraphList = HwpfUtils.getWordHwpfParagraph(cell);
                                    if (CollectionUtils.isNotEmpty(paragraphList)) {
                                        paragraphList.forEach(paragraph -> {
                                            stringBuilder.append(paragraph.text());
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 获取word内容 ,注意的是获取的是word 当中 所有也就是其中就算是有表格同样可以获取
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static String getWordContent(String path) throws Exception {
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        if (WordHelp.isWord2007(path)) {
            XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
            XWPFWordExtractor extractor = new XWPFWordExtractor(xwpfDocument);
            return extractor.getText();
        }
        if (WordHelp.isWord2003(path)) {
            HWPFDocument hwpfDocument = new HWPFDocument(fileInputStream);
            StringBuilder stringBuilder = hwpfDocument.getText();
            String text = stringBuilder.toString();
            if (StringUtils.isEmpty(text)) {
                text = hwpfDocument.getDocumentText();
            }
            if (StringUtils.isNotEmpty(text)) {
                return text;
            }
        }
        return "";
    }

    /**
     * 2003的excel
     *
     * @param filePath
     * @return
     */

    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }


    /**
     * 2007的excel
     *
     * @param filePath
     * @return
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 2003 word
     *
     * @param path
     * @return
     */
    public static boolean isWord2003(String path) {
        return path.matches("^.+\\.(?i)(doc)$");
    }

    /**
     * 2007 word
     *
     * @param path
     * @return
     */
    public static boolean isWord2007(String path) {
        return path.matches("^.+\\.(?i)(docx)$");
    }

    /**
     * 获取包装后的html，与当前word字体格式一致
     *
     * @param html
     * @return
     */
    public static String getWarpCssHtml(String html) {
        return String.format("<div style='font-family:仿宋_GB2312;line-height:150%%;font-size:14.0pt'>%s</div>", html);
    }

    public static String getSongWarpCssHtml(String html) {
        return String.format("<div style='font-family:宋体;line-height:150%%;font-size:10.0pt'>%s</div>", html);
    }

    /**
     * 获取缩进后html
     *
     * @param html
     * @return
     */
    public static String getIndentHtml(String html) {
        return String.format("<div style='text-indent:2em'>%s</div>", html);
    }

    /**
     * 类似于这样的1=2-7-20-32-1-fhd-6364转换为 List形式并且里面的数字如20和32等会是一个元素而像'='或者'-'或者'f'等这样的非数字则单独是一个元素
     *
     * @param text
     * @return
     */
    public List<String> convertNumberHelp(String text) {
        List<String> stringList = Lists.newArrayList();
        if (StringUtils.isEmpty(text.trim())) {
            return stringList;
        }
        StringBuilder stringBuilder = new StringBuilder(8);
        stringBuilder.append(text);
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);
        while (m.find()) {
            String string = stringBuilder.toString();
            int index = string.indexOf(m.group());
            String number = string.substring(0, index);
            if (org.apache.commons.lang.math.NumberUtils.isNumber(number)) {
                stringList.add(number);
            }
            stringList.add(m.group());
            string = string.substring(index + 1, stringBuilder.toString().length());
            stringBuilder.delete(0, stringBuilder.toString().length());
            stringBuilder.append(string);
        }
        if (org.apache.commons.lang.math.NumberUtils.isNumber(stringBuilder.toString())) {
            stringList.add(stringBuilder.toString());
        }
        if (CollectionUtils.isNotEmpty(stringList)) {
            //去重复 (不改变顺序)
            stringList = stringList.stream().distinct().collect(Collectors.toList());
        }
        return stringList;
    }

    /**
     * 数字转换
     *
     * @param numbers
     * @return
     */
    public String convertNumber(List<Integer> numbers) {
        StringBuilder stringBuilder = new StringBuilder(8);
        if (CollectionUtils.isNotEmpty(numbers)) {
            numbers = numbers.stream().distinct().sorted().collect(Collectors.toList());
            Integer[] ints = new Integer[numbers.size()];
            for (int i = 0; i < numbers.size(); i++) {
                ints[i] = numbers.get(i);
            }
            String text = null;
            if (ints.length > 1) {
                text = this.convert(ints, 0);
                LinkedList<String> stringList = Lists.newLinkedList();
                for (char c : text.toCharArray()) {
                    stringList.add(String.valueOf(c));
                }
                for (int i = 0; i < stringList.size(); i++) {
                    if (i == stringList.size() - 1) {
                        if (org.apache.commons.lang.math.NumberUtils.isNumber(stringList.get(i))) {
                            stringBuilder.append(stringList.get(i));
                        }
                    } else {
                        stringBuilder.append(stringList.get(i));
                    }
                }
                text = stringBuilder.toString();
                stringBuilder.delete(0, stringBuilder.toString().length());
            } else {
                text = ints[0].toString();
            }
            List<String> stringList = convertNumberHelp(text);
            if (CollectionUtils.isNotEmpty(stringList)) {
                stringList.stream().forEach(s -> {
                    if (org.apache.commons.lang.math.NumberUtils.isNumber(s)) {
                        stringBuilder.append(parseToCircleNumber(Integer.parseInt(s)));
                    } else {
                        stringBuilder.append(s);
                    }
                });
            }
            if (StringUtils.isNotEmpty(stringBuilder.toString())) {
                text = stringBuilder.toString();
            }
            return text;
        }
        return "";
    }

    /**
     * 获取连续的数字组合
     *
     * @param ints
     * @param index
     * @return
     */
    public String convert(Integer[] ints, int index) {
        int end = index;
        //结束条件，遍历完数组
        if (ints.length == index) {
            return "";
        } else {
            for (int i = index; i < ints.length; i++) {
                if (i < ints.length - 1) {
                    if (ints[i] + 1 == ints[i + 1]) {
                        end = i;
                    } else {
                        if (i > index)
                            end = end + 1;
                        break;
                    }
                } else {
                    if (end == ints.length - 2) {
                        end = ints.length - 1;
                        break;
                    }
                }
            }
            //相等说明不连续
            if (index == end)
                return ints[index] + "," + convert(ints, end + 1);
                //连续
            else
                return ints[index] + "-" + ints[end] + "," + convert(ints, end + 1);

        }
    }

    /**
     * 数字转换为带圆圈的数字 (由于word目前标准子集中只有20,所以暂时只有20)
     *
     * @param number
     * @return
     */
    public String parseToCircleNumber(final Integer number) {
        final String s = "①,②,③,④,⑤,⑥,⑦,⑧,⑨,⑩,⑪,⑫,⑬,⑭,⑮,⑯,⑰,⑱,⑲,⑳";
        final String[] strs = s.split(",");
        List<String> stringList = Lists.newArrayList();
        Map<Integer, String> map = Maps.newHashMap();
        for (int i = 0; i < strs.length; i++) {
            map.put(i + 1, strs[i]);
            stringList.add(String.valueOf(i + 1));
        }
        if (number == null) {
            return null;
        }
        if (!stringList.contains(number.toString())) {
            return number.toString();
        }
        return map.get(number);
    }


}
