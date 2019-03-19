/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.ComicDeseoEntity;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.ComicDeseoPersistence;
import co.edu.uniandes.csw.comics.persistence.ComicPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Sebastian Baquero
 */
@Stateless
public class ComicDeseoComicLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ComicDeseoComicLogic.class.getName());
    
    @Inject
    private ComicDeseoPersistence cDP;
    
    @Inject
    private ComicPersistence cP;
    
    
      /**
     * Agregar un autor a un premio
     *
     * @param prizesId El id premio a guardar
     * @param authorsId El id del autor al cual se le va a guardar el premio.
     * @return El premio que fue agregado al autor.
     */
    public ComicEntity addComic(Long comicId, Long comicDeseoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar el comic con id = {0} al comicDeseo con id = " + comicDeseoId, comicId);
      
       ComicEntity comicEntity = cP.find(comicId);
        
        ComicDeseoEntity comicDEntity = cDP.find(comicDeseoId);
        
        comicDEntity.setComic(comicEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el comic con id = {0} al comic deseo con id = " + comicDeseoId, comicId);
        
        return cP.find(comicId);
    }
    
     /**
     *
     * Obtener un comic deseo por medio de su id y el de su comic.
     *
     * @param comicdeseoId id del premio a ser buscado.
     * @return el comic solicitada por medio de su id.
     */
    public ComicEntity getComic(Long comicDeseoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el comic del comicDeseo con id = {0}", comicDeseoId);
        ComicEntity comicEntity = cDP.find(comicDeseoId).getComic();
               
        LOGGER.log(Level.INFO, "Termina proceso de consultar el comic del comic deseo con id = {0}", comicDeseoId);
        return comicEntity;
    }
    
    /**
     * Borrar el comic de un comic deseo
     *
     * @param comicDeseoId El comic deseo que se desea borrar del comic.
     * @throws BusinessLogicException si el premio no tiene autor
     */
    public void removeComic(Long comicDeseoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el autor del premio con id = {0}", comicDeseoId);
        ComicDeseoEntity comicDEntity = cDP.find(comicDeseoId);
               
        if (comicDEntity.getComic() == null) {
            throw new BusinessLogicException("El comic deseo no tiene comic");
        }
        ComicEntity comicEntity = cP.find(comicDEntity.getComic().getId());
        comicDEntity.setComic(null);
        comicEntity.getComicsDeseo().remove(comicEntity);
               
        LOGGER.log(Level.INFO, "Termina proceso de borrar el comic con id = {0} del comic deseo con id = " + comicDeseoId, comicEntity.getId());
    }

    
}
