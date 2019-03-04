/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import co.edu.uniandes.csw.comics.persistence.OrdenPedidoPersistence;
import co.edu.uniandes.csw.comics.persistence.VendedorPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */

@Stateless
public class OrdenPedidoVendedorLogic {
    
    private static final Logger LOGGER = Logger.getLogger(OrdenPedidoVendedorLogic.class.getName());

     @Inject
    private OrdenPedidoPersistence ordenPedidoPersistence;

    @Inject
    private VendedorPersistence vendedorPersistence;

 /**
     * Remplazar el vendedor de una ordenPedido.
     *
     * @param ordenPedidoId id de la ordenPedido que se quiere actualizar.
     * @param vendedoresId El id del vendedor que se será de a ordenPedido.
     * @return la nueva ordenPedido.
     */
    
 public OrdenPedidoEntity replaceVendedor(Long ordenPedidoId, Long vendedoresId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar ordenPedido con id = {0}", ordenPedidoId);
        VendedorEntity vendedorEntity = vendedorPersistence.find(vendedoresId);
        OrdenPedidoEntity ordenPedidoEntity = ordenPedidoPersistence.find(ordenPedidoId);
        ordenPedidoEntity.setVendedor(vendedorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar ordenPedido con id = {0}", ordenPedidoEntity.getId());
        return ordenPedidoEntity;
    }
 
 
}