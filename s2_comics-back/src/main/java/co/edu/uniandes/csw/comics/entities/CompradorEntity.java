/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.entities;

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
public class CompradorEntity extends BaseEntity implements java.io.Serializable
{

    
    private String alias;
    
    private String email;
    
    @PodamExclude
    @OneToMany(mappedBy = "comprador", fetch=FetchType.LAZY, targetEntity=OrdenPedidoEntity.class)
    private List ordenPedidoCompra = new ArrayList();
    
    @PodamExclude
    @ManyToMany(mappedBy = "compradores")
    private List<ComicEntity> carro = new ArrayList<ComicEntity>();
    
    /**
     * @return the alias
     */
    public String getAlias() 
    {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) 
    {
        this.alias = alias;
    }

    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) 
    {
        this.email = email;
    }
    
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
}
