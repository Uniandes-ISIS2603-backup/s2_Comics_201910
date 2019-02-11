/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.entities;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class OrdenPedidoEntity implements Serializable{
    
    private Integer numeroComprasComprador;

    private Boolean estadosOrden;

    public OrdenPedidoEntity(){
        
    }
    
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
}
