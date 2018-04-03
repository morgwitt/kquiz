/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * FXML Controller class
 *
 * @author morga
 */
public class TtController implements Initializable {

    private Stage stage;
    private HostServices host;
    private RequestToken requestToken;
    private Twitter twitter;
    private TwitterFactory factory;
    
    @FXML
    private Label lblMessage;
    @FXML
    private Label lblTweet;
    @FXML
    private TextField txfPin;
    @FXML
    private Button btnTwitter;
    @FXML
    private Button btnURL;
    @FXML
    private Button btnLogin;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setDebugEnabled(true)
                .setOAuthConsumerKey("OU9aDpSPTmzsg2Rkd68WZ93pp")
                .setOAuthConsumerSecret("gCNqgKVHRfNgYhrhgwcYKMIEflDKXaHmxmw0GjNrsKeBObNap2")
                .setOAuthAccessToken(null)
                .setOAuthAccessTokenSecret(null);
        twitter4j.conf.Configuration configuration = builder.build();
        this.factory = new TwitterFactory(configuration);
    }

    @FXML
    public void clickTweet() throws TwitterException, IOException {
        Status status = twitter.updateStatus(lblTweet.getText());
        btnTwitter.setText("Mensagem postada!");
        btnTwitter.setDisable(true);
    }

    @FXML
    public void loginTwitter() throws TwitterException {
        AccessToken accessToken = null;
        String pin = txfPin.getText();
        try {
            twitter.getOAuthAccessToken(requestToken, pin);
            lblMessage.setText("Login efetuado.");
            this.btnTwitter.setDisable(false);
            this.btnURL.setDisable(true);
        } catch (TwitterException te) {
            if (401 == te.getStatusCode()) {
                System.out.println(te.toString());
            } else {
                te.printStackTrace();
            }
            lblMessage.setText("Falha ao efetuar o login. Acesse o link novamente.");
        }
        this.btnLogin.setDisable(true);
        this.txfPin.setDisable(true);
    }

    @FXML
    public void clickURL(ActionEvent e) throws Exception {
        twitter = factory.getInstance();
        try {
            requestToken = twitter.getOAuthRequestToken();
        } catch (TwitterException ex) {
            Logger.getLogger(TtController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.host.showDocument(requestToken.getAuthenticationURL());
        this.txfPin.setDisable(false);
        this.btnLogin.setDisable(false);
        txfPin.setText("");
        lblMessage.setText("");
    }

    public void setScore(Label ponto) throws IOException {
        this.lblTweet.setText("Acertei " + ponto.getText() + " pontos de 10 no Kquiz.");
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
