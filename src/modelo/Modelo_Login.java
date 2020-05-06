/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import modelo.conexion.ConexionBD;
import modelo.conexion.CuentasBD;
import modelo.usuarios.Seguridad;

/**
 * Modelo del Login
 * 
 * @author José Manuel Gómez Martínez
 * @since 06/05/2020
 */
public class Modelo_Login {
// CONSTRUCTORES
    /**
     * Constructor vacío.
     */
    public Modelo_Login() {
    }
    
    
// METODOS
    /**
     * Inserta en la base de datos la cuenta, si es posible, y devuelve 2
     * strings, con mensajes a mostrar si fuese necesario, o validando el
     * éxito de la inserción.
     * @return Un array de dos String, el primero con el título del mensaje, y 
     * el segundo, con conteniendo el contenido del mensaje a mostrar. 
     * <b>Si la inserción fuese fructifera, devolvería "exito" en ambos 
     * Strings.</b>
     */
    public String[] insertarCuenta(String usuario, String contrasenia, String tipo) {
        Connection con = null;
        PreparedStatement pstm = null;
        int exito;
        String[] resultado;
        
        try {
            con = ConexionBD.getConexion(CuentasBD.CREADOR);
            pstm = con.prepareStatement("INSERT INTO usuarios VALUES(?,?,?)");
            pstm.setString(1, usuario);
            pstm.setString(2, Seguridad.hashPassSHA256(contrasenia));
            pstm.setString(3, tipo);
            exito = pstm.executeUpdate();
            
            if (exito == 1) {
                resultado = new String[]{"¡Usuario Creado!",
                    "Has creado tu usuario: ¡Enhorabuena!"};
            } else {
                resultado = new String[]{"Error",
                    "¡No se ha podido crear su usuario!"};
                
            }
        } catch (MySQLIntegrityConstraintViolationException sqle) {
            sqle.printStackTrace();
            resultado = new String[]{"Usuario no disponible",
                    "Ese nombre de usuario no está disponible. Por favor,"
                                + "escoja uno diferente."};
        } catch (Exception e) {
            e.printStackTrace();
            resultado = new String[]{"Error del Sistema",
                    "¡Hay un error con la base de datos! Por favor, "
                                + "comuniquese con un administrador para "
                                + "solucionar el problema o intentelo más tarde."};
        } finally {
            ConexionBD.cerrar(con);
            ConexionBD.cerrar(pstm);
        }
        
        return resultado;
    }
}
