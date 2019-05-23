/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Sebastian Baquero
 */

@Entity
public class ComicDeseoEntity extends BaseEntity implements Serializable {
    
    /**
     * Atributo modela la fecha de agregado del comic deseo
     */
    private String fechaAgregado;
    
    /**
     * Atributo modela la relacion con un comic que es one to one
     */
    
    @PodamExclude
    @ManyToOne
    private ComicEntity comic;
    
     @PodamExclude
     @ManyToOne
     private CompradorEntity comprador;
    
   // @PodamExclude
  //  @OneToOne
  //  private ComicEntity comic;
    
    /**
     * Constructor vacio de comic deseo entity
     */
    public ComicDeseoEntity(){
    
    }

    /**
     * @return the fechaAgregado
     */
    public String getFechaAgregado() {
        return fechaAgregado;
        
    }

    /**
     * @param fechaAgregado the fechaAgregado to set
     */
    public void setFechaAgregado(String fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }

    /**
     * @return the comic
     */
    public ComicEntity getComic() {
        return comic;
    }

    /**
     * @param comic the comic to set
     */
    public void setComic(ComicEntity comic) {
        this.comic = comic;
    }

    /**
     * @return the comprador
     */
    public CompradorEntity getComprador() {
        return comprador;
    }

    /**
     * @param comprador the comprador to set
     */
    public void setComprador(CompradorEntity comprador) {
        this.comprador = comprador;
    }
    
   
}
