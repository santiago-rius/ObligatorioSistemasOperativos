
import java.util.List;

/**
 *
 * @author evejo
 */
public class Sesion {
    List <Usuario> misUsuarios;
    Usuario usuarioActual;
    String ruta;
    
    Usuario getUsuario(){
        return this.usuarioActual;
    }
    void setUsuario(Usuario usuarioA){
        this.usuarioActual.equals(usuarioA);
    }
    
    private void setRuta(String rutaActual){
        this.ruta = rutaActual;
    }
    
    void agregarAMisUsuarios(Usuario u){
        this.misUsuarios.add(u);
    }
}
