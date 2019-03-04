/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.*;
import co.edu.uniandes.csw.comics.dtos.OrdenPedidoDTO;
import co.edu.uniandes.csw.comics.ejb.*;
import co.edu.uniandes.csw.comics.entities.*;
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
 *
 * @author juan pablo cano
 */
@Path("/comprador/{compradorId: \\d+}/pedido/{pedidoId: \\d+}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompradorOrdenPedidoResource 
{
    private static final Logger LOGGER = Logger.getLogger(CompradorOrdenPedidoResource.class.getName());
    
    @Inject 
    private CompradorOrdenPedidoLogic compradorOrdenLogic;
    
    @Inject 
    private OrdenPedidoLogic ordenPedidoLogic;
    
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
    
    @GET
    public List<OrdenPedidoDTO> getPedidos(@PathParam("compradorId") long idComprador)
    {
        LOGGER.log(Level.INFO, "");
        List<OrdenPedidoDTO> dtos = listEntity2DTO(compradorOrdenLogic.getOrdenes(idComprador));
        LOGGER.log(Level.INFO, "");
        return dtos;
    }
    
    @GET
    @Path("{pedidoId: \\d+}")
    public OrdenPedidoDTO getPedido(@PathParam("compradorId")long compradorId, @PathParam("pedidoId")long ordenId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CompradorOrdenPedidoResource getBook: input: compradorid: {0}, ordenId: {1}", new Object[]{compradorId, ordenId});
        if(ordenPedidoLogic.getOrdenPedido(ordenId) == null)
        {
            throw new WebApplicationException("El recurso /ordenes/" + ordenId + " no existe.", 404);
        }
        
        OrdenPedidoDTO orden = new OrdenPedidoDTO(compradorOrdenLogic.getOrden(compradorId, ordenId));
        LOGGER.log(Level.INFO, "CompradorOrdenPedidoResource getOrden: output: {0}", orden);
        return orden;
    }
    
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
