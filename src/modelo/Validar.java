/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.MuestraMensaje;
import controlador.MuestraMensaje;
import modelo.usuarios.Seguridad;

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
    public static boolean longitudTexto(String texto, int min, int max ){
        return (texto.length() >= min && texto.length() <= max);
    }
    
    /**
     * Comprueba si todos los datos de un futbolista son correctos, exceptuando
     * el id.
     * @param nombre Nombre del futbolista.
     * @param apellido Apellido del futbolista.
     * @param nacionalidad Nacionalidad del futbolista.
     * @param nif NIF único del futbolista.
     * @param anio Año de nacimiento del futbolista.
     * @return Devuelve una array de 2 string, conteniendo en el primero el
     * titulo del mensaje, y en el segundo la descripción de este. <b>Si la
     * acción fuese exitosa devolvería "Exito" en el título (posicion 0).</b>
     */
    public static String[] validarDatosFutbolista(String nombre, String apellido,
            String nacionalidad, String nif, String anio) {
        int anioConvertido;
        
        try {
            anioConvertido = Integer.parseInt(anio);
        } catch (Exception e) {
            return new String[]{"Año Inválido", "Por favor introduzca solo"
                    + " numeros enteros."};
        }
        // nombre
        if (nombre.equals("") || tieneEspacios(nombre, 1)) {
            return new String[]{"Faltan datos.", "Introduzca un nombre sin mas "
                    + "de 1 espacio, por favor."};
        } else if (nombre.matches(".*\\d.*")) {
            return new String[]{"Error de nombre.", "Introduzca un nombre "
                    + "sin numeros, por favor."};
        } else if (!longitudTexto(nombre, 1, 40)) {
            return new String[]{"Error de nombre.", "Introduzca un nombre "
                    + "de longitud 1 a 40, por favor."};
        // apellidos
        } else if (apellido.equals("") || tieneEspacios(apellido, 1)) {
            return new String[]{"Faltan datos.", "Introduzca un apellido sin mas "
                    + "de 1 espacio, por favor."};
        } else if (apellido.matches(".*\\d.*")) {
            return new String[]{"Error de apellido.", "Introduzca un apellido "
                    + "sin numeros, por favor."};
        } else if (!longitudTexto(apellido, 1, 40)) {
            return new String[]{"Error de apellido.", "Introduzca un apellido "
                    + "de longitud 1 a 40, por favor."};
        // nacionalidad
        } else if (nacionalidad.equals("") || tieneEspacios(nacionalidad, 0)) {
            return new String[]{"Faltan datos.", "Introduzca una nacionalidad "
                    + "sin espacios, por favor."};
         } else if (nacionalidad.matches(".*\\d.*")) {
            return new String[]{"Error de nacionalidad.", "Introduzca una nacionalidad "
                    + "sin numeros, por favor."};
        } else if (!longitudTexto(nacionalidad, 1, 30)) {
            return new String[]{"Error de nacionalidad.", "Introduzca una nacionalidad "
                    + "de longitud 1 a 30, por favor."};
        // nif
        } else if (!longitudTexto(nif, 9, 9)) {
            return new String[]{"NIF erroneo.", "Introduzca un nif de longitud"
                    + " 9, por favor"};
        // anio
        } else if (!validarAnio(anioConvertido)) {
            return new String[]{"Fecha erronea.", "Los años deben comprenderse"
                    + " entre 1871 y 2099."};
        }
        
        return new String[]{"Exito", "Todos los datos son correctos."};
    }
    
    /**
     * Comprueba si todos los datos de un club son correctos, exceptuando
     * el id.
     * @param nombre Nombre único del club.
     * @param anio Año de creación del club.
     * @param estadio Estadio del club.
     * @return Devuelve una array de 2 string, conteniendo en el primero el
     * titulo del mensaje, y en el segundo la descripción de este. <b>Si la
     * acción fuese exitosa devolvería "Exito" en el título (posicion 0).</b>
     */
    public static String[] validarDatosClub(String nombre, String anio, String estadio) {
        int anioConvertido;
        
        try {
            anioConvertido = Integer.parseInt(anio);
        } catch (Exception e) {
            return new String[]{"Año Inválido", "Por favor introduzca solo"
                    + " numeros enteros."};
        }
        
        // nombre
        if (nombre.equals("") || tieneEspacios(nombre, 3)) {
            return new String[]{"Faltan datos.", "Introduzca un nombre sin mas "
                    + "de 3 espacios, por favor."};
        } else if (nombre.matches(".*\\d.*")) {
            return new String[]{"Error de nombre.", "Introduzca un nombre "
                    + "sin numeros, por favor."};
        } else if (!longitudTexto(nombre, 1, 50)) {
            return new String[]{"Error de nombre.", "Introduzca un nombre "
                    + "de longitud 1 a 50, por favor."};
        // estadio
        } else if (estadio.equals("") || tieneEspacios(estadio, 1)) {
            return new String[]{"Faltan datos.", "Introduzca una estadio "
                    + "sin mas de 1 espacio, por favor."};
         } else if (estadio.matches(".*\\d.*")) {
            return new String[]{"Error de estadio.", "Introduzca una estadio "
                    + "sin numeros, por favor."};
        } else if (!longitudTexto(estadio, 1, 50)) {
            return new String[]{"Error de estadio.", "Introduzca una estadio "
                    + "de longitud 1 a 50, por favor."};
        // anio
        } else if (!validarAnio(anioConvertido)) {
            return new String[]{"Fecha erronea.", "Los años deben comprenderse"
                    + " entre 1871 y 2099."};
        }
        
        return new String[]{"Exito", "Todos los datos son correctos."};
    }
    
    /**
     * Comprueba si todos los datos de asociar son correctos.
     * @param nifFutbolista Nif único del futbolista.
     * @param nombreClub Nombre único del club.
     * @param temporada Temporada de la asociación, solo la primera fecha.
     * @return Devuelve una array de 2 string, conteniendo en el primero el
     * titulo del mensaje, y en el segundo la descripción de este. <b>Si la
     * acción fuese exitosa devolvería "Exito" en el título (posicion 0).</b>
     */
    public static String[] validarAsociar(String nifFutbolista, String nombreClub,
            String temporada) {
        int anioConvertido;
        
        try {
            anioConvertido = Integer.parseInt(temporada);
        } catch (Exception e) {
            return new String[]{"Año Inválido", "Por favor introduzca solo"
                    + " numeros enteros."};
        }
        
        // nombreClub
        if (nombreClub.equals("") || tieneEspacios(nombreClub, 3)) {
            return new String[]{"Faltan datos.", "Introduzca un nombre sin mas "
                    + "de 3 espacios, por favor."};
        } else if (nombreClub.matches(".*\\d.*")) {
            return new String[]{"Error de nombre.", "Introduzca un nombre "
                    + "sin numeros, por favor."};
        } else if (!longitudTexto(nombreClub, 1, 50)) {
            return new String[]{"Error de nombre.", "Introduzca un nombre "
                    + "de longitud 1 a 50, por favor."};
        // nif
        } else if (!longitudTexto(nifFutbolista, 9, 9)) {
            return new String[]{"NIF erroneo.", "Introduzca un nif de longitud"
                    + " 9, por favor"};
        // anio
        } else if (!validarAnio(anioConvertido)) {
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
    
    /**
     * Analiza si la fecha se comprende entre 1869 y 2099.
     * @param anio Año a analizar.
     * @return true = es válida, false = no es válida
     */
    public static boolean validarAnio(int anio){
        return anio > 1870 && anio < 2100;
    }
    
    /**
     * Valida los datos del cambio de contraseña y responde con éxito o algún
     * error en los datos introducidos por parámetro: contraseña no correcta,
     * o no coincidente.
     * @param user Nomrbe único de usuario.
     * @param antigua Antigua contraseña.
     * @param nueva Nueva contraseña.
     * @param repetida Nueva contraseña repetida.
     * @return Devuelve una array de 2 string, conteniendo en el primero el
     * titulo del mensaje, y en el segundo la descripción de este. <b>Si la
     * acción fuese exitosa devolvería "Exito" en el título (posicion 0).</b>
     */
    public static String[] validarCambioContrasenia(String user, String antigua,
            String nueva, String repetida) {
        Cuentas cuentas = new Cuentas();
        if (!cuentas.validarSesion(user, antigua)[0].equals("Exito")) {
            return new String[]{"Contraseña no correcta", "Su antigua contraseña"
                    + " no coincide."};
        } else if (!Seguridad.validarContrasenia(nueva, repetida)) {
            return new String[]{"La nueva contraseña no coincide", "La contraseña"
                    + " nueva y repetida no coinciden."};
        }
        
        return new String[]{"Exito", "Ha cambiado su contraseña."};
    }
}
