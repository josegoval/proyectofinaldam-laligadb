/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Cuentas;
import modelo.usuarios.Usuario;
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
    private Cuentas modelo;
    
// CONSTRUCTORES
    /**
     * Constructor vacío que inicializa la vista y el modelo predeterminado
     * para el inicio del programa más ágil.
     */
    public Controlador_Login() {
        this.vista = new JFrame_Login();
        this.modelo = new Cuentas();
    }
    
    /**
     * Constructor que inicializa todos los atributos de la clase.
     * @param vista Vista a usar
     * @param modelo Modelo a usar
     */
    public Controlador_Login(JFrame_Login vista, Cuentas modelo) {
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
        vista.setLocationRelativeTo(null);
        vista.dialogoCrearUsuario.setLocationRelativeTo(vista);
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
        String user = vista.texto_usuario.getText();
        String[] resultado = modelo.validarSesion(user, vista.texto_contrasenia.getText());
        
        if (resultado[0].equals("Exito")) {
            arrancarApp(user);
        } else {
            MuestraMensaje.muestraError(vista, resultado[1], resultado[0]);
        }
        
    }
    
    /**
     * Cierra la vista actual de logeo, y carga la App (aplicación principal) 
     * completa en la nueva vista-modelo-controlador pasandole los datos
     * del usuarios a la misma.
     * @param user Nombre de usuario el cual arrancará la aplicación.
     */
    public void arrancarApp(String user) {
        Usuario usuarioLogeado = modelo.buscarUsuario(user);
        // Queda por rellenar al apertura.
        if (usuarioLogeado != null) {
            this.vista.dispose();
            new Controlador_App(usuarioLogeado);
        } else {
            MuestraMensaje.muestraError(vista, "Ha habido un error al "
                    + "conectarse con tu usuario a la base de datos del "
                    + "sistema. Intentelo más tarde o contacte con un "
                    + "administrador.", "Error al conectar");
        }
    }
    
    /**
     * Abre la el jDialog respectivo para crear una cuenta.
     */
    public void nuevaCuenta(){
        vista.dialogoCrearUsuario.setSize(432, 261);
        vista.dialogoCrearUsuario.setLocationRelativeTo(vista);
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
        
        if (resultado[0].equals("¡Usuario Creado!")) {
            MuestraMensaje.muestraExito(vista.dialogoCrearUsuario, resultado[1],
                    resultado[0]);
            vista.dialogoCrearUsuario.dispose();
        } else if(resultado[0].equals("Usuario no disponible")) {
            MuestraMensaje.muestraAdvertencia(vista.dialogoCrearUsuario, 
                    resultado[1], resultado[0]);
        } else {
            MuestraMensaje.muestraError(vista.dialogoCrearUsuario, 
                    resultado[1], resultado[0]);
        }
        
    }
}
