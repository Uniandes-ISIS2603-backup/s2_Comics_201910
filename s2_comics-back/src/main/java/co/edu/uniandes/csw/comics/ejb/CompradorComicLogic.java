/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.*;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.*;
import javax.inject.Inject;

/**
 *
 * @author juan pablo cano
 */
@Stateless
public class CompradorComicLogic
{
    private static final Logger LOGGER = Logger.getLogger(CompradorComicLogic.class.getName());
    
    @Inject
    private ComicPersistence comic;
    
    @Inject
    private CompradorPersistence comprador;
    
    public ComicEntity addComicCarrito(long idComp, long idComic)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un comic a un comprador: {0}", idComp);
        CompradorEntity comp = comprador.find(idComp);
        ComicEntity com = comic.find(idComic);
        comp.getCarro().add(com);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un comic a un comprador: {0}", idComp);
        return comic.find(idComic);
    }
    
    public List<ComicEntity> getComics(long idComp)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de obtención de comics del comprador: {0}", idComp);
        return comprador.find(idComp).getCarro();
    }
    
    public ComicEntity getComic(long idComp, long idCom) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de búsqueda del comic: {0} en el comprador: " + idComp, idCom);
        List<ComicEntity> comics = comprador.find(idComp).getCarro();
        ComicEntity entity = comic.find(idCom);
        int index = comics.indexOf(entity);
        LOGGER.log(Level.INFO, "Termina proceso de busqueda del comic: {0} en el comprador: " + idComp, idCom);
        if(index >= 0)
        {
            return comics.get(index);
        }
        throw new BusinessLogicException("No se encontró el libro");
    } 
    
    public List<ComicEntity> replaceComics(long idComp, List<ComicEntity> comics)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los comics asocidos al comprador con id = {0}", idComp);
        CompradorEntity comp = comprador.find(idComp);
        /*List<ComicEntity> comicsList = comic.findAll();
        for(ComicEntity c : comicsList)
        {
            if(comics.contains(c))
            {
                if(!c.getComprador().contains(comp))
                {
                    c.getComprador().add(comp);
                }
                else
                {
                    c.getComprador().remove(comp);
                }
            }
        }*/
        comp.setCarro(comics);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los comics asocidos al comprador con id = {0}", idComp);
        return comp.getCarro();
    }
    
    public void deleteComic(long idComp, long idComic)
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de eliminación del comic en el comprador con id: {0}", idComp);
        CompradorEntity entity = comprador.find(idComp);
        ComicEntity comEntity = comic.find(idComic);
        //comEntity.getComprador().remove(entity);
        entity.getCarro().remove(comEntity);
        LOGGER.log(Level.INFO, "Termina el proceso de eliminación del comic en el comprador con id: {0}", idComp);
    }
}
