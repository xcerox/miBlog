package org.miblog.rest.recurso;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.miblog.rest.modelo.Articulo;
import org.miblog.rest.modelo.Link;
import org.miblog.rest.servicio.ArticuloServicio;

/**
 *
 * @author j.reyes
 */
@Path("/articulos")
@Produces(MediaType.APPLICATION_JSON)
public class ArticuloRecurso {

    ArticuloServicio servicio = new ArticuloServicio();

    @GET
    public List<Articulo> getArticulos(@QueryParam("autor") String autor,
            @QueryParam("ano") int ano,
            @QueryParam("mes") int mes) {
        if (autor != null && !autor.isEmpty()) {
            return servicio.getArticulosPorAutor(autor);
        }
        if ((mes > 0) && (ano > 0)) {
            return servicio.getArticulosPorFecha(ano, mes);
        } else {
            return servicio.getArticulos();
        }
    }

    @GET
    @Path("/{articuloId}")
    public Articulo getArticulo(@PathParam("articuloId") int id,
            @Context UriInfo uriInfo) {

        Articulo articulo = servicio.getArticulo(id);

        if (articulo.getLinks().isEmpty()) {
            articulo.getLinks().add(new Link("self",
                    getUriForSelf(uriInfo, articulo)));
            articulo.getLinks().add(new Link("comentario",
                    getUriForComentario(uriInfo, articulo)));
        }
        return articulo;
    }

    private String getUriForSelf(UriInfo info, Articulo articulo) {
        return info.getBaseUriBuilder()
                .path(ArticuloRecurso.class)
                .path(Long.toString(articulo.getId()))
                .build()
                .toString();
    }

    private String getUriForComentario(UriInfo info, Articulo articulo) {
        return info.getBaseUriBuilder()
                .path(ArticuloRecurso.class)
                .path(ArticuloRecurso.class, "getComentarios")
                .path(ComentarioRecurso.class)
                .resolveTemplate("articuloId", Long.toString(articulo.getId()))
                .toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addArticulo(Articulo articulo, @Context UriInfo uriInfo)
            throws URISyntaxException {

        Articulo articuloRespuesta = servicio.addArticulo(articulo);

        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(articuloRespuesta.getId()))
                .build();

        return Response.created(uri)
                .entity(articuloRespuesta)
                .build();
    }

    @DELETE
    @Path("/{articuloId}")
    public void deleteArticulo(@PathParam("articuloId") int id) {
        servicio.deleteArticulo(id);
    }

    @PUT
    @Path("/{articuloId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Articulo actualizaArticulo(@PathParam("articuloId") int id,
            Articulo articulo) {
        articulo.setId(id);
        return servicio.updateArticulo(articulo);
    }

    @Path("/{articuloId}/comentarios")
    public ComentarioRecurso getComentarios() {
        return new ComentarioRecurso();
    }
}
