package org.miblog.rest.excepciones;

public class NoEncontradoException extends RuntimeException{

    public NoEncontradoException(String mensaje) {
        super(mensaje);
    }

    public NoEncontradoException() {
        this("Articulo no encontrado");
    }
    
    
    
}
