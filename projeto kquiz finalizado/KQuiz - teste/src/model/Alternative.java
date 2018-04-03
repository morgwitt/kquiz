/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author morga
 */
public class Alternative extends AModel {

    private String statement;
    private EAlternative option;
    private int idQuestion;

    public Alternative(int id, String statement, int option, int idQuestion) {
        this.id = id;
        this.statement = statement;
        this.option = EAlternative.getValue(option);
        this.idQuestion = idQuestion;
    }

    public Alternative() {
    }

    public String getStatement() {
        return statement;
    }

    public void setEstatement(String statement) {
        this.statement = statement;
    }

    public EAlternative getOption() {
        return option;
    }

    public void setOption(EAlternative option) {
        this.option = option;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    @Override
    public String toString() {
        return "Alternativa{" + "enunciado=" + statement + ", opcao=" + option + ", id_pergunta=" + idQuestion + '}';
    }

}
