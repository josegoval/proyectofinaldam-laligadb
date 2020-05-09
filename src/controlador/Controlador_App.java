/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import modelo.Cuentas;
import modelo.Validar;
import modelo.conexion.CuentasBD;
import modelo.dao.ClubDAO;
import modelo.dao.FutbolistaDAO;
import modelo.pojos.Club;
import modelo.pojos.Futbolista;
import modelo.usuarios.TiposUsuario;
import modelo.usuarios.Usuario;
import vista.jframes.JFrame_App;

/**
 * Controlador de la App. <br>
 * Contiene el modelo, la vista, y el usuario con el cual se logeó que 
 * mediante este controlador, modificará la vista. <br>
 * <b>Para buscar rapido los apartados busque por "TODO FUTBOLISTAS", "TODO CLUBS"
 * , "TODO CUENTAS" o "METODOS DE ESTILO". Esto te situará en todos los metodos 
 * referentes a los mismos.</b> <br><i>(Recuerde que según el id, puede hacer
 * CRTL + click para ir al metodo seleccionado)</i>.
 * 
 * @author Jose
 * @since 06/05/2020
 */
public class Controlador_App implements ActionListener {
// ATRIBUTOS
    private JFrame_App vista;
    private Cuentas cuentas;
    private Usuario usuarioLogeado;
    private FutbolistaDAO futbolistas;
    private ClubDAO clubs;
    
// CONSTRUCTORES
    /**
     * Constructor que inicializa todos los atributos de la clase y relaciona
     * la vista y el controlador.
     * @param usuarioLogeado Usuario con el que se logeará la aplicación.
     */
    public Controlador_App(Usuario usuarioLogeado) {
        this.vista = new JFrame_App();
        this.cuentas = new Cuentas();
        this.usuarioLogeado = usuarioLogeado;
        this.futbolistas = new FutbolistaDAO();
        this.clubs = new ClubDAO();
        iniciar();
    }
    
