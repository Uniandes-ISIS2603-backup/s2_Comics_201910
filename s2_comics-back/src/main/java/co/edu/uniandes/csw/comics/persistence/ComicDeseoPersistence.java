/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.persistence;

import co.edu.uniandes.csw.comics.entities.ColeccionistaEntity;
import co.edu.uniandes.csw.comics.entities.ComicDeseoEntity;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
  
  public ComicDeseoEntity find(Long pComicsDId){
      
      LOGGER.log(Level.INFO, "Consultando el comic con id = {0}", pComicsDId);
      TypedQuery<ComicDeseoEntity> q = em.createQuery("select p from ComicDeseoEntity p where (p.id = :pComicsDId )",ComicDeseoEntity.class);
      q.setParameter("pComicsDId", pComicsDId);
      List<ComicDeseoEntity> results = q.getResultList();
      ComicDeseoEntity comicsD = null;
      if(results == null){
          comicsD = null;
      }else if(results.isEmpty()){
      comicsD = null;
      }else if(results.size() >= 1){
      comicsD = results.get(0);
      }
      
       LOGGER.log(Level.INFO, "Saliendo de consultar el comic deseo con id = {0}", pComicsDId);
      return comicsD;
  }
  
 
  
   public List<ComicDeseoEntity> findAll(){
    
      LOGGER.log(Level.INFO, "Consultando todos los ComicDeseoEntity");
        Query q = em.createQuery("select u from ComicdeseoEntity u");
        return q.getResultList();
    }
    
   
   public ComicDeseoEntity update(ComicDeseoEntity pComicDeseoEn){
    
    LOGGER.log(Level.INFO, "Actualizando informacion del comic deseo con id = {0}", pComicDeseoEn.getId());
    return em.merge(pComicDeseoEn);
    }
   
    public void delete(Long pComicDeseoId){
    
        LOGGER.log(Level.INFO, "Se esta borrando el comic Deseo con el id = {0}", pComicDeseoId);
        ComicDeseoEntity cE = em.find(ComicDeseoEntity.class, pComicDeseoId);
        em.remove(cE);
    }
}
