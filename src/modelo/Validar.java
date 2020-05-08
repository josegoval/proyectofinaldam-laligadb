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
        
        if (nombre.equals("") || tieneEspacios(nombre, 1)) {
            return new String[]{"Faltan datos.", "Introduzca un nombre sin mas "
                    + "de 1 espacio, por favor."};
        } else if (nombre.matches(".*\\d.*")) {
            return new String[]{"Error de nombre.", "Introduzca un nombre "
                    + "sin numeros, por favor."};
        } else if (apellido.equals("") || tieneEspacios(apellido, 1)) {
            return new String[]{"Faltan datos.", "Introduzca un apellido sin mas "
                    + "de 1 espacio, por favor."};
        } else if (apellido.matches(".*\\d.*")) {
            return new String[]{"Error de apellido.", "Introduzca un apellido "
                    + "sin numeros, por favor."};
        } else if (nacionalidad.equals("") || tieneEspacios(nacionalidad, 0)) {
            return new String[]{"Faltan datos.", "Introduzca una nacionalidad "
                    + "sin mas de 1 espacio, por favor."};
         } else if (nacionalidad.matches(".*\\d.*")) {
            return new String[]{"Error de nacionalidad.", "Introduzca una nacionalidad "
                    + "sin numeros, por favor."};
        } else if (nif.length() < 9) {
            return new String[]{"NIF erroneo.", "Introduzca un nif de longitud"
                    + " 9, por favor"};
        } else if (anioConvertido <= 1870 || anioConvertido >= 2100) {
            return new String[]{"Fecha erronea.", "Los años deben comprenderse"
                    + " entre 1871 y 2099."};
        }
        
        return new String[]{"Exito", "Todos los datos son correctos."};
    }
    
    /**
     * Analiza si el texto tiene espacios.
     * @param texto Texto a analizar
     * @param max Numero maximo de espacios permitidos
     * @return true = el texto tiene espacios, false = el texto no contiene espacios
     */
    public static boolean tieneEspacios(String texto, int max) {
        int espacios = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (Character.isWhitespace(texto.charAt(i))) {
                espacios ++;
            }
            if (espacios > max) {
                return true;
            }
        }
        return false;
    }
}
