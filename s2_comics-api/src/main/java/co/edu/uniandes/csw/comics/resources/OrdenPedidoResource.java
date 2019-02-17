/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;


import co.edu.uniandes.csw.comics.dtos.ColeccionistaDTO;
import co.edu.uniandes.csw.comics.dtos.ComicDTO;
import co.edu.uniandes.csw.comics.dtos.OrdenPedidoDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */
@Path("OrdenesPedido")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class OrdenPedidoResource {
    private static final Logger LOGGER=Logger.getLogger(OrdenPedidoResource.class.getName());
    
    @POST
    public OrdenPedidoDTO crearOrdenPedido (OrdenPedidoDTO OrdenPedido){
        return OrdenPedido;
    }
    
   @GET
    @Path("{OdenesPedidoId:\\d+}")
    public OrdenPedidoDTO getOrdenPedidoID (@PathParam("OrdenesPedidoId")long id)
    {
        return null;
    }
    
    @PUT
    @Path("{OdenesPedidoId:\\d+}")
    public OrdenPedidoDTO actualizarOrdenPedido (@PathParam("OdenesPedidoId")long id)
    {
    return null;
    }
    
     @DELETE
      @Path("{OdenesPedidoId:\\d+}")
    public OrdenPedidoDTO eliminarOrdenPedido (@PathParam("OdenesPedidoId")long id){
        return null;
    }
}
