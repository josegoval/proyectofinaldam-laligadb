/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import modelo.Modelo_Login;
import modelo.conexion.ConexionBD;
import modelo.conexion.CuentasBD;
import modelo.usuarios.Seguridad;
import vista.jframes.JFrame_Login;

/**
 * Controlador encargado de la parte del Login, que a su vez, es el encargado
 * de arrancar la aplicación por primera vez.
 * 
 * @author Jose
 * @since 05/05/2020
 */
public class Controlador_Login implements ActionListener {
// ATRIBUTOS
    private JFrame_Login vista;
    private Modelo_Login modelo;
    
// CONSTRUCTORES
    /**
     * Constructor vacío que inicializa la vista y el modelo predeterminado
     * para el inicio del programa más ágil.
     */
    public Controlador_Login() {
        this.vista = new JFrame_Login();
        this.modelo = new Modelo_Login();
    }
    
    /**
     * Constructor que inicializa todos los atributos de la clase.
     * @param vista
     * @param modelo 
     */
    public Controlador_Login(JFrame_Login vista, Modelo_Login modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
    
// METODOS
    /**
     * Enlaza la vista con el controlador, y este con el modelo para darle
     * la funcionalidad al programa. Es el metodo de arranque de la propia
     * aplicación.
     */
    public void iniciar(){
        vista.setVisible(true);
        
        // Enlazo la vista con las funcionalidades
        this.vista.btnIniciarSesion.addActionListener(this);
        this.vista.btnIniciarSesion.setActionCommand("IniciarSesion");
        
        this.vista.btnNuevaCuenta.addActionListener(this);
        this.vista.btnNuevaCuenta.setActionCommand("NuevaCuenta");
        
        // Componentes de crear nuevo usuario
        this.vista.jPanel_CrearUsuario.btn_CrearNuevoUsuario.addActionListener(this);
        this.vista.jPanel_CrearUsuario.btn_CrearNuevoUsuario.setActionCommand("CrearNuevoUsuario");
        // 1.- Seguir enlazando los campos restantes...
        // 2.- Crear el JDIALOG que registra al usuario con sus warnins porque
        // el nombre ya este registrado
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "IniciarSesion":
                iniciarSesion();
                break;
            case "NuevaCuenta":
                nuevaCuenta();
                break;
            case "CrearNuevoUsuario":
                crearNuevoUsuario();
                break;
        }
    }
    
    /**
     * Metodo de acción asociado al boton de iniciar sesión.
     * En este metodo se comprueba si la contraseña introducida por el usuario
     * y la registrada en la base de datos, ambas hasheadas, coinciden. Si
     * es así, se pasa a mostrar la siguiente vista con su respectivo 
     * controlador y modelo.
     */
    public void iniciarSesion() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            con = ConexionBD.getConexion(CuentasBD.LOGIN);
            pstm = con.prepareStatement("Select password from usuarios where user=?");
            pstm.setString(1, vista.texto_usuario.getText());
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                if (Seguridad.validarContrasenia(rs.getString(1), 
                        Seguridad.hashPassSHA256(vista.texto_contrasenia.getText()))) {
                    arrancarAplicacion();
                } else {
                    MuestraMensaje.muestraError(vista, "La contraseña no coincide.", 
                            "Contraseña inválida");
                }
            } else {
                MuestraMensaje.muestraAdvertencia(vista, "Ese usuario no existe.", 
                        "Usuario desconocido");
            }
        } catch (Exception e) {
            e.printStackTrace();
            MuestraMensaje.muestraError(vista, "Error al conectarse a la base de datos.", 
                    "Error del sistema");
        } finally {
            ConexionBD.cerrar(con);
            ConexionBD.cerrar(pstm);
            ConexionBD.cerrar(rs);
        }
    }
    
    public void arrancarAplicacion() {
        
    }
    
    /**
     * Abre la el jDialog respectivo para crear una cuenta.
     */
    public void nuevaCuenta(){
        vista.dialogoCrearUsuario.setSize(432, 261);
        vista.dialogoCrearUsuario.setModal(true);
        vista.jPanel_CrearUsuario.texto_NuevaContrasenia.setText("");
        vista.jPanel_CrearUsuario.texto_NuevoUsuario.setText("");
        vista.dialogoCrearUsuario.setVisible(true);
    }
    
    /**
     * Metodo que cubre todo el procedimiento de crear un nuevo usuario
     * desde su respectivo jDialog, una vez que este ya está abierto.
     * @see #nuevaCuenta() 
     */
    public void crearNuevoUsuario() {
        Connection con = null;
        PreparedStatement pstm = null;
        int exito;
        
        try {
            con = ConexionBD.getConexion(CuentasBD.CREADOR);
            pstm = con.prepareStatement("INSERT INTO usuarios VALUES(?,?,?)");
            pstm.setString(1, vista.jPanel_CrearUsuario.texto_NuevoUsuario.getText());
            System.out.println(vista.jPanel_CrearUsuario.texto_NuevoUsuario.getText());
            pstm.setString(2, Seguridad.hashPassSHA256(vista.jPanel_CrearUsuario.texto_NuevaContrasenia.getText()));
            pstm.setString(3, "NORMAL");
            exito = pstm.executeUpdate();
            
            if (exito == 1) {
                MuestraMensaje.muestraExito(vista, 
                        "Has creado tu usuario: ¡Enhorabuena!", 
                        "¡Usuario Creado!");
                vista.dialogoCrearUsuario.dispose();
            } else {
                MuestraMensaje.muestraError(vista, 
                        "¡No se ha podido crear su usuario!", "Error");
            }
        } catch (MySQLIntegrityConstraintViolationException sqle) {
            sqle.printStackTrace();
            MuestraMensaje.muestraAdvertencia(vista, 
                        "Ese nombre de usuario no está disponible. Por favor,"
                                + "escoja uno diferente.", 
                        "Usuario no disponible");
        } catch (Exception e) {
            e.printStackTrace();
            MuestraMensaje.muestraError(vista, 
                        "¡Hay un error con la base de datos! Por favor, "
                                + "comuniquese con un administrador para "
                                + "solucionar el problema o intentelo más tarde.", 
                        "Error del Sistema.");
            vista.dialogoCrearUsuario.dispose();
        } finally {
            ConexionBD.cerrar(con);
            ConexionBD.cerrar(pstm);
        }
        
    }
}
