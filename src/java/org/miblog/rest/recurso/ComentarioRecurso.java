package org.miblog.rest.recurso;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.miblog.rest.modelo.Comentario;
import org.miblog.rest.servicio.ComentarioServicio;

/**
 *
 * @author j.reyes
 */
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComentarioRecurso {
    
    ComentarioServicio comentarioServicio = new ComentarioServicio();
    
    @GET
    public List<Comentario> getComentarios(@PathParam("articuloId") int articuloId){
        return comentarioServicio.findAll(articuloId);
    }    
    
    @GET
    @Path("/{comentarioId}")
    public Comentario getComentario(@PathParam("articuloId") int articuloId, @PathParam("comentarioId") int comentarioId){
        return comentarioServicio.find(articuloId, comentarioId);
    }
    
    @POST
    public Comentario addComentario(@PathParam("articuloId") int articuloId, Comentario comentario){
        return comentarioServicio.add(articuloId, comentario);
    }
    
    @PUT 
    @Path("/{comentarioId}")
    public Comentario updateComentario(@PathParam("articuloId") int articuloId, @PathParam("comentarioId") int comentarioId, Comentario comentario){
        comentario.setId(comentarioId);
        return comentarioServicio.update(articuloId, comentario);
    }
    
    @DELETE
    @Path("/{comentarioId}")
    public void deleteComentario(@PathParam("articuloId") int articuloId, @PathParam("comentarioId") int comentarioId){
        comentarioServicio.delete(articuloId, comentarioId);
    }
    
}
