/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.*;
import co.edu.uniandes.csw.comics.persistence.*;
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
        CompradorEntity comp = comprador.find(idComp);
        //ComicEntity com = comic.
        return null;
    }
}
