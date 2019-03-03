/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.ComicPersistence;
import co.edu.uniandes.csw.comics.persistence.VendedorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class VendedorComicLogic {
    private static final Logger LOGGER = Logger.getLogger(VendedorComicLogic.class.getName());
 @Inject
    private ComicPersistence comicPersistence;

    @Inject
    private VendedorPersistence vendedorPersistence;
    
        public ComicEntity addComic(Long vendedoresId, Long comicsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un libro al autor con id = {0}", vendedoresId);
        VendedorEntity vendedorEntity = vendedorPersistence.find(vendedoresId);
        ComicEntity comicEntity = comicPersistence.find(comicsId);
        comicEntity.getVendedores().add(vendedorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un libro al autor con id = {0}", comicsId);
        return comicPersistence.find(comicsId);
    }
          public List<ComicEntity> getComics(Long vendedoresId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", vendedoresId);
        return vendedorPersistence.find(vendedoresId).getComics();
    }
            public ComicEntity getComic(Long vendedoresId, Long comicsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} del autor con id = " + vendedoresId, comicsId);
        List<ComicEntity> comics = vendedorPersistence.find(vendedoresId).getComics();
        ComicEntity comicEntity = comicPersistence.find(comicsId);
        int index = comics.indexOf(comicEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} del autor con id = " + vendedoresId,comicsId);
        if (index >= 0) {
            return comics.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado al autor");
    }
               public List<ComicEntity> replaceBooks(Long vendedorId, List<ComicEntity> comics) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los libros asocidos al author con id = {0}", vendedorId);
       VendedorEntity vendedorEntity = vendedorPersistence.find(vendedorId);
        List<ComicEntity> comicList = comicPersistence.findAll();
        for (ComicEntity comic : comicList) {
            if (comics.contains(comic)) {
                if (!comic.getVendedores().contains(vendedorEntity)) {
                    comic.getVendedores().add(vendedorEntity);
                }
            } else {
                comic.getVendedores().remove(vendedorEntity);
            }
        }
        vendedorEntity.setComics(comics);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los libros asocidos al author con id = {0}", vendedorId);
        return vendedorEntity.getComics();
    }
        public void removeBook(Long vendedoresId, Long comicsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un libro del author con id = {0}", vendedoresId);
        VendedorEntity authorEntity = vendedorPersistence.find(vendedoresId);
        ComicEntity comicEntity =comicPersistence.find(comicsId);
        comicEntity.getVendedores().remove(authorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del author con id = {0}", vendedoresId);
    }
}
