/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.dtos;

import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.resources.OrdenPedidoResource;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author jp.rodriguezv
 */
public class OrdenPedidoDTO implements Serializable
{
   //ATRIBUTOS
   private static final Logger LOGGER=Logger.getLogger(OrdenPedidoDTO.class.getName());
    
     
    /**
     * identificacdor de la orden de pedido
     */
    private Long id;
    /**
     * tarjeta de credito asiciada con la compra
     */
    private String tarjetaCredito;
   
    /**
     * estado de la orden, es una enumeracion, puede estar 
     * 1. en espera: se genero la orden y esta esperando la confirmacion del vendedor
     * 2. aceptado: el vendedor acepto la orden 
     * 3. rechazado: el vendedor rechazo la orden
     * 4. en proceso: el vendedor  ya envio el comic fisico y se espera ña confirmacion del comprador
     * 5. compelatado: se termino la transaccion, se entrego el producto
     */
    private OrdenPedidoEntity.Estado estado;
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
 * numero de compras realizadas por el comprador asociado a la orden
 */
    private Integer numeroCompras;
    
    /**
     * fecha estimada de enrega
     */
    private String fechaEstimadaEntrega;
    
    /**
     * comentario de rechazo
     */
    private String comentario;
  
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
            
            this.fechaEstimadaEntrega= ordenPedidoEntity.getFechaEstimadaEntrega();
            
            this.id = ordenPedidoEntity.getId();
            this.estado = ordenPedidoEntity.getEstado();
            this.tarjetaCredito=ordenPedidoEntity.getTarjetaCredito();
            this.numeroCompras=ordenPedidoEntity.getNumeroComprasComprador(); 
            CompradorDTO c= new CompradorDTO(ordenPedidoEntity.getComprador());
            this.comprador=c;
            VendedorDTO v= new VendedorDTO(ordenPedidoEntity.getVendedor());
            this.vendedor=v;
            this.comentario = ordenPedidoEntity.getComentario();
            ComicDTO d= new ComicDTO(ordenPedidoEntity.getComic());
            this.comic=d;
            ComicDTO t= new ComicDTO(ordenPedidoEntity.getTrueque());
            this.trueque=t;
        }
    }
    
  
    
    //METODOS

    
  

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
   
    /**
     * convierte el OrdenPedidoDTO a un ordenPedidoEntity
     * @return el nuevo OrdenPedidoEntity
     */
    public  OrdenPedidoEntity toEntity(){
    
       
        OrdenPedidoEntity ordenPedidoEntity = new OrdenPedidoEntity();
        ordenPedidoEntity.setId(this.id);
         ordenPedidoEntity.setEstado(this.estado);
         ordenPedidoEntity.setComentario(this.comentario);
         ordenPedidoEntity.setFechaEstimadaEntrega(this.fechaEstimadaEntrega);
          ordenPedidoEntity.setNumeroComprasComprador(this.numeroCompras);
           ordenPedidoEntity.setTarjetaCredito(this.tarjetaCredito);
        ordenPedidoEntity.setComic(this.comic.toEntity());
      ordenPedidoEntity.setComprador(this.comprador.toEntity());
      ordenPedidoEntity.setVendedor(this.vendedor.toEntity());
      
      LOGGER.log(Level.INFO, "convertido a Entity 1");
      ordenPedidoEntity.setTrueque(this.trueque.toEntity());
      LOGGER.log(Level.INFO, "convertido a Entity");
   
      return ordenPedidoEntity;
        
    }
    
    @Override
		    public String toString() {
		        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
		    }

    /**
     * @return the estado
     */
    public OrdenPedidoEntity.Estado getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(OrdenPedidoEntity.Estado estado) {
        this.estado = estado;
    }

    /**
     * @return the fechaAgregado
     */
    public String getFechaEstimadaEntrega() {
        return fechaEstimadaEntrega;
    }

    /**
     * @param fechaEstimadaEntrega the fechaAgregado to set
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
