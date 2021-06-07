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
    String nombre; //nombre usuario es clave

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

    boolean esNumero(char caracter) {
        if (caracter >= 48 && caracter <= 59) {
            return true;
        }
        return false;
    }

    boolean contieneNumero(String contraseña) {
        for (int i = 0; i < contraseña.length(); i++) {
            if (esNumero(contraseña.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    boolean perteneceAlDic(String cSinNum) {
        return true;
    }

    boolean esLugarConocido(String contraseña) {
        String sinNumeros = null;
        for (int i = 0; i < contraseña.length(); i++) {
            if (!esNumero(contraseña.charAt(i))) {
                sinNumeros = sinNumeros + contraseña.charAt(i);
            }
        }
        if (perteneceAlDic(sinNumeros)) {
            return true;
        }

        return false;
    }

    void validoContraseña(String c) {
        if (c.length() < 3) { //verifico el largo de la contraseña
            System.out.println("La contraseña es demasiado corta");
        }
        if (!contieneNumero(c)) {
            System.out.println("debe contener número");
        }
        if (esLugarConocido(c)) {
            System.out.println("La contraseña no debe tener una sola palabra");
        }
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
