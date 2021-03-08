package tool.utils.excel;

import com.google.common.base.Objects;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.ss.usermodel.Row;
import tool.utils.ArithmeticUtils;
import tool.utils.DateUtils;
import tool.utils.MyEntry;
import tool.utils.Reflections;
import tool.utils.apache.PoiUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by Lenovo on 2020/5/17.
 */
public final class ExcelImportUtils implements Serializable {

    /**
     * @param c   匹配的对象模板即class
     * @param row ,传入excel导入数据的key行(大多数情况是第一行)
     * @return <publicArea,<java.math.BigDecimal,2>> 字段名称,
     */
    public static Multimap<String, Map.Entry<Class<?>, Integer>> getMultimapByClass(Class<?> c, org.apache.poi.ss.usermodel.Row row) {
        final int zero = 0;
        Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap = ArrayListMultimap.create();
        Multimap<String, Class<?>> multimap = ArrayListMultimap.create();
        java.lang.reflect.Field[] fields = c.getDeclaredFields();
        //总列数
        final int colLength = row.getPhysicalNumberOfCells() != zero ? row.getPhysicalNumberOfCells() : row.getLastCellNum();
        if (fields.length != zero) {
            for (int i = zero; i < fields.length; i++) {
                java.lang.reflect.Field field = fields[i];
                multimap.put(field.getName(), field.getType());
            }
        }
        for (int i = zero; i < colLength; i++) {
            org.apache.poi.ss.usermodel.Cell cell = row.getCell(i);
            if (cell == null) {
                continue;
            }
            String key = PoiUtils.getCellValue(cell);
            if (!multimap.containsKey(key)) {
                continue;
            }
            Collection<Class<?>> classCollection = multimap.get(key);
            if (CollectionUtils.isNotEmpty(classCollection)) {
                Iterator<Class<?>> classIterator = classCollection.iterator();
                while (classIterator.hasNext()) {
                    Class<?> aClass = classIterator.next();
                    classArrayListMultimap.put(key, new MyEntry(aClass, Integer.valueOf(i)));
                }
            }
        }
        return classArrayListMultimap;
    }

