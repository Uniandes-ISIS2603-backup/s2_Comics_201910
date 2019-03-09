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
public class CompradorComicDeseoLogic 
{
    private final static Logger LOGGER = Logger.getLogger(CompradorComicDeseoLogic.class.getName());
    
    @Inject
    private CompradorPersistence compradorPersistence;
    
    @Inject
    private ComicDeseoPersistence comicDeseoPersistence;
    
    public ComicDeseoEntity addComicListaDeseo(long idComprador, long idComicDeseo)
    {
        LOGGER.log(Level.INFO, "Se inicia el proceso de asociación del comicDeseo con id: {0} al comprador con id: {1}", new Object[]{idComicDeseo, idComprador});
        CompradorEntity comp = compradorPersistence.find(idComprador);
        ComicDeseoEntity comicDeseo = comicDeseoPersistence.find(idComicDeseo);
        comp.getListaDeseos().add(comicDeseo);
        LOGGER.log(Level.INFO, "Termina proceso de añadir un comic a la lista de deseos");
        return comicDeseo;
    }
    
    public List<ComicDeseoEntity> getListaDeseos(long idComprador)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de busqueda de lista deseos del comprador con id: {0}", idComprador);
        CompradorEntity entity = compradorPersistence.find(idComprador);
        LOGGER.log(Level.INFO, "Termina proceso de busqueda de la lista deseos del comprador con id: {0}", idComprador);
        return entity.getListaDeseos();
    }
    
    public ComicDeseoEntity getComicDeseo(long idComprador, long idComic) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de búsqueda del comic: {0} en el comprador: {1}", new Object[]{idComic, idComprador});
        List<ComicDeseoEntity> list = compradorPersistence.find(idComprador).getListaDeseos();
        ComicDeseoEntity comicEntity = comicDeseoPersistence.find(idComic);
        int index = list.indexOf(comicEntity);
        LOGGER.log(Level.INFO, "Termina proceso de busqueda del comic: {0} en el comprador: {1}", new Object[]{idComic, idComprador});
        if(index >= 0)
        {
            return list.get(index);
        }
        throw new BusinessLogicException("El comic no fue encontrado en la lista de deseos");
    }
    
    public List<ComicDeseoEntity> replaceComicsDeseo(long idComprador, List<ComicDeseoEntity> list)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los comics deseo asocidos al comprador con id = {0}", idComprador);
        CompradorEntity compradorEntity = compradorPersistence.find(idComprador);
        compradorEntity.setListaDeseos(list);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los comics deseo asocidos al comprador con id = {0}", idComprador);
        return compradorEntity.getListaDeseos();
    }
    
    public void removeComic(long idComprador, long idComic)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de eliminacion del comic deseo con id: {0} asociado al comprador con id: {1}", new Object[]{idComic, idComprador});
        ComicDeseoEntity comicEntity = comicDeseoPersistence.find(idComic);
        CompradorEntity compradorEntity = compradorPersistence.find(idComprador);
        LOGGER.log(Level.INFO, "Finaliza proceso de eliminacion del comic deseo con id: {0} asociado al comprador con id: {1}", new Object[]{idComic, idComprador});
        compradorEntity.getListaDeseos().remove(comicEntity);
    }
}
