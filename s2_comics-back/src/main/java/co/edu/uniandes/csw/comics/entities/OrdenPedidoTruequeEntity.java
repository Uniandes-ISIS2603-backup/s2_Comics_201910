/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author jp.rodriguezv
 */
@Entity
public class OrdenPedidoTruequeEntity extends BaseEntity implements Serializable {
  private Integer  numeroComprasComprador;
  private Integer Estado;

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
     * @return the Estado
     */
    public Integer getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(Integer Estado) {
        this.Estado = Estado;
    }
  
    
    
}
