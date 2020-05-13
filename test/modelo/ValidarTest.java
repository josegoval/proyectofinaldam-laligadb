/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase modelo.Validar
 * @author Jose Manuel Gomez Martinez
 * @since 13/05/2020
 */
public class ValidarTest {
    
    public ValidarTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of longitudTexto method, of class Validar.
     */
    @Test
    public void testLongitudTexto() {
        System.out.println("longitudTexto");
        String texto = "1234";
        int min = 1;
        int max = 5;
        boolean expResult = true;
        boolean result = Validar.longitudTexto(texto, min, max);
        assertEquals(expResult, result);
    }

    /**
     * Test of validarDatosFutbolista method, of class Validar.
     */
    @Test
    public void testValidarDatosFutbolista() {
        System.out.println("validarDatosFutbolista");
        String nombre = "pepito";
        String apellido = "pepin";
        String nacionalidad = "Española";
        String nif = "12345678A";
        String anio = "1970";
        String[] expResult = new String[]{"Exito","Todos los datos son correctos."};
        String[] result = Validar.validarDatosFutbolista(nombre, apellido, nacionalidad, nif, anio);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of validarDatosClub method, of class Validar.
     */
    @Test
    public void testValidarDatosClub() {
        System.out.println("validarDatosClub");
        String nombre = "elClub";
        String anio = "2000";
        String estadio = "Gran Estadio";
        String[] expResult =new String[]{"Exito","Todos los datos son correctos."};
        String[] result = Validar.validarDatosClub(nombre, anio, estadio);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of validarAsociar method, of class Validar.
     */
    @Test
    public void testValidarAsociar() {
        System.out.println("validarAsociar");
        String nifFutbolista = "48765432P";
        String nombreClub = "elClub";
        String temporada = "2015";
        String[] expResult = new String[]{"Exito","Todos los datos son correctos."};
        String[] result = Validar.validarAsociar(nifFutbolista, nombreClub, temporada);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of tieneEspacios method, of class Validar.
     */
    @Test
    public void testTieneEspacios() {
        System.out.println("tieneEspacios");
        String texto = "Esto da error";
        int max = 1;
        // true porque se pasa de espacios
        boolean expResult = true;
        boolean result = Validar.tieneEspacios(texto, max);
        assertEquals(expResult, result);
    }

    /**
     * Test of validarAnio method, of class Validar.
     */
    @Test
    public void testValidarAnio() {
        System.out.println("validarAnio");
        int anio = 2200;
        boolean expResult = false;
        boolean result = Validar.validarAnio(anio);
        assertEquals(expResult, result);
    }

    /**
     * Test of validarCambioContrasenia method, of class Validar.
     */
    @Test
    public void testValidarCambioContrasenia() {
        System.out.println("validarCambioContrasenia");
        String user = "admin";
        String antigua = "admin";
        // Coloco la misma porque es el usuario de test, no deberia dar problema.
        String nueva = "admin";
        String repetida = "admin";
        String[] expResult = new String[]{"Exito", "Ha cambiado su contraseña."};
        String[] result = Validar.validarCambioContrasenia(user, antigua, nueva, repetida);
        assertArrayEquals(expResult, result);
    }
    
}
