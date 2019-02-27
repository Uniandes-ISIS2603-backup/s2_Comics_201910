/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.ColeccionistaDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
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
public class CompradorComicDeseoResource {
     private static final Logger LOGGER=Logger.getLogger(CompradorComicDeseoResource.class.getName());
     
     
     
     
    /* 
      @POST
      @Path("{coleccionistasId:\\d+}/listaDeseos")
    public ComicDeseoDTO crearColeccionista (@PathParam("{coleccionistasId:\\d+}")long id,ComicDeseoDTO comic){
        return comic;
    }
     @DELETE
      @Path("{coleccionistasId:\\d+}/listaDeseos/{comicId:\\d+}")
    public ComicDeseoDTO crearColeccionista (@PathParam("{coleccionistasId:\\d+}")long id,@PathParam("{comicId:\\d+}")long idComic){
        return null;
    }
*/
}
