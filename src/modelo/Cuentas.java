/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.conexion.ConexionBD;
import modelo.conexion.CuentasBD;
import modelo.usuarios.Seguridad;
import modelo.usuarios.TiposUsuario;
import modelo.usuarios.Usuario;

/**
 * Clase encargada de Logear al usuario, realizar la vadilación, e insertar y
 * modificar los datos referentes a las cuentas.
 * 
 * @author José Manuel Gómez Martínez
 * @since 06/05/2020
 */
public class Cuentas {
// CONSTRUCTORES
    /**
     * Constructor vacío.
     */
    public Cuentas() {
    }
    
    
// METODOS
    /**
     * Inserta en la base de datos la cuenta, si es posible, y devuelve 2
     * strings, con mensajes a mostrar si fuese necesario, o validando el
     * éxito de la inserción.
     * @param usuario Nombre único de usuario.
     * @param contrasenia Contraseña de usuario sin hashear
     * @param tipo Tipo de usuario (NORMAL o ADMIN)
     * @return Un array de dos String, el primero con el título del mensaje, y 
     * el segundo, con conteniendo el contenido del mensaje a mostrar. 
     * <b>Si la inserción fuese fructifera, "¡Usuario Creado!" en el título.</b>
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
                    "Ese nombre de usuario no está disponible. Por favor, "
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
    
    /**
     * Valida si el usuario y la contraseña, que será hasheada en este método,
     * son correctas con las registradas en la base de datos, y en función del
     * resultado, devuelve una respuesta.
     * @param user Nombre de usuario.
     * @param password Contraseña del usuario <b>SIN HASHEAR</b>
     * @return Un array de dos String, el primero con el título del mensaje, y 
     * el segundo, con conteniendo el contenido del mensaje a mostrar. 
     * <b>Si la validación fuese fructifera, devolvería "Exito" en ambos 
     * Strings.</b>
     */
    public String[] validarSesion(String user, String password){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String[] resultado;
        
        try {
            con = ConexionBD.getConexion(CuentasBD.LOGIN);
            pstm = con.prepareStatement("Select password from usuarios where user=?");
            pstm.setString(1, user);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                if (Seguridad.validarContrasenia(rs.getString(1), 
                        Seguridad.hashPassSHA256(password))) {
                    resultado = new String[]{"Exito", "Exito"};
                } else {
                    resultado = new String[]{"Contraseña inválida", 
                        "La contraseña no coincide."};
                }
            } else {
                resultado = new String[]{"Usuario desconocido", 
                        "Ese usuario no existe."};
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = new String[]{"Error del sistema", 
                        "Error al conectarse a la base de datos."};
        } finally {
            ConexionBD.cerrar(con);
            ConexionBD.cerrar(pstm);
            ConexionBD.cerrar(rs);
        }
        
        return resultado;
    }
    
    /**
     * Busca un usuario en específico en la base de datos y devuelve un objeto
     * de tipo Usuario que ya contiene todos sus datos.
     * @param user Nombre único del usuario a buscar.
     * @return Objeto usuario con todos sus datos si lo encuentra <b>exceptuando
     * la contraseña, por seguridad</b>. Si no lo encuentra o hay algún
     * error, devolverá null.
     */
     public Usuario buscarUsuario(String user){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Usuario usuario = null;
        
        try {
            con = ConexionBD.getConexion(CuentasBD.LOGIN);
            pstm = con.prepareStatement("Select tipo from usuarios where user=?");
            pstm.setString(1, user);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                // La contraseña se establece null
                usuario = new Usuario(user, "", TiposUsuario.valueOf(rs.getString(1)));
            }               
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionBD.cerrar(con);
            ConexionBD.cerrar(pstm);
            ConexionBD.cerrar(rs);
        }
        
        return usuario;
    }
    
     /**
     * Actualiza en la base de datos la contraseña, si es posible, y devuelve 2
     * strings, con mensajes a mostrar si fuese necesario, o validando el
     * éxito del update.
     * @param usuario Nombre único del usuario.
     * @param contrasenia Contraseña del usuario sin hashear
     * @param tipo Tipo de cuenta de la base de datos. El usuario contiene dichos
     * datos.
     * @return Un array de dos String, el primero con el título del mensaje, y 
     * el segundo, con conteniendo el contenido del mensaje a mostrar. 
     * <b>Si la inserción fuese fructifera, "¡Contraseña cambiada!" en el título.</b>
     */
    public String[] updatePassword(String usuario, String contrasenia, CuentasBD tipo) {
        Connection con = null;
        PreparedStatement pstm = null;
        int exito;
        String[] resultado;
        
        try {
            con = ConexionBD.getConexion(tipo);
            pstm = con.prepareStatement("UPDATE usuarios SET password=? WHERE user=?");
            pstm.setString(1, Seguridad.hashPassSHA256(contrasenia));
            pstm.setString(2, usuario);
            exito = pstm.executeUpdate();
            
            if (exito == 1) {
                resultado = new String[]{"¡Contraseña cambiada!",
                    "Ha cambiado su contraseña."};
            } else {
                resultado = new String[]{"Error",
                    "¡No se ha podido cambiar la contraseña!"};
                
            }
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
