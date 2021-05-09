
import ClasesAuxiliares.Nodo;
import FileSystem.Directorio;
import FileSystem.Directorios;
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
    
    Usuario getUsuario(){
        return this.usuarioActual;
    }
    void setUsuario(Usuario usuarioA){
        this.usuarioActual.equals(usuarioA);
    }
    
    private void setRuta(String rutaActual){
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
