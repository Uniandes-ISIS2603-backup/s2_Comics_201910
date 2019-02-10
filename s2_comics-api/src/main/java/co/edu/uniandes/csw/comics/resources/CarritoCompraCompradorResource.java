/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.CarritoCompraDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
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
public class CarritoCompraCompradorResource {
     private static final Logger LOGGER=Logger.getLogger(ColeccionistaResource.class.getName());
     
      @PUT
      @Path("{coleccionistasId:\\d+}/carrito")
    public CarritoCompraDTO actualizarCarritoId (@PathParam("coleccionistasId")long id,CarritoCompraDTO carrito){
        return carrito;
    }
}
