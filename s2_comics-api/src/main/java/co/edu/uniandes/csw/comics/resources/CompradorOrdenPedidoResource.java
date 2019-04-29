/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;


import co.edu.uniandes.csw.comics.dtos.OrdenPedidoDTO;
import co.edu.uniandes.csw.comics.ejb.CompradorOrdenPedidoLogic;
import co.edu.uniandes.csw.comics.ejb.OrdenPedidoLogic;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.*;
import javax.ws.rs.Produces;
import java.util.*;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "comprador/{id}/pedido"
 * @author juan pablo cano
 */
@Path("/comprador/{compradorId: \\d+}/pedido")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompradorOrdenPedidoResource 
{
    private static final Logger LOGGER = Logger.getLogger(CompradorOrdenPedidoResource.class.getName());
    
    @Inject 
    private CompradorOrdenPedidoLogic compradorOrdenLogic;
    
    @Inject 
    private OrdenPedidoLogic ordenPedidoLogic;
    
    /**
     * Asocia un pedido existente con un Comprador existente
     *
     * @param compradorId El ID del Comprador al cual se le va a asociar el pedido
     * @param pedidoId El ID del pedido que se asocia
     * @return JSON {@link OrdenPedidoDTO} - El pedido asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pedido.
     */
    @POST
    @Path("{pedidoId: \\d+}")
    public OrdenPedidoDTO addPedido(@PathParam("compradorId") long idComprador, @PathParam("pedidoId") long idPedido)
    {
        LOGGER.log(Level.INFO, "CompradorOrdenPedidoResource addPedido: input: idComprador {0} , idPedido {1}", new Object[]{idComprador, idPedido});
        if(ordenPedidoLogic.getOrdenPedido(idPedido) == null)
        {
            throw new WebApplicationException("El recurso /ordenes/" + idPedido + " no existe.", 404);
        }
        OrdenPedidoDTO dto = new OrdenPedidoDTO(compradorOrdenLogic.addOrdenPedido(idComprador, idPedido));
        LOGGER.log(Level.INFO, "CompradorOrdenPedidoResource addPedido output: {0}", dto);
        return dto;
    }
    
    /**
     * Busca y devuelve todos los pedidos que existen en un comprador.
     *
     * @param compradorId El ID del comprador del cual se buscan los pedidos
     * @return JSONArray {@link OrdenPedidoDTO} - Los pedidos encontrados en el
     * comprador. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<OrdenPedidoDTO> getPedidos(@PathParam("compradorId") long idComprador)
    {
        LOGGER.log(Level.INFO, "CompradorOrden");
        List<OrdenPedidoDTO> dtos = listEntity2DTO(compradorOrdenLogic.getOrdenes(idComprador));
        LOGGER.log(Level.INFO, "");
        return dtos;
    }
    
    /**
     * Busca y devuelve el pedido con el ID recibido en la URL, relativo a un
     * comprador.
     *
     * @param compradorId El ID del comprador del cual se busca el pedido
     * @param pedidoId El ID del pedido que se busca
     * @return {@link OrdenPedidoDTO} - El pedido encontrado en el comprador.
     * @throws co.edu.uniandes.csw.pedidostore.exceptions.BusinessLogicException
     * si el pedido no está asociado al comprador
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pedido.
     */
    @GET
    @Path("{pedidoId: \\d+}")
    public OrdenPedidoDTO getPedido(@PathParam("compradorId")long compradorId, @PathParam("pedidoId")long ordenId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CompradorOrdenPedidoResource getOrden: input: compradorid: {0}, ordenId: {1}", new Object[]{compradorId, ordenId});
        if(ordenPedidoLogic.getOrdenPedido(ordenId) == null)
        {
            throw new WebApplicationException("El recurso /ordenes/" + ordenId + " no existe.", 404);
        }
        
        OrdenPedidoDTO orden = new OrdenPedidoDTO(compradorOrdenLogic.getOrden(compradorId, ordenId));
        LOGGER.log(Level.INFO, "CompradorOrdenPedidoResource getOrden: output: {0}", orden);
        return orden;
    }
    
    /**
     * Actualiza la lista de pedidos de un comprador con la lista que se recibe en el
     * cuerpo
     *
     * @param compradorId El ID del comprador al cual se le va a asociar el pedido
     * @param ordenes JSONArray {@link OrdenPedidoDTO} - La lista de pedidos que se
     * desea guardar.
     * @return JSONArray {@link OrdenPedidoDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pedido.
     */
    @PUT
    public List<OrdenPedidoDTO> replaceOrdenes(@PathParam("compradorId") long compradorId, List<OrdenPedidoDTO> ordenes)
    {
        LOGGER.log(Level.INFO, "CompradorOrdenPedidoResource replaceOrdenes: input: compradorId: {0}, ordenes: {1}", new Object[]{compradorId, ordenes});
        for(OrdenPedidoDTO orden : ordenes)
        {
            if(ordenPedidoLogic.getOrdenPedido(orden.getId()) == null)
            {
                throw new WebApplicationException("El recurso /ordenes/" + orden.getId() + " no existe.", 404);
            }
        }
        
        List<OrdenPedidoDTO> lista = listEntity2DTO(compradorOrdenLogic.replaceOrden(compradorId, listDtoToEntity(ordenes)));
        LOGGER.log(Level.INFO, "CompradorOrdenPedidoResource replaceOrdenes: output: {0}", lista);
        return lista;
    }
    
    /**
     * Elimina la conexión entre el pedido y e comprador recibidos en la URL.
     *
     * @param compradorId El ID del comprador al cual se le va a desasociar el pedido
     * @param ordenId El ID del pedido que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pedido.
     */
    @DELETE
    @Path("{pedidoId: \\d+}")
    public void removeOrder(@PathParam("compradorId") long compradorId, @PathParam("pedidoId") long ordenId)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CompradorOrdenPedidoResource removeOrden: input: compradorId: {0}, ordenId: {1}", new Object[]{compradorId, ordenId});
        if(ordenPedidoLogic.getOrdenPedido(ordenId) == null)
        {
            throw new WebApplicationException("El recurso /ordenes/" + ordenId + " no existe.", 404);
        }
        
        compradorOrdenLogic.eliminarOrden(compradorId, ordenId);
        LOGGER.info("CompradorOrdenPedidoResource removeOrden: output: void");
    }
    
    private List<OrdenPedidoDTO> listEntity2DTO(List<OrdenPedidoEntity> list)
    {
        List<OrdenPedidoDTO> ordenes = new ArrayList();
        for(OrdenPedidoEntity entity : list)
        {
            ordenes.add(new OrdenPedidoDTO(entity));
        }
        
        return ordenes;
    }
    
    private List<OrdenPedidoEntity> listDtoToEntity(List<OrdenPedidoDTO> list)
    {
        List<OrdenPedidoEntity> ordenes = new ArrayList();
        for(OrdenPedidoDTO dto : list)
        {
            ordenes.add(dto.toEntity());
        }
        
        return ordenes;
    }
}
