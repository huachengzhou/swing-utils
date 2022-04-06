package com.c.biancheng.net;

import com.google.common.base.CaseFormat;
import org.testng.annotations.Test;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowAdapter;


public class JTableDemo {

    @Test
    public void testRun(){
        String text = "estate_land" ;
        text = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, text) ;
        System.out.println(text);
    }
}
