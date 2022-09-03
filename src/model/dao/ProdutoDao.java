package model.dao;

import java.util.List;
import model.Produto;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author giang
 */
public interface ProdutoDao {
    //CRUD
    void insert(Produto obj);
    List<Produto> findAll();
    Produto findById(Integer id);
    void update(Produto obj);
    void deleteById(Integer id);
    
}
