/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.ComicDeseoEntity;
import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.ComicDeseoPersistence;
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
    @Inject
    private CompradorPersistence persistenceComprador;
    
    public ComicDeseoEntity createComicDeseo (String compradoresAlias, ComicDeseoEntity comicDeseoEntity)throws Exception{
      
        LOGGER.log(Level.INFO,"Inicia proceso de crear comic deseo");
        CompradorEntity comprador = persistenceComprador.findByAlias(compradoresAlias);
        comicDeseoEntity.setComprador(comprador);
        LOGGER.log(Level.INFO,"Termina proceso de crear comic deseo");
        return persistenceComicD.create(comicDeseoEntity);
     
    }
    
    public List<ComicDeseoEntity> getComicsDeseo (String compradoresAlias){
        LOGGER.log(Level.INFO,"Inicia proceso de consultar todos los comics deseo del comprador con el alias = {0}",compradoresAlias);
        CompradorEntity compradorEntity = persistenceComprador.findByAlias(compradoresAlias);
        LOGGER.log(Level.INFO,"Termina proceso de consultar todos los comics deseo del comprador con el alias = {0}", compradoresAlias);
      //  return compradorEntity.getComicsDeseos();
        List<ComicDeseoEntity> nc = new ArrayList<ComicDeseoEntity>();
         return nc;
       
    }
    
    public ComicDeseoEntity getComicDeseo(String compradoresAlias, Long comicsDeseoId){
    LOGGER.log(Level.INFO, "Inicia proceso de consultar el comic deseo con id={0} del comprador con alias = "+ compradoresAlias, comicsDeseoId);
    return persistenceComicD.find(compradoresAlias, comicsDeseoId);
    
    }
    
    public void deleteComicDeseo(String compradoresAlias, Long comicsDeseoId) throws BusinessLogicException{
    LOGGER.log(Level.INFO, "Inicia proceso de borrado del comic deseo con id={0} del comprador con alias = "+ compradoresAlias, comicsDeseoId);
    ComicDeseoEntity viejo = getComicDeseo(compradoresAlias, comicsDeseoId);
    if(viejo==null){   
    throw new BusinessLogicException("El comic deseo con id ="+comicsDeseoId+ "no esta asociado al comprador con el alias de"+compradoresAlias);
    } 
    persistenceComicD.delete(viejo.getId());
    LOGGER.log(Level.INFO, "Terminado el proceso de borrado del comic deseo con id={0} del comprador con alias = "+ compradoresAlias, comicsDeseoId);

    
    }
    
    
}
