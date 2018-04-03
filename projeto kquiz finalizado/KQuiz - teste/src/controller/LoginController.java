/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.Helper;
import facade.Facade;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

/**
 *
 * @author morga
 */
public class LoginController implements Initializable {

    private Stage stage;
    private HostServices host;
    
    @FXML
    private TextField txfEmail;
    @FXML
    private PasswordField psfPassword;
    @FXML
    private Label lblFeedback;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void clickLogin(ActionEvent e) {
        String email = this.txfEmail.getText();
        String password = this.psfPassword.getText();
        lblFeedback.setText("");
        if (email.equals("")) {
            lblFeedback.setText("Digite um email!");
        } else if (password.equals("")) {
            lblFeedback.setText("Digite uma senha!");
        } else if (!Helper.emailValidation(email)) {
            lblFeedback.setText("Digite um email valido!");
        } else {
            User banco = Facade.getInstance().getEmail(email);
            System.out.println(Facade.getInstance().getEmail(email));
            if (banco == null) {
                lblFeedback.setText("E-mail não cadastrado.");
                return;
            }
            User local = new User();
            local.setEmail(email);
            local.setPassword(password);
            if (local.equals(banco)) {
                this.loadQuestionScreen();
            } else {
                lblFeedback.setText("Senha errada.");
            }
        }
    }

    @FXML
    public void clickRegistration(ActionEvent e) {
        this.loadRegistrationScreen();
    }

    private void loadQuestionScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/Questions.fxml"));
            Parent root = (Parent) loader.load();
            QuestionsController controller = loader.getController();
            controller.setStage(stage);
            controller.setHost(this.host);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadRegistrationScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/Registration.fxml"));
            Parent root = (Parent) loader.load();
            RegistrationController controller = loader.getController();
            controller.setStage(stage);
            controller.setHost(this.host);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public HostServices getHost() {
        return host;
    }

    public void setHost(HostServices host) {
        this.host = host;
    }

}