    /**
     * Enlaza la vista y el controlador para darle funcionalidad, y establece
     * aquellos parámetros visuales según el tipo de usuario.
     */
    public void iniciar(){
        // Cambios en vista
        vista.setTitle("La Liga DB - " + usuarioLogeado.getUser() + " [" 
                + usuarioLogeado.getTipo().name() + "]");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        // Cambios en la localizacion de los dialogos.
        vista.dialogoFutbolistaAniadir.setLocationRelativeTo(null);
        vista.dialogoFutbolistaModificar.setLocationRelativeTo(null);
        vista.dialogoClubAniadir.setLocationRelativeTo(null);
        vista.dialogoClubModificar.setLocationRelativeTo(null);
        vista.dialogoAsociar.setLocationRelativeTo(null);
        
        // Enlace controlador-vista
        if (usuarioLogeado.getTipo() == TiposUsuario.NORMAL) {
            // Referente al panel de futbolistas...
            vista.jPanel_futbolistas.btn_aniadir.setVisible(false);
            vista.jPanel_futbolistas.btn_modificar.setVisible(false);
            vista.jPanel_futbolistas.btn_asociar.setVisible(false);
            vista.jPanel_futbolistas.btn_eliminar.setVisible(false);
            // Referente al panel de clubs...
            vista.jPanel_clubs.btn_aniadir.setVisible(false);
            vista.jPanel_clubs.btn_modificar.setVisible(false);
            vista.jPanel_clubs.btn_asociar.setVisible(false);
            vista.jPanel_clubs.btn_eliminar.setVisible(false);
        } else {
            // Referente al panel de futbolistas...
            vista.jPanel_futbolistas.btn_aniadir.addActionListener(this);
            vista.jPanel_futbolistas.btn_aniadir.setActionCommand("futbolistas_aniadir");
            vista.jPanel_futbolistas.btn_modificar.addActionListener(this);
            vista.jPanel_futbolistas.btn_modificar.setActionCommand("futbolistas_modificar");
            vista.jPanel_futbolistas.btn_asociar.addActionListener(this);
            vista.jPanel_futbolistas.btn_asociar.setActionCommand("futbolistas_asociar");
            vista.jPanel_futbolistas.btn_eliminar.addActionListener(this);
            vista.jPanel_futbolistas.btn_eliminar.setActionCommand("futbolistas_eliminar");
                // Añadir futbolista
                vista.jPanel_FutbolistaAniadir.btn_accion.addActionListener(this);
                vista.jPanel_FutbolistaAniadir.btn_accion.setActionCommand("fa_aniadir");
                vista.jPanel_FutbolistaAniadir.btn_cancelar.addActionListener(this);
                vista.jPanel_FutbolistaAniadir.btn_cancelar.setActionCommand("fa_cancelar");
                // Modificar futbolista
                vista.jPanel_FutbolistaModificar.btn_accion.addActionListener(this);
                vista.jPanel_FutbolistaModificar.btn_accion.setActionCommand("fm_modificar");
                vista.jPanel_FutbolistaModificar.btn_cancelar.addActionListener(this);
                vista.jPanel_FutbolistaModificar.btn_cancelar.setActionCommand("fm_cancelar");
                // Asociar 
                vista.jPanel_Asociar.btn_accion.addActionListener(this);
                vista.jPanel_Asociar.btn_accion.setActionCommand("asociar_asociar");
                vista.jPanel_Asociar.btn_cancelar.addActionListener(this);
                vista.jPanel_Asociar.btn_cancelar.setActionCommand("asociar_cancelar");
            
            // Referente al panel de clubs...
            vista.jPanel_clubs.btn_aniadir.addActionListener(this);
            vista.jPanel_clubs.btn_aniadir.setActionCommand("clubs_aniadir");
            vista.jPanel_clubs.btn_modificar.addActionListener(this);
            vista.jPanel_clubs.btn_modificar.setActionCommand("clubs_modificar");
            vista.jPanel_clubs.btn_asociar.addActionListener(this);
            vista.jPanel_clubs.btn_asociar.setActionCommand("clubs_asociar");
            vista.jPanel_clubs.btn_eliminar.addActionListener(this);
            vista.jPanel_clubs.btn_eliminar.setActionCommand("clubs_eliminar");
                // Añadir futbolista
                vista.jPanel_ClubAniadir.btn_accion.addActionListener(this);
                vista.jPanel_ClubAniadir.btn_accion.setActionCommand("ca_aniadir");
                vista.jPanel_ClubAniadir.btn_cancelar.addActionListener(this);
                vista.jPanel_ClubAniadir.btn_cancelar.setActionCommand("ca_cancelar");
                // Modificar futbolista
                vista.jPanel_ClubModificar.btn_accion.addActionListener(this);
                vista.jPanel_ClubModificar.btn_accion.setActionCommand("cm_modificar");
                vista.jPanel_ClubModificar.btn_cancelar.addActionListener(this);
                vista.jPanel_ClubModificar.btn_cancelar.setActionCommand("cm_cancelar");
                // Asociar (ya se asoció arriba)
        }
        
        // Para todos los demas...
        // Referente al panel de futbolistas...
        vista.jPanel_futbolistas.btn_buscar.addActionListener(this);
        vista.jPanel_futbolistas.btn_buscar.setActionCommand("futbolistas_buscar");
        vista.jPanel_futbolistas.btn_actualizar.addActionListener(this);
        vista.jPanel_futbolistas.btn_actualizar.setActionCommand("futbolistas_actualizar");
        // Referente al panel de clubss...
        vista.jPanel_clubs.btn_buscar.addActionListener(this);
        vista.jPanel_clubs.btn_buscar.setActionCommand("clubs_buscar");
        vista.jPanel_clubs.btn_actualizar.addActionListener(this);
        vista.jPanel_clubs.btn_actualizar.setActionCommand("clubs_actualizar");
        
        // Actualizo las tablas por primera vez.
        futbolistasActualizar();
        clubsActualizar();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            // ACCIONES FUTBOLISTAS
            case "futbolistas_aniadir":
                futbolistasAniadir();
                break;
                
            case "futbolistas_modificar":
                futbolistasModificar();
                break;
                
            case "futbolistas_asociar":
                System.out.println("controlador.Controlador_App.actionPerformed()");
                asociar();
                break;
                
            case "futbolistas_eliminar":
                futbolistasEliminar();
                break;
                
            case "futbolistas_buscar":
                System.out.println("controlador.Controlador_App.actionPerformed()");
                break;
                
            case "futbolistas_actualizar":
                futbolistasActualizar();
                break;
                // ACCIONES AÑADIR FUTBOLISTAS
                case "fa_aniadir":
                    faAniadir();
                break;
                case "fa_cancelar":
                    vista.dialogoFutbolistaAniadir.dispose();
                break;
                // ACCIONES MODIFICAR FUTBOLISTAS
                case "fm_modificar":
                    fmModificar();
                break;
                case "fm_cancelar":
                    vista.dialogoFutbolistaModificar.dispose();
                break;
                // ACCIONES ASOCIAR
                case "asociar_asociar":
                    System.out.println("controlador.Controlador_App.actionPerformed()");
                    asociarAccion();
                break;
                case "asociar_cancelar":
                    vista.dialogoAsociar.dispose();
                break;
            // ACCIONES CLUBS
            case "clubs_aniadir":
                clubsAniadir();
                break;
                
            case "clubs_modificar":
                clubsModificar();
                break;
                
            case "clubs_asociar":
                System.out.println("controlador.Controlador_App.actionPerformed()");
                asociar();
                break;
                
            case "clubs_eliminar":
                clubsEliminar();
                break;
                
            case "clubs_buscar":
                System.out.println("controlador.Controlador_App.actionPerformed()");
                break;

            case "clubs_actualizar":
                clubsActualizar();
                break;
                // ACCIONES AÑADIR CLUBS
                case "ca_aniadir":
                    caAniadir();
                break;
                case "ca_cancelar":
                    vista.dialogoClubAniadir.dispose();
                break;
                // ACCIONES MODIFICAR CLUBS
                case "cm_modificar":
                    cmModificar();
                break;
                case "cm_cancelar":
                    vista.dialogoClubModificar.dispose();
                break;
        }
    }

