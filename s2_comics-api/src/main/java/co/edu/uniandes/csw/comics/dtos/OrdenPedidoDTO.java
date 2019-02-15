/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.dtos;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class OrdenPedidoDTO implements Serializable
{
   //ATRIBUTOS
    
    /**
     * identificacdor de la orden de pedido
     */
    private Integer id;
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
    /**
     * comprador que expide la orden de pedido
     */
    private CompradorDTO comprador;
    /**
     * vendedor al cual esta dirigida la orden
     */
    private VendedorDTO vendedor;
    
    //CONSTRUCTORES
    /**
     * Constructoe vacio
     */
    public OrdenPedidoDTO()
    {
        
    }
    
   // /**
   //  * constructor con valores
   //  * @param pVendedor
   //  * @param pComprador
   //  * @param pNumero
   //  * @param pId
   //  */
   // public OrdenPedidoDTO(VendedorDTO pVendedor, CompradorDTO pComprador, Integer pNumero, Integer pId){
     //   vendedor=pVendedor;
     //   comprador=pComprador;
     //   numeroComprasComprador=pNumero;
     //   estado=1;
     //   id=pId;
        
   // }
    
    //METODOS

    /**
     * @return the numeroComprasComprador
     */
    public Integer getNumeroComprasComprador() {
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
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
   
}
