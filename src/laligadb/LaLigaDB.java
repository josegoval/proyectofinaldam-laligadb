/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laligadb;

import modelo.usuarios.Seguridad;

/**
 *
 * @author Jose
 */
public class LaLigaDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(Seguridad.hashPassSHA256("EZTO EZ asi !"));
        System.out.println(Seguridad.validarContrasenia(
                Seguridad.hashPassSHA256("EZTO EZ asi !"),
                "6700377b2f4bd22ff0205ef10a0728cefba043d1aef34e2f79a271872da0dce0"));
    }
    
}
