/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.ordenpedidos.ejb;

import co.edu.uniandes.csw.OrdenPedidos.entities.*;
import co.edu.uniandes.csw.OrdenPedidos.entities.OrdenPedidoEntity.Estado;
import co.edu.uniandes.csw.OrdenPedidos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.OrdenPedidos.persistence.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.*;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre Comprador y ordenPedido
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
    
    /**
     * Asocia un OrdenPedido existente a un Comprador
     *
     * @param idComprador Identificador de la instancia de Comprador
     * @param idPedido Identificador de la instancia de OrdenPedido
     * @return Instancia de OrdenPedidoEntity que fue asociada a Comprador
     */
    public OrdenPedidoEntity addOrdenPedido(long idComprador, long idPedido)
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de asociar una orden con id: {0} a un comprador con id: " + idComprador, idPedido);
        CompradorEntity compradorEntity = compradorPersistence.find(idComprador);
        OrdenPedidoEntity pedidoEntity = ordenPedidoPersistence.find(idPedido);
        compradorEntity.getOrdenPedidoCompra().add(pedidoEntity);
        LOGGER.log(Level.INFO, "Finaliza el proceso de asociar una orden con id: {0} a un comprador con id: " + idComprador, idPedido);
        return ordenPedidoPersistence.find(idPedido);
    }
    
    /**
     * Obtiene una colección de instancias de OrdenPedidoEntity asociadas a una
     * instancia de Comprador
     *
     * @param idComprador Identificador de la instancia de Comprador
     * @return Colección de instancias de OrdenPedidoEntity asociadas a la instancia de
     * Comprador
     */
    public List<OrdenPedidoEntity> getOrdenes(long idComprador)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de recolección de las ordenes del comprador con id: {0}", idComprador);
        return compradorPersistence.find(idComprador).getOrdenPedidoCompra();
    }
    
    /**
     * Obtiene una instancia de OrdenPedidoEntity asociada a una instancia de Comprador
     *
     * @param idComprador Identificador de la instancia de Comprador
     * @param idPedido Identificador de la instancia de OrdenPedido
     * @return La entidadd de Libro del comprador
     * @throws BusinessLogicException Si el libro no está asociado al comprador
     */
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
    
    /**
     * Remplaza las instancias de OrdenPedido asociadas a una instancia de Comprador
     *
     * @param CompradorId Identificador de la instancia de Comprador
     * @param pedidos Colección de instancias de OrdenPedidoEntity a asociar a instancia
     * de Comprador
     * @return Nueva colección de OrdenPedidoEntity asociada a la instancia de Comprador
     */
    public List<OrdenPedidoEntity> replaceOrden(long idComprador, List<OrdenPedidoEntity> pedidos)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazo de los pedidos asociados al comprador con id: {0}", idComprador);
        CompradorEntity compradorEntity = compradorPersistence.find(idComprador);
        compradorEntity.setOrdenPedidoCompra(pedidos);
        LOGGER.log(Level.INFO, "Finaliza proceso de reemplazo de los pedidos asociados al comprador con id: {0}", idComprador);
        return pedidos;
    }
    
    /**
     * Desasocia un OrdenPedido existente de un Comprador existente
     *
     * @param idComprador Identificador de la instancia de Comprador
     * @param idPedido Identificador de la instancia de OrdenPedido
     */
    public void eliminarOrden(long idComprador, long idPedido) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de eliminación del pedido con id: {0} asociado al comprador con id: " + idComprador, idPedido);
        CompradorEntity compradorEntity = compradorPersistence.find(idComprador);
        OrdenPedidoEntity pedidoEntity = ordenPedidoPersistence.find(idPedido);
        if(pedidoEntity.getEstado() != Estado.FINALIZADO)
        {
            throw new BusinessLogicException("No se puede eliminar la compra debido a que no se ha completado");
        }
        compradorEntity.getOrdenPedidoCompra().remove(pedidoEntity);
        LOGGER.log(Level.INFO, "Finaliza el proceso de eliminación del pedido con id: {0} asociado al comprador con id: " + idComprador, idPedido);        
    }
}