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
 * Clase que implementa la conexion con la persistencia para la relación entre Comprador y comicDeseo
 * @Comprador juan pablo cano
 */
@Stateless
public class CompradorComicDeseoLogic 
{
    private final static Logger LOGGER = Logger.getLogger(CompradorComicDeseoLogic.class.getName());
    
    @Inject
    private CompradorPersistence compradorPersistence;
    
    @Inject
    private ComicDeseoPersistence comicDeseoPersistence;
    
    /**
     * Asocia un ComicDeseo existente a un Comprador
     *
     * @param idComprador Identificador de la instancia de Comprador
     * @param idComicDeseo Identificador de la instancia de ComicDeseo
     * @return Instancia de ComicDeseoEntity que fue asociada a Comprador
     */
    public ComicDeseoEntity addComicListaDeseo(long idComprador, long idComicDeseo)
    {
        LOGGER.log(Level.INFO, "Se inicia el proceso de asociación del comicDeseo con id: {0} al comprador con id: {1}", new Object[]{idComicDeseo, idComprador});
        CompradorEntity comp = compradorPersistence.find(idComprador);
        ComicDeseoEntity comicDeseo = comicDeseoPersistence.find(idComicDeseo);
        comp.getListaDeseos().add(comicDeseo);
        LOGGER.log(Level.INFO, "Termina proceso de añadir un comic a la lista de deseos");
        return comicDeseo;
    }
    
    /**
     * Obtiene una colección de instancias de ComicDeseoEntity asociadas a una
     * instancia de Comprador
     *
     * @param idComprador Identificador de la instancia de Comprador
     * @return Colección de instancias de ComicDeseoEntity asociadas a la instancia de
     * Comprador
     */
    public List<ComicDeseoEntity> getListaDeseos(long idComprador)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de busqueda de lista deseos del comprador con id: {0}", idComprador);
        CompradorEntity entity = compradorPersistence.find(idComprador);
        LOGGER.log(Level.INFO, "Termina proceso de busqueda de la lista deseos del comprador con id: {0}", idComprador);
        return entity.getListaDeseos();
    }
    
    /**
     * Obtiene una instancia de ComicDeseoEntity asociada a una instancia de Comprador
     *
     * @param idComprador Identificador de la instancia de Comprador
     * @param idComicDeseo Identificador de la instancia de ComicDeseo
     * @return La entidadd de Libro del comprador
     * @throws BusinessLogicException Si el libro no está asociado al comprador
     */
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
    
    /**
     * Remplaza las instancias de ComicDeseo asociadas a una instancia de Comprador
     *
     * @param CompradorId Identificador de la instancia de Comprador
     * @param list Colección de instancias de ComicDeseoEntity a asociar a instancia
     * de Comprador
     * @return Nueva colección de ComicDeseoEntity asociada a la instancia de Comprador
     */
    public List<ComicDeseoEntity> replaceComicsDeseo(long idComprador, List<ComicDeseoEntity> list)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los comics deseo asocidos al comprador con id = {0}", idComprador);
        CompradorEntity compradorEntity = compradorPersistence.find(idComprador);
        compradorEntity.setListaDeseos(list);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los comics deseo asocidos al comprador con id = {0}", idComprador);
        return compradorEntity.getListaDeseos();
    }
    
    /**
     * Desasocia un ComicDeseo existente de un Comprador existente
     *
     * @param idComprador Identificador de la instancia de Comprador
     * @param idComicDeseo Identificador de la instancia de ComicDeseo
     */
    public void removeComic(long idComprador, long idComic)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de eliminacion del comic deseo con id: {0} asociado al comprador con id: {1}", new Object[]{idComic, idComprador});
        ComicDeseoEntity comicEntity = comicDeseoPersistence.find(idComic);
        CompradorEntity compradorEntity = compradorPersistence.find(idComprador);
        LOGGER.log(Level.INFO, "Finaliza proceso de eliminacion del comic deseo con id: {0} asociado al comprador con id: {1}", new Object[]{idComic, idComprador});
        compradorEntity.getListaDeseos().remove(comicEntity);
    }
}
