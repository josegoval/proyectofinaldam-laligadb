/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.usuarios;

import modelo.conexion.CuentasBD;

/**
 * Clase básica Usuario que contiene todo lo necesario para logear y validar
 * las cuestiones referentes al usuario.
 * 
 * @author Jose
 * @since 04/05/2020
 */
public class Usuario {
// ATRIBUTOS
    /**
     * Nombre o nick único y referente al logeo del usuario.
     */
    private String user;
    /**
     * Contraseña del usuario en formato de 64 caracteres hexadecimales dados
     * por el hash. <b>En ningún momento, salvo en su propia transformación,
     * se guarda la contraseña real del usuario.</b>
     */
    private String password;
    /**
     * Tipo de usuario. Actualmente o ADMIN o NORMAL.
     */
    private TiposUsuario tipo;
    
// CONSTRUCTORES
    /**
     * Constructor que inicializa todos los atributos de la clase.
     * @param user Nombre o nick único y referente al logeo del usuario.
     * @param password Contraseña del usuario en formato de 64 caracteres 
     * hexadecimales dados por el hash.
     * @param tipo Tipo de usuario. Actualmente o ADMIN o NORMAL.
     */
    public Usuario(String user, String password, TiposUsuario tipo) {
        this.user = user;
        this.password = password;
        this.tipo = tipo;
    }
    
// METODOS
    /**
     * Transforma el tipo del usuario al tipo de cuenta que se conecta
     * a la base de datos.
     * @return CuentaBD con la que se conectará a la base de datos.
     */
    public CuentasBD getTipoCuentaBD(){
        return CuentasBD.valueOf(tipo.toString());
    }
    
// SETTERS & GETTERS
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TiposUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TiposUsuario tipo) {
        this.tipo = tipo;
    }

}
