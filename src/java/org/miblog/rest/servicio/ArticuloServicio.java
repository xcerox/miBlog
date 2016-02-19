package org.miblog.rest.servicio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.miblog.rest.dao.BaseDeDato;
import org.miblog.rest.modelo.Articulo;
import org.miblog.rest.excepciones.NoEncontradoException;

/**
 *
 * @author j.reyes
 */

public class ArticuloServicio {
    private final List<Articulo> articulos = BaseDeDato.getInstance().getArticulos();
    
    public List<Articulo> getArticulosPorAutor(String autor){
        return articulos.stream()
                .filter(articulo -> articulo.getAutor().equalsIgnoreCase(autor))
                .collect(Collectors.toList());
    }
    
    
    public List<Articulo> getArticulosPorFecha(int ano, int mes){
        
        Calendar fecha = new GregorianCalendar();
    
        List<Articulo> articulosSeleccionados = articulos.stream()
                .map((articulo) -> { fecha.setTime(articulo.getFechaCreacion()
                                            .getTime());
                                    return articulo;})
                .filter((articulo) -> ((fecha.get(Calendar.YEAR) == ano) 
                        && (fecha.get(Calendar.MONTH) == (mes - 1))))
                .collect(Collectors.toList());
        
        return articulosSeleccionados;
    }
    
    public List<Articulo> getArticulos(){
        return articulos;
    }  
    
    public Articulo getArticulo(int id){
        Optional<Articulo> articulo =  articulos.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
        
       if(articulo.isPresent())
           return articulo.get();
       else
           throw new NoEncontradoException();

    }
    
    private int getMaximo(){
        int size = articulos.size();
        if(size > 0)
            return articulos.get(size - 1).getId() + 1;
        else 
            return 1;
    }
    
    public Articulo addArticulo(Articulo articulo){
        articulo.setId(getMaximo());
        articulo.setFechaCreacion(new GregorianCalendar() );
        articulos.add(articulo);
        return articulo;
    }
    
    private int getPosition(int id){
        for (int position = 0; position < articulos.size(); position++) {
            if(articulos.get(position).getId() == id){
                return position;
            }
        }
        
        return -1;
    }
    
    public Articulo updateArticulo(Articulo articulo){
        int position = getPosition(articulo.getId());
        try{
            articulos.set(position, articulo);
            return articulo;
        }catch(IndexOutOfBoundsException indexOut){
            throw new NoEncontradoException();
        }
    }
    
    public void deleteArticulo(int id){
        int position = getPosition(id);
        articulos.remove(position);
    }
    

}
