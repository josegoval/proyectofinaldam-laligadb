/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Clase encargada de mostrar los mensajes de alerta y exito (JOptioPanel) de
 * forma automatizada.
 * 
 * @author Jose
 * @since 06/05/2020
 */
public class MuestraMensaje {
   /**
     * Muestra un mensaje informativo de exito (INFORMATION_MESSAGE) al usuario.
     * @param componente Componente al que alojar.
     * @param mensaje Mensaje a mostrar.
     * @param titulo Titulo del mensaje.
     */
    public static void muestraExito(Component componente, String mensaje, String titulo) {
        JOptionPane.showMessageDialog(componente, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }    

    /**
     * Muestra un mensaje de error (ERROR_MESSAGE) al usuario.
     * @param componente Componente al que alojar.
     * @param mensaje Mensaje a mostrar.
     * @param titulo Titulo del mensaje.
     */
    public static void muestraError(Component componente, String mensaje, String titulo) {
        JOptionPane.showMessageDialog(componente, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }    
    
    /**
     * Muestra un mensaje de warning (WARNING_MESSAGE) al usuario.
     * @param componente Componente al que alojar.
     * @param mensaje Mensaje a mostrar.
     * @param titulo Titulo del mensaje.
     */
    public static void muestraAdvertencia(Component componente, String mensaje, String titulo) {
        JOptionPane.showMessageDialog(componente, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
    }    
    
    /**
     * Muestra un mensaje de confirmación con "sí" o "no". Y devuelve el 
     * resultado escogido.
     * @param componente Componente al que alojar.
     * @param mensaje Mensaje a mostrar.
     * @param titulo Titulo del mensaje.
     * @return true = si , false = no
     */
    public static boolean muestraConfirmación(Component componente, String mensaje, String titulo){
        int opcion = JOptionPane.showConfirmDialog(componente, mensaje, titulo, 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == 0) {
            return true;
        } else {
            return false;
        }
    }
}
