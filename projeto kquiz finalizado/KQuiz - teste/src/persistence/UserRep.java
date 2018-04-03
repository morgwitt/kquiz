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
import model.User;
import util.DBUtil;

/**
 *
 * @author morga
 */
public class UserRep implements IRepository<User, Integer> {

    Connection conexao = null;
    PreparedStatement ps = null;

    @Override
    public void toAdd(User entidade) {
        try {
            String sql = "INSERT INTO kquiz.tbl_usuario"
                    + "(nome,email, senha)"
                    + "VALUES(?,?,?)";
            conexao = DBUtil.getConnetion();
            ps = conexao.prepareStatement(sql);
            ps.setString(1, entidade.getName());
            ps.setString(2, entidade.getEmail());
            ps.setString(3, entidade.getSenha());
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void edit(User entidade) {
        try {
            String sql = "UPDATE kquiz.tbl_usuario "
                    + "SET nome = ?, email = ?, senha = ? "
                    + "WHERE id = ?";
            conexao = DBUtil.getConnetion();
            ps = conexao.prepareStatement(sql);
            ps.setString(1, entidade.getName());
            ps.setString(2, entidade.getEmail());
            ps.setString(3, entidade.getSenha());
            ps.setInt(4, entidade.getId());
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @Override
    public void remove(User entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<User> search() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User search(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User searchFromEmail(String email) {
        User usuario = null;
        try {
            String sql = "SELECT * "
                    + "FROM kquiz.tbl_usuario where tbl_usuario.email = ? ";
            conexao = DBUtil.getConnetion();
            ps = conexao.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                usuario = new User();
                usuario.setId(resultado.getInt("id"));
                usuario.setName(resultado.getString("nome"));
                usuario.setEmail(resultado.getString("email"));
                usuario.setHash(resultado.getString("senha"));
            }
            ps.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return usuario;
    }

}
