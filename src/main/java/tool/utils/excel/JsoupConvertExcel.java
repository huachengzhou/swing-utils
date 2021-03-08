package tool.utils.excel;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Lenovo on 2020/5/23.
 */
public class JsoupConvertExcel {


    public static HSSFWorkbook writeExcel(org.jsoup.nodes.Element ele) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();


        final AtomicInteger atomicInteger = new AtomicInteger(0);

        Elements heads = ele.select("thead");
        if (CollectionUtils.isNotEmpty(heads)) {
            Iterator<Element> iterator = heads.iterator();
            while (iterator.hasNext()) {
                Element element = iterator.next();
                writeRowValue(element, sheet, atomicInteger);
            }
        }

        Elements bodys = ele.select("tbody");
        if (CollectionUtils.isNotEmpty(bodys)){
            Iterator<Element> iterator = bodys.iterator();
            while (iterator.hasNext()) {
                Element element = iterator.next();
                writeRowValue(element, sheet, atomicInteger);
            }
        }

        Elements foots = ele.select("tfoot");
        if (CollectionUtils.isNotEmpty(foots)){
            Iterator<Element> iterator = foots.iterator();
            while (iterator.hasNext()) {
                Element element = iterator.next();
                writeRowValue(element, sheet, atomicInteger);
            }
        }

        int len = atomicInteger.get();
        for (int i = 0; i < len; i++) {
            sheet.setColumnWidth(i,6000);
        }

        return wb;
    }

    private static void writeRowValue(org.jsoup.nodes.Element ele, HSSFSheet sheet, AtomicInteger atomicInteger) {
        Elements elements = ele.select("tr");
        if (CollectionUtils.isEmpty(elements)) {
            return;
        }
        Iterator<Element> iterator = elements.iterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            Elements select = element.select("td");
            if (CollectionUtils.isEmpty(select)) {
                select = element.select("th");
            }
            if (CollectionUtils.isEmpty(select)) {
                continue;
            }
            int rowIndex = atomicInteger.get();
            Row row = sheet.createRow(rowIndex);
            atomicInteger.incrementAndGet();
            Iterator<Element> elementIterator = select.iterator();
            int k = 0;
            while (elementIterator.hasNext()) {
                Element next = elementIterator.next();
                String text = next.text();
                Cell cell = row.createCell(k);
                cell.setCellValue(text);
                k++;
            }
        }
    }


}
