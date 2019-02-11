/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;


import co.edu.uniandes.csw.comics.dtos.CalificacionDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
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
public class CalificacionResource {
    private static final Logger LOGGER=Logger.getLogger(CalificacionResource.class.getName());
     
      @POST
    public CalificacionDTO crearCalificacion (CalificacionDTO calificacion){
        return calificacion;
    }
}


