/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.CarritoCompraDTO;
import co.edu.uniandes.csw.comics.dtos.ColeccionistaDTO;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */
@Path("coleccionistas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CarritoCompraResource {
       private static final Logger LOGGER=Logger.getLogger(ColeccionistaResource.class.getName());
    
    @POST
     @Path("{coleccionistasId:\\d+}")
    public CarritoCompraDTO crearCarrito(CarritoCompraDTO carrito){
        return carrito;
    }
    @GET
      @Path("{coleccionistasId:\\d+}/carrito")
    public CarritoCompraDTO obtenerCarritoId (@PathParam("coleccionistasId")long id){
        return null;
    }
    
     @DELETE
      @Path("{coleccionistasId:\\d+}/carrito")
    public CarritoCompraDTO eliminarCarritoId (@PathParam("coleccionistasId")long id){
        return null;
    }
}
