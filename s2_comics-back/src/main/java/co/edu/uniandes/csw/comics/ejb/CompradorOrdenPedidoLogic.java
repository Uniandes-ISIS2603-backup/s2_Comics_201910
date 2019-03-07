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
public class CompradorOrdenPedidoLogic 
{
    private final static Logger LOGGER = Logger.getLogger(CompradorOrdenPedidoLogic.class.getName());
    
    @Inject
    private OrdenPedidoPersistence ordenPedidoPersistence;
    
    @Inject
    private CompradorPersistence compradorPersistence;
    
    public OrdenPedidoEntity addOrdenPedido(long idComprador, long idPedido)
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de asociar una orden con id: {0} a un comprador con id: " + idComprador, idPedido);
        CompradorEntity compradorEntity = compradorPersistence.find(idComprador);
        OrdenPedidoEntity pedidoEntity = ordenPedidoPersistence.find(idPedido);
        compradorEntity.getOrdenPedidoCompra().add(pedidoEntity);
        LOGGER.log(Level.INFO, "Finaliza el proceso de asociar una orden con id: {0} a un comprador con id: " + idComprador, idPedido);
        return ordenPedidoPersistence.find(idPedido);
    }
    
    public List<OrdenPedidoEntity> getOrdenes(long idComprador)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de recolección de las ordenes del comprador con id: {0}", idComprador);
        return compradorPersistence.find(idComprador).getOrdenPedidoCompra();
    }
    
    public OrdenPedidoEntity getOrden(long idComprador, long idPedido) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de obtención del pedido con id: {0} asociado al comprador: " + idComprador, idPedido);
        CompradorEntity compradorEntity = compradorPersistence.find(idComprador);
        OrdenPedidoEntity pedidoEntity = ordenPedidoPersistence.find(idPedido);
        int index = compradorEntity.getOrdenPedidoCompra().indexOf(pedidoEntity);
        LOGGER.log(Level.INFO, "Finaliza proceso de obtención del pedido con id: {0} asociado al comprador: " + idComprador, idPedido);
        if(index >= 0)
        {
            return compradorEntity.getOrdenPedidoCompra().get(index);
        }
        throw new BusinessLogicException("No se encontró el pedido con id: " + idPedido + " asociado al comprador con id: " + idComprador);
    }
    
    public List<OrdenPedidoEntity> replaceOrden(long idComprador, List<OrdenPedidoEntity> pedidos)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazo de los pedidos asociados al comprador con id: {0}", idComprador);
        CompradorEntity compradorEntity = compradorPersistence.find(idComprador);
        compradorEntity.setOrdenPedidoCompra(pedidos);
        LOGGER.log(Level.INFO, "Finaliza proceso de reemplazo de los pedidos asociados al comprador con id: {0}", idComprador);
        return pedidos;
    }
    
    public void eliminarOrden(long idComprador, long idPedido) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de eliminación del pedido con id: {0} asociado al comprador con id: " + idComprador, idPedido);
        CompradorEntity compradorEntity = compradorPersistence.find(idComprador);
        OrdenPedidoEntity pedidoEntity = ordenPedidoPersistence.find(idPedido);
        if(pedidoEntity.getEstado() != 4)
        {
            throw new BusinessLogicException("No se puede eliminar la compra debido a que no se ha completado");
        }
        compradorEntity.getOrdenPedidoCompra().remove(pedidoEntity);
        LOGGER.log(Level.INFO, "Finaliza el proceso de eliminación del pedido con id: {0} asociado al comprador con id: " + idComprador, idPedido);        
    }
}