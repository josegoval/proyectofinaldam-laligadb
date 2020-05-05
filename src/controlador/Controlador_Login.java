/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import modelo.Modelo_Login;
import vista.JFrame_Login;

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
        this.vista.btnIniciarSesion.setActionCommand("iniciar_sesion");
        
        // 1.- Seguir enlazando los campos restantes...
        // 2.- Crear el JDIALOG que registra al usuario con sus warnins porque
        // el nombre ya este registrado
    }
    
}
