/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase dedicada a la conexión con la base de datos y el cerrado de todas sus
 * clases dependientes.
 * 
 * @author José Manuel Gómez Martínez.
 * @since 06/05/2020
 */
public class ConexionBD {
// ATRIBUTOS
    /**
     * Conexion con la base de datos.
     */
    private static Connection conexion = null;
    /**
     * Base de datos a seleccionar dentro de la conexión establecida.
     */
    private static final String db = "laligadb";
    /**
     * Habilita traer parametros de procedimientos de la base de datos.
     */
    private static final String inoutPermissions = "?noAccessToProcedureBodies=true";
    /**
     * URL completa de la dirección a la base de datos.
     */
    private static final String url = "jdbc:mysql://localhost/" + db + inoutPermissions;
    /**
     * Usuario a conectar con la base de datos. <br>
     * <b>Por defecto, el usuario está puesto al de login.</b>
     */
    private static String user = "login";
    /**
     * Contraseña de la cuenta de la base de datos.<br>
     * <b>Por defecto, la contraseña es la de de login.</b>
     */
    private static String password = "0oi98uy76tr54ew32q1";
    
// METODOS
    /**
     * Establece y devuelve la conexión con la base de datos. No obstante,
     * si hay algún tipo de error devolverá null.
     * @param tipo Tipo de cuenta con la que se conectará a la base de datos.
     * @return Devuelve la conexion (Connection) con la base de datos, aunque
     * si hubiese algún error, devolverá null.
     */
    public static synchronized Connection getConexion(CuentasBD tipo) {
        switch(tipo) {
            case LOGIN:
                cambiarUsuarioLogin();
                break;
            case CREADOR:
                cambiarUsuarioCreador();
                break;
            case ADMIN:
                cambiarUsuarioAdmin();
                break;
            case NORMAL:
                cambiarUsuarioNormal();
                break;
            default:
                cambiarUsuarioLogin();
                break;
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, password);
            return conexion;
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error al cargar el driver/clase.");
            cnfe.printStackTrace();
            
        } catch (SQLException sqle) {
            System.err.println("Error de SQL al conectar con la BD.");
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Cierra la conexión con la base de datos.
     * @param conexion Conexión a cerrar
     * @return true = cerrada, false = no se pudo cerrar por alguna razón <i>(es
     * posible a que se deba que el parámetro de entrada fuese null).
     */
    public static boolean cerrar(Connection conexion){
        if (conexion != null) {
            try {
                conexion.close();
                return true;
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    /**
     * Cierra el PreparedStatement introducido.
     * @param pstm PreparedStatement a cerrar.
     * @return true = cerrada, false = no se pudo cerrar por alguna razón <i>(es
     * posible a que se deba que el parámetro de entrada fuese null).
     */
    public static boolean cerrar(PreparedStatement pstm){
        if (pstm != null) {
            try {
                pstm.close();
                return true;
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    /**
     * Cierra el CallableStatement introducido.
     * @param cstmt CallableStatement a cerrar.
     * @return true = cerrada, false = no se pudo cerrar por alguna razón <i>(es
     * posible a que se deba que el parámetro de entrada fuese null).
     */
    public static boolean cerrar(CallableStatement cstmt){
        if (cstmt != null) {
            try {
                cstmt.close();
                return true;
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    /**
     * Cierra el ResultSet introducido.
     * @param rs ResultSet a cerrar.
     * @return true = cerrada, false = no se pudo cerrar por alguna razón <i>(es
     * posible a que se deba que el parámetro de entrada fuese null).
     */
    public static boolean cerrar(ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
                return true;
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    /**
     * Cambia el usuario de la base de datos a login. <br>
     * <i>Esta cuenta se usa exclusivamente para logearse 
     * (select ... from usuarios).</i>
     */
    public static void cambiarUsuarioLogin() {
        user = "login";
        password = "0oi98uy76tr54ew32q1";
    }
    
     /**
     * Cambia el usuario de la base de datos a creador. <br>
     * <i>Esta cuenta se usa para crear cuentas (insert into usuarios...) en la 
     * base de datos.</i>
     */
    public static void cambiarUsuarioCreador() {
        user = "creador";
        password = "1qw23er45ty67ui89op0";
    }
    
    /**
     * Cambia el usuario de la base de datos a Admin. <br>
     * <i>Es la cuenta utilizada por los usuarios tipo ADMIN.</i>
     */
    public static void cambiarUsuarioAdmin() {
        user = "admin";
        password = "plkoijhuygftrdsewaq";
    }
 
    /**
     * Cambia el usuario de la base de datos a Normal. <br>
     * <i>Es la cuenta utilizada por los usuarios tipo NORMAL.</i>
     */
    public static void cambiarUsuarioNormal() {
        user = "normal";
        password = "mkjnbhgvcfdxzsa";
    }   
}
