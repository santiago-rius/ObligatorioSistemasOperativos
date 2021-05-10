package Consola;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Santiago
 */
public class UsuarioEstandar extends Usuario{
    
    public UsuarioEstandar(String unNombre) {
        super(unNombre);
    }

    @Override
    public boolean equals(Object obj) {
        Usuario usu = (UsuarioEstandar) obj;
        return usu.nombre.equals(this.nombre);
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
      
    
}
