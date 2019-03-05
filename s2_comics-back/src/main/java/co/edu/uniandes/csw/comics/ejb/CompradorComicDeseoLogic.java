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
    
    public ComicDeseoEntity addComicDeseo(long idComicDeseo, long idComprador)
    {
        LOGGER.log(Level.INFO, "Se inicia el proceso de asociación del comicDeseo con id: {0} al comprador con id: " + idComprador, idComicDeseo);
        return null;
    }
}
