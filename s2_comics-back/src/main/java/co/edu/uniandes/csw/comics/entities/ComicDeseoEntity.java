/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */

@Entity
public class ComicDeseoEntity extends BaseEntity implements Serializable {
    
    private Date fechaAgregado;
    @PodamExclude
    @ManyToOne
    private CompradorEntity comprador;
    
    public ComicDeseoEntity(){
    
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
    
    public CompradorEntity getComprador(){
        return comprador;
    }
    
    public void setComprador(CompradorEntity compradorEntity){
    this.comprador = compradorEntity;
    }
}
