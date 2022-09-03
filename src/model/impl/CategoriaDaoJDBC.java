/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.impl;

import db.DB;
import db.DbException;
import java.util.List;
import model.Categoria;
import model.dao.CategoriaDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author giang
 */
public class CategoriaDaoJDBC implements CategoriaDao {

    private Connection conn;

    public CategoriaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Categoria obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO categoria (nome) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getNome());

            int pk = st.executeUpdate();

            if (pk > 0) {
                JOptionPane.showMessageDialog(null, pk + " linha foi adicionada com sucesso!");
                ResultSet rs = st.getGeneratedKeys();
                rs.first();
                obj.setCod(rs.getInt(1));

                DB.closeResultSet();
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            DB.closeStatement();
        }
    }

    @Override
    public List<Categoria> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<Categoria> list = new ArrayList<>();

        try {
            st = conn.prepareStatement("SELECT * FROM categoria;");
            rs = st.executeQuery();

            if (rs.first()) {
                rs.first();
                do {
                    list.add(new Categoria(rs.getInt("codcategoria"), rs.getString("nome")));

                } while (rs.next());
                return list;
            }

            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            DB.closeResultSet();
            DB.closeStatement();
        }

    }

    @Override
    public Categoria findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Categoria obj;

        try {
            st = conn.prepareStatement("SELECT * FROM categoria WHERE codcategoria = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.first()) {
                rs.first();
                obj = new Categoria(rs.getInt("codcategoria"), rs.getString("nome"));

                return obj;

            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            DB.closeResultSet();
            DB.closeStatement();
        }

        return null;
    }

    @Override
    public void update(Categoria obj) {
        if (this.findById(obj.getCod()) == null) {
            throw new NullPointerException("Este código não existe!");
        }

        Categoria old = this.findById(obj.getCod());
        PreparedStatement st = null;
        
        try {
            st = conn.prepareStatement("UPDATE categoria SET nome = ? WHERE codcategoria = ?", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getNome());
            st.setInt(2, obj.getCod());
            
            int linhasAfetadas = st.executeUpdate();
            
            JOptionPane.showMessageDialog(null, linhasAfetadas + " linhas afetadas!");

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            DB.closeResultSet();
            DB.closeStatement();
        }

    }

    @Override
    public void deleteById(Integer id) {
        if(this.findById(id) == null){
            throw new NullPointerException("Este código não corresponde a nehum produto cadastrado no sistema!" );
        }
        
        PreparedStatement st = null;
        
        try {
            st = conn.prepareStatement("DELETE FROM categoria WHERE codcategoria = ?", Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, id);
            int linhasAfetadas = st.executeUpdate();
            
            JOptionPane.showMessageDialog(null, linhasAfetadas + " linha(s) afetada(s)!");
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        
        }finally{
            DB.closeStatement();
        }
    }

}
