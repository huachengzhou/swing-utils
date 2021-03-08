#aspose 运行注册
```

    /**
     * 获取18.10版本的注册流
     * @return
     */
    public static InputStream getLicenseInputStream(){
        /**use method
         /* License aposeLic = new License();
         /* aposeLic.setLicense(AsposeHelp.getLicenseInputStream());
         */
        return AsposeHelp.class.getClassLoader().getResourceAsStream("license18.10.xml") ;
    }
 
```