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
 * Pruebas unitarias para la clase modelo.pojos.Club
 * @author Jose Manuel Gomez Martinez
 * @since 13/05/2020
 */
public class ClubTest {
    
    public static Club clubTest;
    
    public ClubTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        clubTest = new Club(0, "inventado", 1990, "su estadio");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getArrayAtributos method, of class Club.
     */
    @Test
    public void testGetArrayAtributos() {
        System.out.println("getArrayAtributos");
        String[] expResult = new String[]{"0", "inventado", "1990", "su estadio"};
        String[] result = clubTest.getArrayAtributos();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Club.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 0;
        int result = clubTest.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombre method, of class Club.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        String expResult = "inventado";
        String result = clubTest.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNombre method, of class Club.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "nuevoNombre";
        clubTest.setNombre(nombre);
        assertEquals(nombre, clubTest.getNombre());
    }

    /**
     * Test of getAnio_creacion method, of class Club.
     */
    @Test
    public void testGetAnio_creacion() {
        System.out.println("getAnio_creacion");
        assertEquals(1990, clubTest.getAnio_creacion());
    }

    /**
     * Test of setAnio_creacion method, of class Club.
     */
    @Test
    public void testSetAnio_creacion() {
        System.out.println("setAnio_creacion");
        clubTest.setAnio_creacion(1970);
        assertEquals(1970, clubTest.getAnio_creacion());
    }

    /**
     * Test of getEstadio method, of class Club.
     */
    @Test
    public void testGetEstadio() {
        System.out.println("getEstadio");
        String expResult = "su estadio";
        String result = clubTest.getEstadio();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEstadio method, of class Club.
     */
    @Test
    public void testSetEstadio() {
        System.out.println("setEstadio");
        String estadio = "nuevoEstadio";
        clubTest.setEstadio(estadio);
        assertEquals(estadio, clubTest.getEstadio());
    }

}
