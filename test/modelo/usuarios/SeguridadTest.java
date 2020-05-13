/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.usuarios;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase modelo.usuarios.Seguridad
 * @author Jose Manuel Gomez Martinez
 * @since 13/05/2020
 */
public class SeguridadTest {
    
    public SeguridadTest() {
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
     * Test of hashPassSHA256 method, of class Seguridad.
     */
    @Test
    public void testHashPassSHA256() {
        System.out.println("hashPassSHA256");
        String password = "contra";
        String expResult = "2270e73a86e507f7a99d98e739a62f96ec812c1a19b37a0db27785e620518566";
        String result = Seguridad.hashPassSHA256(password);
        assertEquals(expResult, result);
    }

    /**
     * Test of validarContrasenia method, of class Seguridad.
     */
    @Test
    public void testValidarContrasenia() {
        System.out.println("validarContrasenia");
        String a = "2270e73a86e507f7a99d98e739a62f96ec812c1a19b37a0db27785e620518566";
        String b = "2270e73a86e507f7a99d98e739a62f96ec812c1a19b37a0db27785e620518566";
        boolean expResult = true;
        boolean result = Seguridad.validarContrasenia(a, b);
        assertEquals(expResult, result);
    }
    
}
