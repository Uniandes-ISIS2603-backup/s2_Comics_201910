/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.dtos;

import co.edu.uniandes.csw.comics.entities.ComicDeseoEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Sebastian Baquero
 */


public class ComicDeseoDTO implements Serializable {
 
    private Date fechaAgregado;
    private CompradorDTO comprador;
    
    
    public ComicDeseoDTO (){
    
    }
    
    /**
     * Crea un objeto AuthorDTO a partir de un objeto AuthorEntity.
     *
     * @param comicDeseoEntity Entidad AuthorEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    
  public ComicDeseoDTO(ComicDeseoEntity pComicDE){
        
    this.fechaAgregado = pComicDE.getFechaAgregado();
    
   }
   
    /**
     * COnvierte un objeto ComicDeseoDTO a ComicDeseoEntity
     */
    
    public ComicDeseoEntity toEntity (){
    
       ComicDeseoEntity cDE = new ComicDeseoEntity();
       cDE.setFechaAgregado(this.getFechaAgregado());
       if(this.comprador !=null){
           
       //cDE.setComprador(this.comprador.toEntity);
       }
       return cDE;
    }

    /**
     * @return the fechaAgregado
     */
    public Date getFechaAgregado() {
        return fechaAgregado;
    }

    /**
     * @param fechaAgregado the fechaAgregado to set
     */
    public void setFechaAgregado(Date fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }
    
}
