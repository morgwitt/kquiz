/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kquiz;

import controller.LoginController;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author morga
 */
public class KQuiz extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/korean.jpg")));
        stage.setTitle("KQuiz da Morg");
        HostServices host = getHostServices();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/Login.fxml"));
        Parent root = (Parent) loader.load();
        LoginController controller = loader.getController();
        controller.setStage(stage);
        controller.setHost(host);
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

}
