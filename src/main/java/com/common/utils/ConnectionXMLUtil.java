package com.common.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ConnectionXMLUtil {

    private static volatile ConnectionXMLUtil xmlUtil;
    private final Map<String, String> db = new HashMap<>();

    private ConnectionXMLUtil() {
    }

    /**
     * 需要在resources文件中添加db.xml配置属性
     *
     * @return
     */
    public static ConnectionXMLUtil getXmlUtil() {
        if (xmlUtil == null) xmlUtil = new ConnectionXMLUtil();
        return xmlUtil;
    }

    /**
     * 最好不用test method
     *
     * @return
     */
    public ComboPooledDataSource getC3p0DataSource() {
        init();
        ComboPooledDataSource c3p0 = new ComboPooledDataSource();
        try {
            c3p0.setMaxPoolSize(Integer.parseInt(db.get("cMaxPoolSize")));
            c3p0.setMinPoolSize(Integer.parseInt(db.get("cMinPoolSize")));
            c3p0.setInitialPoolSize(Integer.parseInt(db.get("cInitialPoolSize")));
            c3p0.setMaxIdleTime(Integer.parseInt(db.get("cMaxIdleTime")));
            c3p0.setPassword(db.get("cPassword"));
            c3p0.setUser(db.get("cUsername"));
            c3p0.setJdbcUrl(db.get("cJdbcUrl"));
            c3p0.setDriverClass(db.get("cDriverClass"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c3p0;
    }

    private void init() {
        final String fileName = "db.xml";
        String path = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
        try {
            File file = new File(path);
            if (file.isFile()) {
                InputStream inputStream = new FileInputStream(path);
                SAXReader reader = new SAXReader();
                Document document = reader.read(inputStream);
                Element rootElement = document.getRootElement();
                Element cJdbcUrl = rootElement.element("cJdbcUrl");
                db.put("cJdbcUrl", cJdbcUrl.getText());
                db.put("cUsername", rootElement.element("cUsername").getText());
                db.put("cPassword", rootElement.element("cPassword").getText());
                db.put("cDriverClass", rootElement.element("cDriverClass").getText());
                db.put("cInitialPoolSize", rootElement.element("cInitialPoolSize").getText());
                db.put("cMinPoolSize", rootElement.element("cMinPoolSize").getText());
                db.put("cMaxPoolSize", rootElement.element("cMaxPoolSize").getText());
                db.put("cMaxIdleTime", rootElement.element("cMaxIdleTime").getText());
            } else {
                System.out.println("不能用test方法");
                System.out.println("maven 需要配置resources文件 其中需要包括<include>*.xml</include>");
                throw new NullPointerException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
