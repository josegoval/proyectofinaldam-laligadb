/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                    arrancarApp();
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
    
    /**
     * Cierra la vista actual de logeo, y carga la App (aplicación principal) 
     * completa en la nueva vista-modelo-controlador pasandole los datos
     * del usuarios a la misma.
     */
    public void arrancarApp() {
        this.vista.dispose();
        // Queda por rellenar al apertura.
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
        String[] resultado = modelo.insertarCuenta(
                vista.jPanel_CrearUsuario.texto_NuevoUsuario.getText(), 
                vista.jPanel_CrearUsuario.texto_NuevaContrasenia.getText(),
                "NORMAL");
        
        switch (resultado[0]) {
            case "exito":
                break;
            case "¡Usuario Creado!":
                MuestraMensaje.muestraExito(vista, resultado[1], resultado[0]);
                vista.dialogoCrearUsuario.dispose();
                break;
            case "Usuario no disponible":
                MuestraMensaje.muestraAdvertencia(vista, resultado[1], resultado[0]);
                break;
            case "Error del Sistema":
                MuestraMensaje.muestraError(vista, resultado[1], resultado[0]);
                break;
            case "Error":
                MuestraMensaje.muestraError(vista, resultado[1], resultado[0]);
                break;
            
        }
        
    }
}
