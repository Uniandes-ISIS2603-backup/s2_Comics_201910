/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.entities;

import java.io.Serializable;
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
public class CompradorEntity extends ColeccionistaEntity implements Serializable
{
    
    @PodamExclude
    @OneToMany(mappedBy = "comprador", fetch=FetchType.LAZY, targetEntity=OrdenPedidoEntity.class)
    private List ordenPedidoCompra = new ArrayList();
    
    @PodamExclude
    @ManyToMany(mappedBy = "compradores")
    private List<ComicEntity> carro = new ArrayList<ComicEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy="comprador")
    private List<ComicDeseoEntity> listaDeseos = new ArrayList<ComicDeseoEntity>();
    
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

    /**
     * @return the listaDeseos
     */
    public List<ComicDeseoEntity> getListaDeseos() {
        return listaDeseos;
    }

    /**
     * @param listaDeseos the listaDeseos to set
     */
    public void setListaDeseos(List<ComicDeseoEntity> listaDeseos) {
        this.listaDeseos = listaDeseos;
    }
}
