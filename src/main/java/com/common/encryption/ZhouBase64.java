package com.common.encryption;

import java.io.Serializable;
import java.util.Base64;

/**
 * @Auther: zch
 * @Date: 2018/7/26 21:03
 * @Description:
 */
public class ZhouBase64 implements Serializable {
    private ZhouBase64() {
    }

    /**
     * JDK8实现的Base64加密
     *
     * @param var
     * @return
     */
    public final static String isEncode(String var) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytes = encoder.encode(var.getBytes());
        return new String(bytes).toString();
    }

    /**
     * JDK8实现的Base64解密
     *
     * @param var
     * @return
     */
    public final static String isDecode(String var) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bs = decoder.decode(var.getBytes());
        return new String(bs).toString();
    }

    @SuppressWarnings("unused")
    private static void isit() {
        String nString = isEncode("helloworld");
        System.out.println(nString);
        nString = isDecode(nString);
        System.out.println(nString);
    }
}
