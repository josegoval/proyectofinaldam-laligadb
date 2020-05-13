/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojos;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase modelo.pojos.Futbolista
 * @author Jose Manuel Gomez Martinez
 * @since 13/05/2020
 */
public class FutbolistaTest {
    
    public static Futbolista futbolistaTest;
    
    public FutbolistaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        futbolistaTest = new Futbolista(0, "inventado", "otro", 1990, "ninguna", 
                "98765432P");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getArrayAtributos method, of class Futbolista.
     */
    @Test
    public void testGetArrayAtributos() {
        System.out.println("getArrayAtributos");
        String[] expResult = new String[]{"0", "98765432P", "inventado", "otro",
            "1990", "ninguna"};
        String[] result = futbolistaTest.getArrayAtributos();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Futbolista.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 0;
        int result = futbolistaTest.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombre method, of class Futbolista.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        String expResult = "inventado";
        String result = futbolistaTest.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNombre method, of class Futbolista.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "nuevoNombre";
        futbolistaTest.setNombre(nombre);
        assertEquals(nombre, futbolistaTest.getNombre());
    }

    /**
     * Test of getApellido method, of class Futbolista.
     */
    @Test
    public void testGetApellido() {
        System.out.println("getApellido");
        String expResult = "otro";
        String result = futbolistaTest.getApellido();
        assertEquals(expResult, result);
    }

    /**
     * Test of setApellido method, of class Futbolista.
     */
    @Test
    public void testSetApellido() {
        System.out.println("setApellido");
        String apellido = "nuevoApellido";
        futbolistaTest.setApellido(apellido);
        assertEquals(apellido, futbolistaTest.getApellido());
    }

    /**
     * Test of getAnio_nacimiento method, of class Futbolista.
     */
    @Test
    public void testGetAnio_nacimiento() {
        System.out.println("getAnio_nacimiento");
        int expResult = 1990;
        int result = futbolistaTest.getAnio_nacimiento();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAnio_nacimiento method, of class Futbolista.
     */
    @Test
    public void testSetAnio_nacimiento() {
        System.out.println("setAnio_nacimiento");
        int anio_nacimiento = 1970;
        futbolistaTest.setAnio_nacimiento(anio_nacimiento);
        assertEquals(anio_nacimiento, futbolistaTest.getAnio_nacimiento());
    }

    /**
     * Test of getNacionalidad method, of class Futbolista.
     */
    @Test
    public void testGetNacionalidad() {
        System.out.println("getNacionalidad");
        String expResult = "ninguna";
        String result = futbolistaTest.getNacionalidad();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNacionalidad method, of class Futbolista.
     */
    @Test
    public void testSetNacionalidad() {
        System.out.println("setNacionalidad");
        String nacionalidad = "Española";
        futbolistaTest.setNacionalidad(nacionalidad);
        assertEquals(nacionalidad, futbolistaTest.getNacionalidad());
    }

    /**
     * Test of getNif method, of class Futbolista.
     */
    @Test
    public void testGetNif() {
        System.out.println("getNif");
        String expResult = "98765432P";
        String result = futbolistaTest.getNif();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNif method, of class Futbolista.
     */
    @Test
    public void testSetNif() {
        System.out.println("setNif");
        String nif = "12345678J";
        futbolistaTest.setNif(nif);
        assertEquals(nif, futbolistaTest.getNif());
    }
    
}
