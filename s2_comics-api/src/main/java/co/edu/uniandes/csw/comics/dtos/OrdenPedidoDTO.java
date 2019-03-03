/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.dtos;

import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class OrdenPedidoDTO implements Serializable
{
   //ATRIBUTOS
   
    /**
     * tarjeta de credito asiciada con la compra
     */
    private String tarjetaCredito;
    
    /**
     * identificacdor de la orden de pedido
     */
    private Long id;
    /**
     * estado de la orden, es una enumeracion, puede estar 
     * 1. en espera: se genero la orden y esta esperando la confirmacion del vendedor
     * 2. aceptado: el vendedor acepto la orden 
     * 3. rechazado: el vendedor rechazo la orden
     * 4. compelatado: se termino la transaccion, se entrego el producto
     */
    private Integer estado;
    /**
     * comprador que expide la orden de pedido
     */
    private CompradorDTO comprador;
    /**
     * vendedor al cual esta dirigida la orden
     */
    private VendedorDTO vendedor;
    
    /**
     * comic que se va a comprar o intercambiar
     */
    private ComicDTO comic;
    
    /**
     * comic que se va a intercambiar
     */
    private ComicDTO trueque;
    
/**
 * 
 */
    private Integer numeroCompras;
    
    
    //CONSTRUCTORES
    /**
     * Constructoe vacio
     */
    public OrdenPedidoDTO()
    {
        
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param ordenPedidoEntity: Es la entidad que se va a convertir a DTO
     */
     public OrdenPedidoDTO(OrdenPedidoEntity ordenPedidoEntity)
    {
        if (ordenPedidoEntity != null) {
            this.id = ordenPedidoEntity.getId();
            this.estado = ordenPedidoEntity.getEstado();
            this.tarjetaCredito=ordenPedidoEntity.getTarjetaCredito();
            this.numeroCompras=ordenPedidoEntity.getNumeroComprasComprador(); 
        }
    }
    
  
    
    //METODOS

    
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
    public CompradorDTO getComprador() {
        return comprador;
    }

    /**
     * @param comprador the comprador to set
     */
    public void setComprador(CompradorDTO comprador) {
        this.comprador = comprador;
    }

    /**
     * @return the vendedor
     */
    public VendedorDTO getVendedor() {
        return vendedor;
    }

    /**
     * @param vendedor the vendedor to set
     */
    public void setVendedor(VendedorDTO vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return the comic
     */
    public ComicDTO getComic() {
        return comic;
    }

    /**
     * @param comic the comic to set
     */
    public void setComic(ComicDTO comic) {
        this.comic = comic;
    }

    /**
     * @return the trueque
     */
    public ComicDTO getTrueque() {
        return trueque;
    }

    /**
     * @param trueque the trueque to set
     */
    public void setTrueque(ComicDTO trueque) {
        this.trueque = trueque;
    }
    
     /**
     * @return the numeroCompras
     */
    public Integer getNumeroCompras() {
        return numeroCompras;
    }

    /**
     * @param numeroCompras the numeroCompras to set
     */
    public void setNumeroCompras(Integer numeroCompras) {
        this.numeroCompras = numeroCompras;
    }
   
    
    public  OrdenPedidoEntity toEntity(){
       OrdenPedidoEntity ordenPedidoEntity = new OrdenPedidoEntity();
        ordenPedidoEntity.setId(this.id);
         ordenPedidoEntity.setEstado(this.estado);
          ordenPedidoEntity.setNumeroComprasComprador(this.numeroCompras);
           ordenPedidoEntity.setTarjetaCredito(this.tarjetaCredito);
     //      ordenPedidoEntity.setComic(comic.toEntity);
      ordenPedidoEntity.setComprador(this.comprador.toEntity());
       ordenPedidoEntity.setVendedor(this.vendedor.toEntity());
    //    ordenPedidoEntity.setTrueque(this.trueque.toEntity());
        return ordenPedidoEntity;
    }
    
    @Override
		    public String toString() {
		        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
		    }
}
