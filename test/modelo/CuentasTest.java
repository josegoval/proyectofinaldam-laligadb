/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.conexion.CuentasBD;
import modelo.usuarios.TiposUsuario;
import modelo.usuarios.Usuario;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase modelo.Cuentas
 * @author Jose Manuel Gomez Martinez
 * @since 13/05/2020
 */
public class CuentasTest {
    
    public CuentasTest() {
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
     * Test of insertarCuenta method, of class Cuentas.
     */
    @Test
    public void testInsertarCuenta() {
        System.out.println("insertarCuenta");
        String usuario = "admin";
        String contrasenia = "admin";
        String tipo = "ADMIN";
        Cuentas instance = new Cuentas();
        String[] expResult = new String[]{"Usuario no disponible",
                    "Ese nombre de usuario no está disponible. Por favor, "
                                + "escoja uno diferente."};
        String[] result = instance.insertarCuenta(usuario, contrasenia, tipo);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of validarSesion method, of class Cuentas.
     */
    @Test
    public void testValidarSesion() {
        System.out.println("validarSesion");
        String user = "admin";
        String password = "admin";
        Cuentas instance = new Cuentas();
        String[] expResult = new String[]{"Exito", "Exito"};
        String[] result = instance.validarSesion(user, password);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of buscarUsuario method, of class Cuentas.
     */
    @Test
    public void testBuscarUsuario() {
        System.out.println("buscarUsuario");
        String user = "admin";
        Cuentas instance = new Cuentas();
        Usuario expResult = new Usuario("admin", "", TiposUsuario.ADMIN);
        Usuario result = instance.buscarUsuario(user);
        assertEquals(expResult.getUser(), result.getUser());
        assertEquals(expResult.getPassword(), result.getPassword());
        assertEquals(expResult.getTipo(), result.getTipo());
    }

    /**
     * Test of updatePassword method, of class Cuentas.
     */
    @Test
    public void testUpdatePassword() {
        System.out.println("updatePassword");
        String usuario = "admin";
        String contrasenia = "admin";
        CuentasBD tipo = CuentasBD.ADMIN;
        Cuentas instance = new Cuentas();
        String[] expResult = new String[]{"¡Contraseña cambiada!",
                    "Ha cambiado su contraseña."};
        String[] result = instance.updatePassword(usuario, contrasenia, tipo);
        assertArrayEquals(expResult, result);
    }
    
}
