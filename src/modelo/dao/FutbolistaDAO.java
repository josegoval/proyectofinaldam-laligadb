/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import modelo.Validar;
import modelo.conexion.ConexionBD;
import modelo.conexion.CuentasBD;
import modelo.pojos.Futbolista;
import modelo.usuarios.Seguridad;

/**
 * FutbolistaDAO recoge y contiene todos los metodos para sacar futbolistas de
 * una base de datos y pasarselos al controlador en formato entendible por
 * éste.  Así como insertar, eliminar y modificar.
 * 
 * @author Jose
 * @since 07/05/2020
 */
public class FutbolistaDAO {
// ATRIBUTOS
    private List<Futbolista> futbolistas;
    
// CONSTRUCTORES
    /**
     * Constructor vacio que inicializa todos los atributos de la clase.
     */
    public FutbolistaDAO() {
        futbolistas = new ArrayList<>();
    }
    
// METODOS
    /**
     * Crea y añade un futbolista al arraylist de esta misma clase.
     * @param id Id único del futbolista.
     * @param nombre Nombre del futbolista.
     * @param apellido Apellido del futbolista.
     * @param anio_nacimiento Año de nacimiento del futbolista.
     * @param nacionalidad Nacionalidad del futbolista.
     * @param nif NIF único del futbolista.
     */
    public void aniadirFutbolistaDAO(int id, String nombre, String apellido,
            int anio_nacimiento, String nacionalidad, String nif) {
        futbolistas.add(new Futbolista(id, nombre, apellido, anio_nacimiento, 
                nacionalidad, nif));
    }
    
    /**
     * Consulta a la base de datos todos los futbolistas, y devuelve un 
     * DefaultTableModel con todos los datos de los futbolistas construidos.
     * @param cuenta Cuenta de la base de datos a la que se conectará. El 
     * usuario posee dichos datos.
     * @return DefaultTableModel con todos los futbolistas registrados en 
     * la base de datos. Si hubiese algún error al traerse los datos de la
     * base de datos devolvería null.
     */
    public DefaultTableModel getTablaFutbolistas(CuentasBD cuenta) {
        DefaultTableModel tabla;
        String[] encabezado;
        // Casoística de error...
        if (!selectFutbolistas(cuenta)) {
            return null;
        } 
        // Casoística exitosa...
        tabla = new DefaultTableModel();
        encabezado = new String[]{"ID","NIF", "Nombre", "Apellido", 
            "A. Nacimiento", "Nacionalidad"};
        tabla.setColumnIdentifiers(encabezado);
        futbolistas.stream()
                .forEach(futbolista -> tabla.addRow(futbolista.getArrayAtributos()));
        return tabla;
    }
    
    /**
     * Consulta a la base de datos todos los futbolistas, y devuelve un 
     * DefaultComboBoxModel con todos los NIF de los futbolistas...
     * @param cuenta Cuenta de la base de datos a la que se conectará. El 
     * usuario posee dichos datos.
     * @return DefaultComboBoxModel con todos los NIF de futbolistas registrados
     * en la base de datos. Si hubiese algún error al traerse los datos de la
     * base de datos devolvería null.
     */
    public DefaultComboBoxModel getComboBoxFutbolistas(CuentasBD cuenta) {
        DefaultComboBoxModel comboBox;
        // Casoística de error...
        if (!selectFutbolistas(cuenta)) {
            return null;
        } 
        // Casoística exitosa...
        comboBox = new DefaultComboBoxModel();
        futbolistas.stream()
                .forEach(futbolista -> comboBox.addElement(futbolista.getNif()));
        return comboBox;
    }
    
