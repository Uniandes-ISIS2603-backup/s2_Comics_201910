/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.persistence;

import co.edu.uniandes.csw.comics.entities.ComicEntity;
import java.util.List;
import javax.persistence.TypedQuery;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author estudiante
 */
@Stateless
public class ComicPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ComicPersistence.class.getName());
    
    @PersistenceContext(unitName= "comicsPU")
    protected EntityManager em;
    
    public ComicEntity create(ComicEntity comicEntity) {
        LOGGER.log(Level.INFO, "Creando un libro nuevo");
        em.persist(comicEntity);
        LOGGER.log(Level.INFO, "Libro creado");
        return comicEntity;
    }
    
    public ComicEntity find(Long comicsId) {
        LOGGER.log(Level.INFO, "Consultando el libro con id={0}", comicsId);
        return em.find(ComicEntity.class, comicsId);
    }
      
    public List<ComicEntity> findAll(){      
        TypedQuery<ComicEntity> query=em.createQuery("select u from ComicEntity u",ComicEntity.class);
        return query.getResultList();
    }    
      
    public void delete(Long comicsId) {
        LOGGER.log(Level.INFO, "Borrando el libro con id={0}", comicsId);
       ComicEntity comicEntity = em.find(ComicEntity.class, comicsId);
        em.remove(comicEntity);
    }
}
