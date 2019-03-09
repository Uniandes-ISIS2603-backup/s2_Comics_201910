/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import co.edu.uniandes.csw.comics.persistence.OrdenPedidoPersistence;
import co.edu.uniandes.csw.comics.persistence.CompradorPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jp.rodriguezv
 */
@Stateless
public class OrdenPedidoCompradorLogic {
       
    private static final Logger LOGGER = Logger.getLogger(OrdenPedidoCompradorLogic.class.getName());

     @Inject
    private OrdenPedidoPersistence ordenPedidoPersistence;

    @Inject
    private CompradorPersistence compradorPersistence;

 /**
     * Remplazar el comprador de una ordenPedido.
     *
     * @param ordenPedidoId id de la ordenPedido que se quiere actualizar.
     * @param compradoresId El id del cojmprador que se será de a ordenPedido.
     * @return la nueva ordenPedido.
     */
    
 public OrdenPedidoEntity replaceComprador(Long ordenPedidoId, Long compradoresId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar ordenPedido con id = {0}", ordenPedidoId);
        CompradorEntity compradorEntity = compradorPersistence.find(compradoresId);
        OrdenPedidoEntity ordenPedidoEntity = ordenPedidoPersistence.find(ordenPedidoId);
        ordenPedidoEntity.setComprador(compradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar ordenPedido con id = {0}", ordenPedidoEntity.getId());
        return ordenPedidoEntity;
    }
 
 
    
}
