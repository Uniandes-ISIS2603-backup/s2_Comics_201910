/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.ColeccionistaDTO;
import co.edu.uniandes.csw.comics.dtos.ComicDTO;
import java.util.HashMap;
import java.util.logging.Level;
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
    private HashMap<Long, ComicDTO> comics;
    
    public ComicResource(){
        
    comics = new HashMap<Long, ComicDTO>();
    
    }
    @POST
    public ComicDTO crearComic (ComicDTO pComic){
        
        //comics.put(pComic.getIdComic(), pComic);
        return pComic;
    }
   
    @GET
    public ComicDTO getComics (ComicDTO pComicDTO){
    
        return pComicDTO;
       
    }
    
    @GET
    @Path("{comicsId:\\d+}")
    
     public ComicDTO getComicXID (@PathParam("comicsId")long id)throws  Exception{
        
        try{
          //  return comics.get(id);
          return null;
            
        }catch(Exception e)
        {
        throw new Exception("No se encontro el comic solicitado");
       }
        
    
   }
    
    @PUT
    @Path("{comicsId:\\d+}")
    public ComicDTO actualizarComic (@PathParam("comicsId")long id){
        
        try{
            
            return null;
           //ComicDTO updateComic = comics.get(id);
           //updateComic.setAnioSalida(anioSalida);
        
        }catch(Exception e)
        {
        
       }
        return null;
    }
    
     @DELETE
      @Path("{comicsId:\\d+}")
    public ComicDTO eliminarComic (@PathParam("comicsId")long id)throws Exception{
        try{
            
            ComicDTO deleComic = comics.get(id);
            comics.remove(id);
            return deleComic;
            
        }catch(Exception e)
        {
        throw new Exception("No se pudo eliminar el comic solicitado");
        }
    }
}
