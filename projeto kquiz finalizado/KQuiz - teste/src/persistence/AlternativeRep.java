/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Alternative;
import model.EAlternative;
import util.DBUtil;

/**
 *
 * @author morga
 */
public class AlternativeRep implements IRepository<Alternative, Integer> {

    Connection conexao = null;
    PreparedStatement ps = null;

    @Override
    public void toAdd(Alternative entidade) {
        try {
            String sql = "INSERT INTO kquiz.tbl_alternativa(enunciado,opcao,id_pergunta)"
                    + "VALUES(?,?,?)";
            conexao = DBUtil.getConnetion();
            ps = conexao.prepareStatement(sql);
            ps.setString(1, entidade.getStatement());
            ps.setInt(2, entidade.getOption().getValue());
            ps.setInt(3, entidade.getIdQuestion());
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @Override
    public void edit(Alternative entidade) {
        try {
            String sql = "UPDATE kquiz.tbl_alternativa"
                    + "SET enunciado = ?, opcao = ?, id_pergunta =?"
                    + "WHERE id = ?";
            conexao = DBUtil.getConnetion();
            ps = conexao.prepareStatement(sql);
            ps.setString(1, entidade.getStatement());
            ps.setInt(2, entidade.getOption().value);
            ps.setInt(3, entidade.getId());
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @Override
    public void remove(Alternative entidade) {
        try {
            String sql = "DELETE FROM kquiz.tbl_alternativa\n"
                    + "WHERE tbl_alternativa.id = ?;";
            conexao = DBUtil.getConnetion();
            ps = conexao.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public ArrayList<Alternative> search() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Alternative search(Integer id) {
        Alternative a = new Alternative();
        try {
            String sql = "SELECT tbl_alternativa.id,"
                    + "    tbl_alternativa.enunciado,"
                    + "    tbl_alternativa.opcao,"
                    + "    tbl_alternativa.id_pergunta"
                    + "FROM kquiz.tbl_alternativa"
                    + "where tbl_alternativa.id = ?";
            conexao = DBUtil.getConnetion();
            ps = conexao.prepareStatement(sql);
            ps.executeQuery();
            ps.setInt(1, a.getId());
            ps.setString(2, a.getStatement());
            ps.setObject(3, a.getOption());
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return a;
    }

    public ArrayList<Alternative> searchAlternativeFromQuestion(int id) {
        ArrayList<Alternative> alternativas = new ArrayList<>();

        try {
            String sql = "SELECT kquiz.tbl_alternativa.* FROM kquiz.tbl_alternativa join kquiz.tbl_pergunta "
                    + "ON kquiz.tbl_alternativa.id_pergunta = kquiz.tbl_pergunta.id "
                    + "where kquiz.tbl_pergunta.id = ? ";
            conexao = DBUtil.getConnetion();
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                alternativas.add(new Alternative(
                        resultado.getInt("id"),
                        resultado.getString("enunciado"),
                        resultado.getInt("opcao"),
                        resultado.getInt("id_pergunta")));
            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return alternativas;
    }

}
