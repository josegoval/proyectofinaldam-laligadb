/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
    
}
