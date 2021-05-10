package Consola;


import ClasesAuxiliares.Nodo;
import FileSystem.Directorio;
import FileSystem.Directorios;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author evejo
 */
public class Sesion {
    List <Usuario> misUsuarios;
    Usuario usuarioActual;
    String ruta;
    Directorios directorios;

    public Sesion() {
        misUsuarios = new ArrayList<>();
        usuarioActual = new UsuarioEstandar("usuario no autenticado");
        ruta = "/";
        directorios = new Directorios();
    }
    
    Usuario getUsuario(){
        return this.usuarioActual;
    }
    void setUsuarioActual(Usuario usuarioA){
        this.usuarioActual = usuarioA;
    }
    
    public void setRuta(String rutaActual){
        this.ruta = rutaActual;
    }

    public Directorios getDirectorios() {
        return directorios;
    }

    public void setDirectorios(Directorios directorios) {
        this.directorios = directorios;
    }

    
    void agregarAMisUsuarios(Usuario u){
        this.misUsuarios.add(u);
    }
}
