/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.ColeccionistaDTO;
import co.edu.uniandes.csw.comics.dtos.ComicDTO;
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
 * @author Sebastian Baquero
 */

@Path("comics")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ComicResource {
    
    private static final Logger LOGGER=Logger.getLogger(ComicResource.class.getName());
    
    @POST
    public ComicDTO crearComic (ComicDTO pComic)
    {
        return pComic;
    }
    
    @GET
    public ComicDTO getComic (ComicDTO pComic){
    
        return pComic;
    }
    
    @GET
    @Path("{comicsId:\\d+}")
    public ComicDTO getComicID (@PathParam("comicsId")long id)
    {
        return null;
    }
    
    @PUT
    @Path("{comicsId:\\d+}")
    public ComicDTO actualizarComic (@PathParam("comicsId")long id)
    {
    return null;
    }
    
     @DELETE
      @Path("{comicsId:\\d+}")
    public ColeccionistaDTO eliminaroleccionista (@PathParam("comicsId")long id){
        return null;
    }
}
