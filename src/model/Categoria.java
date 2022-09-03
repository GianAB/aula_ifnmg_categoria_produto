/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giang
 */
public class Categoria {
    private Integer cod;
    private String nome;
    
    public Categoria(){
    }

    public Categoria(Integer cod, String nome) {
        this.cod = cod;
        this.nome = nome;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Categoria{");
        sb.append("cod=").append(cod);
        sb.append(", nome=").append(nome);
        sb.append('}');
        return sb.toString();
    }
    
    
}