// TODO FUTBOLISTAS
    /**
     * Actualiza en la vista la tabla de futbolistas con todos los registros
     * de los mismos en la base de datos.
     */
    public void futbolistasActualizar(){
        DefaultTableModel tabla = futbolistas
                .getTablaFutbolistas(usuarioLogeado.getTipoCuentaBD());
        
        try {
            vista.jPanel_futbolistas.jTable.setModel(tabla);
        } catch (Exception e) {
            e.printStackTrace();
            MuestraMensaje.muestraError(vista, "Error al conectar con la base "
                    + "de datos", "Error del Sistema");
        }
    }

    // AÑADIR FUTBOLISTA METODOS
    /**
     * Accion relacionada al boton de añadir en la pestaña de futbolistas.
     * Abre la ventana de añadir futbolista.
     */
    private void futbolistasAniadir() {
        estandarizarVentana(vista.dialogoFutbolistaAniadir, "Añadir un nuevo futbolista",
                380, 350, false, true, vista);
    }
    
    /**
     * Recoge los datos de la vista y los trata de insertar en la base de
     * datos, mostrando un mensaje u otro según el éxito o fracaso de la
     * acción.
     */
    private void faAniadir() {
        String nombre = vista.jPanel_FutbolistaAniadir.txt_nombre.getText().trim();
        String apellido = vista.jPanel_FutbolistaAniadir.txt_apellido.getText().trim();
        String nacionalidad = vista.jPanel_FutbolistaAniadir.txt_nacionalidad.getText().trim();
        String nif = vista.jPanel_FutbolistaAniadir.txt_nif.getText().trim();
        String anio = vista.jPanel_FutbolistaAniadir.txt_anio_nacimiento.getText().trim();
        String[] comprobacion = Validar.validarDatosFutbolista(nombre, apellido,
                nacionalidad, nif, anio);
        Futbolista futbolista;
        String[] resultado;
        
        // Comprueba si los datos están bien.
        if (comprobacion[0].equals("Exito")) {
            futbolista = new Futbolista(nombre, apellido, Integer.parseInt(anio), 
                    nacionalidad, nif);
            resultado = futbolistas.insertarFutbolista(futbolista, 
                usuarioLogeado.getTipoCuentaBD());
            // Comprueba el exito de la inserción
            if (resultado[0].equals("Exito")) {
                MuestraMensaje.muestraExito(vista.jPanel_FutbolistaAniadir, 
                    resultado[1], resultado[0]);
                 vista.dialogoFutbolistaAniadir.dispose();
                 futbolistasActualizar();
            } else {
                MuestraMensaje.muestraError(vista.jPanel_FutbolistaAniadir, 
                    resultado[1], resultado[0]);
            }
        } else {
            MuestraMensaje.muestraAdvertencia(vista.jPanel_FutbolistaAniadir, 
                    comprobacion[1], comprobacion[0]);
        }
        
    }
    
    // MODIFICAR FUTBOLISTA METODOS
    /**
     * Accion relacionada al boton de modificar en la pestaña de futbolistas.
     * Abre la ventana de añadir futbolista.
     */
    private void futbolistasModificar() {
        int seleccion = vista.jPanel_futbolistas.jTable.getSelectedRow();
        
        if (seleccion == -1) {
            MuestraMensaje.muestraAdvertencia(vista, "Por favor seleccione un "
                    + "futbolista a modificar.", "Sin selección");
        } else {
            // Establezco los datos del futbolista seleccionado en la ventana
            vista.jPanel_FutbolistaModificar.txt_id.setText(
                    vista.jPanel_futbolistas.jTable
                            .getValueAt(seleccion, 0).toString());
            vista.jPanel_FutbolistaModificar.txt_nif.setText(
                    vista.jPanel_futbolistas.jTable
                            .getValueAt(seleccion, 1).toString());
            vista.jPanel_FutbolistaModificar.txt_nombre.setText(
                    vista.jPanel_futbolistas.jTable
                            .getValueAt(seleccion, 2).toString());
            vista.jPanel_FutbolistaModificar.txt_apellido.setText(
                    vista.jPanel_futbolistas.jTable
                            .getValueAt(seleccion, 3).toString());
            vista.jPanel_FutbolistaModificar.txt_anio_nacimiento.setText(
                    vista.jPanel_futbolistas.jTable
                            .getValueAt(seleccion, 4).toString());
            vista.jPanel_FutbolistaModificar.txt_nacionalidad.setText(
                    vista.jPanel_futbolistas.jTable
                            .getValueAt(seleccion, 5).toString());
            // Abro la ventana
            estandarizarVentana(vista.dialogoFutbolistaModificar, "Modificar un futbolista",
                    380, 350, false, true, vista);
        }
    }
    
    /**
     * Recoge los datos de la vista y los trata de "UPDATE" en la base de
     * datos, mostrando un mensaje u otro según el éxito o fracaso de la
     * acción.
     */
    private void fmModificar() {
        String id = vista.jPanel_FutbolistaModificar.txt_id.getText().trim();
        String nombre = vista.jPanel_FutbolistaModificar.txt_nombre.getText().trim();
        String apellido = vista.jPanel_FutbolistaModificar.txt_apellido.getText().trim();
        String nacionalidad = vista.jPanel_FutbolistaModificar.txt_nacionalidad.getText().trim();
        String nif = vista.jPanel_FutbolistaModificar.txt_nif.getText().trim();
        String anio = vista.jPanel_FutbolistaModificar.txt_anio_nacimiento.getText().trim();
        String[] comprobacion = Validar.validarDatosFutbolista(nombre, apellido,
                nacionalidad, nif, anio);
        Futbolista futbolista;
        String[] resultado;
        
        // Comprueba si los datos están bien.
        if (comprobacion[0].equals("Exito")) {
            futbolista = new Futbolista(Integer.parseInt(id), nombre, apellido, 
                    Integer.parseInt(anio), nacionalidad, nif);
            resultado = futbolistas.actualizarFutbolista(futbolista, 
                usuarioLogeado.getTipoCuentaBD());
            // Comprueba el exito de la inserción
            if (resultado[0].equals("Exito")) {
                MuestraMensaje.muestraExito(vista.jPanel_FutbolistaModificar, 
                    resultado[1], resultado[0]);
                 vista.dialogoFutbolistaModificar.dispose();
                 futbolistasActualizar();
            } else {
                MuestraMensaje.muestraError(vista.jPanel_FutbolistaModificar, 
                    resultado[1], resultado[0]);
            }
        } else {
            MuestraMensaje.muestraAdvertencia(vista.jPanel_FutbolistaModificar, 
                    comprobacion[1], comprobacion[0]);
        }
    }

    // ELIMINAR FUTBOLISTA METODOS
    /**
     * Elimina de la base de datos (DELETE) el futbolista seleccionado, 
     * informando al usuario del resultado de la acción.
     */
    private void futbolistasEliminar() {
        Futbolista futbolistaEliminar;
        int seleccion = vista.jPanel_futbolistas.jTable.getSelectedRow();
        boolean eliminar;
        String[] resultado;
        
        if (seleccion == -1) {
            MuestraMensaje.muestraAdvertencia(vista, "Por favor seleccione un "
                    + "futbolista a eliminar.", "Sin selección");
        } else {
            futbolistaEliminar = new Futbolista(
                    Integer.parseInt(vista.jPanel_futbolistas.jTable
                            .getValueAt(seleccion, 0).toString()), 
                    vista.jPanel_futbolistas.jTable
                            .getValueAt(seleccion, 2).toString(), 
                    vista.jPanel_futbolistas.jTable
                            .getValueAt(seleccion, 3).toString(), 
                    Integer.parseInt(vista.jPanel_futbolistas.jTable
                            .getValueAt(seleccion, 4).toString()), 
                    vista.jPanel_futbolistas.jTable
                            .getValueAt(seleccion, 5).toString(), 
                    vista.jPanel_futbolistas.jTable
                            .getValueAt(seleccion, 1).toString());
            // Abro la ventana
            eliminar = MuestraMensaje.muestraConfirmación(
                    vista.jPanel_futbolistas, 
                    "¿Está seguro que quiere eliminar al futbolista " 
                            + futbolistaEliminar.getNif() + " con nombre completo "
                            + futbolistaEliminar.getNombre() + " "
                            + futbolistaEliminar.getApellido() + "?",
                    "Eliminar futbolista " + futbolistaEliminar.getId());
            // Pasa al procedimiento
            if (eliminar) {
                resultado = futbolistas.eliminarFutbolista(futbolistaEliminar, 
                        usuarioLogeado.getTipoCuentaBD());
                if (resultado[0].equals("Exito")) {
                    MuestraMensaje.muestraExito(vista.jPanel_futbolistas, 
                            resultado[1], resultado[0]);
                } else {
                    MuestraMensaje.muestraError(vista.jPanel_futbolistas, 
                            resultado[1], resultado[0]);
                }
                // Recarga la tabla independientemente del resultado
                futbolistasActualizar();
            }
        }
    }
    
