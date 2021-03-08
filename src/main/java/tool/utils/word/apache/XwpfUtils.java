package tool.utils.word.apache;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Author noatnu
 * @Description
 * @createDate 2019/6/13
 **/
public class XwpfUtils {

    /**
     * 获取word中的所有表格
     * 2007
     * docx 后缀 Microsoft Office2007 版本 和 xlsx对应
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<org.apache.poi.xwpf.usermodel.XWPFTable> getWordXWPFTable(String path) throws IOException {
        org.apache.poi.xwpf.usermodel.XWPFDocument xwpf = new org.apache.poi.xwpf.usermodel.XWPFDocument(new FileInputStream(path));
        return xwpf.getTables();
    }

    /**
     * 获取word中一个表格的所有行
     * 2007
     * docx 后缀 Microsoft Office2007 版本 和 xlsx对应
     *
     * @param xwpfTable
     * @return
     */
    public static List<org.apache.poi.xwpf.usermodel.XWPFTableRow> getWordXWPFRow(org.apache.poi.xwpf.usermodel.XWPFTable xwpfTable) {
        return xwpfTable.getRows();
    }


    /**
     * 获取一个word中的一行的所有单元格
     * 2007
     * docx 后缀 Microsoft Office2007 版本 和 xlsx对应
     *
     * @param xwpfTableRow
     * @return
     */
    public static List<org.apache.poi.xwpf.usermodel.XWPFTableCell> getWordXWPFCell(org.apache.poi.xwpf.usermodel.XWPFTableRow xwpfTableRow) {
        return xwpfTableRow.getTableCells();
    }

    /**
     * 获取word中一个单元格的所有段落
     * 2007
     * docx 后缀 Microsoft Office2007 版本 和 xlsx对应
     *
     * @param xwpfTableCell
     * @return
     */
    public static List<XWPFParagraph> getWordXWPFParagraph(org.apache.poi.xwpf.usermodel.XWPFTableCell xwpfTableCell) {
        return xwpfTableCell.getParagraphs();
    }

}
