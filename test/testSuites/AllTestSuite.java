/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testSuites;

import modelo.CuentasTest;
import modelo.ValidarTest;
import modelo.conexion.ConexionBDTest;
import modelo.dao.ClubDAOTest;
import modelo.dao.FutbolistaDAOTest;
import modelo.pojos.ClubTest;
import modelo.pojos.FutbolistaTest;
import modelo.usuarios.SeguridadTest;
import modelo.usuarios.UsuarioTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Pruebas unitarias para todas las clases de test del modelo
 * @author Jose Manuel Gomez Martinez
 * @since 13/05/2020
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    ConexionBDTest.class,
    ClubDAOTest.class,
    FutbolistaDAOTest.class,
    ClubTest.class,
    FutbolistaTest.class,
    SeguridadTest.class,
    UsuarioTest.class,
    CuentasTest.class,
    ValidarTest.class,
})
public class AllTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
