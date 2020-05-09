/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojos;

import java.util.Vector;

/**
 * Clase básica Club.
 * 
 * @author Jose
 * @since 04/05/2020
 */
public class Club {
// ATRIBUTOS
    /**
     * id único del club.
     */
    private final int id;
    /**
     * Nombre único del club.
     */
    private String nombre;
    /**
     * Año de creación del club.
     */
    private int anio_creacion;
    /**
     * Estadio "casa" del club.
     */
    private String estadio;

// CONSTRUCTORES
    /**
     * Constructor que inicializa todos los atributos de la clase.
     * @param id id único del club.
     * @param nombre Nombre único del club.
     * @param anio_creacion Año de creación del club.
     * @param estadio Estadio "casa" del club.
     */
    public Club(int id, String nombre, int anio_creacion, String estadio) {
        this.id = id;
        this.nombre = nombre;
        this.anio_creacion = anio_creacion;
        this.estadio = estadio;
    }
    
    /**
     * Constructor que inicializa todos los atributos de la clase menos el id 
     * que se asigna a 0 por convención. Es exclusivo para el uso del insert.
     * @param nombre Nombre único del club.
     * @param anio_creacion Año de creación del club.
     * @param estadio Estadio "casa" del club.
     */
    public Club(String nombre, int anio_creacion, String estadio) {
        this.id = 0;
        this.nombre = nombre;
        this.anio_creacion = anio_creacion;
        this.estadio = estadio;
    }
    
// METODOS
    /**
     * Construye un array de String con todos los datos del club
     * @return Un array de string con el siguiente orden: id, nombre, año
     * de creación, estadio.
     */
    public String[] getArrayAtributos() {
        return new String[]{Integer.toString(id), nombre, 
            Integer.toString(anio_creacion), estadio};
    }
    
// SETTERS & GETTERS
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio_creacion() {
        return anio_creacion;
    }

    public void setAnio_creacion(int anio_creacion) {
        this.anio_creacion = anio_creacion;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

}
