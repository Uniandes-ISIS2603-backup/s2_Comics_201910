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
 * @author estudiante
 */

@Entity
public class ComicDeseoEntity extends BaseEntity implements Serializable {
    
    private SimpleDateFormat fechaAgregado;
    @PodamExclude
    @OneToOne
    private ComicEntity comic;
    
    public ComicDeseoEntity(){
    
    }

    /**
     * @return the fechaAgregado
     */
    public SimpleDateFormat getFechaAgregado() {
        return fechaAgregado;
        
    }

    /**
     * @param fechaAgregado the fechaAgregado to set
     */
    public void setFechaAgregado(SimpleDateFormat fechaAgregado) {
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
    
   
}
