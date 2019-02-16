/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.entities;

import javax.persistence.Entity;

/**
 *
 * @author Juan Pablo Cano
 */
@Entity
public class VendedorEntity extends BaseEntity implements java.io.Serializable
{

    private String alias;

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
