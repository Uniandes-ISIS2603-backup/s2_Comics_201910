/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.persistence;

import co.edu.uniandes.csw.comics.entities.CalificacionEntity;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
      private final static Logger LOGGER = Logger.getLogger(ComicDeseoPersistence.class.getName());
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
          public CalificacionEntity update(CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Actualizando el author con id={0}", calificacionEntity.getId());
      
        return em.merge(calificacionEntity);
          }
              public void delete(Long calificacionId) {

        LOGGER.log(Level.INFO, "Borrando el author con id={0}", calificacionId);
        // Se hace uso de mismo método que esta explicado en public AuthorEntity find(Long id) para obtener la author a borrar.
       CalificacionEntity calificacionEntity = em.find(CalificacionEntity.class, calificacionId);
       
        em.remove(calificacionEntity);
    }
}
