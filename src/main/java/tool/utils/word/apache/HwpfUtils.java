package tool.utils.word.apache;

import com.google.common.collect.Lists;
import org.apache.poi.hwpf.usermodel.Table;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Author zch
 * @Description
 * @createDate 2019/6/2
 **/
public class HwpfUtils {


    /**
     * 获取word中的所有表格
     * 2003
     * doc 后缀 Microsoft Office2003 版本 和 xls对应
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<Table> getWordHwpfTable(String path) throws IOException {
        org.apache.poi.poifs.filesystem.POIFSFileSystem pfs = new org.apache.poi.poifs.filesystem.POIFSFileSystem(new FileInputStream(new File(path)));
        org.apache.poi.hwpf.HWPFDocument hwpf = new org.apache.poi.hwpf.HWPFDocument(pfs);
        org.apache.poi.hwpf.usermodel.Range range = hwpf.getRange();
        org.apache.poi.hwpf.usermodel.TableIterator it = new org.apache.poi.hwpf.usermodel.TableIterator(range);
        List<Table> tableList = Lists.newArrayList();
        while (it.hasNext()) {
            Table tb = it.next();
            if (tb != null) {
                tableList.add(tb);
            }
        }
        return tableList;
    }


    /**
     * 获取word中一个表格的所有行
     * 2003
     * doc 后缀 Microsoft Office2003 版本 和 xls对应
     *
     * @param table
     * @return
     */
    public static List<org.apache.poi.hwpf.usermodel.TableRow> getWordHwpfRow(Table table) {
        List<org.apache.poi.hwpf.usermodel.TableRow> tableRowList = Lists.newArrayList();
        for (int i = 0; i < table.numRows(); i++) {
            org.apache.poi.hwpf.usermodel.TableRow tableRow = table.getRow(i);
            if (tableRow != null) {
                tableRowList.add(tableRow);
            }
        }
        return tableRowList;
    }

    /**
     * 获取一个word中的一行的所有单元格
     * 2003
     * doc 后缀 Microsoft Office2003 版本 和 xls对应
     *
     * @param tableRow
     * @return
     */
    public static List<org.apache.poi.hwpf.usermodel.TableCell> getWordHwpfCell(org.apache.poi.hwpf.usermodel.TableRow tableRow) {
        List<org.apache.poi.hwpf.usermodel.TableCell> tableCellList = Lists.newArrayList();
        for (int j = 0; j < tableRow.numCells(); j++) {
            org.apache.poi.hwpf.usermodel.TableCell tableCell = tableRow.getCell(j);
            if (tableCell != null) {
                tableCellList.add(tableCell);
            }
        }
        return tableCellList;
    }

    /**
     * 获取word中一个单元格的所有段落
     * 2003
     * doc 后缀 Microsoft Office2003 版本 和 xls对应
     *
     * @param tableCell
     * @return
     */
    public static List<org.apache.poi.hwpf.usermodel.Paragraph> getWordHwpfParagraph(org.apache.poi.hwpf.usermodel.TableCell tableCell) {
        List<org.apache.poi.hwpf.usermodel.Paragraph> paragraphList = Lists.newArrayList();
        for (int k = 0; k < tableCell.numParagraphs(); k++) {
            org.apache.poi.hwpf.usermodel.Paragraph paragraph = tableCell.getParagraph(k);
            if (paragraph != null) {
                paragraphList.add(paragraph);
            }
        }
        return paragraphList;
    }

}
