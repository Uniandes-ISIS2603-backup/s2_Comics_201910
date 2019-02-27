/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.entities;

import java.io.Serializable;
//import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
   
    @PodamExclude
    @ManyToOne
    private VendedorEntity vendedor;
   
    
    private ComicEntity comic;
    
    private ComicEntity trueque;
    
    
    
    /**
     * tarjeta de credito asiciada con la compra
     */
    private String tarjetaCredito;
    
    /**
     * identificacdor de la orden de pedido
     */
    @Id
    private Long id;
    /**
     * numero de compras realizadas por el comprador
     */
    private Integer numeroComprasComprador;

    
    /**
     * estado de la orden, es una enumeracion, puede estar 
     * 1. en espera: se genero la orden y esta esperando la confirmacion del vendedor
     * 2. aceptado: el vendedor acepto la orden 
     * 3. rechazado: el vendedor rechazo la orden
     * 4. compelatado: se termino la transaccion, se entrego el producto
     */
    private Integer estado;
   
    public OrdenPedidoEntity(){
        
    }

    /**
     * @return the tarjetaCredito
     */
    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    /**
     * @param tarjetaCredito the tarjetaCredito to set
     */
    public void setTarjetaCredito(String tarjetaCredito) 
    {
        this.tarjetaCredito = tarjetaCredito;
    }

    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long pId) 
    {
        this.id=pId;
    }

    /**
     * @return the numeroComprasComprador
     */
    public Integer getNumeroComprasComprador()
    {
        return numeroComprasComprador;
    }

    /**
     * @param numeroComprasComprador the numeroComprasComprador to set
     */
    public void setNumeroComprasComprador(Integer numeroComprasComprador) {
        this.numeroComprasComprador = numeroComprasComprador;
    }

    /**
     * @return the estado
     */
    public Integer getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Integer estado) {
        this.estado = estado;
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

    /**
     * @return the vendedor
     */
    public VendedorEntity getVendedor() {
        return vendedor;
    }

    /**
     * @param vendedor the vendedor to set
     */
    public void setVendedor(VendedorEntity vendedor) {
        this.vendedor = vendedor;
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
     * @return the trueque
     */
    public ComicEntity getTrueque() {
        return trueque;
    }

    /**
     * @param trueque the trueque to set
     */
    public void setTrueque(ComicEntity trueque) {
        this.trueque = trueque;
    }
    
    

    
}