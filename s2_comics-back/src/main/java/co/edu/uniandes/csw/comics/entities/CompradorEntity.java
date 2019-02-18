/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.entities;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class CompradorEntity extends ColeccionistaEntity implements java.io.Serializable
{
    
    @PodamExclude
    @OneToMany(mappedBy = "comprador", fetch=FetchType.LAZY, targetEntity=OrdenPedidoEntity.class)
    private List ordenPedidoCompra = new ArrayList();
    
    @PodamExclude
    @ManyToMany(mappedBy = "compradores")
    private List<ComicEntity> carro = new ArrayList<ComicEntity>();
    
    /**
     * @return the ordenPedidoCompra
     */
    public List<OrdenPedidoEntity> getOrdenPedidoCompra() 
    {
        return ordenPedidoCompra;
    }    

    /**
     * @param ordenPedidoCompra the ordenPedidoCompra to set
     */
    public void setOrdenPedidoCompra(List<OrdenPedidoEntity> ordenPedidoCompra) {
        this.ordenPedidoCompra = ordenPedidoCompra;
    }

    /**
     * @return the carro
     */
    public List<ComicEntity> getCarro() {
        return carro;
    }

    /**
     * @param carro the carro to set
     */
    public void setCarro(List<ComicEntity> carro) {
        this.carro = carro;
    }
}
