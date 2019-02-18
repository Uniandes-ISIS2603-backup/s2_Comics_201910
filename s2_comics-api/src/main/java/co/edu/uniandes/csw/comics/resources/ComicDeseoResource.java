/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.ComicDeseoDTO;
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
 * @author Sebastian Baquero
 */

@Path("comicDeseo")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped


public class ComicDeseoResource {
 
  private static final Logger LOGGER = Logger.getLogger(ComicDeseoResource.class.getName());
  
  @POST
  public ComicDeseoDTO createComicDeseo (ComicDeseoDTO pComicD){
      return pComicD;
  }
  
  @GET
  public ComicDeseoDTO findComicDeseo (ComicDeseoDTO pComicD){
       return pComicD;
  }
  
  @GET
  @Path("{comicDeseoId:\\d+}")
  public ComicDeseoDTO findComicDeseoXId (@PathParam("comicDeseoId")long id){
      return null;
  }
  
  @DELETE
  @Path("{name: [a-zA-Z][a-zA-Z]*}")
  public ComicDeseoDTO deleteComicDeseo (){
  return null;
  }
  
  //@PUT
  
  
  
}
