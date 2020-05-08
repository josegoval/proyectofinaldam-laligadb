/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojos;

/**
 * Clase básica futbolista.
 * 
 * @author Jose Manuel Gómez Martínez
 * @since 04/05/2020
 */
public class Futbolista {
// ATRIBUTOS
    /**
     * id único del futbolista.
     */
    private final int id;
    /**
     * Nombre del futbolista.
     */
    private String nombre;
    /**
     * Apellido del futbolista.
     */
    private String apellido;
    /**
     * Año de nacimiento del futbolista.
     */
    private int anio_nacimiento;
    /**
     * Nacionalidad del futbolista.
     */
    private String nacionalidad;
    /**
     * NIF del futbolista.
     */
    private String nif;

// CONSTRUCTORES
    /**
     * Constructor que inicializa todos los atributos de la clase.
     * @param id id unico del futbolista.
     * @param nombre Nombre del futbolista.
     * @param apellido Apellido del futbolista.
     * @param anio_nacimiento Año de nacimiento del futbolista.
     * @param nacionalidad Nacionalidad del futbolista.
     * @param nif NIF del futbolista.
     */
    public Futbolista(int id, String nombre, String apellido, int anio_nacimiento, String nacionalidad, String nif) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.anio_nacimiento = anio_nacimiento;
        this.nacionalidad = nacionalidad;
        this.nif = nif;
    }
    
    /**
     * Constructor que inicializa todos los atributos de la clase menos el id 
     * que se asigna a 0 por convención. Es exclusivo para el uso del insert.
     * @param nombre Nombre del futbolista.
     * @param apellido Apellido del futbolista.
     * @param anio_nacimiento Año de nacimiento del futbolista.
     * @param nacionalidad Nacionalidad del futbolista.
     * @param nif NIF del futbolista.
     */
    public Futbolista(String nombre, String apellido, int anio_nacimiento, String nacionalidad, String nif) {
        this.id = 0;
        this.nombre = nombre;
        this.apellido = apellido;
        this.anio_nacimiento = anio_nacimiento;
        this.nacionalidad = nacionalidad;
        this.nif = nif;
    }
    
// METODOS
    /**
     * Construye un array de String con todos los datos del futbolista.
     * @return Un array de string con el siguiente orden: id, nif, nombre,
     * apellido, año de nacimiento, nacionalidad.
     */
    public String[] getArrayAtributos() {
        return new String[]{Integer.toString(id), nif, nombre, apellido, 
            Integer.toString(anio_nacimiento), nacionalidad};
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getAnio_nacimiento() {
        return anio_nacimiento;
    }

    public void setAnio_nacimiento(int anio_nacimiento) {
        this.anio_nacimiento = anio_nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }
    
}
