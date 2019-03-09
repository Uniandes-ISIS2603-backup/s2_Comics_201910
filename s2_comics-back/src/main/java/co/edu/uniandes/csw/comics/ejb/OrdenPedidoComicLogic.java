/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.persistence.ComicPersistence;
import co.edu.uniandes.csw.comics.persistence.CompradorPersistence;
import co.edu.uniandes.csw.comics.persistence.OrdenPedidoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jp.rodriguezv
 */
@Stateless
public class OrdenPedidoComicLogic {
       
    private static final Logger LOGGER = Logger.getLogger(OrdenPedidoComicLogic.class.getName());

     @Inject
    private OrdenPedidoPersistence ordenPedidoPersistence;

    @Inject
    private ComicPersistence comicPersistence;

 /**
     * Remplazar el comic de una ordenPedido.
     *
     * @param ordenPedidoId id de la ordenPedido que se quiere actualizar.
     * @param comicsId El id del comic que  será de la ordenPedido.
     * @return la nueva ordenPedido.
     */
    
 public OrdenPedidoEntity replaceComic(Long ordenPedidoId, Long comicsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar ordenPedido con id = {0}", ordenPedidoId);
        ComicEntity comicEntity = comicPersistence.find(comicsId);
        OrdenPedidoEntity ordenPedidoEntity = ordenPedidoPersistence.find(ordenPedidoId);
        ordenPedidoEntity.setComic(comicEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar ordenPedido con id = {0}", ordenPedidoEntity.getId());
        return ordenPedidoEntity;
    }
 
 
     
}
