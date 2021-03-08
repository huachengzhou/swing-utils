package com.data;

import com.entity.School;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.utils.excel.ExcelImportUtils;

import java.io.InputStream;
import java.util.*;

/**
 * Created by Lenovo on 2020/5/17.
 */
public class UniversityGetExcelUtils {

    private static final Logger logger = LoggerFactory.getLogger(UniversityGetExcelUtils.class);

    public static LinkedHashMap<String, List<School>> getChineseBaseData() throws Exception {
        String fileName = String.join("/", "data", "school_chinese.xls");
        LinkedHashMap<String, List<School>> mapList = new LinkedHashMap<>();
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        Workbook workbook = null;
        Row row = null;
        StringBuilder builder = new StringBuilder();
        //2.读取文件
        try {
            workbook = WorkbookFactory.create(resourceAsStream);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        //只取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //工作表的第一行
        row = sheet.getRow(0);
        Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap = ExcelImportUtils.getMultimapByClass(School.class, row);
        //读取数据的起始行
        int startRowNumber = 4;
        //导入成功数据条数
        int successCount = 0;
        //总列数
        int colLength = row.getPhysicalNumberOfCells() != 0 ? row.getPhysicalNumberOfCells() : row.getLastCellNum();
        //总行数
        int rowLength = sheet.getPhysicalNumberOfRows() != 0 ? sheet.getPhysicalNumberOfRows() : sheet.getLastRowNum();
        rowLength = rowLength - startRowNumber;
        if (rowLength == 0) {
            builder.append("没有数据!");
            return mapList;
        }

        LinkedList<School> linkedList = new LinkedList<>();
        School school = null;
        for (int i = startRowNumber; i < startRowNumber + rowLength; i++) {
            row = sheet.getRow(i);
            school = new School();
            ExcelImportUtils.excelImportHelp(classArrayListMultimap, school, builder, row, null, false);
            linkedList.add(school);
        }
        LinkedList<School> linkedList2 = new LinkedList<>();

        String groupName = null;
        int index = 0;
        if (CollectionUtils.isNotEmpty(linkedList)) {
            linkedList.forEach(oo -> linkedList2.add(oo));
            Iterator<School> iterator = linkedList.iterator();
            while (iterator.hasNext()) {
                School oo = iterator.next();
                index++;
                if (StringUtils.isBlank(oo.getName()) && StringUtils.isBlank(oo.getLevel())) {
                    groupName = linkedList2.get(index).getLocation();
                }
                if (StringUtils.isNotBlank(groupName)) {
                    if (mapList.containsKey(groupName)) {
                        if (StringUtils.isNotBlank(oo.getName())) {
                            mapList.get(groupName).add(oo);
                        }
                    } else {
                        List<School> list = new ArrayList<>();
                        if (StringUtils.isNotBlank(oo.getName())) {
                            list.add(oo);
                        }
                        mapList.put(groupName, list);
                    }
                }
            }
        }
        return mapList;
    }

    public static LinkedHashMap<String, List<School>> getChineseAdultData() throws Exception {
        String fileName = String.join("/", "data", "Chinese_University_adult.xls");
        LinkedHashMap<String, List<School>> mapList = new LinkedHashMap<>();
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        Workbook workbook = null;
        Row row = null;
        StringBuilder builder = new StringBuilder();
        //2.读取文件
        try {
            workbook = WorkbookFactory.create(resourceAsStream);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        //只取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //工作表的第一行
        row = sheet.getRow(0);
        Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap = ExcelImportUtils.getMultimapByClass(School.class, row);
        //读取数据的起始行
        int startRowNumber = 4;
        //导入成功数据条数
        int successCount = 0;
        //总列数
        int colLength = row.getPhysicalNumberOfCells() != 0 ? row.getPhysicalNumberOfCells() : row.getLastCellNum();
        //总行数
        int rowLength = sheet.getPhysicalNumberOfRows() != 0 ? sheet.getPhysicalNumberOfRows() : sheet.getLastRowNum();
        rowLength = rowLength - startRowNumber;
        if (rowLength == 0) {
            builder.append("没有数据!");
            return mapList;
        }

        LinkedList<School> linkedList = new LinkedList<>();
        School school = null;
        for (int i = startRowNumber; i < startRowNumber + rowLength; i++) {
            row = sheet.getRow(i);
            school = new School();
            ExcelImportUtils.excelImportHelp(classArrayListMultimap, school, builder, row, null, false);
            linkedList.add(school);
        }
        LinkedList<School> linkedList2 = new LinkedList<>();

        String groupName = null;
        int index = 0;
        if (CollectionUtils.isNotEmpty(linkedList)) {
            linkedList.forEach(oo -> linkedList2.add(oo));
            Iterator<School> iterator = linkedList.iterator();
            while (iterator.hasNext()) {
                School oo = iterator.next();
                index++;
                if (StringUtils.isBlank(oo.getName()) && StringUtils.isBlank(oo.getCompetentDepartment())) {
                    groupName = linkedList2.get(index).getCompetentDepartment();
                }
                if (StringUtils.isNotBlank(groupName)) {
                    if (mapList.containsKey(groupName)) {
                        if (StringUtils.isNotBlank(oo.getName())) {
                            mapList.get(groupName).add(oo);
                        }
                    } else {
                        List<School> list = new ArrayList<>();
                        if (StringUtils.isNotBlank(oo.getName())) {
                            list.add(oo);
                        }
                        mapList.put(groupName, list);
                    }
                }
            }
        }
        return mapList;
    }

    public static void main(String[] args) {
        try {
            getChineseAdultData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