// TODO CLUBS    
     /**
     * Actualiza en la vista la tabla de clubs con todos los registros
     * de los mismos en la base de datos.
     */
    public void clubsActualizar(){
        DefaultTableModel tabla = clubs
                .getTablaClubs(usuarioLogeado.getTipoCuentaBD());
        
        try {
            vista.jPanel_clubs.jTable.setModel(tabla);
        } catch (Exception e) {
            e.printStackTrace();
            MuestraMensaje.muestraError(vista, "Error al conectar con la base "
                    + "de datos", "Error del Sistema");
        }
    }
    
    // AÑADIR CLUB METODOS
    /**
     * Accion relacionada al boton de añadir en la pestaña de clubs.
     * Abre la ventana de añadir club.
     */
    private void clubsAniadir() {
        estandarizarVentana(vista.dialogoClubAniadir, "Añadir un nuevo club",
                370, 290, false, true, vista);
    }
    
    /**
     * Recoge los datos de la vista y los trata de insertar en la base de
     * datos, mostrando un mensaje u otro según el éxito o fracaso de la
     * acción.
     */
    private void caAniadir() {
        String nombre = vista.jPanel_ClubAniadir.txt_nombre.getText().trim();
        String anio = vista.jPanel_ClubAniadir.txt_anio_creacion.getText().trim();
        String estadio = vista.jPanel_ClubAniadir.txt_estadio.getText().trim();
        String[] comprobacion = Validar.validarDatosClub(nombre, anio, estadio);
        Club club;
        String[] resultado;
        
        // Comprueba si los datos están bien.
        if (comprobacion[0].equals("Exito")) {
            club = new Club(nombre, Integer.parseInt(anio), estadio);
            resultado = clubs.insertarClub(club, usuarioLogeado.getTipoCuentaBD());
            // Comprueba el exito de la inserción
            if (resultado[0].equals("Exito")) {
                MuestraMensaje.muestraExito(vista.jPanel_ClubAniadir, 
                    resultado[1], resultado[0]);
                 vista.dialogoClubAniadir.dispose();
                 clubsActualizar();
            } else {
                MuestraMensaje.muestraError(vista.jPanel_ClubAniadir, 
                    resultado[1], resultado[0]);
            }
        } else {
            MuestraMensaje.muestraAdvertencia(vista.jPanel_ClubAniadir, 
                    comprobacion[1], comprobacion[0]);
        }
        
    }
    
    // MODIFICAR CLUB METODOS
    /**
     * Accion relacionada al boton de modificar en la pestaña de clubs.
     * Abre la ventana de añadir club.
     */
    private void clubsModificar() {
        int seleccion = vista.jPanel_clubs.jTable.getSelectedRow();
        
        if (seleccion == -1) {
            MuestraMensaje.muestraAdvertencia(vista, "Por favor seleccione un "
                    + "club a modificar.", "Sin selección");
        } else {
            // Establezco los datos del futbolista seleccionado en la ventana
            vista.jPanel_ClubModificar.txt_id.setText(
                    vista.jPanel_clubs.jTable
                            .getValueAt(seleccion, 0).toString());
            vista.jPanel_ClubModificar.txt_nombre.setText(
                    vista.jPanel_clubs.jTable
                            .getValueAt(seleccion, 1).toString());
            vista.jPanel_ClubModificar.txt_anio_creacion.setText(
                    vista.jPanel_clubs.jTable
                            .getValueAt(seleccion, 2).toString());
            vista.jPanel_ClubModificar.txt_estadio.setText(
                    vista.jPanel_clubs.jTable
                            .getValueAt(seleccion, 3).toString());
            // Abro la ventana
            estandarizarVentana(vista.dialogoClubModificar, "Modificar un clubs",
                    370, 290, false, true, vista);
        }
    }
    
    /**
     * Recoge los datos de la vista y los trata de "UPDATE" en la base de
     * datos, mostrando un mensaje u otro según el éxito o fracaso de la
     * acción.
     */
    private void cmModificar() {
        String id = vista.jPanel_ClubModificar.txt_id.getText().trim();
        String nombre = vista.jPanel_ClubModificar.txt_nombre.getText().trim();
        String anio = vista.jPanel_ClubModificar.txt_anio_creacion.getText().trim();
        String estadio = vista.jPanel_ClubModificar.txt_estadio.getText().trim();
        String[] comprobacion = Validar.validarDatosClub(nombre, anio, estadio);
        Club club;
        String[] resultado;
        
        // Comprueba si los datos están bien.
        if (comprobacion[0].equals("Exito")) {
            club = new Club(Integer.parseInt(id), nombre, Integer.parseInt(anio), estadio);
            resultado = clubs.actualizarClub(club, usuarioLogeado.getTipoCuentaBD());
            // Comprueba el exito de la inserción
            if (resultado[0].equals("Exito")) {
                MuestraMensaje.muestraExito(vista.jPanel_ClubModificar, 
                    resultado[1], resultado[0]);
                 vista.dialogoClubModificar.dispose();
                 clubsActualizar();
            } else {
                MuestraMensaje.muestraError(vista.jPanel_ClubModificar, 
                    resultado[1], resultado[0]);
            }
        } else {
            MuestraMensaje.muestraAdvertencia(vista.jPanel_ClubModificar, 
                    comprobacion[1], comprobacion[0]);
        }
    }
    
    // ELIMINAR CLUB METODOS
    /**
     * Elimina de la base de datos (DELETE) el club seleccionado, 
     * informando al usuario del resultado de la acción.
     */
    private void clubsEliminar() {
        Club clubEliminar;
        int seleccion = vista.jPanel_clubs.jTable.getSelectedRow();
        boolean eliminar;
        String[] resultado;
        
        if (seleccion == -1) {
            MuestraMensaje.muestraAdvertencia(vista, "Por favor seleccione un "
                    + "club a eliminar.", "Sin selección");
        } else {
            clubEliminar = new Club(
                    Integer.parseInt(vista.jPanel_clubs.jTable
                            .getValueAt(seleccion, 0).toString()), 
                    vista.jPanel_clubs.jTable.getValueAt(seleccion, 1).toString(), 
                    Integer.parseInt(vista.jPanel_clubs.jTable
                            .getValueAt(seleccion, 2).toString()), 
                    vista.jPanel_clubs.jTable.getValueAt(seleccion, 3).toString());
            // Abro la ventana
            eliminar = MuestraMensaje.muestraConfirmación(
                    vista.jPanel_clubs, 
                    "¿Está seguro que quiere eliminar al club "
                            + clubEliminar.getNombre() + "?",
                    "Eliminar club " + clubEliminar.getId());
            // Pasa al procedimiento
            if (eliminar) {
                resultado = clubs.eliminarClub(clubEliminar, 
                        usuarioLogeado.getTipoCuentaBD());
                if (resultado[0].equals("Exito")) {
                    MuestraMensaje.muestraExito(vista.jPanel_clubs, 
                            resultado[1], resultado[0]);
                } else {
                    MuestraMensaje.muestraError(vista.jPanel_clubs, 
                            resultado[1], resultado[0]);
                }
                // Recarga la tabla independientemente del resultado
                clubsActualizar();
            }
        }
    }
    
