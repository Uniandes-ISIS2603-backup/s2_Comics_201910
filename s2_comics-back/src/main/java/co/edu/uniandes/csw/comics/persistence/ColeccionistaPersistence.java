/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.persistence;

import co.edu.uniandes.csw.comics.entities.CalificacionEntity;
import co.edu.uniandes.csw.comics.entities.ColeccionistaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class ColeccionistaPersistence {
    @PersistenceContext(unitName="comicsPU")
    protected EntityManager em;
    
    
    public ColeccionistaEntity create(ColeccionistaEntity entity)
    {
        em.persist(entity);
        return entity;
    }
        
    
    public ColeccionistaEntity find(Long coleccionistaId){
        return em.find(ColeccionistaEntity.class, coleccionistaId);
    }
      public List<ColeccionistaEntity> findAll(){
          
          TypedQuery<ColeccionistaEntity> query=em.createQuery("select u from ColeccionistaEntity u",ColeccionistaEntity.class);
          return query.getResultList();
      }  
}
