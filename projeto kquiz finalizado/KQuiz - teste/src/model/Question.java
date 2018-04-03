/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
/**
 *
 * @author morga
 */
public class Question extends AModel {

    private String statement;
    private ArrayList<Alternative> alternatives;
    private EAlternative answer;

    public Question() {
    }

    public Question(int id, String statement, int answer) {
        this.id = id;
        this.statement = statement;
        this.answer = EAlternative.getValue(answer);
    }

    public Question(String enunciado) {
        this.statement = enunciado;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String enunciado) {
        this.statement = enunciado;
    }

    public ArrayList<Alternative> setAlternatives() {
        return alternatives;
    }

    public void setAlternatives(ArrayList<Alternative> alternativas) {
        this.alternatives = alternativas;
    }

    @Override
    public String toString() {
        return "Enunciado: " + statement + " ID: " + id + ".";
    }

    public EAlternative getAnswer() {
        return answer;
    }

    public void setAnswer(EAlternative resposta) {
        this.answer = resposta;
    }

}
