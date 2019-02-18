/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.persistence;

import co.edu.uniandes.csw.comics.entities.CalificacionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ca.orduz
 */
@Stateless
public class CalificacionPersistence {
    @PersistenceContext(unitName= "comicsPU")
    protected EntityManager em;
    
    public CalificacionEntity create(CalificacionEntity calificacionEntity){
        em.persist(calificacionEntity);
        return calificacionEntity;
    }
    
    public CalificacionEntity find(Long calificacionId){
        return em.find(CalificacionEntity.class, calificacionId);
    }
      public List<CalificacionEntity> findAll(){
          
          TypedQuery<CalificacionEntity> query=em.createQuery("select u from CalificacionEntity u",CalificacionEntity.class);
          return query.getResultList();
      }      
}
