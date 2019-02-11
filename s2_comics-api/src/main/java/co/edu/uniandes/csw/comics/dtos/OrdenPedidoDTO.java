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
    comprador=null;
    vendedor=null;
    estado=1;
    numeroComprasComprador=0;
    }
    
    /**
     * constructor con valores
     * @param pVendedor
     * @param pComprador
     * @param pNumero
     */
    public OrdenPedidoDTO(VendedorDTO pVendedor, CompradorDTO pComprador, Integer pNumero){
        vendedor=pVendedor;
        comprador=pComprador;
        numeroComprasComprador=pNumero;
        estado=1;
    }
    
    //METODOS
    /**
     *@return vendedor
     */
    public VendedorDTO getVendedor(){
        return vendedor;
    }
    /**
     * @set actualiza vendedor
     * @param pVendedor , nuevo vendedor
     */
    public void setVendedor(VendedorDTO pVendedor){
        vendedor=pVendedor;
    }
    
    /**
     *@return Comprador
     */
    public CompradorDTO getComprador(){
        return comprador;
    }
    /**
     * @set actualiza comprador
     * @param pComprador , nuevo comprador
     */
    public void setComprador(CompradorDTO pComprador){
        comprador=pComprador;
    }
    
     /**
     *@return Estado
     */
    public Integer getEstado(){
        return estado;
    }
    /**
     * @set actualiza el estado de la orden
     * @param pEstado , nuevo estado
     */
    public void setEstado(Integer pEstado){
        estado=pEstado;
    }
    
      /**
     *@return numeroComprasComprador
     */
    public Integer getNumeroComprasComprador(){
        return numeroComprasComprador;
    }
    /**
     * @set actualiza el numeroComprasComprador
     * @param pNumero , nuevo numeroComprasComprador
     */
    public void setnumeroComprasComprador(Integer pNumero){
        numeroComprasComprador=pNumero;
    }
}
