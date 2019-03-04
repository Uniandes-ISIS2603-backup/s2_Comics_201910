/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.*;
import javax.persistence.ManyToMany;


/**
 *
 * @author Juan Pablo Cano
 */
@Entity
public class VendedorEntity extends ColeccionistaEntity implements java.io.Serializable
{      
    
    @PodamExclude
    @OneToMany(mappedBy="vendedor")
    private List<ComicEntity> comics = new ArrayList<ComicEntity>();
     @PodamExclude
    @OneToMany(mappedBy="vendedor")
    private List<CalificacionEntity> calificaciones = new ArrayList<CalificacionEntity>();
     @PodamExclude
      @OneToMany(mappedBy="vendedor")
    private List<OrdenPedidoEntity> ordenes = new ArrayList<OrdenPedidoEntity>();
    /**
     * @return the comics
     */
    public List<ComicEntity> getComics() {
        return comics;
    }

    /**
     * @param comics the comics to set
     */
    public void setComics(List<ComicEntity> comics) {
        this.comics = comics;
    }        

    /**
     * @return the name

    /**
     * @return the calificaciones
     */
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * @return the ordenes
     */
    public List<OrdenPedidoEntity> getOrdenes() {
        return ordenes;
    }

    /**
     * @param ordenes the ordenes to set
     */
    public void setOrdenes(List<OrdenPedidoEntity> ordenes) {
        this.ordenes = ordenes;
    }
}
