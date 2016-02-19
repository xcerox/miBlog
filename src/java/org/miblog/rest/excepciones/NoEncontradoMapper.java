package org.miblog.rest.excepciones;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.miblog.rest.modelo.MensajeError;

@Provider
public class NoEncontradoMapper implements ExceptionMapper<NoEncontradoException>{

    @Override
    public Response toResponse(NoEncontradoException exception) {
        MensajeError mensaje = new MensajeError(exception.getMessage(), "Este articulo no existe", 404);
        return Response.status(Response.Status.NOT_FOUND)
                .entity(mensaje)
                .build();
    }
    
    
}
