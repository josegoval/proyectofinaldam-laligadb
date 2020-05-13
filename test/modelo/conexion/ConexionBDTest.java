/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase modelo.conexion.ConexionBD
 * @author Jose Manuel Gomez Martinez
 * @since 13/05/2020
 */
public class ConexionBDTest {
    
    public ConexionBDTest() {
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
     * Test of getConexion method, of class ConexionBD.
     */
    @Test
    public void testGetConexion() {
        System.out.println("getConexion");
        CuentasBD tipo = CuentasBD.ADMIN;
        Connection result = ConexionBD.getConexion(tipo);
        assertNotNull(result);
    }

    /**
     * Test of cerrar method, of class ConexionBD.
     */
    @Test
    public void testCerrar_Connection() {
        System.out.println("cerrar");
        Connection conexion = ConexionBD.getConexion(CuentasBD.LOGIN);
        boolean expResult = true;
        boolean result = ConexionBD.cerrar(conexion);
        assertEquals(expResult, result);
    }

    /**
     * Test of cerrar method, of class ConexionBD.
     */
    @Test
    public void testCerrar_PreparedStatement() {
        System.out.println("cerrar");
        PreparedStatement pstm = null;
        try {
            pstm = ConexionBD.getConexion(CuentasBD.LOGIN)
                    .prepareStatement("SELECT * FROM usuarios");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBDTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean expResult = true;
        boolean result = ConexionBD.cerrar(pstm);
        assertEquals(expResult, result);
    }

    /**
     * Test of cerrar method, of class ConexionBD.
     */
    @Test
    public void testCerrar_CallableStatement() {
        System.out.println("cerrar");
        CallableStatement cstmt = null;
        try {
            cstmt = ConexionBD.getConexion(CuentasBD.ADMIN)
                    .prepareCall("{CALL insertClub(?,?,?,?)}");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBDTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean expResult = true;
        boolean result = ConexionBD.cerrar(cstmt);
        assertEquals(expResult, result);
    }

    /**
     * Test of cerrar method, of class ConexionBD.
     */
    @Test
    public void testCerrar_ResultSet() {
        System.out.println("cerrar");
        ResultSet rs = null;
        try {
            rs = ConexionBD.getConexion(CuentasBD.LOGIN)
                    .prepareStatement("SELECT * FROM usuarios").executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBDTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean expResult = true;
        boolean result = ConexionBD.cerrar(rs);
        assertEquals(expResult, result);
    }
    
}
