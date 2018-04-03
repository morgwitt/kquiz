/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.Facade;
import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import util.Helper;

/**
 * FXML Controller class
 *
 * @author morga
 */
public class RegistrationController implements Initializable {

    private HostServices host;
    private Stage stage;
    @FXML
    private Label lblFeedback;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtPasswordAgain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblFeedback.setText("");
    }

    @FXML
    public void clickRegistration(ActionEvent e) {
        String email = this.txtEmail.getText();
        String name = this.txtName.getText();
        String password = this.txtPassword.getText();
        String passwordAgain = this.txtPasswordAgain.getText();

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        User emailVerification = Facade.getInstance().getEmail(email);

        if (name.equals("")) {
            lblFeedback.setText("Digite um nome.");
        } else if (password.equals("") || !password.equals(passwordAgain)) {
            lblFeedback.setText("Erro na senha ou no confirma senha.");
        } else if (!Helper.emailValidation(email)) {
            lblFeedback.setText("Digite um email valido.");
        } else if (emailVerification == null) {
            Facade.getInstance().addUser(user);
            lblFeedback.setText("sucesso");
            loadLoginScreen();
        } else {
            lblFeedback.setText("Email já cadastrado, tente outro.");
        }
    }

    private void loadLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/Login.fxml"));
            Parent root = (Parent) loader.load();
            LoginController controller = loader.getController();
            controller.setStage(stage);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void backToLogin(ActionEvent e) {
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/Login.fxml"));
            Parent root = (Parent) loader.load();
            LoginController controller = loader.getController();
            controller.setStage(stage);
            controller.setHost(host);
            Scene scene = new Scene(root);
            stage.setResizable(false);
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
