/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.usuarios;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;

/**
 * Clase encargada del hashing de la contraseña en su respectivo SHA-256, y
 * de hacer algunas validaciones referentes a la misma.
 * 
 * @author Jose
 * @since 04/05/2020
 */
public class Seguridad {
    
    /**
     * Transforma el dato de entrada, en un texto de 64 caracteres, dado por
     * el hashing de un SHA-256.
     * @param password Texto a transformar (hashing).
     * @return String de 64 caracteres transformado por un SHA-256.
     */
    public static String hashPassSHA256(String password) {
        MessageDigest md;
        byte[] digestedBytes;
        String hashed;
        
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            digestedBytes = md.digest();
            return transformarEnHexadecimal(digestedBytes);
        } catch (NoSuchAlgorithmException nse) {
            System.err.println("No se encontró el algoritmo hash.");
        } catch (Exception e) {
            System.err.println("Error al hashear la contraseña.");
        }
        
        return null;
    }
    
    /**
     * Transforma en hexadecimal un array de byte. <br> Para ello, mueve primero
     * los 4 primeros bits válidos de la izquierda, a la derecha 4 posiciones
     * y lo enmascara con 0xf (1111) y  los guarda en un StringBuffer. Después, 
     * enmascara los 4 bits restantes de ese byte con el mismo procedimiento 
     * (0xf)y los vuelve a unir al Buffer. Repitiendo este proceso hasta haber
     * recorrido por completo el array de byte.
     * @param hash Array de byte a transformar en hexadecimal.
     * @return String transformado. Para este caso, utilizando un SHA-256, 
     * devuelve una cadena de 64 caracteres.
     */
    private static String transformarEnHexadecimal(byte[] hash) {
        StringBuffer resultado = new StringBuffer();
        
        for (int i = 0; i < hash.length; i++) {
            resultado.append(Integer.toHexString((hash[i]>>>4) & 0xf));
            resultado.append(Integer.toHexString(hash[i] & 0xf));
        }
        
        return resultado.toString();
    }
    
    /**
     * Metodo que comprueba si dos contraseñas son iguales.
     * @param a Texto a.
     * @param b Texto b.
     * @return true = son iguales, false = no son iguales.
     */
    public static boolean validarContrasenia(String a, String b){
        return a.equals(b);
    }
}
