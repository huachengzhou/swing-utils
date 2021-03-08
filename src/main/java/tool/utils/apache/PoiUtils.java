package tool.utils.apache;

import com.google.common.collect.Lists;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tool.utils.word.WordHelp;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @Author zch
 * @Description
 * @createDate 2019/2/2
 **/
public class PoiUtils {


    public static String getCellValue(Cell cell) {
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DecimalFormat decimalFormat = new DecimalFormat("#.####");
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            cellValue = cell.getStringCellValue();
        } else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                double d = cell.getNumericCellValue();
                Date date = HSSFDateUtil.getJavaDate(d);
                cellValue = sFormat.format(date);
            } else {
                cellValue = decimalFormat.format((cell.getNumericCellValue()));
            }
        } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            cellValue = "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            cellValue = String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
            cellValue = "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            try {
                cellValue = String.valueOf(cell.getStringCellValue());
            } catch (IllegalStateException e) {
                try {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                } catch (Exception e1) {
                    return cellValue;
                }
            }
        }
        return StringUtils.trim(cellValue);
    }

    public static Workbook getWorkbook(String path) throws Exception {
        FileInputStream inputStream = new FileInputStream(path);
        Workbook workbook = WordHelp.isExcel2003(path) ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
        return workbook;
    }

    public static Workbook getWorkbook2(String path)throws Exception{
        return WorkbookFactory.create(new File(path));
    }

    /**
     *有效行 (获取有记录的行数，即：最后有数据的行是第n行，前面有m行是空行没数据，则返回n-m)
     * @param sheet
     * @return
     */
    public static int getPhysicalNumberOfRows(Sheet sheet){
        return sheet.getPhysicalNumberOfRows();
    }

    /**
     * 有效行 (获取有记录的行数，即：最后有数据的行是第n行，前面有m行是空行没数据，则返回n-m)
     * @param path
     * @return
     * @throws Exception
     */
    public static int getPhysicalNumberOfRows(String path)throws Exception{
        Workbook workbook = getWorkbook(path);
        return getPhysicalNumberOfRows(workbook.getSheetAt(0));
    }

    /**
     * 有效列 (获取有记录的列数，即：最后有数据的列是第n列，前面有m列是空列没数据，则返回n-m)
     * @param sheet
     * @return
     */
    public static int getPhysicalNumberOfCells(Sheet sheet){
        Row row = sheet.getRow(0);
        return row.getPhysicalNumberOfCells();
    }

    /**
     * 把excel对象 写入一个地址
     * @param workbook
     * @param path
     * @throws Exception
     */
    public static void writeWorkbookToPath(Workbook workbook, String path)throws Exception {
        File file = new File(path);
        FileOutputStream fileoutputStream = new FileOutputStream(file);
        workbook.write(fileoutputStream);
        fileoutputStream.close();
    }

    /**
     * 合并单元格
     * @param cellRangeAddress
     * @param sheet
     */
    public static void mergedRegion(CellRangeAddress cellRangeAddress, Sheet sheet){
        sheet.addMergedRegion(cellRangeAddress);
    }

    /**
     * 有效列 (获取有记录的列数，即：最后有数据的列是第n列，前面有m列是空列没数据，则返回n-m)
     * @param path
     * @return
     * @throws Exception
     */
    public static int getPhysicalNumberOfCells(String path)throws Exception{
        Workbook workbook = getWorkbook(path);
        return getPhysicalNumberOfCells(workbook.getSheetAt(0));
    }


    /**
     * error return -1
     * 获取最后一行 ( 如果sheet中一行数据都没有则返回-1，只有第一行有数据则返回0，最后有数据的行是第n行则返回 n-1)
     * @param sheet
     * @return
     */
    public static int getLastRowNum(Sheet sheet){
        return sheet.getLastRowNum();
    }

    /**
     * error return -1
     * 获取所有列 (如果row中一列数据都没有则返回-1，只有第一列有数据则返回1，最后有数据的列是第n列则返回 n)
     * @param sheet
     * @return
     */
    public static int getLastCellNum(Sheet sheet){
        Row row = sheet.getRow(0);
        return row.getLastCellNum();
    }

    public static List<Sheet> getSheetList(String path) throws Exception {
        List<Sheet> sheetList = Lists.newArrayList();
        Workbook workbook = getWorkbook(path);
        for (int i = 0; i < 10000; i++) {
            Sheet sheet = null;
            try {
                sheet = workbook.getSheetAt(i);
            } catch (Exception e) {
            }
            if (sheet != null) {
                sheetList.add(sheet);
            } else {
                break;
            }
        }
        return sheetList;
    }

    /**
     * 回写excel数据 xls格式的excel
     *
     * @param path excel路径
     * @param map  list中的map对应的是列号与值
     */
    public static void writebackExcel(String path, Map<Integer, List<Map<Integer, String>>> map) throws IOException {
        //行号  列数据
        FileInputStream inputStream = new FileInputStream(path);
        Workbook workbook = WordHelp.isExcel2003(path) ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
//        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(path));
        Set<Map.Entry<Integer, List<Map<Integer, String>>>> keySet = map.entrySet();
        Iterator<Map.Entry<Integer, List<Map<Integer, String>>>> iterator = keySet.iterator();
        Sheet sheet = workbook.getSheetAt(0);//只写一个文件薄
        Row row = null;
        Cell cell = null;
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Map<Integer, String>>> entry = iterator.next();
            int lineNumber = entry.getKey();
            row = sheet.getRow(lineNumber);//获取行
            if (row != null) {
                List<Map<Integer, String>> mapList = entry.getValue();
                for (Map<Integer, String> integerStringMap : mapList) {
                    for (Map.Entry<Integer, String> entryA : integerStringMap.entrySet()) {
                        int columnNumber = entryA.getKey();
                        String value = entryA.getValue();
                        cell = row.getCell(columnNumber);//获取列
                        if (cell != null) cell.setCellValue(value);
                    }
                }
            }
        }
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(path)));
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

//    public static CellStyle getStyle(Workbook workbook) {
//        CellStyle cellStyle = workbook.createCellStyle();
//        Font font = getFontStyle(workbook, "微软雅黑");
//        cellStyle.setFont(font);
//        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);        //单元格垂直居中
//        //设置边框
//        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
//        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
//        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
//        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
//        return cellStyle;
//    }
//
//    public static void setStyle(CellStyle cellStyle) {
//        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
//        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
//        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
//        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
//    }

    //判断文件格式
    public static Workbook readExcel(String filePath){
        if(filePath==null){
            return null;
        }
        String extString = FilenameUtils.getExtension(filePath) ;

        try {
            InputStream is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return new XSSFWorkbook(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static Font getFontStyle(Workbook workbook, String... fName) {
        Font font = workbook.createFont();
        if (fName == null) {
            font.setFontName("Arial");
        }
        String s = font.getFontName();
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher("" + s);
        while (matcher.find()) {
            if (matcher.group() == null) {//中文或者非英文
                font.setCharSet(HSSFFont.DEFAULT_CHARSET);//设置中文字体，那必须还要再对单元格进行编码设置
            }
        }
        font.setFontHeightInPoints((short) 12);                //字体大小
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);             //加粗
        return font;
    }

}