    /**
     * excel模板导入辅助
     *
     * @param classArrayListMultimap 核心映射对象map
     * @param target                 对象
     * @param stringBuilder          信息对象工具收集
     * @param isBreak                遇到错误赋值是否立即结束 注意当这个设为false的时候这个辅助方法就不会抛出不合乎规范的错误了
     * @param row
     * @param requiredList           必填项map
     * @return
     */
    public static boolean excelImportHelp(Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap, Object target, StringBuilder stringBuilder, Row row, Multimap<String, List<String>> requiredList, boolean isBreak) {
        Iterator<Map.Entry<String, Map.Entry<Class<?>, Integer>>> entryIterator = classArrayListMultimap.entries().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Map.Entry<Class<?>, Integer>> stringEntryEntry = entryIterator.next();
            Map.Entry<Class<?>, Integer> classIntegerEntry = stringEntryEntry.getValue();
            String key = stringEntryEntry.getKey();
            Class<?> aClass = classIntegerEntry.getKey();
            Integer index = classIntegerEntry.getValue();
            org.apache.poi.ss.usermodel.Cell cell = row.getCell(index);
            if (cell == null) {
                continue;
            }
            //是否属于必填
            boolean required = false;
            if (requiredList != null){
                if (!requiredList.isEmpty()) {
                    required = requiredList.containsKey(key);
                }
            }

            String value = PoiUtils.getCellValue(cell);
            //当单元表格没有填写数据,并且属于必填项,那么此条数据不通过
            if (StringUtils.isBlank(value) && required) {
                return false;
            }
            //当单元表格没有填写数据,并且不属于必填项,那么此单元格数据跳过
            if (StringUtils.isBlank(value) && !required) {
                continue;
            }
            //String类型则直接赋值
            if (Objects.equal(String.class.getName(), aClass.getName())) {
                try {
                    Reflections.invokeSetter(target, key, value);
                } catch (Exception e) {
                    excelImportWriteErrorInfo(key, row.getRowNum(), index, String.format("非法字符串 {%s} ", value), required, stringBuilder);
                    if (isBreak) {
                        return false;
                    }
                }
            }
            //整数类型处理
            if (Objects.equal(Integer.class.getName(), aClass.getName())) {
                if (!ArithmeticUtils.isInteger(value)) {
                    excelImportWriteErrorInfo(key, row.getRowNum(), index, String.format("非法整数数字 {%s} ", value), required, stringBuilder);
                    if (isBreak) {
                        return false;
                    }
                }
                if (ArithmeticUtils.isInteger(value)) {
                    Reflections.invokeSetter(target, key, Integer.valueOf(value));
                }
            }
            //整数类型处理
            if (Objects.equal(Byte.class.getName(), aClass.getName())) {
                if (!ArithmeticUtils.isByte(value)) {
                    excelImportWriteErrorInfo(key, row.getRowNum(), index, String.format("非法整数数字 {%s} ", value), required, stringBuilder);
                    if (isBreak) {
                        return false;
                    }
                }
                if (ArithmeticUtils.isByte(value)) {
                    Reflections.invokeSetter(target, key, Byte.valueOf(value));
                }
            }

            //整数类型处理
            if (Objects.equal(Short.class.getName(), aClass.getName())) {
                if (!ArithmeticUtils.isShort(value)) {
                    excelImportWriteErrorInfo(key, row.getRowNum(), index, String.format("非法整数数字 或者超出数据类型范围 {%s} ", value), required, stringBuilder);
                    if (isBreak) {
                        return false;
                    }
                }
                if (ArithmeticUtils.isShort(value)) {
                    Reflections.invokeSetter(target, key, Short.valueOf(value));
                }
            }
            //长整数类型处理
            if (Objects.equal(Long.class.getName(), aClass.getName())) {
                if (!ArithmeticUtils.isLong(value)) {
                    excelImportWriteErrorInfo(key, row.getRowNum(), index, String.format("非法整数数字 {%s} ", value), required, stringBuilder);
                    if (isBreak) {
                        return false;
                    }
                }
                if (ArithmeticUtils.isLong(value)) {
                    Reflections.invokeSetter(target, key, Long.valueOf(value));
                }
            }
            //高精度数字类型
            if (Objects.equal(BigDecimal.class.getName(), aClass.getName())) {
                if (!NumberUtils.isNumber(value)) {
                    excelImportWriteErrorInfo(key, row.getRowNum(), index, String.format("非法数字 {%s} ", value), required, stringBuilder);
                    if (isBreak) {
                        return false;
                    }
                }
                if (NumberUtils.isNumber(value)) {
                    Reflections.invokeSetter(target, key, new BigDecimal(value));
                }
            }
            //高精度数字类型
            if (Objects.equal(BigInteger.class.getName(), aClass.getName())) {
                if (!NumberUtils.isNumber(value)) {
                    excelImportWriteErrorInfo(key, row.getRowNum(), index, String.format("非法数字 {%s} ", value), required, stringBuilder);
                    if (isBreak) {
                        return false;
                    }
                }
                if (NumberUtils.isNumber(value)) {
                    Reflections.invokeSetter(target, key, new BigInteger(value));
                }
            }
            //双精度数字类型
            if (Objects.equal(Double.class.getName(), aClass.getName())) {
                if (!ArithmeticUtils.isDouble(value)) {
                    excelImportWriteErrorInfo(key, row.getRowNum(), index, String.format("非法数字 {%s} ", value), required, stringBuilder);
                    if (isBreak) {
                        return false;
                    }
                }
                if (ArithmeticUtils.isDouble(value)) {
                    Reflections.invokeSetter(target, key, Double.valueOf(value));
                }
            }
            //浮点数字类型
            if (Objects.equal(Float.class.getName(), aClass.getName())) {
                if (!ArithmeticUtils.isFloat(value)) {
                    excelImportWriteErrorInfo(key, row.getRowNum(), index, String.format("非法数字 {%s} ", value), required, stringBuilder);
                    if (isBreak) {
                        return false;
                    }
                }
                if (ArithmeticUtils.isFloat(value)) {
                    Reflections.invokeSetter(target, key, Float.valueOf(value));
                }
            }
            //布尔类型
            if (Objects.equal(Boolean.class.getName(), aClass.getName())) {
                try {
                    Reflections.invokeSetter(target, key, Boolean.valueOf(value));
                } catch (Exception e) {
                    excelImportWriteErrorInfo(key, row.getRowNum(), index, String.format("真假格式错误 {%s} ", value), required, stringBuilder);
                    if (isBreak) {
                        return false;
                    }
                }
            }
            //日期类型 统一处理
            if (Objects.equal(Date.class.getName(), aClass.getName())) {
                try {
                    Reflections.invokeSetter(target, key, DateUtils.parse(value));
                } catch (Exception e) {
                    excelImportWriteErrorInfo(key, row.getRowNum(), index, String.format("日期格式错误 {%s} ", value), required, stringBuilder);
                    if (isBreak) {
                        return false;
                    }
                }
            }

        }
        return true;
    }


    public static void excelImportWriteErrorInfo(final int rowIndex, final int colIndex, String info, boolean required, final StringBuilder stringBuilder) {
        excelImportWriteErrorInfo(null, rowIndex, colIndex, info, required, stringBuilder);
    }

    public static void excelImportWriteErrorInfo(final int rowIndex, final int colIndex, String info, final StringBuilder stringBuilder) {
        excelImportWriteErrorInfo(null, rowIndex, colIndex, info, false, stringBuilder);
    }

    public static void excelImportWriteErrorInfo(final int rowIndex, String info, final StringBuilder stringBuilder) {
        excelImportWriteErrorInfo(null, rowIndex, 0, info, false, stringBuilder);
    }

    /**
     * @param rowIndex 行索引
     * @param colIndex 列索引
     * @param info     错误信息
     * @param required 是否必填
     * @return
     */
    public static void excelImportWriteErrorInfo(final String key, final int rowIndex, final int colIndex, String info, boolean required, final StringBuilder stringBuilder) {
        stringBuilder.append("\n");
        stringBuilder.append("第");
        stringBuilder.append(String.valueOf(rowIndex));
        stringBuilder.append("行");
        if (colIndex != 0) {
            stringBuilder.append("第");
            stringBuilder.append(String.valueOf(colIndex + 1));
            if (StringUtils.isNotBlank(key)) {
                stringBuilder.append(" (").append(key).append(") ");
            }
            stringBuilder.append("列");
        }
        stringBuilder.append(StringUtils.isNotBlank(info) ? info : "校验出错");
        stringBuilder.append("请检查");
        if (required) {
            stringBuilder.append("(必填项)");
        }
    }


    /**
     * 字符串 list 分组
     *
     * @param list
     * @param splitSize
     * @return
     */
    public static List<List<String>> splitsStringList(List<String> list, int splitSize) {
        return new SplitsList<String>().splitsList(list, splitSize);
    }

    public static List<List<Integer>> splitsIntegerList(List<Integer> list, int splitSize) {
        return new SplitsList<Integer>().splitsList(list, splitSize);
    }

    public static List<List<Long>> splitsLongList(List<Long> list, int splitSize) {
        return new SplitsList<Long>().splitsList(list, splitSize);
    }

    /**
     * 泛型 分组方法
     *
     * @param <T>
     */
    public static class SplitsList<T> {
        private T t;

        public List<List<T>> splitsList(List<T> list, int splitSize) {
            if (null == list) {
                return null;
            }
            int listSize = list.size();
            List<List<T>> newList = new ArrayList<>();
            if (listSize < splitSize) {
                newList.add(list);
                return newList;
            }
            int addLength = splitSize;
            int times = listSize / splitSize;
            if (listSize % splitSize != 0) {
                times += 1;
            }
            int start = 0;
            int end = 0;
            int last = times - 1;
            for (int i = 0; i < times; i++) {
                start = i * splitSize;
                if (i < last) {
                    end = start + addLength;
                } else {
                    end = listSize;
                }
                newList.add(list.subList(start, end));
            }
            return newList;
        }

        public T getT() {
            return t;
        }
    }

}
