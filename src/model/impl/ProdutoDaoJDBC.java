/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.impl;

import db.DB;
import db.DbException;
import java.util.List;
import model.Produto;
import model.dao.ProdutoDao;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Categoria;

/**
 *
 * @author giang
 */
public class ProdutoDaoJDBC implements ProdutoDao {

    private Connection conn;

    public ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Produto obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO produto(nome, codcategoria) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getNome());
            st.setInt(2, obj.getCategoria().getCod());

            int pk = st.executeUpdate();

            if (pk > 0) {
                JOptionPane.showMessageDialog(null, pk + " linha foi adicionada !");
                ResultSet rs = st.getGeneratedKeys();
                rs.first();
                obj.setCod(rs.getInt(1));

                DB.closeResultSet();
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            DB.closeStatement();
        }
    }

    @Override
    public List<Produto> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<Produto> list = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM produto AS p "
                    + "INNER JOIN categoria AS c ON p.codcategoria = c.codcategoria");

            rs = st.executeQuery();

            if (rs.first()) {
                rs.first();

                do {
                    Categoria cat = new Categoria(rs.getInt("codcategoria"), rs.getString("c.nome"));

                    list.add(new Produto(rs.getInt("codproduto"), rs.getString("p.nome"), cat));

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
    public Produto findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Produto obj;

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM produto AS p "
                    + "INNER JOIN categoria AS c ON p.codcategoria = c.codcategoria "
                    + "WHERE p.codproduto = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.first()) {
                rs.first();
                Categoria cat = new Categoria(rs.getInt("codcategoria"), rs.getString("c.nome"));
                obj = new Produto(rs.getInt("codproduto"), rs.getString("p.nome"), cat);

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
    public void update(Produto obj) {
        if (this.findById(obj.getCod()) == null) {
            throw new NullPointerException("Este código não existe!");
        }

        Produto old = this.findById(obj.getCod());
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE produto SET nome = ? WHERE codproduto = ?", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setInt(2, obj.getCod());

            int linhasAfetadas = st.executeUpdate();

            JOptionPane.showMessageDialog(null, linhasAfetadas + " linha(s) afetada(s)!");

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            DB.closeResultSet();
            DB.closeStatement();
        }

    }

    @Override
    public void deleteById(Integer id) {
        if (this.findById(id) == null) {
            throw new NullPointerException("Este código não existe!");
        }

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM produto WHERE codproduto = ?", Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, id);
            int linhasAfetadas = st.executeUpdate();

            JOptionPane.showMessageDialog(null, linhasAfetadas + " linha(s) afetada(s)!");

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            DB.closeStatement();
        }
    }
}
