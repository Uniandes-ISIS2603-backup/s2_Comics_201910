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
 * Clase que implementa la conexion con la persistencia para la relación entre Comprador y comic
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
    
    /**
     * Asocia un Comic existente a un Comprador
     *
     * @param idComp Identificador de la instancia de Comprador
     * @param idComic Identificador de la instancia de Comic
     * @return Instancia de ComicEntity que fue asociada a Comprador
     */
    public ComicEntity addComicCarrito(long idComp, long idComic)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un comic a un comprador: {0}", idComp);
        CompradorEntity comp = comprador.find(idComp);
        ComicEntity com = comic.find(idComic);
        comp.getCarro().add(com);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un comic a un comprador: {0}", idComp);
        return comic.find(idComic);
    }
    
    /**
     * Obtiene una colección de instancias de ComicEntity asociadas a una
     * instancia de Comprador
     *
     * @param idComp Identificador de la instancia de Comprador
     * @return Colección de instancias de ComicEntity asociadas a la instancia de
     * Comprador
     */
    public List<ComicEntity> getComics(long idComp)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de obtención de comics del comprador: {0}", idComp);
        return comprador.find(idComp).getCarro();
    }
    
    /**
     * Obtiene una instancia de ComicEntity asociada a una instancia de Comprador
     *
     * @param idComp Identificador de la instancia de Comprador
     * @param idCom Identificador de la instancia de Comic
     * @return La entidadd de Libro del comprador
     * @throws BusinessLogicException Si el libro no está asociado al comprador
     */
    public ComicEntity getComic(long idComp, long idCom)
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
        return null;
    } 
    
    /**
     * Remplaza las instancias de Comic asociadas a una instancia de Comprador
     *
     * @param CompradorId Identificador de la instancia de Comprador
     * @param list Colección de instancias de ComicEntity a asociar a instancia
     * de Comprador
     * @return Nueva colección de ComicEntity asociada a la instancia de Comprador
     */
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
    
    /**
     * Desasocia un Comic existente de un Comprador existente
     *
     * @param idComp Identificador de la instancia de Comprador
     * @param idComic Identificador de la instancia de Comic
     */
    public void deleteComic(long idComp, long idComic)
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de eliminación del comic en el comprador con id: {0}", idComp);
        CompradorEntity entity = comprador.find(idComp);
        ComicEntity comEntity = comic.find(idComic);
        System.out.println("Tamaño del arreglo antes: " + comprador.find(idComp).getCarro().size());
        comEntity.getCompradores().remove(entity);
        //comprador.find(idComp).getCarro().remove(comEntity);
        System.out.println("Tamaño del arreglo después: " + comprador.find(idComp).getCarro().size());
        /*if(getComic(idComp, idComic) != null)
        {
            System.out.println("Error, aún sigue en el arreglo");
        }*/
        LOGGER.log(Level.INFO, "Termina el proceso de eliminación del comic en el comprador con id: {0}", idComp);
    }
}
