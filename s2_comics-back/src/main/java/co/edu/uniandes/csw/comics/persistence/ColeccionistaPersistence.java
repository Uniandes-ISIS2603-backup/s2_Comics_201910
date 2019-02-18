//<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.persistence;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import co.edu.uniandes.csw.comics.entities.ColeccionistaEntity;
import java.util.List;

/**
 *
 * @author Sebastian Baquero
 */
@Stateless
public class ColeccionistaPersistence {
    
    private final static Logger LOGGER = Logger.getLogger(ColeccionistaPersistence.class.getName());
    
    @PersistenceContext(unitName="comicsPU")
    protected EntityManager em;
    
    public ColeccionistaPersistence(){
    
    }
    
    public ColeccionistaEntity create(ColeccionistaEntity pColeccionistaEn){
    
        LOGGER.log(Level.INFO, "Creando nuevo coleccionista");
        em.persist(pColeccionistaEn);
        LOGGER.log(Level.INFO, "Coleccionista Creado");
        
        return pColeccionistaEn;
    }
    
    public List<ColeccionistaEntity> findAll (){
    
        LOGGER.log(Level.INFO, "Consultando todos los coleccionistas");
        Query q = em.createQuery("select u from ColeccionistaEntity u");
        return q.getResultList();
    }
    
    public ColeccionistaEntity find(Long pColeccionistaId){
    
        LOGGER.log(Level.INFO, "Consultando Coleccionista con id = {0}", pColeccionistaId);
        return em.find(ColeccionistaEntity.class, pColeccionistaId);
    }
    
    public ColeccionistaEntity update(ColeccionistaEntity pColeccionistaEn){
    
    LOGGER.log(Level.INFO, "Actualizando informacion del coleccionista con id = {0}", pColeccionistaEn);
    return em.merge(pColeccionistaEn);
    }
    
    public void delete(Long pColeccionistaId){
    
        LOGGER.log(Level.INFO, "Se esta borrando el coleccionista con el id = {0}", pColeccionistaId);
        ColeccionistaEntity cE = em.find(ColeccionistaEntity.class, pColeccionistaId);
        em.remove(cE);
    }

    
    
}
