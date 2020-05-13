/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import modelo.conexion.CuentasBD;
import modelo.pojos.Futbolista;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase modelo.dao.FutbolistaDAO
 * @author Jose Manuel Gomez Martinez
 * @since 13/05/2020
 */
public class FutbolistaDAOTest {
    
    public static FutbolistaDAO instance;
    
    public FutbolistaDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new FutbolistaDAO();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of aniadirFutbolistaDAO method, of class FutbolistaDAO.
     */
    @Test
    public void testAniadirFutbolistaDAO() {
        System.out.println("aniadirFutbolistaDAO");
        int id = 0;
        String nombre = "Nombre";
        String apellido = "Apellido";
        int anio_nacimiento = 1970;
        String nacionalidad = "Española";
        String nif = "48765432P";
        instance.aniadirFutbolistaDAO(id, nombre, apellido, anio_nacimiento, nacionalidad, nif);
        assertEquals(instance.getFutbolistas().size(), 1);
    }

    /**
     * Test of getTablaFutbolistas method, of class FutbolistaDAO.
     */
    @Test
    public void testGetTablaFutbolistas() {
        System.out.println("getTablaFutbolistas");
        // Hago uso de la cuenta que no puede hacer dicha acción para provocar
        // el error, de otra forma sería fructífero.
        CuentasBD cuenta = CuentasBD.LOGIN;
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.getTablaFutbolistas(cuenta);
        assertEquals(expResult, result);
    }

    /**
     * Test of getComboBoxFutbolistas method, of class FutbolistaDAO.
     */
    @Test
    public void testGetComboBoxFutbolistas() {
        System.out.println("getComboBoxFutbolistas");
        // Hago uso de la cuenta que no puede hacer dicha acción para provocar
        // el error, de otra forma sería fructífero.
        CuentasBD cuenta = CuentasBD.LOGIN;
        DefaultComboBoxModel expResult = null;
        DefaultComboBoxModel result = instance.getComboBoxFutbolistas(cuenta);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTablaMilitan method, of class FutbolistaDAO.
     */
    @Test
    public void testGetTablaMilitan() {
        System.out.println("getTablaMilitan");
        // Hago uso de la cuenta que no puede hacer dicha acción para provocar
        // el error, de otra forma sería fructífero.
        CuentasBD cuenta = CuentasBD.LOGIN;
        int idFutbolista = 1;
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.getTablaMilitan(idFutbolista, cuenta);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertarFutbolista method, of class FutbolistaDAO.
     */
    @Test
    public void testInsertarFutbolista() {
        System.out.println("insertarFutbolista");
        // Provoco un error por año
        Futbolista futbolista = new Futbolista("Repetido", "Repetido", 2200, 
                "Repetido", "38888878P");
        CuentasBD cuenta = CuentasBD.ADMIN;
        String[] expResult = new String[]{"Año inválido", "Por favor, introduzca"
                    + " una fecha de nacimiento entre 1871 y 2099."};
        String[] result = instance.insertarFutbolista(futbolista, cuenta);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of actualizarFutbolista method, of class FutbolistaDAO.
     */
    @Test
    public void testActualizarFutbolista() {
        System.out.println("actualizarFutbolista");
        Futbolista futbolista = new Futbolista(1, "Manu", "Manutazo", 1970, 
                "Española", "38888878P");
        CuentasBD cuenta = CuentasBD.ADMIN;
        String[] expResult = new String[]{"Exito",
                    "¡Has actualizado al futbolista " + futbolista.getNif()
                        + " en la base de datos!."};
        String[] result = instance.actualizarFutbolista(futbolista, cuenta);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of eliminarFutbolista method, of class FutbolistaDAO.
     */
    @Test
    public void testEliminarFutbolista() {
        System.out.println("eliminarFutbolista");
        Futbolista futbolista = new Futbolista(0, "No existo", "No existo", 
                1970, "No existo", "No existo");
        CuentasBD cuenta = CuentasBD.ADMIN;
        String[] expResult = new String[]{"Error",
                    "¡El futbolista que intenta eliminar no existe!"};
        String[] result = instance.eliminarFutbolista(futbolista, cuenta);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of selectFutbolistas method, of class FutbolistaDAO.
     */
    @Test
    public void testSelectFutbolistas() {
        System.out.println("selectFutbolistas");
        CuentasBD cuenta = CuentasBD.NORMAL;
        boolean expResult = true;
        boolean result = instance.selectFutbolistas(cuenta);
        assertEquals(expResult, result);
    }

    /**
     * Test of buscarMilitadoFutbolista method, of class FutbolistaDAO.
     */
    @Test
    public void testBuscarMilitadoFutbolista() {
        System.out.println("buscarMilitadoFutbolista");
        int idFutbolista = 0;
        CuentasBD cuenta = CuentasBD.NORMAL;
        String[][] result = instance.buscarMilitadoFutbolista(idFutbolista, cuenta);
        assertNotNull(result);
    }

    /**
     * Test of getClubs method, of class FutbolistaDAO.
     */
    @Test
    public void testGetClubs() {
        System.out.println("getClubs");
        List<Futbolista> result = instance.getFutbolistas();
        assertNotNull(result);
    }
    
}
