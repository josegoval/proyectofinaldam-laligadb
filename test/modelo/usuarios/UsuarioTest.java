/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.usuarios;

import modelo.conexion.CuentasBD;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase modelo.usuarios.Usuario
 * @author Jose Manuel Gomez Martinez
 * @since 13/05/2020
 */
public class UsuarioTest {
    
    public UsuarioTest() {
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
     * Test of getTipoCuentaBD method, of class Usuario.
     */
    @Test
    public void testGetTipoCuentaBD() {
        System.out.println("getTipoCuentaBD");
        Usuario instance = new Usuario("noImporta", "", TiposUsuario.ADMIN);
        CuentasBD expResult = CuentasBD.ADMIN;
        CuentasBD result = instance.getTipoCuentaBD();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser method, of class Usuario.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        Usuario instance = new Usuario("noImporta", "", TiposUsuario.ADMIN);
        String expResult = "noImporta";
        String result = instance.getUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUser method, of class Usuario.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        String user = "nuevoUser";
        Usuario instance = new Usuario("noImporta", "", TiposUsuario.ADMIN);
        instance.setUser(user);
        assertEquals(user, instance.getUser());
    }

    /**
     * Test of getPassword method, of class Usuario.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Usuario instance = new Usuario("noImporta", "abcd", TiposUsuario.ADMIN);
        String expResult = "abcd";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class Usuario.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "nuevaPass";
        Usuario instance = new Usuario("noImporta", "abcd", TiposUsuario.ADMIN);
        instance.setPassword(password);
        assertEquals(password, instance.getPassword());
    }

    /**
     * Test of getTipo method, of class Usuario.
     */
    @Test
    public void testGetTipo() {
        System.out.println("getTipo");
        Usuario instance = new Usuario("noImporta", "abcd", TiposUsuario.ADMIN);
        TiposUsuario expResult = TiposUsuario.ADMIN;
        TiposUsuario result = instance.getTipo();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTipo method, of class Usuario.
     */
    @Test
    public void testSetTipo() {
        System.out.println("setTipo");
        TiposUsuario tipo = TiposUsuario.NORMAL;
        Usuario instance = new Usuario("noImporta", "abcd", TiposUsuario.ADMIN);
        instance.setTipo(tipo);
        assertEquals(tipo, instance.getTipo());
    }
    
}
