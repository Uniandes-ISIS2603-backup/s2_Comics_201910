/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class OrdenPedidoEntity extends BaseEntity implements Serializable
{
    @PodamExclude
    @ManyToOne
    private CompradorEntity comprador;
    
    private Integer numeroComprasComprador;

    private Boolean estadosOrden;
    
    public Integer getNumeroComprasComprador(){
        return numeroComprasComprador;
    }
    
    public Boolean getEstadosOrden(){
        return estadosOrden;
    }
    
    public void setNumeroComprasComprador(Integer pNumeroComprasComprador){
        numeroComprasComprador = pNumeroComprasComprador;
    }
    
    public void setEstadosOrden(Boolean pEstadosOrden){
        estadosOrden = pEstadosOrden;
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
