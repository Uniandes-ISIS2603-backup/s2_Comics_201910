/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.entities;

import java.io.Serializable;
import javax.persistence.Entity;
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
    
    /**
     * tarjeta de credito asiciada con la compra
     */
    private String tarjetaCredito;
    
    /**
     * identificacdor de la orden de pedido
     */
    private Integer identificador;
    /**
     * numero de compras realizadas por el comprador
     */
    private Integer numeroComprasComprador;

    private Boolean estadosOrden;
    
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
    public void setTarjetaCredito(String tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    /**
     * @return the id
     */
    public Integer getIdentificador()
    {
        return identificador;
    }

    /**
     * @param id the id to set
     */
    public void setIdentificador(Integer pId) 
    {
        this.identificador = pId;
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
}
