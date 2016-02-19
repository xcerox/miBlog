package org.miblog.rest.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Comentario {
    private int id;
    private Calendar fechaCreacion;
    private String comentario;
    private String autor;

    public Comentario(int id, String comentario, String autor) {
        this.id = id;
        this.fechaCreacion = new GregorianCalendar();
        this.comentario = comentario;
        this.autor = autor;
    }

    public Comentario() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Calendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    
}
