/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.persistence;

import co.edu.uniandes.csw.comics.entities.ComicEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sebastian Baquero
 */
@Stateless
public class ComicPersistence {
    
    @PersistenceContext(unitName="comicsPU")
    protected EntityManager em;
    
    public ComicEntity create (ComicEntity pComicEntity){
    
        em.persist(pComicEntity);
        return pComicEntity;
    }
    
    public ComicEntity find (Long pComicId){
    
       return em.find(ComicEntity.class, pComicId);
    }
    
    public List<ComicEntity> findAll (){
        
    TypedQuery<ComicEntity> q = em.createQuery("select u from ComicEntity u", ComicEntity.class);
    return q.getResultList();
    
    }
}
