/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.usuarios.TiposUsuario;
import modelo.usuarios.Usuario;

/**
 * Main de inicio de la aplicacion.
 * 
 * @author Jose
 * @since 06/05/2020
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        new Controlador_Login().iniciar();
        //new Controlador_App(new Usuario("None", "", TiposUsuario.valueOf("ADMIN"))).iniciar();
    }
    
}
