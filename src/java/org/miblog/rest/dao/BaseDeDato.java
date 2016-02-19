
package org.miblog.rest.dao;

import java.util.ArrayList;
import java.util.List;
import org.miblog.rest.modelo.Articulo;
import org.miblog.rest.modelo.Comentario;

/**
 *
 * @author j.reyes
 */
public final class BaseDeDato {
    private final static BaseDeDato baseDeDato = new BaseDeDato();
    private final List<Articulo> listArticulo = new ArrayList<>();

    public BaseDeDato() {
        Articulo primerArticulo = new Articulo(1,"primer articulo",
                "este es mi primer articulo","jairo reyes");
        Articulo segundoArticulo = new Articulo(2,"segundo articulo",
                "este es mi segundo articulo","jairo reyes");
        
        primerArticulo.getComentarios().add(new Comentario(1, "Primer comentario", "frank"));
        listArticulo.add(primerArticulo);
        listArticulo.add(segundoArticulo);
    }
    
    public static BaseDeDato getInstance(){
        return baseDeDato;
    }
    
    public List<Articulo> getArticulos(){
        return listArticulo;
    }
    
    
}
