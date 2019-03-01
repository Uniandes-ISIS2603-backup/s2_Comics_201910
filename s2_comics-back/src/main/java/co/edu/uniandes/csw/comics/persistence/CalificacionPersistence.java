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
    
    public CalificacionEntity find(Long vendedoresId,Long calificacionesId){
              LOGGER.log(Level.INFO, "Consultando la calificacion con id = {0} del vendedor con id = " + vendedoresId, calificacionesId);
        TypedQuery<CalificacionEntity> q = em.createQuery("select p from CalificacionEntity p where (p.vendedor.id = :vendedorid) and (p.id = :calificacionesId)", CalificacionEntity.class);
        q.setParameter("vendedorid", vendedoresId);
        q.setParameter("calificacionesId", calificacionesId);
        List<CalificacionEntity> results = q.getResultList();
        CalificacionEntity calificacion = null;
        if (results == null) {
            calificacion = null;
        } else if (results.isEmpty()) {
            calificacion = null;
        } else if (results.size() >= 1) {
            calificacion = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar la calificacion con id = {0} del vendedor con id =" + vendedoresId, calificacionesId);
        return calificacion;
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
