/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author giang
 */
public class Produto {
    private Integer cod;
    private String nome;
    private Categoria categoria;
    
    public Produto(){
        
    }

    public Produto(String nome, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }
    
    public Produto(Integer cod, String nome, Categoria categoria) {
        this.cod = cod;
        this.nome = nome;
        this.categoria = categoria;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nProduto{");
        sb.append("\ncod=").append(cod);
        sb.append("\nnome=").append(nome);
        sb.append("\ncategoria=").append(categoria.getNome());
        sb.append("\n}");
        return sb.toString();
    }
    
    
}