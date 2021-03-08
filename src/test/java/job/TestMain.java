package job;

/**
 * Created by Lenovo on 2020/5/17.
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.general.recruitment.JobBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class TestMain {

    private static int count = 0;

    public static void main(String[] args) {
        String strUrl = "https://search.51job.com/list/170200,000000,0000,00,9,99,%25E5%25A4%25A7%25E6%2595%25B0%25E6%258D%25AE,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
        Document document = getDom(strUrl);
        List<JobBean> list = getPageInfo(document);
        System.out.println("---------------" + (++count) + "-------------");
        for (JobBean jobBean : list) {
            System.out.println(jobBean);
        }
        getNextPageInfo(document);

    }

    public static void getNextPageInfo(Document document) {
        Elements elements = document.select(".bk");
        Element element = elements.get(1);
        String strUrl = element.select("a").attr("href");
        if (strUrl == null) {
            return;
        }
        Document dom = getDom(strUrl);
        List<JobBean> list = getPageInfo(dom);
        System.out.println("---------------" + (++count) + "-------------");
        for (JobBean jobBean : list) {
            System.out.println(jobBean);
        }
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getNextPageInfo(dom);

    }


    public static List<JobBean> getPageInfo(Document document) {
        List<JobBean> list = new ArrayList<JobBean>();
        Elements elements = document.select("#resultList .el");
        elements.remove(0);
        for (Element element : elements) {
            Elements elements2 = element.select("span");
            JobBean jobBean = new JobBean();
            jobBean.set(elements2.get(0).text(), elements2.get(1).text(), elements2.get(2).text(), elements2.get(3).text(), elements2.get(4).text());
            list.add(jobBean);
        }
        return list;
    }

    public static Document getDom(String strUrl) {
        try {
            URL url = new URL(strUrl);
            Document document = Jsoup.parse(url, 4000);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
