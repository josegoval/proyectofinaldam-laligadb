/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import modelo.Validar;
import modelo.conexion.ConexionBD;
import modelo.conexion.CuentasBD;
import modelo.pojos.Club;

/**
 * ClubDAO recoge y contiene todos los metodos para sacar clubs de
 * una base de datos y pasarselos al controlador en formato entendible por
 * éste. Así como insertar, eliminar y modificar.
 * 
 * @author Jose
 * @since 07/05/2020
 */
public class ClubDAO {
// ATRIBUTOS
    private List<Club> clubs;
    
// CONSTRUCTORES
    /**
     * Constructor vacio que inicializa todos los atributos de la clase.
     */
    public ClubDAO() {
        clubs = new ArrayList<>();
    }
    
// METODOS
    /**
     * Crea y añade un club al arraylist de esta misma clase.
     * @param id Id único del club.
     * @param nombre Nombre único del club.
     * @param anio_nacimiento Año de creación del club.
     * @param estadio Estadio del club.
     */
    public void aniadirClubDAO(int id, String nombre, int anio_nacimiento,
            String estadio) {
        clubs.add(new Club(id, nombre, anio_nacimiento, estadio));
    }
    
    /**
     * Consulta a la base de datos todos los clubs, y devuelve un 
     * DefaultTableModel con todos los datos de los clubs construidos.
     * @param cuenta Cuenta de la base de datos a la que se conectará. El 
     * usuario posee dichos datos.
     * @return DefaultTableModel con todos los clubs registrados en 
     * la base de datos. Si hubiese algún error al traerse los datos de la
     * base de datos devolvería null.
     */
    public DefaultTableModel getTablaClubs(CuentasBD cuenta) {
        DefaultTableModel tabla;
        String[] encabezado;
        // Casoística de error...
        if (!selectClubs(cuenta)) {
            return null;
        } 
        // Casoística exitosa...
        tabla = new DefaultTableModel();
        encabezado = new String[]{"ID","Nombre", "Año de Creación", "Estadio"}; 
        tabla.setColumnIdentifiers(encabezado);
        clubs.stream()
                .forEach(club -> tabla.addRow(club.getArrayAtributos()));
        return tabla;
    }
    
    /**
     * Consulta a la base de datos todos los clubs, y devuelve un 
     * DefaultComboBoxModel con todos los Nombre de los clubs...
     * @param cuenta Cuenta de la base de datos a la que se conectará. El 
     * usuario posee dichos datos.
     * @return DefaultComboBoxModel con todos los nombres de clubs registrados
     * en la base de datos. Si hubiese algún error al traerse los datos de la
     * base de datos devolvería null.
     */
    public DefaultComboBoxModel getComboBoxClubs(CuentasBD cuenta) {
        DefaultComboBoxModel comboBox;
        // Casoística de error...
        if (!selectClubs(cuenta)) {
            return null;
        } 
        // Casoística exitosa...
        comboBox = new DefaultComboBoxModel();
        clubs.stream()
                .forEach(club -> comboBox.addElement(club.getNombre()));
        return comboBox;
    }
    
