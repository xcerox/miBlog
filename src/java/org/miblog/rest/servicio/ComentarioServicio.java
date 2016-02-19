
package org.miblog.rest.servicio;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import org.miblog.rest.modelo.Articulo;
import org.miblog.rest.modelo.Comentario;

/**
 *
 * @author j.reyes
 */
public class ComentarioServicio {
    private final ArticuloServicio articuloServicio = new ArticuloServicio();
    
    public List<Comentario> findAll(int articuloId){
        return articuloServicio.getArticulo(articuloId).getComentarios();
    }
    
    public Comentario find(int articuloId, int comentarioId){
        List<Comentario> comentarios = articuloServicio.getArticulo(articuloId).getComentarios();
        
        Optional<Comentario> comentario = comentarios.stream()
                .filter(c -> c.getId() == comentarioId)
                .findFirst();
        
        if(comentario.isPresent())
            return comentario.get();
        else 
            return null;
    }
    
    private int getMaximo(Articulo articulo){
        int size = articulo.getComentarios().size();
        if(size > 0)
            return articulo.getComentarios().get(size - 1).getId() + 1;
        return 1;
    }
    
    public Comentario add(int articuloId,Comentario comentario){
        Articulo articulo = articuloServicio.getArticulo(articuloId);
        comentario.setFechaCreacion(new GregorianCalendar());
        comentario.setId(getMaximo(articulo));
        articulo.getComentarios().add(comentario);
        return  comentario;
    }
    
    private int getPosition(Articulo articulo, int comentarioId){
        List<Comentario> comentarios = articulo.getComentarios();
        
        for (int index = 0; index < comentarios.size(); index++) 
            if(comentarios.get(index).getId() == comentarioId)
                  return  index;
        
        return  -1;
    }
    
    public Comentario update(int articuloId,Comentario comentario){
        Articulo articulo = articuloServicio.getArticulo(articuloId);
        int posicion = getPosition(articulo, comentario.getId());
        try{
            articulo.getComentarios().set(posicion, comentario);
            return comentario;
        }catch(IndexOutOfBoundsException indexOut){
            return null;
        }
    }
    
    public void delete(int articuloId, int comentarioId){
        Articulo articulo = articuloServicio.getArticulo(articuloId);
        int posicion = getPosition(articulo, comentarioId);
        articulo.getComentarios().remove(posicion);
    }
}
