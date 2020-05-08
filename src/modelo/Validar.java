/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.MuestraMensaje;
import controlador.MuestraMensaje;

/**
 * Clase dedicada a la validación de todos los posibles parámetros de entrada.
 * 
 * @author Jose
 * @since 05/05/2020
 */
public class Validar {
    /**
     * Comprueba si el texto introducido por parámetro tiene la longitud
     * necesaria.
     * @param texto Texto a analizar.
     * @param max Longitud máxima inclusiva.
     * @param min Longitud mínima inclusiva.
     * @return true = está entre max y min, false = no está entre max y min.
     */
    public static boolean longitudTexto(String texto, int max, int min){
        return (texto.length() >= min && texto.length() <= max);
    }
    
    /**
     * Comprueba si todos los datos de un futbolista son correctos, exceptuando
     * el id.
     * @return Devuelve una array de 2 string, conteniendo en el primero el
     * titulo del mensaje, y en el segundo la descripción de este. <b>Si la
     * acción fuese exitosa devolvería "Exito" en el título (posicion 0).</b>
     */
    public static String[] validarDatosFutbolista (String nombre, String apellido,
            String nacionalidad, String nif, String anio) {
        int anioConvertido;
        
        try {
            anioConvertido = Integer.parseInt(anio);
        } catch (Exception e) {
            return new String[]{"Año Inválido", "Por favor introduzca solo"
                    + " numeros enteros."};
        }
        
        if (nombre.equals("")) {
            return new String[]{"Faltan datos.", "Introduzca un nombre, "
                    + "por favor."};
        } else if (apellido.equals("")) {
            return new String[]{"Faltan datos.", "Introduzca un apellido,"
                    + " por favor."};
        } else if (nacionalidad.equals("")) {
            return new String[]{"Faltan datos.", "Introduzca una nacionalidad,"
                    + " por favor."};
        } else if (nif.length() < 9) {
            return new String[]{"NIF erroneo.", "Introduzca un nif de longitud"
                    + " 9, por favor"};
        } else if (anioConvertido <= 1870 || anioConvertido >= 2100) {
            return new String[]{"Fecha erronea.", "Los años deben comprenderse"
                    + " entre 1871 y 2099."};
        }
        
        return new String[]{"Exito", "Todos los datos son correctos."};
    }
}