// ASOCIAR (TODO CLUB - TODO FUTBOLISTA)
    /**
     * Accion relacionada al boton de asociar en la pestaña de futbolistas o 
     * clubs.
     * Abre la ventana con el futbolista y club seleccionado, si los hubiese,
     * en un combo box seleccionable-editable con todos los futbolistas y clubs
     * disponibles, así como el año de temporada.
     */
    public void asociar(){
        int seleccionFutbolista = vista.jPanel_futbolistas.jTable.getSelectedRow();
        int seleccionClub = vista.jPanel_clubs.jTable.getSelectedRow();
        
        // Rellena los combo box
        try {
            vista.jPanel_Asociar.jComboBox_NifFutbolista.setModel(futbolistas
                    .getComboBoxFutbolistas(usuarioLogeado.getTipoCuentaBD()));
            vista.jPanel_Asociar.jComboBox_NombreClub.setModel(clubs
                    .getComboBoxClubs(usuarioLogeado.getTipoCuentaBD()));
            // Establezco los pre-establecidos, si el usuario tiene alguno 
            // seleccionado de antemano. No es un paso obligatorio.
            if (seleccionFutbolista != -1) {
                 vista.jPanel_Asociar.jComboBox_NifFutbolista.setSelectedItem(
                    vista.jPanel_futbolistas.jTable
                            .getValueAt(seleccionFutbolista, 1));
            }
            if (seleccionClub != -1) {
                vista.jPanel_Asociar.jComboBox_NombreClub.setSelectedItem(
                    vista.jPanel_clubs.jTable
                            .getValueAt(seleccionClub, 1));
            }
        } catch (Exception e) {
            MuestraMensaje.muestraError(vista, "Error al conectarse a la base"
                    + " de datos, por favor, contacte con un administrador o "
                    + "intentelo más tarde.", "Error del Sistema");
        }
        
        // Abre la ventana
        estandarizarVentana(vista.dialogoAsociar, "Asociar", 385, 280, true, 
                true, vista);
        
    }
    
    /**
     * Acción de pulsar el botón de asociar una vez que se haya habierto dicho
     * diálogo.
     */
    public void asociarAccion(){
        String nifFutbolista = vista.jPanel_Asociar.jComboBox_NifFutbolista
                .getSelectedItem().toString().trim();
        String NombreClub = vista.jPanel_Asociar.jComboBox_NombreClub
                .getSelectedItem().toString().trim();
        String anio = vista.jPanel_Asociar.txt_anio_nacimiento.getText().trim();
        String[] comprobacion = Validar.validarAsociar(nifFutbolista, 
                NombreClub, anio);
        
        // Comprueba si los datos están bien.
        if (comprobacion[0].equals("Exito")) {
            // SIGUE POR AQUÍ
            
        } else {
            MuestraMensaje.muestraAdvertencia(vista.jPanel_ClubModificar, 
                    comprobacion[1], comprobacion[0]);
        }
        
        
        
    }
    
// TODO CUENTAS
    
// METODOS DE ESTILO
    /**
     * Modifica la ventana introducida para que aparezca con los datos 
     * propuestos y la coloca como visible. Además centra la ventana a la
     * vista del controlador. 
     * @param ventana Ventana a modificar.
     * @param titulo Titulo de la ventana.
     * @param sx Size x
     * @param sy Size y
     * @param resizable ¿Resizable?
     * @param modal ¿Modal?
     * @param relativoA Ventana a la que mostrar relativo.
     */
    private void estandarizarVentana(JDialog ventana, String titulo, int sx, 
            int sy, boolean resizable, boolean modal, Component relativoA) {
        ventana.setTitle(titulo);
        ventana.setSize(sx, sy);
        ventana.setLocationRelativeTo(relativoA);
        ventana.setResizable(resizable);
        ventana.setModal(modal);
        ventana.setVisible(true);
    }
    
}
