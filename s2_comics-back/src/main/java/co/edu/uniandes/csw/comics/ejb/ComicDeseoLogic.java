/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.ComicDeseoEntity;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.ComicDeseoPersistence;
import co.edu.uniandes.csw.comics.persistence.ComicPersistence;
import co.edu.uniandes.csw.comics.persistence.CompradorPersistence;
import java.util.ArrayList;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author Sebastian Baquero
 */
@Stateless
public class ComicDeseoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ComicDeseoLogic.class.getName());
    @Inject
    private ComicDeseoPersistence persistenceComicD;
    //@Inject
    //private CompradorPersistence persistenceComprador;
    @Inject
    private ComicPersistence persistenceC;
    
    public ComicDeseoEntity createComicDeseo (Long comicId, ComicDeseoEntity comicDeseoEntity)throws BusinessLogicException{
      
        LOGGER.log(Level.INFO,"Inicia proceso de crear comic deseo");
        
        if(persistenceC.find(comicId)== null){
        
            throw new BusinessLogicException("El comic deseo con id"+comicId+"no existe en comics");
        }
        
        ComicEntity comic =  persistenceC.find(comicId);
        comicDeseoEntity.setComic(comic);
        LOGGER.log(Level.INFO,"Termina proceso de crear comic deseo");
        return persistenceComicD.create(comicDeseoEntity);
     
    }
    
    public List<ComicDeseoEntity> getComicsDeseo(){
        
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los comics");
        List<ComicDeseoEntity> comics = persistenceComicD.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los comics");
        
        return comics;
        
        
        
       
    }
    
    public ComicDeseoEntity getComicDeseo( Long comicsDeseoId)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el comic deseo con id={0}", comicsDeseoId);
        ComicDeseoEntity comic = persistenceComicD.find(comicsDeseoId);
        ComicEntity c = comic.getComic();
        if(c == null){
        
            throw new BusinessLogicException("El comic deseo con id"+comicsDeseoId+"no existe en comics");
        }
        return comic;
    
    }
    
    public void deleteComicDeseo( Long comicsDeseoId) {
         LOGGER.log(Level.INFO, "Inicia proceso de borrar el comic deseo con id = {0}", comicsDeseoId);
         
         ComicDeseoEntity comic = persistenceComicD.find(comicsDeseoId);
        ComicEntity c = comic.getComic();
       
        
        persistenceComicD.delete(comicsDeseoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el libro con id = {0}", comicsDeseoId);
    
    }
    
    public ComicDeseoEntity updateComicDeseo(Long comicId, ComicDeseoEntity comicDEntity) {
        
         LOGGER.log(Level.INFO, "Inicia proceso de actualizar el comic con id = {0}", comicId);
        
        ComicDeseoEntity newEntity = persistenceComicD.update(comicDEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el comic deseo con id = {0}", comicDEntity.getId());
        return newEntity;
        
        
        
        
    }
       
}
