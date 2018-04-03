/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.Facade;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Alternative;
import model.EAlternative;
import model.Question;

/**
 * FXML Controller class
 *
 * @author morga
 */
public class QuestionsController implements Initializable {

    private Stage stage;
    private HostServices host;
    @FXML
    private Button btnNext;
    @FXML
    private Label lblTitle;
    @FXML
    private RadioButton rdbA;
    @FXML
    private RadioButton rdbB;
    @FXML
    private RadioButton rdbC;
    @FXML
    private RadioButton rdbD;
    @FXML
    private RadioButton rdbE;
    @FXML
    private Label lblScore;
    private ToggleGroup group;
    private ArrayList<Question> banco;
    private int currentQuestion = 0;
    private int ponto = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        group = new ToggleGroup();
        rdbA.setToggleGroup(group);
        rdbB.setToggleGroup(group);
        rdbC.setToggleGroup(group);
        rdbD.setToggleGroup(group);
        rdbE.setToggleGroup(group);
        lblScore.setText("" + 0);
        banco = Facade.getInstance().getQuestions();
        lblTitle.setText(banco.get(currentQuestion).getStatement());
        this.setAltInQuestion(banco.get(currentQuestion).getId());

    }

    @FXML
    public void radioSelected(ActionEvent e){
        this.btnNext.setDisable(false);
    }
    
    private void setAltInQuestion(int id) {
        ArrayList<Alternative> alternatives = Facade.getInstance().getStatement(id);
        clearAlternatives();
        for (Alternative a : alternatives) {
            switch (a.getOption()) {
                case A:
                    rdbA.setDisable(false);
                    rdbA.setText("a) " + a.getStatement());
                    break;
                case B:
                    rdbB.setDisable(false);
                    rdbB.setText("b) " + a.getStatement());
                    break;
                case C:
                    rdbC.setDisable(false);
                    rdbC.setText("c) " + a.getStatement());
                    break;
                case D:
                    rdbD.setDisable(false);
                    rdbD.setText("d) " + a.getStatement());
                    break;
                case E:
                    rdbE.setDisable(false);
                    rdbE.setText("e) " + a.getStatement());
                    break;
            }
        }
    }

    private void clearAlternatives() {
        rdbA.setText("a)");
        rdbB.setText("b)");
        rdbC.setText("c)");
        rdbD.setText("d)");
        rdbE.setText("e)");
        rdbA.setDisable(true);
        rdbB.setDisable(true);
        rdbC.setDisable(true);
        rdbD.setDisable(true);
        rdbE.setDisable(true);

    }

    public void verifyAnswers() {
        EAlternative answer = banco.get(currentQuestion).getAnswer();
        RadioButton selected = (RadioButton) group.getSelectedToggle();
        if (selected.equals(rdbA) && EAlternative.A == answer) {
            ponto++;
            lblScore.setText("" + ponto);
        } else if (selected.equals(rdbB) && EAlternative.B == answer) {
            ponto++;
            lblScore.setText("" + ponto);
        } else if (selected.equals(rdbC) && EAlternative.C == answer) {
            ponto++;
            lblScore.setText("" + ponto);
        } else if (selected.equals(rdbD) && EAlternative.D == answer) {
            ponto++;
            lblScore.setText("" + ponto);
        } else if (selected.equals(rdbE) && EAlternative.E == answer) {
            ponto++;
            lblScore.setText("" + ponto);
        } else {
            lblScore.setText("" + ponto);
        }
        selected.setSelected(false);
    }

    @FXML
    public void nextQuestion(ActionEvent e) throws IOException {
        verifyAnswers();
        currentQuestion++;
        if (currentQuestion < banco.size()) {
            lblTitle.setText(banco.get(currentQuestion).getStatement());
            this.setAltInQuestion(banco.get(currentQuestion).getId());
            this.btnNext.setDisable(true);
        } else {
            this.loadTtScreen();
        }
    }

    @FXML
    public void closeProgram(ActionEvent e){
        stage.close();
    }
    
    private void loadTtScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/Tt.fxml"));
        Parent root = (Parent) loader.load();
        TtController controller = loader.getController();
        controller.setScore(lblScore);
        controller.setHost(this.host);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
