/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Cuentas;
import modelo.usuarios.TiposUsuario;
import modelo.usuarios.Usuario;
import vista.jframes.JFrame_App;

/**
 * Controlador de la App. <br>
 * Contiene el modelo, la vista, y el usuario con el cual se logeó que 
 * mediante este controlador, modificará la vista.
 * 
 * @author Jose
 * @since 06/05/2020
 */
public class Controlador_App implements ActionListener {
// ATRIBUTOS
    private JFrame_App vista;
    private Cuentas cuentas;
    private Usuario usuario;
    
// CONSTRUCTORES

    public Controlador_App(Usuario usuario) {
        this.vista = new JFrame_App();
        this.cuentas = new Cuentas();
        this.usuario = usuario;
        iniciar();
    }
    
    /**
     * Enlaza la vista y el controlador para darle funcionalidad, y establece
     * aquellos parámetros visuales según el tipo de usuario.
     */
    public void iniciar(){
        vista.setTitle("La Liga DB - " + usuario.getUser() + " [" 
                + usuario.getTipo().name() + "]");
        vista.setVisible(true);
        
        if (usuario.getTipo() == TiposUsuario.NORMAL) {
            // Referente al panel de futbolistas...
            vista.jPanel_futbolistas.btn_aniadir.setVisible(false);
            vista.jPanel_futbolistas.btn_modificar.setVisible(false);
            vista.jPanel_futbolistas.btn_asociar.setVisible(false);
            vista.jPanel_futbolistas.btn_eliminar.setVisible(false);
            // Referente al panel de clubs...
            vista.jPanel_clubs.btn_aniadir.setVisible(false);
            vista.jPanel_clubs.btn_modificar.setVisible(false);
            vista.jPanel_clubs.btn_asociar.setVisible(false);
            vista.jPanel_clubs.btn_eliminar.setVisible(false);
        } else {
            // Referente al panel de futbolistas...
            vista.jPanel_futbolistas.btn_aniadir.addActionListener(this);
            vista.jPanel_futbolistas.btn_aniadir.setActionCommand("futbolistas_aniadir");
            vista.jPanel_futbolistas.btn_modificar.addActionListener(this);
            vista.jPanel_futbolistas.btn_modificar.setActionCommand("futbolistas_modificar");
            vista.jPanel_futbolistas.btn_asociar.addActionListener(this);
            vista.jPanel_futbolistas.btn_asociar.setActionCommand("futbolistas_asociar");
            vista.jPanel_futbolistas.btn_eliminar.addActionListener(this);
            vista.jPanel_futbolistas.btn_eliminar.setActionCommand("futbolistas_eliminar");
            // Referente al panel de clubs...
            vista.jPanel_futbolistas.btn_aniadir.addActionListener(this);
            vista.jPanel_futbolistas.btn_aniadir.setActionCommand("clubs_aniadir");
            vista.jPanel_futbolistas.btn_modificar.addActionListener(this);
            vista.jPanel_futbolistas.btn_modificar.setActionCommand("clubs_modificar");
            vista.jPanel_futbolistas.btn_asociar.addActionListener(this);
            vista.jPanel_futbolistas.btn_asociar.setActionCommand("clubs_asociar");
            vista.jPanel_futbolistas.btn_eliminar.addActionListener(this);
            vista.jPanel_futbolistas.btn_eliminar.setActionCommand("clubs_eliminar");
        }
        
        // Para todos los demas...
        // Referente al panel de futbolistas...
        vista.jPanel_futbolistas.btn_buscar.addActionListener(this);
        vista.jPanel_futbolistas.btn_buscar.setActionCommand("futbolistas_buscar");
        vista.jPanel_futbolistas.btn_actualizar.addActionListener(this);
        vista.jPanel_futbolistas.btn_actualizar.setActionCommand("futbolistas_actualizar");
        // Referente al panel de clubss...
        vista.jPanel_clubs.btn_buscar.addActionListener(this);
        vista.jPanel_clubs.btn_buscar.setActionCommand("clubs_buscar");
        vista.jPanel_clubs.btn_actualizar.addActionListener(this);
        vista.jPanel_clubs.btn_actualizar.setActionCommand("clubs_actualizar");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "futbolistas_aniadir":
                
                break;
                
            case "futbolistas_modificar":
                
                break;
                
            case "futbolistas_asociar":
                
                break;
                
            case "futbolistas_eliminar":
                
                break;
                
            case "futbolistas_buscar":
                
                break;
                
            case "futbolistas_actualizar":
                break;
                
            case "clubs_aniadir":
                break;
                
            case "clubs_modificar":
                break;
                
            case "clubs_asociar":
                break;
                
            case "clubs_eliminar":
                break;
                
            case "clubs_buscar":
                break;

            case "clubs_actualizar":
                break;
        }
    }
    
    
}
