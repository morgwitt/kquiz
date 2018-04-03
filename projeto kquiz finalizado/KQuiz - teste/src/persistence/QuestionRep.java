/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import model.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DBUtil;

/**
 *
 * @author morga
 */
public class QuestionRep implements IRepository<Question, Integer> {

    Connection conexao = null;
    PreparedStatement ps = null;

    @Override
    public void toAdd(Question entidade) {

        try {
            String sql = "INSERT INTO kquiz.tbl_pergunta"
                    + "(enunciado, resposta) VALUES(?,?)";
            conexao = DBUtil.getConnetion();
            ps = conexao.prepareStatement(sql);
            ps.setString(1, entidade.getStatement());
            ps.setInt(2, entidade.getAnswer().value);
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @Override
    public void edit(Question entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Question entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Question> search() {
        ArrayList<Question> perguntas = new ArrayList();
        try {
            String sql = "SELECT tbl_pergunta.id, tbl_pergunta.enunciado, tbl_alternativa.opcao "
                    + "FROM tbl_pergunta "
                    + "JOIN tbl_alternativa "
                    + "ON tbl_pergunta.resposta = tbl_alternativa.id ";
            conexao = DBUtil.getConnetion();
            ps = conexao.prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                perguntas.add(new Question(
                        resultado.getInt("id"),
                        resultado.getString("enunciado"),
                        resultado.getInt("opcao")));
            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return perguntas;
    }

    @Override
    public Question search(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
