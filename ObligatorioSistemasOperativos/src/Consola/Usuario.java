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
public class Usuario {

    String contraseña;
    String nombre;

    public Usuario(String unNombre) {
        this.nombre = unNombre;
        this.contraseña = "root";
    }

    public String getContraseña() {
        return this.contraseña;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setContraseña(String unaContraseña) {
        this.contraseña = unaContraseña;
    }

    void setNombre(String unNombre) {
        this.nombre = unNombre;
    }
    
    public boolean esAdmin() {
        return this.nombre.equals("root");
    }
    
    public boolean esVisita() {
        return this.nombre.equals("visita");
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario) {
            Usuario toCompare = (Usuario) obj;
            return this.nombre.equals(toCompare.nombre);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.nombre.hashCode();
    }
    
}
