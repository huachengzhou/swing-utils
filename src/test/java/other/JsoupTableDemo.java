package other;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.testng.annotations.Test;
import tool.utils.CrawlerUtils;
import tool.utils.excel.JsoupConvertExcel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Lenovo on 2020/5/23.
 */
public class JsoupTableDemo {

    @Test
    public void testA() throws Exception {
        String url = "http://data.stats.gov.cn/easyquery.htm?cn=A01";

        org.jsoup.nodes.Document document = CrawlerUtils.getCrawlerDocument(url, true);
        String select = "table";
        org.jsoup.select.Elements elements = document.select(select);
        Iterator<Element> iterator = elements.iterator();
        int k = 0;
        while (iterator.hasNext()) {
            k++;
            org.jsoup.nodes.Element doc = iterator.next();
            HSSFWorkbook workbook = JsoupConvertExcel.writeExcel(doc);
            String value = doc.html();
            value = doc.text();
            value = doc.toString();
            String data = doc.data();
            String val = doc.val();
            Map<String, String> dataset = doc.dataset();
            String ownText = doc.ownText();
            Node root = doc.root();
            System.out.println("---------------------------------------------------------------------");

            FileOutputStream outputStream = new FileOutputStream("D:\\data\\text"+ RandomStringUtils.randomNumeric(3)+".xls");
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }

    }


    @Test
    public void replaceText(){
        String path = "D:\\table.txt" ;
    }


}
