package com.gold.company;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MyMouseEvent implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        System.out.println("MyMouseEvent click");
    }
}

