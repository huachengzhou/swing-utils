package com.gold.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class WindowsMain extends Application {
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        // 窗口点击叉号关闭询问
        stage.setOnCloseRequest(event -> {
            event.consume();  // 消除默认事件
            handleClose();
        });

        // 布局
        Button button = new Button("关闭窗口");

        // 鼠标点击关闭窗口
        button.setOnMouseClicked(event -> handleClose());

        VBox vBox = new VBox();
        vBox.getChildren().add(button);
        Scene scene = new Scene(vBox, 400, 400);

        stage.setScene(scene);
        stage.show();
    }

    public void handleClose() {
        // 接收窗体返回值
        boolean ret = WindowAlert.display("关闭窗口", "是否关闭窗口？");
        System.out.println(ret);
        if (ret) {
            stage.close();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}

