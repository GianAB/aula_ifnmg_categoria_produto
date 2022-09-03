/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

/**
 *
 * @author giang
 */
public class DbException extends RuntimeException {
    static final long serialVersionUID = 1;
    
    public DbException(String message) {
        super(message);
    }
}
