package com.general.enums;

public enum PanelEnum {
    Chinese_University("Chinese_University", "2020中国教育部公布大学大全"),
    Get_current_ip("Get.current.ip", "获取你的IP"),
    CHECK_CONNECTED_NET("Check.connected.net", "测试网络连通性"),
    Chinese_University_adult("Chinese_University_adult", "2020中国教育部全国成人高等学校名单"),
    PDF_CONVERT_WORD("pdf.word", "pdf转word"),
    PDF_CONVERT_WORD_LOW("pdf.word.low", "pdf 转 word(低精度 无限制)"),
    PDF_CONVERT_WORD_HIGH("pdf.word.high", "pdf 转 word 高精度(最多10页)"),
    WORD_CONVERT_PDF("word.pdf", "word 转 pdf "),
    WEB_CRAWLER("web.crawler", "网络爬虫"),
    WEB_WEI_XIN_CRAWLER("web.crawler.wei.xin", "微信公众号"),
    Software_Engineer_A("Software_Engineer_A", "复杂网络数据抓取"),
    Software_Engineer_B("Software_Engineer_B", "复杂网络数据下载"),
    Software_Engineer_C("Software_Engineer_C", "复杂情况微信公众号数据抓取"),
    Software_Engineer_D("Software_Engineer_D", "复杂情况微博数据抓取"),
    Software_Engineer_E("Software_Engineer_E", "复杂情况淘宝数据抓取"),
    Software_Engineer_F("Software_Engineer_F", "复杂情况统计局数据抓取"),
    Software_Engineer_Tool("Software_Engineer_Tool", "字符串转换"),
    Software_Engineer_hugo("Software_Engineer_hugo", "hugo静态网站生成"),
    Software_structure_database_syn_mysql("Software_structure_database_syn_mysql", "mysql结构同步"),

    ;
    private String key;
    private String name;

    PanelEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
