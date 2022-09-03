package model.dao;

import java.util.List;
import model.Categoria;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author giang
 */
public interface CategoriaDao {
    //CRUD
    void insert(Categoria obj);
    List<Categoria> findAll();
    Categoria findById(Integer id);
    void update(Categoria obj);
    void deleteById(Integer id);
    
}
