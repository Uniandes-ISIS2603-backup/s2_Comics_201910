/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.*;

/**
 *
 * @author Juan Pablo Cano
 */
@Entity
public class VendedorEntity extends ColeccionistaEntity implements java.io.Serializable
{    
    @PodamExclude
    @OneToMany(mappedBy="vendedor")
    private List<ComicEntity> comics = new ArrayList<ComicEntity>();

    /**
     * @return the comics
     */
    public List<ComicEntity> getComics() {
        return comics;
    }

    /**
     * @param comics the comics to set
     */
    public void setComics(List<ComicEntity> comics) {
        this.comics = comics;
    }
        
}
