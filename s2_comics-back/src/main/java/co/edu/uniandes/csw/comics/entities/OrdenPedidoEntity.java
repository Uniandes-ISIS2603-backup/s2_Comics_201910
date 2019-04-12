/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
//import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jp.rodriguezv
 */
@Entity
public class OrdenPedidoEntity  extends BaseEntity implements Serializable
{
    /**
     * estado de la orden, es una enumeracion, puede estar 
     * 1. en espera: se genero la orden y esta esperando la confirmacion del vendedor
     * 2. aceptado: el vendedor acepto la orden 
     * 3. rechazado: el vendedor rechazo la orden
     * 4. finalizado: se termino la transaccion, se entrego el producto
     */
    public static enum Estado{
        EN_ESPERA,
        ACEPTADO,
        RECHAZADO,
        ENVIADO,
        FINALIZADO
    }
   
      /**
     * fecha estimada de enrega
     */
    private String fechaEstimadaEntrega;
    
    /**
     * comentario de rechazo
     */
    private String comentario;

    /**
     * estado de la orden, es una enumeracion
     */
    private Estado estado;
    
    /**
     * comprador asociado a la ordenPedido
     */
    @PodamExclude
    @ManyToOne
    private CompradorEntity comprador;
   
    /**
     * vendedor asociado a la ordenPedido
     */
    @PodamExclude
    @ManyToOne
    private VendedorEntity vendedor;
   
    /**
     * comic asociado a la ordenPedido
     */
    private ComicEntity comic;
    
    /**
     * comic truque asociado a la orden pedido
     */
    private ComicEntity trueque;
    
    
    /**
     * tarjeta de credito asiciada con la compra
     */
    private String tarjetaCredito;
    
    /**
     * numero de compras realizadas por el comprador
     */
    private Integer numeroComprasComprador;

      
    
   
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
    
  


    /**
     * @param estado the estado to set
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * @return the estado
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * @return the fechaEstimadaEntrega
     */
    public String getFechaEstimadaEntrega() {
        return fechaEstimadaEntrega;
    }

    /**
     * @param fechaEstimadaEntrega the fechaEstimadaEntrega to set
     */
    public void setFechaEstimadaEntrega(String fechaEstimadaEntrega) {
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}