/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.persistence;

import co.edu.uniandes.csw.comics.entities.ColeccionistaEntity;
import co.edu.uniandes.csw.comics.entities.ComicDeseoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Sebastian Baquero
 */
@Stateless
public class ComicDeseoPersistence {
    
  private final static Logger LOGGER = Logger.getLogger(ComicDeseoPersistence.class.getName());
  @PersistenceContext(unitName="comicsPU")
  protected EntityManager em;
  
  public ComicDeseoPersistence (){
  
  }
  
  public ComicDeseoEntity create(ComicDeseoEntity pComicE){
  LOGGER.log(Level.INFO, "Creando nueva clase");
  em.persist(pComicE);
   LOGGER.log(Level.INFO, "Comic deseo creado");
   return pComicE;
  }
  
  public List<ComicDeseoEntity> findAll(){
      LOGGER.log(Level.INFO, "Consultando todos los Comic deseo");
        Query q = em.createQuery("select u from ComicDeseoEntity u");
        return q.getResultList();
  }
  
   public ComicDeseoEntity find(Long pComicDeseoId){
    
        LOGGER.log(Level.INFO, "Consultando Comic Deseo con id = {0}", pComicDeseoId);
        return em.find(ComicDeseoEntity.class, pComicDeseoId);
    }
   
   public ComicDeseoEntity update(ComicDeseoEntity pComicDeseoEn){
    
    LOGGER.log(Level.INFO, "Actualizando informacion del comic deseo con id = {0}", pComicDeseoEn);
    return em.merge(pComicDeseoEn);
    }
   
    public void delete(Long pComicDeseoId){
    
        LOGGER.log(Level.INFO, "Se esta borrando el comic Deseo con el id = {0}", pComicDeseoId);
        ComicDeseoEntity cE = em.find(ComicDeseoEntity.class, pComicDeseoId);
        em.remove(cE);
    }
}
