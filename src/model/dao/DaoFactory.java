/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import db.DB;
import model.impl.CategoriaDaoJDBC;
import model.impl.ProdutoDaoJDBC;

/**
 *
 * @author giang
 */
public class DaoFactory {

    public static CategoriaDao createCategoriaDao() {
        return new CategoriaDaoJDBC(DB.getConnection());
    }
    
    public static ProdutoDao createProdutoDao() {
        return new ProdutoDaoJDBC(DB.getConnection());
    }
    
}
