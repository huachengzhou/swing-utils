package tool.utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Lenovo on 2020/5/14.
 */
public class CrawlerUtils {
    /**
     * 请求超时时间,默认20000ms
     */
    private static int timeout = 20000;
    /**
     * 等待异步JS执行时间,默认20000ms
     */
    private static int waitForBackgroundJavaScript = 20000;

    private static org.jsoup.nodes.Document getDocument(String uri) throws IOException {
        org.jsoup.nodes.Document doc = null;
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36");
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000) // 设置连接超时时间 3秒钟
                .setSocketTimeout(4000) // 设置读取超时时间4秒钟
                .build();
        httpGet.setConfig(config);
        HttpResponse httpResponse = null;

        httpResponse = httpClient.execute(httpGet);
        if (httpResponse == null) {
            return null;
        }
        HttpEntity httpEntity = httpResponse.getEntity();
        if (httpEntity == null) {
            return null;
        }
        String webHtml = EntityUtils.toString(httpEntity, "utf-8");
        if (StringUtils.isEmpty(webHtml)) {
            return null;
        }
        doc = org.jsoup.Jsoup.parse(webHtml);
        return doc;
    }


    /**
     * 将网页返回为解析后的文档格式
     *
     * @param html
     * @return
     * @throws Exception
     */
    private static org.jsoup.nodes.Document parseHtmlToDoc(String html) throws Exception {
        return removeHtmlSpace(html);
    }

    private static org.jsoup.nodes.Document removeHtmlSpace(String str) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(str);
        String result = doc.html().replace("&nbsp;", "");
        return org.jsoup.Jsoup.parse(result);
    }

    /**
     * 获取页面文档字串(等待异步JS执行)
     *
     * @param url 页面URL
     * @return
     * @throws Exception
     */
    private static String getHtmlPageResponse(String url) throws Exception {
        String result = "";

        final WebClient webClient = new WebClient(BrowserVersion.CHROME);

        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX

        webClient.getOptions().setTimeout(timeout);//设置“浏览器”的请求超时时间
        webClient.setJavaScriptTimeout(timeout);//设置JS执行的超时时间

        HtmlPage page;
        try {
            page = webClient.getPage(url);
        } catch (Exception e) {
            webClient.close();
            throw e;
        }
        webClient.waitForBackgroundJavaScript(waitForBackgroundJavaScript);//该方法阻塞线程

        result = page.asXml();
        webClient.close();

        return result;
    }

    /**
     * 获取页面文档Document对象(等待异步JS执行)
     *
     * @param url 页面URL
     * @return
     * @throws Exception
     */
    private static org.jsoup.nodes.Document getHtmlPageResponseAsDocument(String url) throws Exception {
        return parseHtmlToDoc(getHtmlPageResponse(url));
    }

    public static org.jsoup.nodes.Document getCrawlerDocument(String url,boolean js)throws Exception{
        org.jsoup.nodes.Document document = null;
        if (js){
            document = getHtmlPageResponseAsDocument(url) ;
        }else {
            try {
                document =   getDocument(url) ;
            } catch (Exception e) {
                document = getHtmlPageResponseAsDocument(url) ;
            }
        }
        return document;
    }

    public static org.jsoup.nodes.Document getCrawlerDocument(String url)throws Exception{
        org.jsoup.nodes.Document document = null;
        try {
            document =   getDocument(url) ;
        } catch (Exception e) {
            document = getHtmlPageResponseAsDocument(url) ;
        }
        return document;
    }



}
