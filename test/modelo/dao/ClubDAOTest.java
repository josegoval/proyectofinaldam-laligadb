/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import modelo.conexion.CuentasBD;
import modelo.pojos.Club;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase modelo.dao.ClubDAO
 * @author Jose Manuel Gomez Martinez
 * @since 13/05/2020
 */
public class ClubDAOTest {
    
    public static ClubDAO instance;
    
    public ClubDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new ClubDAO();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of aniadirClubDAO method, of class ClubDAO.
     */
    @Test
    public void testAniadirClubDAO() {
        System.out.println("aniadirClubDAO");
        int id = 12;
        String nombre = "Ninguno";
        int anio_nacimiento = 1990;
        String estadio = "Un Estadio";
        instance.aniadirClubDAO(id, nombre, anio_nacimiento, estadio);
        assertEquals(instance.getClubs().size(), 1);
    }

    /**
     * Test of getTablaClubs method, of class ClubDAO.
     */
    @Test
    public void testGetTablaClubs() {
        System.out.println("getTablaClubs");
        // Hago uso de la cuenta que no puede hacer dicha acción para provocar
        // el error, de otra forma sería fructífero.
        CuentasBD cuenta = CuentasBD.LOGIN;
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.getTablaClubs(cuenta);
        assertEquals(expResult, result);
    }

    /**
     * Test of getComboBoxClubs method, of class ClubDAO.
     */
    @Test
    public void testGetComboBoxClubs() {
        System.out.println("getComboBoxClubs");
        // Hago uso de la cuenta que no puede hacer dicha acción para provocar
        // el error, de otra forma sería fructífero.
        CuentasBD cuenta = CuentasBD.LOGIN;
        DefaultComboBoxModel expResult = null;
        DefaultComboBoxModel result = instance.getComboBoxClubs(cuenta);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTablaMilitan method, of class ClubDAO.
     */
    @Test
    public void testGetTablaMilitan() {
        System.out.println("getTablaMilitan");
        // Hago uso de la cuenta que no puede hacer dicha acción para provocar
        // el error, de otra forma sería fructífero.
        CuentasBD cuenta = CuentasBD.LOGIN;
        int idClub = 1;
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.getTablaMilitan(idClub, cuenta);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertarClub method, of class ClubDAO.
     */
    @Test
    public void testInsertarClub() {
        System.out.println("insertarClub");
        // Provoco un error por año
        Club club = new Club("Real Madrid", 2200, "Calderón");
        CuentasBD cuenta = CuentasBD.ADMIN;
        String[] expResult = new String[]{"Año inválido", "Por favor, introduzca"
                    + " una fecha de creación entre 1871 y 2099."};
        String[] result = instance.insertarClub(club, cuenta);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of actualizarClub method, of class ClubDAO.
     */
    @Test
    public void testActualizarClub() {
        System.out.println("actualizarClub");
        Club club = new Club(1, "Real Madrid", 1950, "Calderón");
        CuentasBD cuenta = CuentasBD.ADMIN;
        String[] expResult = new String[]{"Exito",
                    "¡Has actualizado al club " + club.getNombre()
                        + " en la base de datos!."};
        String[] result = instance.actualizarClub(club, cuenta);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of eliminarClub method, of class ClubDAO.
     */
    @Test
    public void testEliminarClub() {
        System.out.println("eliminarClub");
        Club club = new Club(0, "no existo", 1950, "Calderón");
        CuentasBD cuenta = CuentasBD.ADMIN;
        String[] expResult = new String[]{"Equipo comprometido",
                    "Ese equipo ya tiene una asociación y por lo tanto no se"
                    + " puede eliminar."};
        String[] result = instance.eliminarClub(club, cuenta);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of selectClubs method, of class ClubDAO.
     */
    @Test
    public void testSelectClubs() {
        System.out.println("selectClubs");
        CuentasBD cuenta = CuentasBD.NORMAL;
        boolean expResult = true;
        boolean result = instance.selectClubs(cuenta);
        assertEquals(expResult, result);
    }

    /**
     * Test of asociar method, of class ClubDAO.
     */
    @Test
    public void testAsociar() {
        System.out.println("asociar");
        String nifFutbolista = "12341234L";
        String nombreClub = "no existe";
        int temporada = 1920;
        CuentasBD cuenta = CuentasBD.ADMIN;
        String[] expResult = new String[]{"Futbolista o club inexistente",
                    "El club o futbolista seleccionado ya no existe o introdujo"
                            + "mal sus datos."};
        String[] result = instance.asociar(nifFutbolista, nombreClub, temporada, cuenta);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of buscarMilitadoClub method, of class ClubDAO.
     */
    @Test
    public void testBuscarMilitadoClub() {
        System.out.println("buscarMilitadoClub");
        // Salvo por error de SQL interno o de conexion no debe de dar error.
        int idClub = 0;
        CuentasBD cuenta = CuentasBD.NORMAL;
        String[][] result = instance.buscarMilitadoClub(idClub, cuenta);
        assertNotNull(result);
    }
    
}