     /**
     * Consulta a la base de datos todos los futbolistas que han militado en 
     * un club y devuelve una tabla con los datos.
     * @param idClub ID único del club que buscar.
     * @param cuenta Cuenta de la base de datos a la que se conectará. El 
     * usuario posee dichos datos.
     * @return DefaultTableModel con todos los datos militan registrados en 
     * la base de datos para ese club. Si hubiese algún error al traerse 
     * los datos de la base de datos devolvería null.
     */
    public DefaultTableModel getTablaMilitan(int idClub, CuentasBD cuenta) {
        DefaultTableModel tabla;
        String[] encabezado;
        String[][] resultado = buscarMilitadoClub(idClub, cuenta);
        // Casoística de error...
        if (resultado == null) {
            return null;
        } 
        // Casoística exitosa...
        tabla = new DefaultTableModel();
        encabezado = new String[]{"ID", "NIF", "Nombre", "Apellido", 
            "A.Nacimiento", "Nacionalidad", "Temporada"};
        tabla.setColumnIdentifiers(encabezado);
        for (String[] datos : resultado) {
            tabla.addRow(datos);
        }
        
        return tabla;
    }
    
// CRUD BASICO
    /**
     * Inserta en la base de datos al club con la cuenta dada. <br>
     * <i>Las respuestas de errores engloban un mal año, un nombre repetido y 
     * errores del sistema y base de datos. El resto de comprobaciones han
     * de asegurarse en el controlador, de lo contrario dará un error, pero
     * no especificará de qué tipo de dato.</i>
     * @param club club a añadir.
     * @param cuenta Cuenta de la base de datos a la que se conectará. El 
     * usuario posee dichos datos.
     * @return Devuelve un array de 2 Strings, siendo el primero el titulo del
     * mensaje, y el segundo el contenido de la respuesta. <b>Si la inserción
     * fuese exitosa, devolverá en la posición 0, el título "Exito".</b>
     */
    public String[] insertarClub(Club club, CuentasBD cuenta) {
        Connection con = null;
        CallableStatement cstmt = null;
        String[] resultado;
        
        try {
            con = ConexionBD.getConexion(cuenta);
            cstmt = con.prepareCall("{call insertClub(?,?,?,?)}");
            cstmt.setString(1, club.getNombre());
            cstmt.setInt(2, club.getAnio_creacion());
            cstmt.setString(3, club.getEstadio());
            cstmt.registerOutParameter(4, Types.BOOLEAN);
            cstmt.execute();
            
            if (cstmt.getBoolean(4)) {
                resultado = new String[]{"Exito",
                    "¡Has añadido al club " + club.getNombre()
                        + " a la base de datos!."};
            } else {
                // Puede fallar o por fecha...
                if (!Validar.validarAnio(club.getAnio_creacion())) {
                resultado = new String[]{"Año inválido", "Por favor, introduzca"
                    + " una fecha de nacimiento entre 1871 y 2099."};
                } else {
                // O porque el nombre ya exista
                resultado = new String[]{"Campos repetidos",
                    "Ese nombre de club ya está registrado. Por favor,"
                        + "escoja uno diferente o compruebe si el "
                        + "club que está intentando añadir, ya existe."};
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = new String[]{"Error del Sistema",
                    "¡Hay un error con la base de datos! Por favor, "
                        + "comuniquese con un administrador para "
                        + "solucionar el problema o intentelo más tarde."};
        } finally {
            ConexionBD.cerrar(con);
            ConexionBD.cerrar(cstmt);
        }
        
        return resultado;
    }

    /**
     * Actualiza en la base de datos al club con la cuenta dada. <br>
     * <i>Las respuestas de errores engloban un mal año, un club repetido y 
     * errores del sistema y base de datos. El resto de comprobaciones han
     * de asegurarse en el controlador, de lo contrario dará un error, pero
     * no especificará de qué tipo de dato.</i>
     * @param club Club que actualizar, con los nuevos datos.
     * @param cuenta Cuenta de la base de datos a la que se conectará. El 
     * usuario posee dichos datos.
     * @return Devuelve un array de 2 Strings, siendo el primero el titulo del
     * mensaje, y el segundo el contenido de la respuesta. <b>Si la 
     * actualización fuese exitosa, devolverá en la posición 0, el título 
     * "Exito".</b>
     */
    public String[] actualizarClub(Club club, CuentasBD cuenta) {
        Connection con = null;
        CallableStatement cstmt = null;
        String[] resultado;
        
        try {
            con = ConexionBD.getConexion(cuenta);
            cstmt = con.prepareCall("{call updateClub(?,?,?,?,?)}");
            cstmt.setInt(1, club.getId());
            cstmt.setString(2, club.getNombre());
            cstmt.setInt(3, club.getAnio_creacion());
            cstmt.setString(4, club.getEstadio());
            cstmt.registerOutParameter(5, Types.BOOLEAN);
            cstmt.execute();
            
            if (cstmt.getBoolean(5)) {
                resultado = new String[]{"Exito",
                    "¡Has actualizado al club " + club.getNombre()
                        + " en la base de datos!."};
            } else {
                // Puede fallar o por fecha...
                if (!Validar.validarAnio(club.getAnio_creacion())) {
                resultado = new String[]{"Año inválido", "Por favor, introduzca"
                    + " una fecha de nacimiento entre 1871 y 2099."};
                } else {
                // O porque el nombre ya exista
                resultado = new String[]{"Campos repetidos",
                    "Ese nombre de club ya está registrado. Por favor,"
                        + "escoja uno diferente o compruebe si el "
                        + "club que está intentando añadir, ya existe."};
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = new String[]{"Error del Sistema",
                    "¡Hay un error con la base de datos! Por favor, "
                        + "comuniquese con un administrador para "
                        + "solucionar el problema o intentelo más tarde."};
        } finally {
            ConexionBD.cerrar(con);
            ConexionBD.cerrar(cstmt);
        }
        
        return resultado;
    }
    
    /**
     * Elimina en la base de datos al club con la cuenta dada. <br>
     * @param club Club que eliminar.
     * @param cuenta Cuenta de la base de datos a la que se conectará. El 
     * usuario posee dichos datos.
     * @return Devuelve un array de 2 Strings, siendo el primero el titulo del
     * mensaje, y el segundo el contenido de la respuesta. <b>Si la eliminación
     * fuese exitosa, devolverá en la posición 0, el título "Exito".</b>
     */
    public String[] eliminarClub(Club club, CuentasBD cuenta){
        Connection con = null;
        CallableStatement cstmt = null;
        String[] resultado;
        
        try {
            con = ConexionBD.getConexion(cuenta);
            cstmt = con.prepareCall("{call deleteClub(?,?)}");
            cstmt.setInt(1, club.getId());
            cstmt.registerOutParameter(2, Types.BOOLEAN);
            cstmt.execute();
            
            if (cstmt.getBoolean(2)) {
                resultado = new String[]{"Exito",
                    "¡Has eliminado al club " + club.getNombre()
                        + " de la base de datos!."};
            } else {
                resultado = new String[]{"Error",
                    "¡El club que intenta eliminar no existe!"};
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = new String[]{"Error del Sistema",
                    "¡Hay un error con la base de datos! Por favor, "
                        + "comuniquese con un administrador para "
                        + "solucionar el problema o intentelo más tarde."};
        } finally {
            ConexionBD.cerrar(con);
            ConexionBD.cerrar(cstmt);
        }
        
        return resultado;
    }
    
    /**
     * Saca todos los clubs de la base de datos y los establece en el
     * arraylist de esta clase.
     * @param cuenta Cuenta de la base de datos a la que se conectará. El 
     * usuario posee dichos datos.
     * @return true = exito, false = ocurrio algún error.
     */
    public boolean selectClubs(CuentasBD cuenta){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            con = ConexionBD.getConexion(cuenta);
            pstm = con.prepareStatement("SELECT * FROM clubs");
            rs = pstm.executeQuery();
            
            // Reinicio los futbolistas ya registrados
            this.clubs = new  ArrayList<>();
            while (rs.next()) {              
                aniadirClubDAO(rs.getInt("id"), rs.getString("nombre"), 
                        rs.getInt("anio_creacion"), rs.getString("estadio"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.clubs = null;
        } finally {
            ConexionBD.cerrar(con);
            ConexionBD.cerrar(pstm);
            ConexionBD.cerrar(rs);
        }
        
        if (this.clubs == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Inserta en la base de datos militan la asociación con la cuenta dada. <br>
     * <i>Las respuestas de errores engloban un mal año, valores repetidos y 
     * errores del sistema y base de datos. El resto de comprobaciones han
     * de asegurarse en el controlador, de lo contrario dará un error, pero
     * no especificará de qué tipo de dato.</i>
     * 
     * @param nifFutbolista NIF único del futbolista.
     * @param nombreClub Nombre único del club.
     * @param temporada Temporada a asociar.
     * @param cuenta Cuenta de la base de datos a la que se conectará. El 
     * usuario posee dichos datos.
     * @return Devuelve un array de 2 Strings, siendo el primero el titulo del
     * mensaje, y el segundo el contenido de la respuesta. <b>Si la inserción
     * fuese exitosa, devolverá en la posición 0, el título "Exito".</b>
     */
    public String[] asociar(String nifFutbolista, String nombreClub, 
            int temporada, CuentasBD cuenta) {
        Connection con = null;
        CallableStatement cstmt = null;
        String[] resultado = null;
        
        try {
            con = ConexionBD.getConexion(cuenta);
            cstmt = con.prepareCall("{call asociar(?,?,?,?)}");
            cstmt.setString(1, nifFutbolista);
            cstmt.setString(2, nombreClub);
            cstmt.setInt(3, temporada);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            cstmt.execute();
            
            switch (cstmt.getString(4)) {
                case "Exito":
                    resultado = new String[]{"Exito",
                    "¡Has asociado al club " + nombreClub + " y " + nifFutbolista
                        + " en la base de datos!."};
                    break;
                    
                case "Futbolista o club inexistente":
                    resultado = new String[]{"Futbolista o club inexistente",
                    "El club o futbolista seleccionado ya no existe o introdujo"
                            + "mal sus datos."};
                    break;
                    
                case "Entrada duplicada":
                    resultado = new String[]{"Entrada duplicada",
                    "Ya existe esa relación futbolista-club-temporada."};
                    break;
                    
                case "Error de datos":
                    // Puede fallar o por fecha...
                    if (!Validar.validarAnio(temporada)) {
                    resultado = new String[]{"Año inválido", "Por favor, introduzca"
                        + " una fecha de nacimiento entre 1871 y 2099."};
                    } else {
                    // O porque la asociación ya exista
                    resultado = new String[]{"Campos repetidos",
                        "Existe algún error de datos, por favor, revise los"
                            + "parámetros o contacte con un administrador."};
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = new String[]{"Error del Sistema",
                    "¡Hay un error con la base de datos! Por favor, "
                        + "comuniquese con un administrador para "
                        + "solucionar el problema o intentelo más tarde."};
        } finally {
            ConexionBD.cerrar(con);
            ConexionBD.cerrar(cstmt);
        }
        
        return resultado;
    }
    
// RELACION MILITAN
    /**
     * Busca en la base de datos los futbolistas que hayan militado en el club
     * y devuelve un String[][] con todos los datos en el siguiente
     * orden: idFutbolista, nifFutbolista, NombreFutbolista, ApellidoFutbolista, 
     * AñoDeNacimientoFutbolista, Nacionalidad, Temporada.
     * @param idClub ID único del club que buscar.
     * @param cuenta Cuenta de la base de datos a la que se conectará. El 
     * usuario posee dichos datos.
     * @return String[][] con todos los datos en el siguiente
     * orden: idFutbolista, nifFutbolista, NombreFutbolista, ApellidoFutbolista, 
     * AñoDeNacimientoFutbolista, Nacionalidad, Temporada.
     */
    public String[][] buscarMilitadoClub(int idClub, CuentasBD cuenta) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String[][] resultado;
        int valorTemporada;
        
        try {
            con = ConexionBD.getConexion(cuenta);
            pstm = con.prepareStatement("SELECT f.*, m.temporada FROM "
                    + "militan AS m JOIN futbolistas AS f ON m.futbolista=f.id "
                    + "WHERE m.club=?");
            pstm.setInt(1, idClub);
            rs = pstm.executeQuery();
            
            // Me situo al final
            rs.last();
            // Defino la longitud del array
            resultado = new String[rs.getRow()][7];
            // Vuelvo al principio
            rs.beforeFirst();
            // Construyo el array [posicion][idFutbolista, nifFutbolista, 
            // NombreFutbolista, ApellidoFutbolista, AñoDeNacimientoFutbolista, 
            // Nacionalidad, Temporada]
            for (int i = 0; rs.next(); i++) {
                resultado[i][0] = Integer.toString(rs.getInt("id"));
                resultado[i][1] = rs.getString("nif");
                resultado[i][2] = rs.getString("nombre");
                resultado[i][3] = rs.getString("apellido");
                resultado[i][4] = Integer.toString(rs.getInt("anio_nacimiento"));
                resultado[i][5] = rs.getString("nacionalidad");
                valorTemporada = rs.getInt("temporada");
                resultado[i][6] = Integer.toString(valorTemporada) + 
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
    
}