    /**
     * Consulta a la base de datos todos los clubs en los que ha participado
     * un futbolista y devuelve una tabla con los datos.
     * @param idFutbolista ID único del futbolista que buscar.
     * @param cuenta Cuenta de la base de datos a la que se conectará. El 
     * usuario posee dichos datos.
     * @return DefaultTableModel con todos los datos militan registrados en 
     * la base de datos para ese futbolista. Si hubiese algún error al traerse 
     * los datos de la base de datos devolvería null.
     */
    public DefaultTableModel getTablaMilitan(int idFutbolista, CuentasBD cuenta) {
        DefaultTableModel tabla;
        String[] encabezado;
        String[][] resultado = buscarMilitadoFutbolista(idFutbolista, cuenta);
        // Casoística de error...
        if (resultado == null) {
            return null;
        } 
        // Casoística exitosa...
        tabla = new DefaultTableModel();
        encabezado = new String[]{"ID Club", "Nombre Club", "Temporada"};
        tabla.setColumnIdentifiers(encabezado);
        for (String[] datos : resultado) {
            tabla.addRow(datos);
        }
        
        return tabla;
    }
    
// CRUD BASICO
    /**
     * Inserta en la base de datos al futbolista con la cuenta dada. <br>
     * <i>Las respuestas de errores engloban un mal año, un nif repetido y 
     * errores del sistema y base de datos. El resto de comprobaciones han
     * de asegurarse en el controlador, de lo contrario dará un error, pero
     * no especificará de qué tipo de dato.</i>
     * @param futbolista Futbolista a añadir.
     * @param cuenta Cuenta de la base de datos a la que se conectará. El 
     * usuario posee dichos datos.
     * @return Devuelve un array de 2 Strings, siendo el primero el titulo del
     * mensaje, y el segundo el contenido de la respuesta. <b>Si la inserción
     * fuese exitosa, devolverá en la posición 0, el título "Exito".</b>
     */
    public String[] insertarFutbolista(Futbolista futbolista, CuentasBD cuenta) {
        Connection con = null;
        PreparedStatement pstm = null;
        String[] resultado;
        
        try {
            con = ConexionBD.getConexion(cuenta);
            pstm = con.prepareStatement("INSERT INTO futbolistas (nombre, "
                    + "apellido, anio_nacimiento, nacionalidad, nif) "
                    + "VALUES(?,?,?,?,?)");
            pstm.setString(1, futbolista.getNombre());
            pstm.setString(2, futbolista.getApellido());
            pstm.setInt(3, futbolista.getAnio_nacimiento());
            pstm.setString(4, futbolista.getNacionalidad());
            pstm.setString(5, futbolista.getNif());
            
            if (pstm.executeUpdate() >= 1) {
                resultado = new String[]{"Exito",
                    "¡Has añadido al futbolista " + futbolista.getNif()
                        + " a la base de datos!."};
            } else {
                resultado = new String[]{"Error",
                    "¡No se ha podido añadir su futbolista!"};
            }
        } catch (MySQLIntegrityConstraintViolationException sqle) {
            sqle.printStackTrace();
            // Puede fallar o por fecha...
            if (!Validar.validarAnio(futbolista.getAnio_nacimiento())) {
                resultado = new String[]{"Año inválido", "Por favor, introduzca"
                    + " una fecha de nacimiento entre 1871 y 2099."};
            } else {
                // O porque el nif ya exista
                resultado = new String[]{"Campos repetidos",
                    "Ese NIF de futbolista ya está registrado. Por favor,"
                        + "escoja uno diferente o compruebe si el "
                        + "futbolista que está intentando añadir, ya existe."};
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = new String[]{"Error del Sistema",
                    "¡Hay un error con la base de datos! Por favor, "
                        + "comuniquese con un administrador para "
                        + "solucionar el problema o intentelo más tarde."};
        } finally {
            ConexionBD.cerrar(con);
            ConexionBD.cerrar(pstm);
        }
        
        return resultado;
    }

    /**
     * Actualiza en la base de datos al futbolista con la cuenta dada. <br>
     * <i>Las respuestas de errores engloban un mal año, un nif repetido y 
     * errores del sistema y base de datos. El resto de comprobaciones han
     * de asegurarse en el controlador, de lo contrario dará un error, pero
     * no especificará de qué tipo de dato.</i>
     * @param futbolista Futbolista que actualizar, con los nuevos datos.
     * @param cuenta Cuenta de la base de datos a la que se conectará. El 
     * usuario posee dichos datos.
     * @return Devuelve un array de 2 Strings, siendo el primero el titulo del
     * mensaje, y el segundo el contenido de la respuesta. <b>Si la 
     * actualización fuese exitosa, devolverá en la posición 0, el título 
     * "Exito".</b>
     */
    public String[] actualizarFutbolista(Futbolista futbolista, CuentasBD cuenta) {
        Connection con = null;
        PreparedStatement pstm = null;
        String[] resultado;
        
        try {
            con = ConexionBD.getConexion(cuenta);
            pstm = con.prepareStatement("UPDATE futbolistas SET nombre=?, "
                    + "apellido=?, anio_nacimiento=?, nacionalidad=?, nif=? "
                    + "where id=?");
            pstm.setString(1, futbolista.getNombre());
            pstm.setString(2, futbolista.getApellido());
            pstm.setInt(3, futbolista.getAnio_nacimiento());
            pstm.setString(4, futbolista.getNacionalidad());
            pstm.setString(5, futbolista.getNif());
            pstm.setInt(6, futbolista.getId());
            
            if (pstm.executeUpdate() >= 1) {
                resultado = new String[]{"Exito",
                    "¡Has actualizado al futbolista " + futbolista.getNif()
                        + " en la base de datos!."};
            } else {
                resultado = new String[]{"Error",
                    "¡No se ha podido actualizar su futbolista!"};
            }
        } catch (MySQLIntegrityConstraintViolationException sqle) {
            sqle.printStackTrace();
            // Puede fallar o por fecha...
            if (!Validar.validarAnio(futbolista.getAnio_nacimiento())) {
                resultado = new String[]{"Año inválido", "Por favor, introduzca"
                    + " una fecha de nacimiento entre 1871 y 2099."};
            } else {
                // O porque el nif ya exista
                resultado = new String[]{"Campos repetidos",
                    "Ese NIF de futbolista ya está registrado. Por favor,"
                        + "escoja uno diferente o compruebe si el "
                        + "futbolista que es´ta intentando añadir, ya existe."};
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = new String[]{"Error del Sistema",
                    "¡Hay un error con la base de datos! Por favor, "
                        + "comuniquese con un administrador para "
                        + "solucionar el problema o intentelo más tarde."};
        } finally {
            ConexionBD.cerrar(con);
            ConexionBD.cerrar(pstm);
        }
        
        return resultado;
    }
    
    /**
     * Elimina en la base de datos al futbolista con la cuenta dada. <br>
     * @param futbolista Futbolista que eliminar.
     * @param cuenta Cuenta de la base de datos a la que se conectará. El 
     * usuario posee dichos datos.
     * @return Devuelve un array de 2 Strings, siendo el primero el titulo del
     * mensaje, y el segundo el contenido de la respuesta. <b>Si la eliminación
     * fuese exitosa, devolverá en la posición 0, el título "Exito".</b>
     */
    public String[] eliminarFutbolista(Futbolista futbolista, CuentasBD cuenta){
        Connection con = null;
        PreparedStatement pstm = null;
        String[] resultado;
        
        try {
            con = ConexionBD.getConexion(cuenta);
            pstm = con.prepareStatement("DELETE FROM futbolistas where id=?");
            pstm.setInt(1, futbolista.getId());
            
            if (pstm.executeUpdate() >= 1) {
                resultado = new String[]{"Exito",
                    "¡Has eliminado al futbolista " + futbolista.getNif()
                        + " de la base de datos!."};
            } else {
                resultado = new String[]{"Error",
                    "¡El futbolista que intenta eliminar no existe!"};
            }
        } catch (MySQLIntegrityConstraintViolationException sqle) {
            sqle.printStackTrace();
            // Puede fallar o por fecha...
            resultado = new String[]{"Jugador comprometido",
                    "Ese jugador ya tiene una asociación y por lo tanto no se"
                    + " puede eliminar."};
        } catch (Exception e) {
            e.printStackTrace();
            resultado = new String[]{"Error del Sistema",
                    "¡Hay un error con la base de datos! Por favor, "
                        + "comuniquese con un administrador para "
                        + "solucionar el problema o intentelo más tarde."};
        } finally {
            ConexionBD.cerrar(con);
            ConexionBD.cerrar(pstm);
        }
        
        return resultado;
    }
    
    /**
     * Saca todos los futbolistas de la base de datos y los establece en el
     * arraylist de esta clase.
     * @param cuenta Cuenta de la base de datos a la que se conectará. El 
     * usuario posee dichos datos.
     * @return true = exito, false = ocurrio algún error.
     */
    public boolean selectFutbolistas(CuentasBD cuenta){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            con = ConexionBD.getConexion(cuenta);
            pstm = con.prepareStatement("SELECT * FROM futbolistas");
            rs = pstm.executeQuery();
            
            // Reinicio los futbolistas ya registrados
            this.futbolistas = new  ArrayList<>();
            while (rs.next()) {              
                aniadirFutbolistaDAO(rs.getInt("id"), rs.getString("nombre"), 
                        rs.getString("apellido"), rs.getInt("anio_nacimiento"),
                        rs.getString("nacionalidad"), rs.getString("nif"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.futbolistas = null;
        } finally {
            ConexionBD.cerrar(con);
            ConexionBD.cerrar(pstm);
            ConexionBD.cerrar(rs);
        }
        
        if (this.futbolistas == null) {
            return false;
        } else {
            return true;
        }
    }

// RELACION MILITAN
    /**
     * Busca en la base de datos los clubs en los que ha militado un futbolista
     * y devuelve un String[][] con todos los datos en el siguiente
     * orden: idClub, NombreClub, Temporada.
     * @param idFutbolista Futbolista que buscar.
     * @param cuenta Cuenta de la base de datos a la que se conectará. El 
     * usuario posee dichos datos.
     * @return String[][] con todos los datos en el siguiente
     * orden: idClub, NombreClub, Temporada.
     */
    public String[][] buscarMilitadoFutbolista(int idFutbolista, CuentasBD cuenta) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String[][] resultado;
        int valorTemporada;
        
        try {
            con = ConexionBD.getConexion(cuenta);
            pstm = con.prepareStatement("SELECT c.id,c.nombre, m.temporada FROM "
                    + "militan AS m JOIN clubs AS c ON m.club=c.id WHERE"
                    + " m.futbolista=?");
            pstm.setInt(1, idFutbolista);
            rs = pstm.executeQuery();
            
            // Me situo al final
            rs.last();
            // Defino la longitud del array
            resultado = new String[rs.getRow()][3];
            // Vuelvo al principio
            rs.beforeFirst();
            // Construyo el array [posicion][idClub, NombreClub, Temporada]
            for (int i = 0; rs.next(); i++) {
                resultado[i][0] = Integer.toString(rs.getInt("id"));
                resultado[i][1] = rs.getString("nombre");
                valorTemporada = rs.getInt("temporada");
                resultado[i][2] = Integer.toString(valorTemporada) + 
                        "/" + Integer.toString(valorTemporada + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = null;
        } finally {
            ConexionBD.cerrar(con);
            ConexionBD.cerrar(pstm);
            ConexionBD.cerrar(rs);
        }
        
        return resultado;
    }
    
// GETTERS
    public List<Futbolista> getFutbolistas(){
        return futbolistas;
    }
    
}
