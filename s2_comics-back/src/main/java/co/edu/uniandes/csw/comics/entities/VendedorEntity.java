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
 * @author estudiante
 */
@Entity
public class VendedorEntity extends BaseEntity implements Serializable
{
    
    private String alias;
    
    public VendedorEntity()
    {
        
    }

    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
}
