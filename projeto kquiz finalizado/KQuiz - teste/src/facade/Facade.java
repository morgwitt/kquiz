/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.ArrayList;
import model.Alternative;
import model.Question;
import model.User;
import persistence.AlternativeRep;
import persistence.QuestionRep;
import persistence.UserRep;

/**
 *
 * @author morga
 */
public class Facade {

    private static Facade facade;

    private AlternativeRep altR;
    private QuestionRep questionR;
    private UserRep userR;

    private Facade() {
        altR = new AlternativeRep();
        questionR = new QuestionRep();
        userR = new UserRep();
    }

    public static Facade getInstance() {
        if (facade == null) {
            facade = new Facade();
        }
        return facade;
    }

    public User getUserFromEmail(String email) {
        return this.userR.searchFromEmail(email);
    }

    public Question getQuestionFromId(int id) {
        return this.questionR.search(id);
    }

    public void updateUser(User user) {
        this.userR.edit(user);
    }

    public ArrayList<Question> getQuetionFromId(int id) {
        return this.questionR.search();
    }

    public ArrayList<Question> getQuestions() {
        return this.questionR.search();
    }

    public ArrayList<Alternative> getStatement(int id) {
        return this.altR.searchAlternativeFromQuestion(id);
    }

    public void addUser(User user) {
        this.userR.toAdd(user);
    }

    public User getEmail(String email) {
        return this.userR.searchFromEmail(email);
    }

}
