/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import modelo.Cuentas;
import modelo.Validar;
import modelo.conexion.CuentasBD;
import modelo.dao.ClubDAO;
import modelo.dao.FutbolistaDAO;
import modelo.pojos.Futbolista;
import modelo.usuarios.TiposUsuario;
import modelo.usuarios.Usuario;
import vista.jframes.JFrame_App;

/**
 * Controlador de la App. <br>
 * Contiene el modelo, la vista, y el usuario con el cual se logeó que 
 * mediante este controlador, modificará la vista.
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
        vista.setVisible(true);
        
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
                vista.jPanel_FutbolistaDatosAniadir.btn_accion.addActionListener(this);
                vista.jPanel_FutbolistaDatosAniadir.btn_accion.setActionCommand("fa_aniadir");
                vista.jPanel_FutbolistaDatosAniadir.btn_cancelar.addActionListener(this);
                vista.jPanel_FutbolistaDatosAniadir.btn_cancelar.setActionCommand("fa_cancelar");
            
            // Referente al panel de clubs...
            vista.jPanel_clubs.btn_aniadir.addActionListener(this);
            vista.jPanel_clubs.btn_aniadir.setActionCommand("clubs_aniadir");
            vista.jPanel_clubs.btn_modificar.addActionListener(this);
            vista.jPanel_clubs.btn_modificar.setActionCommand("clubs_modificar");
            vista.jPanel_clubs.btn_asociar.addActionListener(this);
            vista.jPanel_clubs.btn_asociar.setActionCommand("clubs_asociar");
            vista.jPanel_clubs.btn_eliminar.addActionListener(this);
            vista.jPanel_clubs.btn_eliminar.setActionCommand("clubs_eliminar");
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
        //clubsActualizar();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            // FUTBOLISTAS
            case "futbolistas_aniadir":
                futbolistasAniadir();
                break;
                
            case "futbolistas_modificar":
                System.out.println("controlador.Controlador_App.actionPerformed()");
                futbolistasModificar();
                break;
                
            case "futbolistas_asociar":
                System.out.println("controlador.Controlador_App.actionPerformed()");
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
            // AÑADIR FUTBOLISTAS
                case "fa_aniadir":
                    faAniadir();
                break;
                case "fa_cancelar":
                    vista.dialogoFutbolistaAniadir.dispose();
                break;
            // MODIFICAR FUTBOLISTAS
                
            // CLUBS
            case "clubs_aniadir":
                System.out.println("club ania");
                break;
                
            case "clubs_modificar":
                System.out.println("controlador.Controlador_App.actionPerformed()");
                break;
                
            case "clubs_asociar":
                System.out.println("controlador.Controlador_App.actionPerformed()");
                break;
                
            case "clubs_eliminar":
                System.out.println("controlador.Controlador_App.actionPerformed()");
                break;
                
            case "clubs_buscar":
                System.out.println("controlador.Controlador_App.actionPerformed()");
                break;

            case "clubs_actualizar":
                System.out.println("controlador.Controlador_App.actionPerformed()");
                break;
        }
    }
    
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
                100, 100, 380, 350, false, true);
        
    }
    
    /**
     * Recoge los datos de la vista y los trata de insertar en la base de
     * datos, mostrando un mensaje u otro según el éxito o fracaso de la
     * acción.
     */
    private void faAniadir() {
        String nombre = vista.jPanel_FutbolistaDatosAniadir.txt_nombre.getText();
        String apellido = vista.jPanel_FutbolistaDatosAniadir.txt_apellido.getText();
        String nacionalidad = vista.jPanel_FutbolistaDatosAniadir.txt_nacionalidad.getText();
        String nif = vista.jPanel_FutbolistaDatosAniadir.txt_nif.getText();
        String anio = vista.jPanel_FutbolistaDatosAniadir.txt_anio_nacimiento.getText();
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
                MuestraMensaje.muestraExito(vista.dialogoFutbolistaAniadir, 
                    resultado[1], resultado[0]);
                 vista.dialogoFutbolistaAniadir.dispose();
                 futbolistasActualizar();
            } else {
                MuestraMensaje.muestraError(vista.dialogoFutbolistaAniadir, 
                    resultado[1], resultado[0]);
            }
        } else {
            MuestraMensaje.muestraAdvertencia(vista.dialogoFutbolistaAniadir, 
                    comprobacion[1], comprobacion[0]);
        }
        
    }
    
    // MODIFICAR FUTBOLISTA METODOS
    private void futbolistasModificar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // ELIMINAR FUTBOLISTA METODOS
    private void futbolistasEliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // METODOS DE ESTILO
    /**
     * Modifica la ventana introducida para que aparezca con los datos 
     * propuestos y la coloca como visible.
     * @param ventana Ventana a modificar.
     * @param titulo Titulo de la ventana.
     * @param lx Localizacion x
     * @param ly Localizacion y
     * @param sz Size x
     * @param sy Size y
     * @param resizable ¿Resizable?
     * @param modal ¿Modal?
     */
    private void estandarizarVentana(JDialog ventana, String titulo, int lx, 
            int ly, int sz, int sy, boolean resizable, boolean modal) {
        ventana.setTitle(titulo);
        ventana.setLocation(lx, ly);
        ventana.setSize(sz, sy);
        ventana.setResizable(resizable);
        ventana.setModal(modal);
        ventana.setVisible(true);
    }

    
    
    /**
     * Actualiza en la vista la tabla de clubs con todos los registros
     * de los mismos en la base de datos.
     */
    /*public void clubsActualizar(){
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
    */
}
